package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.CompraEntity;
import entity.CompraItemEntity;
import model.Articulo;
import model.Compra;
import model.CompraItem;

public class CompraDAO extends HibernateDAO<Compra, CompraEntity> {
	private static CompraDAO instancia = null;

	protected CompraDAO() {
		super(CompraEntity.class);
	}

	public static CompraDAO getInstancia() {
		if (instancia == null) {
			instancia = new CompraDAO();
		}
		return instancia;
	}

	@Override
	protected Compra toModel(CompraEntity entity) {
		Compra model = new Compra();
		model.setCompraId(entity.getCompraId());
		model.setFecha(entity.getFecha());
		model.setProveedor(entity.getProveedor());
		model.setRecepcionada(entity.isRecepcionada());
		model.setItems(getModelItems(entity.getItems()));
		return model;
	}

	private List<CompraItem> getModelItems(List<CompraItemEntity> items) {
		return items.stream().map(item -> {
			CompraItem model = new CompraItem();
			model.setCompraItemId(item.getCompraItemId());
			model.setPrecio(item.getPrecio());
			model.setOrdenCompra(OrdenCompraDAO.getInstancia().toModel(item.getOrdenCompra()));
			return model;
		}).collect(Collectors.toList());
	}

	private List<CompraItemEntity> getEntityItems(List<CompraItem> items) {
		return items.stream().map(item -> {
			CompraItemEntity entity = new CompraItemEntity();
			entity.setCompraItemId(item.getCompraItemId());
			entity.setPrecio(item.getPrecio());
			entity.setOrdenCompra(OrdenCompraDAO.getInstancia().toEntity(item.getOrdenCompra()));
			return entity;
		}).collect(Collectors.toList());
	}

	@Override
	protected CompraEntity toEntity(Compra model) {
		CompraEntity entity = new CompraEntity();

		entity.setCompraId(model.getCompraId());
		entity.setProveedor(model.getProveedor());
		entity.setRecepcionada(model.isRecepcionada());
		entity.setFecha(model.getFecha());
		entity.setItems(getEntityItems(model.getItems()));

		return entity;
	}

	public List<Compra> findAllComprasPendientes() {
		List<Compra> compras = new ArrayList<>();
		Session session = null;
		try {
			session = this.openSession();
			Query<CompraEntity> query = session.createQuery("from CompraEntity p where p.recepcionada = :recepcionada",
					CompraEntity.class);
			query.setParameter("recepcionada", false);
			List<CompraEntity> entitis = query.list();
			for (CompraEntity entity : entitis) {
				compras.add(toModel(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return compras;
	}

	public List<String> getUltimosProveedores() {
		Session session = null;
		List<String> result = new ArrayList<>();
		try{
			session = this.openSession();
			Query q = session.createQuery("SELECT p.proveedor from CompraEntity p order by fecha desc");
			result = q.setMaxResults(3).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null)
				session.close();
		}
		return result;
	}
}
