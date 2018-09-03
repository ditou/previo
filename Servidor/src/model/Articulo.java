package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.ArticuloDAO;
import dto.ArticuloDTO;
import dto.ArticuloUbicacionDTO;

public class Articulo {
	private Integer articuloId;
	private String codigoBarras;
	private String descripcion;
	private int tamanio;
	private String unidad;
	private java.math.BigDecimal precioVenta;
	private int cantFijaCompra;
	private int cantOcupaUbicacion;
	private List<Lote> lotes;

	private boolean activo;
	private String presentacion;

	public Articulo() {
		lotes = new ArrayList<>();
	}

	public Articulo(ArticuloDTO dto) {
		this();
		this.codigoBarras = dto.getCodigoBarras();
		this.descripcion = dto.getDescripcion();
		this.presentacion = dto.getPresentacion();
		this.tamanio = dto.getTamanio();
		this.unidad = dto.getUnidad();
		this.precioVenta = dto.getPrecioVenta();
		this.cantFijaCompra = dto.getCantFijaCompra();
		this.cantOcupaUbicacion = dto.getCantOcupaUbicacion();
		this.articuloId = dto.getArticuloId();
		if(articuloId == null) {
			this.activo = true;
		}else {
			Articulo articuloDB = ArticuloDAO.getInstancia().findById(this.articuloId);
			this.setActivo(articuloDB.isActivo());
			for(Lote lote : articuloDB.getLotes()) {
				this.agregarLote(lote);
			}
		}
	}

	public ArticuloDTO toDTO() {
		ArticuloDTO articulo = new ArticuloDTO();
		articulo.setArticuloId(this.getArticuloId());
		articulo.setCantFijaCompra(this.getCantFijaCompra());
		articulo.setCantOcupaUbicacion(this.getCantOcupaUbicacion());
		articulo.setCodigoBarras(this.getCodigoBarras());
		articulo.setDescripcion(this.getDescripcion());
		articulo.setPresentacion(this.getPresentacion());
		articulo.setPrecioVenta(this.getPrecioVenta());
		articulo.setTamanio(this.getTamanio());
		articulo.setUnidad(this.getUnidad());
		Collections.sort(lotes, (l1, l2)->l1.getFechaVencimiento().compareTo(l2.getFechaVencimiento()));
		for (Lote lote : this.lotes) {
			articulo.agregarLote(lote.toDTO());
		}
		articulo.setActivo(this.isActivo());
		return articulo;
	}

	public Integer getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(Integer articuloId) {
		this.articuloId = articuloId;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public java.math.BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(java.math.BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getCantFijaCompra() {
		return cantFijaCompra;
	}

	public void setCantFijaCompra(int cantFijaCompra) {
		this.cantFijaCompra = cantFijaCompra;
	}

	public int getCantOcupaUbicacion() {
		return cantOcupaUbicacion;
	}

	public void setCantOcupaUbicacion(int cantOcupaUbicacion) {
		this.cantOcupaUbicacion = cantOcupaUbicacion;
	}

	

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean hayStock(int cantidad) {
		int stockActual = lotes != null && lotes.size() > 0 ? lotes.stream().mapToInt(l->l.getStock()).sum() : 0;

		if (cantidad > stockActual)
			return false;
		else
			return true;
	}


	public void despacharStock(int cantidad) {
		int posi = 0;
		while (cantidad != 0) {
			Lote lote = this.lotes.get(posi);
			cantidad = lote.despacharStock(cantidad);
			posi++;
		}
		save();
	}
	
	public void movimientoStock(int cantidad, Integer loteId, Integer ubicacionId) {
		Lote loteToUpdate = null;
		for(Lote lote : lotes) {
			if(lote.getLoteId().equals(loteId))
				loteToUpdate = lote;
		}
		loteToUpdate.movimientoStock(cantidad, ubicacionId);

		save();
	}
	
	public void recepcionarStock(Integer loteId, int cantidad) {
		Lote loteToUpdate = null;
		for(Lote lote : lotes) {
			if(lote.getLoteId().equals(loteId))
				loteToUpdate = lote;
		}
		loteToUpdate.recepcionarStock(cantidad);
	}
	

	public void agregarLote(int cantidad, LocalDate fechaVto, Integer nroLote) {
		Lote lote = new Lote(fechaVto, nroLote, cantidad);
		this.agregarLote(lote);
		save();
	}

	public Articulo save() {
		Articulo art = ArticuloDAO.getInstancia().save(this);

		return art;

	}

	public void darDeBaja() {
		this.activo = false;
		save();
	}

	public int getCantidadByPedido(int cantidad) {
		int aux = cantFijaCompra;
		while (aux < cantidad)
			aux += cantFijaCompra;
		return aux;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}
	
	
	public void agregarLote(Lote lote) {
		if(this.lotes == null) {
			lotes = new ArrayList<>();
		}
		
		lotes.add(lote);
	}
	
	public static List<ArticuloUbicacionDTO> getArticulosWithUbicacion(){
		List<Articulo> articulos = ArticuloDAO.getInstancia().getAll();
		List<ArticuloUbicacionDTO> retorna = new ArrayList<>();
		for(Articulo art : articulos) {
			for(Lote lote : art.getLotes()) {
				for(Ubicacion ubi : lote.getUbicaciones()) {
					ArticuloUbicacionDTO arta = new ArticuloUbicacionDTO();
					arta.setDescripcion(art.getDescripcion() +" ("+art.getPresentacion() + " " + art.getTamanio() + art.getUnidad() + ")");
					arta.setCodigoBarras(art.getCodigoBarras());
					arta.setNroLote(lote.getNroLote());
					arta.setVencimiento(lote.getFechaVencimiento());
					arta.setCodUbicacion(ubi.getCodigoUbicacion());
					arta.setCantidad(ubi.getCantidad());
					retorna.add(arta);
				}
			}
		}
		Collections.sort(retorna, (a1, a2) -> a1.getCodUbicacion().compareTo(a2.getCodUbicacion()));
		return retorna;
	}

}
