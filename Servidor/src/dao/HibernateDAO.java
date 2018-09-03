package dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

public abstract class HibernateDAO<M, E> {
	private static final SessionFactory sf = HibernateUtil.getSessionFactory();
	private final Class<E> entityClass;

	protected HibernateDAO(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	protected Session openSession() {
		return sf.openSession();
	}

	public M save(M model) {
		E entity = toEntity(model);
		entity = this.saveEntity(entity);
		return toModel(entity);
	}

	protected E saveEntity(E entity) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
		} catch (RuntimeException re) {
			if(transaction!=null) {
				transaction.rollback();
			}
			re.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return entity;
	}

	public M findById(Integer id) {
		Session session = null;
		M model = null;
		try {
			session = this.openSession();
			E entity = session.get(entityClass, id);

			if (entity != null) {
				model = toModel(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null)

				session.close();

		}

		return model;
	}

	public List<M> getAll() {
		List<M> retorna = null;
		Session session = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<E> criteria = builder.createQuery(entityClass);
			criteria.from(entityClass);
			List<E> entities = session.createQuery(criteria).getResultList();

			retorna = entities.stream().map(e -> toModel(e)).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return retorna;
	}

	abstract M toModel(E entity);

	abstract E toEntity(M model);
}
