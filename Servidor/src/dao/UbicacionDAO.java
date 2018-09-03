package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.UbicacionEntity;
import model.Articulo;
import model.Ubicacion;

public class UbicacionDAO extends HibernateDAO<Ubicacion, UbicacionEntity> {
	private static UbicacionDAO instancia = null;

	protected UbicacionDAO() {
		super(UbicacionEntity.class);
	}

	public static UbicacionDAO getInstancia() {
		if (instancia == null) {
			instancia = new UbicacionDAO();
		}
		return instancia;
	}

	@Override
	protected Ubicacion toModel(UbicacionEntity entity) {
		Ubicacion model = new Ubicacion();
		model.setCantidad(entity.getCantidad());
		model.setCodigoUbicacion(entity.getCodigoUbicacion());
		model.setLibre(entity.isLibre());
		model.setUbicacionId(entity.getUbicacionId());
		// if (entity.getArticulo() != null)
		// model.setArticulo(ArticuloDAO.getInstancia().toModel(entity.getArticulo()));
		return model;
	}

	@Override
	protected UbicacionEntity toEntity(Ubicacion model) {
		UbicacionEntity entity = new UbicacionEntity();
		entity.setCantidad(model.getCantidad());
		entity.setCodigoUbicacion(model.getCodigoUbicacion());
		entity.setLibre(model.isLibre());
		entity.setUbicacionId(model.getUbicacionId());
		// if (model.getArticulo() != null)
		// entity.setArticulo(ArticuloDAO.getInstancia().toEntity(model.getArticulo()));
		return entity;
	}

	public Ubicacion findUbicacionLibre() {
		Session session = null;
		List<UbicacionEntity> ubicaciones = new ArrayList<>();
		try {
			session = this.openSession();
			Query<UbicacionEntity> query = session.createQuery("from UbicacionEntity u where u.libre = :libre",
					UbicacionEntity.class);
			query.setParameter("libre", true);
			ubicaciones = query.setMaxResults(1).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return ubicaciones.size() > 0 ? toModel(ubicaciones.get(0)) : null;
	}

	/*
	 * public List<Ubicacion> findByArticulo(Articulo articulo) { Session session =
	 * this.openSession(); Query<UbicacionEntity> query =
	 * session.createQuery("from UbicacionEntity u where u.articulo = :articulo",
	 * UbicacionEntity.class); query.setParameter("articulo",
	 * ArticuloDAO.getInstancia().toEntity(articulo)); List<UbicacionEntity>
	 * ubicaciones = query.getResultList(); List<Ubicacion> ubicacionesmodel = new
	 * ArrayList<>(); session.close(); if(ubicaciones != null) { for(UbicacionEntity
	 * ubicacion : ubicaciones) { ubicacionesmodel.add(toModel(ubicacion)); } }
	 * return ubicacionesmodel; }
	 */

}
