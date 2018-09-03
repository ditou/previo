package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entity.ArticuloEntity;
import entity.LoteEntity;
import entity.UbicacionEntity;
import model.Articulo;
import model.Lote;
import model.Ubicacion;

public class ArticuloDAO extends HibernateDAO<Articulo, ArticuloEntity> {

	private static ArticuloDAO instancia = null;

	protected ArticuloDAO() {
		super(ArticuloEntity.class);
	}

	public static ArticuloDAO getInstancia() {
		if (instancia == null) {
			instancia = new ArticuloDAO();
		}
		return instancia;
	}

	@Override
	public List<Articulo> getAll() {
		return super.getAll().stream().filter(a -> a.isActivo()).collect(Collectors.toList());
	}

	@Override
	Articulo toModel(ArticuloEntity entity) {
		Articulo model = new Articulo();
		model.setArticuloId(entity.getArticuloId());
		model.setCantFijaCompra(entity.getCantFijaCompra());
		model.setCantOcupaUbicacion(entity.getCantOcupaUbicacion());
		model.setTamanio(entity.getTamanio());
		model.setCodigoBarras(entity.getCodigoBarras());
		model.setDescripcion(entity.getDescripcion());
		model.setLotes(getLotes(entity.getLotes()));
		model.setPrecioVenta(entity.getPrecioVenta());
		model.setUnidad(entity.getUnidad());
		model.setActivo(entity.isActivo());
		model.setPresentacion(entity.getPresentacion());

		return model;
	}

	private List<Lote> getLotes(List<LoteEntity> lotes) {
		List<Lote> models = new ArrayList<>();
		if(!lotes.isEmpty()) {
			for(LoteEntity lote : lotes) {
				Lote model = new Lote();
	
				model.setFechaVencimiento(lote.getFechaVencimiento());
				model.setLoteId(lote.getLoteId());
				model.setNroLote(lote.getNroLote());
				List<Ubicacion> ubicaciones = new ArrayList<>();
				for (UbicacionEntity ubicacion : lote.getUbicaciones()) {
					ubicaciones.add(UbicacionDAO.getInstancia().toModel(ubicacion));
				}
				model.setUbicaciones(ubicaciones);	
				models.add(model);
			}
		}
		return models;
	}

	@Override
	ArticuloEntity toEntity(Articulo model) {
		ArticuloEntity entity = new ArticuloEntity();
		entity.setArticuloId(model.getArticuloId());
		entity.setCantFijaCompra(model.getCantFijaCompra());
		entity.setCantOcupaUbicacion(model.getCantOcupaUbicacion());
		entity.setTamanio(model.getTamanio());
		entity.setCodigoBarras(model.getCodigoBarras());
		entity.setDescripcion(model.getDescripcion());
		entity.setLotes(getLotesEntity(model.getLotes()));
		entity.setPrecioVenta(model.getPrecioVenta());
		entity.setUnidad(model.getUnidad());
		entity.setActivo(model.isActivo());
		entity.setPresentacion(model.getPresentacion());
		
		return entity;
	}

	private List<LoteEntity> getLotesEntity(List<Lote> lotes) {
		List<LoteEntity> entities = new ArrayList<LoteEntity>();
		for(Lote lote : lotes) {
			LoteEntity entity = new LoteEntity();
			entity.setFechaVencimiento(lote.getFechaVencimiento());
			entity.setNroLote(lote.getNroLote());
			
			if (lote.getLoteId() != null)
				entity.setLoteId(lote.getLoteId());
			
			List<UbicacionEntity> ubicaciones = new ArrayList<>();
			for (Ubicacion ubicacion : lote.getUbicaciones()) {
				ubicaciones.add(UbicacionDAO.getInstancia().toEntity(ubicacion));
			}
			entity.setUbicaciones(ubicaciones);
			entities.add(entity);
		}
		return entities;
	}

}
