package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.ClienteEntity;
import entity.FacturaEntity;
import entity.FacturaItemEntity;
import model.Cliente;
import model.Factura;
import model.FacturaItem;

public class FacturaDAO extends HibernateDAO<Factura, FacturaEntity> {
	private static FacturaDAO instancia = null;

	protected FacturaDAO() {
		super(FacturaEntity.class);
	}

	public static FacturaDAO getInstancia() {
		if (instancia == null) {
			instancia = new FacturaDAO();
		}
		return instancia;
	}

	@Override
	protected Factura toModel(FacturaEntity entity) {
		Factura model = this.convert(entity);
		model.setCliente(ClienteDAO.getInstancia().toModel(entity.getCliente()));
		return model;
	}

	protected Factura toModel(FacturaEntity entity, Cliente cliente) {
		Factura model = this.convert(entity);
		model.setCliente(cliente);
		return model;
	}

	private Factura convert(FacturaEntity entity) {
		Factura model = new Factura();
		model.setFacturaId(entity.getFacturaId());
		model.setFecha(entity.getFecha());
		model.setItems(getModelItems(entity.getItems()));
		model.setLetra(entity.getLetra());
		model.setNumero(entity.getNumero());
		model.setSaldo(entity.getSaldo());
		model.setIva(entity.getIva());
		return model;
	}

	private List<FacturaItem> getModelItems(List<FacturaItemEntity> items) {
		return items.stream().map(item -> {
			FacturaItem model = new FacturaItem();
			model.setArticulo(ArticuloDAO.getInstancia().toModel(item.getArticulo()));
			model.setCantidad(item.getCantidad());
			model.setFacturaItemId(item.getFacturaItemId());
			model.setPrecio(item.getPrecio());
			return model;
		}).collect(Collectors.toList());
	}

	@Override
	protected FacturaEntity toEntity(Factura model) {
		FacturaEntity entity = convert(model);
		entity.setCliente(ClienteDAO.getInstancia().toEntity(model.getCliente()));
		return entity;
	}

	protected FacturaEntity toEntity(Factura model, ClienteEntity clienteEntity) {
		FacturaEntity entity = convert(model);
		entity.setCliente(clienteEntity);
		return entity;
	}

	private FacturaEntity convert(Factura model) {
		FacturaEntity entity = new FacturaEntity();
		entity.setFacturaId(model.getFacturaId());
		entity.setFecha(model.getFecha());
		entity.setItems(getEntityItems(model.getItems()));
		entity.setLetra(model.getLetra());
		entity.setNumero(model.getNumero());
		entity.setSaldo(model.getSaldo());
		entity.setIva(model.getIva());
		return entity;
	}

	private List<FacturaItemEntity> getEntityItems(List<FacturaItem> items) {
		return items.stream().map(item -> {
			FacturaItemEntity model = new FacturaItemEntity();
			model.setArticulo(ArticuloDAO.getInstancia().toEntity(item.getArticulo()));
			model.setCantidad(item.getCantidad());
			model.setFacturaItemId(item.getFacturaItemId());
			model.setPrecio(item.getPrecio());
			return model;
		}).collect(Collectors.toList());
	}

	public int getProximoNumeroEmitirByLetra(String letra) {
		Session session = null;
		FacturaEntity entity = null;
		try {
			session = this.openSession();
			Query<FacturaEntity> query = session.createQuery("from FacturaEntity p where p.letra = :letra",
					FacturaEntity.class);
			query.setMaxResults(1);
			query.setParameter("letra", letra);

			entity = query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return entity != null ? entity.getNumero() + 1 : 1;
	}

	public List<Factura> getFacturasPendienteDeCancelacionPorCliente(Cliente cliente) {
		List<Factura> facturas = new ArrayList<>();
		Session session = null;
		try {
			ClienteEntity clienteEntity = ClienteDAO.getInstancia().toEntity(cliente);
			session = this.openSession();
			Query<FacturaEntity> query = session.createQuery(
					"from FacturaEntity p where p.saldo > 0 AND p.cliente = :cliente", FacturaEntity.class);
			query.setParameter("cliente", clienteEntity);
			query.getResultList().forEach(f -> {
				facturas.add(toModel(f));
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return facturas;
	}
}
