package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.OrdenCompraEntity;
import entity.PedidoEntity;
import model.Articulo;
import model.OrdenCompra;
import model.Pedido;

public class OrdenCompraDAO extends HibernateDAO<OrdenCompra, OrdenCompraEntity> {
	private static OrdenCompraDAO instancia = null;

	protected OrdenCompraDAO() {
		super(OrdenCompraEntity.class);
	}

	public static OrdenCompraDAO getInstancia() {
		if (instancia == null) {
			instancia = new OrdenCompraDAO();
		}
		return instancia;
	}

	@Override
	protected OrdenCompra toModel(OrdenCompraEntity entity) {
		OrdenCompra model = new OrdenCompra();
		model.setArticulo(ArticuloDAO.getInstancia().toModel(entity.getArticulo()));
		model.setCantidad(entity.getCantidad());
		model.setPendiente(entity.isPendiente());
		model.setFecha(entity.getFecha());
		model.setOrdenCompraId(entity.getOrdenCompraId());
		for(PedidoEntity pedido : entity.getPedidosOrigen()) {
			model.getPedidosOrigen().add(PedidoDAO.getInstancia().toModel(pedido));
		}
		return model;
	}

	@Override
	protected OrdenCompraEntity toEntity(OrdenCompra model) {
		OrdenCompraEntity entity = new OrdenCompraEntity();
		entity.setArticulo(ArticuloDAO.getInstancia().toEntity(model.getArticulo()));
		entity.setCantidad(model.getCantidad());
		entity.setPendiente(model.isPendiente());
		entity.setFecha(model.getFecha());
		entity.setOrdenCompraId(model.getOrdenCompraId());
		for(Pedido pedido : model.getPedidosOrigen()) {
			entity.getPedidosOrigen().add(PedidoDAO.getInstancia().toEntity(pedido));

		}
		return entity;
	}

	public Integer findOrdenCompraPendienteByArticulo(Articulo articulo) {
		Session session = null;
		List<OrdenCompraEntity> entity = new ArrayList<>();
		try {
			session = this.openSession();
			Query<OrdenCompraEntity> query = session.createQuery(
					"from OrdenCompraEntity p " + "where p.articulo = :articulo and p.pendiente = :pendiente",
					OrdenCompraEntity.class);
			query.setParameter("articulo", ArticuloDAO.getInstancia().toEntity(articulo));
			query.setParameter("pendiente", true);
			entity = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return entity.size() > 0 ? entity.get(0).getOrdenCompraId() : null;
	}

	public List<OrdenCompra> findAllOrdenCompraPendientes() {
		List<OrdenCompra> ordenes = new ArrayList<>();
		Session session = null;
		try {
			session = this.openSession();
			Query<OrdenCompraEntity> query = session
					.createQuery("from OrdenCompraEntity p where p.pendiente = :pendiente", OrdenCompraEntity.class);
			query.setParameter("pendiente", true);
			List<OrdenCompraEntity> ordenEntities = query.list();
			for (OrdenCompraEntity entity : ordenEntities) {
				ordenes.add(toModel(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return ordenes;
	}

}
