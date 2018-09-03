package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.ClienteEntity;
import entity.PedidoEntity;
import entity.PedidoItemEntity;
import model.Cliente;
import model.EstadoPedido;
import model.Pedido;
import model.PedidoItem;

public class PedidoDAO extends HibernateDAO<Pedido, PedidoEntity> {
	private static PedidoDAO instancia = null;

	protected PedidoDAO() {
		super(PedidoEntity.class);
	}

	public static PedidoDAO getInstancia() {
		if (instancia == null) {
			instancia = new PedidoDAO();
		}
		return instancia;
	}

	public List<Pedido> findAllPedidosByEstado(EstadoPedido estado) {
		List<Pedido> pedidos = new ArrayList<>();
		Session session = null;
		try {
			session = this.openSession();
			Query<PedidoEntity> query = session.createQuery("from PedidoEntity p where p.estado = :estado",
					PedidoEntity.class);
			query.setParameter("estado", estado);
			List<PedidoEntity> pedidosEntity = query.list();
			for (PedidoEntity pedidoEntity : pedidosEntity) {
				pedidos.add(toModel(pedidoEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return pedidos;
	}

	@Override
	Pedido toModel(PedidoEntity entity) {
		Pedido model = new Pedido();
		model.setAclaracion(entity.getAclaracion());
		model.setCliente(ClienteDAO.getInstancia().toModel(entity.getCliente()));
		model.setDireccionEnvio(entity.getDireccionEnvio());
		model.setEstado(entity.getEstado());
		model.setFechaCreacion(entity.getFechaCreacion());
		model.setFechaEntrega(entity.getFechaEntrega());
		model.setItems(getItems(entity.getItems()));
		model.setPedidoId(entity.getPedidoId());
		return model;
	}

	private List<PedidoItem> getItems(List<PedidoItemEntity> items) {
		return items.stream().map(i -> {
			PedidoItem model = new PedidoItem();
			model.setArticulo(ArticuloDAO.getInstancia().toModel(i.getArticulo()));
			model.setCantidad(i.getCantidad());
			model.setPedidoItemId(i.getPedidoItemId());
			model.setTotalBruto(i.getTotalBruto());
			return model;
		}).collect(Collectors.toList());
	}

	@Override
	protected PedidoEntity toEntity(Pedido model) {
		PedidoEntity entity = new PedidoEntity();
		Cliente cliente = ClienteDAO.getInstancia().findById(model.getCliente().getClienteId());
		entity.setAclaracion(model.getAclaracion());
		entity.setCliente(ClienteDAO.getInstancia().toEntity(cliente));
		entity.setDireccionEnvio(model.getDireccionEnvio());
		entity.setEstado(model.getEstado());
		entity.setFechaCreacion(model.getFechaCreacion());
		entity.setFechaEntrega(model.getFechaEntrega());
		entity.setItems(getItemsEntity(model.getItems()));
		entity.setPedidoId(model.getPedidoId());
		return entity;
	}

	private List<PedidoItemEntity> getItemsEntity(List<PedidoItem> items) {
		return items.stream().map(i -> {
			PedidoItemEntity entity = new PedidoItemEntity();
			entity.setArticulo(ArticuloDAO.getInstancia().toEntity(i.getArticulo()));
			entity.setCantidad(i.getCantidad());
			entity.setPedidoItemId(i.getPedidoItemId());
			entity.setTotalBruto(i.getTotalBruto());
			return entity;
		}).collect(Collectors.toList());
	}

	public List<Pedido> findAllPedidosByCliente(Cliente cliente) {
		List<Pedido> pedidos = new ArrayList<>();
		Session session = null;
		try {
			ClienteEntity clienteEntity = ClienteDAO.getInstancia().toEntity(cliente);
			session = this.openSession();
			Query<PedidoEntity> query = session.createQuery("from PedidoEntity p where p.cliente = :cliente order by p.pedidoId desc",
					PedidoEntity.class);
			query.setParameter("cliente", clienteEntity);
			List<PedidoEntity> pedidosEntity = query.list();
			for (PedidoEntity pedidoEntity : pedidosEntity) {
				pedidos.add(toModel(pedidoEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return pedidos;
	}
	
	public Pedido findPedidoByIdAndCliente(Integer pedidoId, Integer clienteId) {
		List<Pedido> pedidos = new ArrayList<>();
		Session session = null;
		try {
			ClienteEntity clienteEntity = ClienteDAO.getInstancia().toEntity(ClienteDAO.getInstancia().findById(clienteId));
			session = this.openSession();
			Query<PedidoEntity> query = session.createQuery("from PedidoEntity p where p.cliente = :cliente and p.pedidoId = :pedidoId",
					PedidoEntity.class);
			query.setParameter("cliente", clienteEntity);
			query.setParameter("pedidoId", pedidoId);
			List<PedidoEntity> pedidosEntity = query.list();
			for (PedidoEntity pedidoEntity : pedidosEntity) {
				pedidos.add(toModel(pedidoEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return pedidos != null && pedidos.size() > 0 ? pedidos.get(0) : null;
	}
}
