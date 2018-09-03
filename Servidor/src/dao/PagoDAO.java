package dao;

import entity.PagoEntity;
import model.Pago;

public class PagoDAO extends HibernateDAO<Pago, PagoEntity> {
	private static PagoDAO instancia = null;

	protected PagoDAO() {
		super(PagoEntity.class);
	}

	public static PagoDAO getInstancia() {
		if (instancia == null) {
			instancia = new PagoDAO();
		}
		return instancia;
	}

	@Override
	protected Pago toModel(PagoEntity entity) {
		Pago model = new Pago();
		
		model.setTotal(entity.getTotal());
		
		model.setCliente(ClienteDAO.getInstancia().toModel(entity.getCliente()));
		model.setFecha(entity.getFecha());
		model.setPagoId(entity.getPagoId());
		return model;
	}
	
	protected Pago toModelWithoutClient(PagoEntity entity) {
		Pago model = new Pago();
		model.setTotal(entity.getTotal());
		model.setFecha(entity.getFecha());
		model.setPagoId(entity.getPagoId());
		return model;
	}

	@Override
	protected PagoEntity toEntity(Pago model) {
		PagoEntity entity = new PagoEntity();
		
		entity.setCliente(ClienteDAO.getInstancia().toEntity(model.getCliente()));
		entity.setTotal(model.getTotal());
		entity.setFecha(model.getFecha());
		entity.setPagoId(model.getPagoId());
		return entity;
	}

	protected PagoEntity toEntityWithoutClient(Pago model) {
		PagoEntity entity = new PagoEntity();
		entity.setTotal(model.getTotal());
		entity.setFecha(model.getFecha());
		entity.setPagoId(model.getPagoId());
		return entity;
	}
}
