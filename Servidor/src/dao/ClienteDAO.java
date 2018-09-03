package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.ClienteEntity;
import entity.ClienteObservacionEntity;
import entity.CuentaCorrienteEntity;
import entity.CuentaCorrienteItemEntity;
import entity.FacturaEntity;
import entity.PagoEntity;
import model.Cliente;
import model.ClienteObservacion;
import model.CuentaCorriente;
import model.CuentaCorrienteItem;
import model.Factura;
import model.Pago;

public class ClienteDAO extends HibernateDAO<Cliente, ClienteEntity> {

	private static ClienteDAO instancia = null;

	private ClienteDAO() {
		super(ClienteEntity.class);
	}

	public static ClienteDAO getInstancia() {
		if (instancia == null) {
			instancia = new ClienteDAO();
		}
		return instancia;
	}

	@Override
	public List<Cliente> getAll() {

		return super.getAll().stream().filter(c -> c.isActivo()).collect(Collectors.toList());
	}

	public Cliente findClienteByEmail(String email) {
		Session session = null;
		Cliente model = null;
		try {

			session = this.openSession();

			Query<ClienteEntity> query = session.createQuery("from ClienteEntity p where p.email = :email",
					ClienteEntity.class);
			query.setParameter("email", email);
			ClienteEntity entity = query.uniqueResult();

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

	@Override
	Cliente toModel(ClienteEntity entity) {
		Cliente cliente = new Cliente();
		cliente.setActivo(entity.isActivo());
		cliente.setCategoriaFiscal(entity.getCategoriaFiscal());
		cliente.setClienteId(entity.getClienteId());
		cliente.setContrasenia(entity.getContrasenia());
		cliente.setCuentaCorriente(getCuentaCorriente(entity.getCuentaCorriente(), cliente));
		cliente.setDireccion(entity.getDireccion());
		cliente.setEmail(entity.getEmail());
		cliente.setNombre(entity.getNombre());
		cliente.setNroIdentificacion(entity.getNroIdentificacion());
		cliente.setObservaciones(getObservaciones(entity.getObservaciones()));
		cliente.setTelefono(entity.getTelefono());
		return cliente;
	}

	private List<ClienteObservacion> getObservaciones(List<ClienteObservacionEntity> observaciones) {
		return observaciones.stream().map(obs -> {
			ClienteObservacion model = new ClienteObservacion();
			model.setDescripcion(obs.getDescripcion());
			model.setClienteObservacionId(obs.getClienteObservacionId());
			model.setFecha(obs.getFecha());
			return model;
		}).collect(Collectors.toList());
	}

	private CuentaCorriente getCuentaCorriente(CuentaCorrienteEntity cuentaCorriente, Cliente cliente) {
		CuentaCorriente model = new CuentaCorriente();
		model.setLimiteCredito(cuentaCorriente.getLimiteCredito());
		model.setCuentaCorrienteId(cuentaCorriente.getCuentaCorrienteId());

		List<CuentaCorrienteItem> items = cuentaCorriente.getItems().stream().map(entity -> {
			CuentaCorrienteItem item = new CuentaCorrienteItem();
			item.setFecha(entity.getFecha());
			item.setCuentaCorrienteItemId(entity.getCuentaCorrienteItemId());
			item.setFactura(getFactura(entity.getFactura(), cliente));
			item.setPagos(getPagosModel(entity.getPagos()));
			return item;
		}).collect(Collectors.toList());
		model.setItems(items);

		return model;
	}

	private List<Pago> getPagosModel(List<PagoEntity> pagos) {
		List<Pago> models = new ArrayList<>();
		if(pagos != null) {
			for(PagoEntity p : pagos) {
				p.setCliente(null);
				Pago pago = PagoDAO.getInstancia().toModelWithoutClient(p);
				models.add(pago);
			}
		}
		return models;
	}

	private Factura getFactura(FacturaEntity entity, Cliente cliente) {
		return FacturaDAO.getInstancia().toModel(entity, cliente);
	}

	@Override
	ClienteEntity toEntity(Cliente model) {
		ClienteEntity entity = new ClienteEntity();
		entity.setActivo(model.isActivo());
		entity.setCategoriaFiscal(model.getCategoriaFiscal());
		entity.setClienteId(model.getClienteId());
		entity.setContrasenia(model.getContrasenia());
		entity.setCuentaCorriente(getCuentaCorriente(model.getCuentaCorriente(), entity));
		entity.setDireccion(model.getDireccion());
		entity.setEmail(model.getEmail());
		entity.setNombre(model.getNombre());
		entity.setNroIdentificacion(model.getNroIdentificacion());
		entity.setObservaciones(getObservacionesEntity(model.getObservaciones()));
		entity.setTelefono(model.getTelefono());

		return entity;
	}

	private List<ClienteObservacionEntity> getObservacionesEntity(List<ClienteObservacion> observaciones) {
		return observaciones.stream().map(obs -> {
			ClienteObservacionEntity model = new ClienteObservacionEntity();
			model.setDescripcion(obs.getDescripcion());
			model.setClienteObservacionId(obs.getClienteObservacionId());
			model.setFecha(obs.getFecha());
			return model;
		}).collect(Collectors.toList());
	}

	private CuentaCorrienteEntity getCuentaCorriente(CuentaCorriente cuentaCorriente, ClienteEntity clienteEntity) {
		CuentaCorrienteEntity model = new CuentaCorrienteEntity();
		if (cuentaCorriente != null) {
			model.setLimiteCredito(cuentaCorriente.getLimiteCredito());
			model.setCuentaCorrienteId(cuentaCorriente.getCuentaCorrienteId());
			model.setTotal(cuentaCorriente.getTotal());

			List<CuentaCorrienteItemEntity> items = cuentaCorriente.getItems().stream().map(modelitem -> {
				CuentaCorrienteItemEntity item = new CuentaCorrienteItemEntity();
				item.setFecha(modelitem.getFecha());
				item.setCuentaCorrienteItemId(modelitem.getCuentaCorrienteItemId());
				item.setFactura(getFacturaEntity(modelitem.getFactura(), clienteEntity));
				item.setPagos(getPagos(modelitem.getPagos()));
				return item;
			}).collect(Collectors.toList());
			model.setItems(items);
		}
		return model;
	}

	private List<PagoEntity> getPagos(List<Pago> pagos) {
		List<PagoEntity> entities = new ArrayList<>();
		if(pagos != null) {
			for(Pago p : pagos) {
				p.setCliente(null);
				PagoEntity pagoEntity = PagoDAO.getInstancia().toEntityWithoutClient(p);
				entities.add(pagoEntity);
			}
		}
		return entities;
	}

	private FacturaEntity getFacturaEntity(Factura factura, ClienteEntity clienteEntity) {
		return FacturaDAO.getInstancia().toEntity(factura, clienteEntity);
	}

}
