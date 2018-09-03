package dto;

import java.io.Serializable;

public class FacturaItemDTO implements Serializable{

	private static final long serialVersionUID = 6453178944671614942L;
	
	private Integer facturaItemId;
	private ArticuloDTO articulo;
	private int cantidad;
	private java.math.BigDecimal precio;
	
	public Integer getFacturaItemId() {
		return facturaItemId;
	}
	public void setFacturaItemId(Integer facturaItemId) {
		this.facturaItemId = facturaItemId;
	}
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public java.math.BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(java.math.BigDecimal precio) {
		this.precio = precio;
	}
	
}
