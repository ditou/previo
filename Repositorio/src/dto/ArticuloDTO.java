package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDTO implements Serializable {
	private static final long serialVersionUID = -2978613196004879851L;
	private Integer articuloId;
	private String codigoBarras;
	private String descripcion;
	private int tamanio;
	private String unidad;
	private java.math.BigDecimal precioVenta;
	private int cantFijaCompra;
	private int cantOcupaUbicacion;
	private String presentacion;
	private List<LoteDTO> lotes;

	private boolean activo;

	public ArticuloDTO(){
		this.lotes = new ArrayList<>();
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


	public List<LoteDTO> getLotes() {
		return lotes;
	}
	
	public void setLotes(List<LoteDTO> lotes) {
		this.lotes = lotes;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	
	public void agregarLote(LoteDTO lote) {
		if(this.lotes == null)
			lotes = new ArrayList<>();
		lotes.add(lote);
	}
}
