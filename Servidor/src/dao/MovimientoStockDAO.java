package dao;

import entity.MovimientoStockEntity;
import model.MovimientoStock;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MovimientoStockDAO extends HibernateDAO<MovimientoStock, MovimientoStockEntity> {
	private static MovimientoStockDAO instancia = null;

	protected MovimientoStockDAO() {
		super(MovimientoStockEntity.class);
	}

	public static MovimientoStockDAO getInstancia() {
		if (instancia == null) {
			instancia = new MovimientoStockDAO();
		}
		return instancia;
	}

	@Override
	protected MovimientoStock toModel(MovimientoStockEntity entity) {
		MovimientoStock model = new MovimientoStock();
		model.setArticulo(ArticuloDAO.getInstancia().toModel(entity.getArticulo()));
		model.setAutorizadoPor(entity.getAutorizadoPor());
		model.setCantidad(entity.getCantidad());
		model.setDestino(entity.getDestino());
		model.setEncargado(entity.getEncargado());
		model.setFecha(entity.getFecha());
		model.setMovimientoStockId(entity.getMovimientoStockId());
		model.setTipoMovimiento(entity.getTipoMovimiento());
		return model;
	}

	@Override
	protected MovimientoStockEntity toEntity(MovimientoStock model) {
		MovimientoStockEntity entity = new MovimientoStockEntity();
		entity.setArticulo(ArticuloDAO.getInstancia().toEntity(model.getArticulo()));
		entity.setAutorizadoPor(model.getAutorizadoPor());
		entity.setCantidad(model.getCantidad());
		entity.setDestino(model.getDestino());
		entity.setEncargado(model.getEncargado());
		entity.setFecha(model.getFecha());
		entity.setMovimientoStockId(model.getMovimientoStockId());
		entity.setTipoMovimiento(model.getTipoMovimiento());
		return entity;
	}

}
