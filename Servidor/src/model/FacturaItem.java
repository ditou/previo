package model;

import dto.FacturaItemDTO;

public class FacturaItem {
	
	private Integer facturaItemId;
	private Articulo articulo;
	private int cantidad;
	private java.math.BigDecimal precio;

	public FacturaItem(){

	}

	public FacturaItem(FacturaItemDTO item) {
		this.facturaItemId = item.getFacturaItemId();
		this.articulo = new Articulo (item.getArticulo());
		this.cantidad = item.getCantidad();
		this.precio = item.getPrecio();
	}
	
	public FacturaItemDTO toDTO() {
		FacturaItemDTO factura =  new FacturaItemDTO();
		factura.setArticulo(this.getArticulo().toDTO());
		factura.setCantidad(this.getCantidad());
		factura.setFacturaItemId(this.getFacturaItemId());
		factura.setPrecio(this.getPrecio());
		
		return factura;	
	}
	
	public Integer getFacturaItemId() {
		return facturaItemId;
	}
	public void setFacturaItemId(Integer facturaItemId) {
		this.facturaItemId = facturaItemId;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
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
