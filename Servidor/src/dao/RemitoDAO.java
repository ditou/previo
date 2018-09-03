package dao;

import java.util.List;
import java.util.stream.Collectors;

import entity.RemitoEntity;
import entity.RemitoItemEntity;
import model.Remito;
import model.RemitoItem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RemitoDAO extends HibernateDAO<Remito, RemitoEntity> {
	private static RemitoDAO instancia = null;

	protected RemitoDAO() {
		super(RemitoEntity.class);
	}

	public static RemitoDAO getInstancia() {
		if (instancia == null) {
			instancia = new RemitoDAO();
		}
		return instancia;
	}

	@Override
	protected Remito toModel(RemitoEntity entity) {
		Remito model = new Remito();
		model.setFecha(entity.getFecha());
		model.setItems(getModelItems(entity.getItems()));
		model.setRemitoId(entity.getRemitoId());
		model.setCliente(ClienteDAO.getInstancia().toModel(entity.getCliente()));
		return model;
	}

	private List<RemitoItem> getModelItems(List<RemitoItemEntity> items) {
		return items.stream().map(item -> {
			RemitoItem model = new RemitoItem();
			model.setCantidad(item.getCantidad());
			model.setRemitoItemId(item.getRemitoItemId());
			model.setArticulo(ArticuloDAO.getInstancia().toModel(item.getArticulo()));
			return model;
		}).collect(Collectors.toList());
	}

	private List<RemitoItemEntity> getEntityItems(List<RemitoItem> items) {
		return items.stream().map(item -> {
			RemitoItemEntity entity = new RemitoItemEntity();
			entity.setCantidad(item.getCantidad());
			entity.setRemitoItemId(item.getRemitoItemId());
			entity.setArticulo(ArticuloDAO.getInstancia().toEntity(item.getArticulo()));
			return entity;
		}).collect(Collectors.toList());
	}

	@Override
	protected RemitoEntity toEntity(Remito model) {
		RemitoEntity entity = new RemitoEntity();
		entity.setCliente(ClienteDAO.getInstancia().toEntity(model.getCliente()));
		entity.setFecha(model.getFecha());
		entity.setItems(getEntityItems(model.getItems()));
		entity.setRemitoId(model.getRemitoId());
		return entity;
	}

}
