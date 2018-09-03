package model;

import java.time.LocalDate;

import dto.CompraItemDTO;

public class CompraItem {
	
	private Integer compraItemId;
    private OrdenCompra ordenCompra;
    private java.math.BigDecimal precio;

    public CompraItem(OrdenCompra ordenCompra, java.math.BigDecimal precio) {
    	this.ordenCompra = ordenCompra;
    	this.precio = precio;
    }
    
	public CompraItem(){

	}

	public CompraItem(CompraItemDTO item) {
		this.compraItemId = item.getCompraItemId();
		this.ordenCompra = new OrdenCompra(item.getOrdenCompra());
		this.precio = item.getPrecio();
	}
	
	public CompraItemDTO toDTO() {
		CompraItemDTO item =  new CompraItemDTO();
		item.setOrdenCompra(this.ordenCompra.toDTO());
		item.setCompraItemId(this.compraItemId);
		item.setPrecio(this.precio);
		return item;
	}

	public Integer getCompraItemId() {
		return compraItemId;
	}

	public void setCompraItemId(Integer compraItemId) {
		this.compraItemId = compraItemId;
	}

	public java.math.BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(java.math.BigDecimal precio) {
		this.precio = precio;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public void recepcionar(LocalDate fechaVto, Integer nroLote) {
		this.ordenCompra.recepcionar(fechaVto, nroLote);
		
	}
	
	public void recepcionar(Integer loteId) {
		this.ordenCompra.recepcionar(loteId);
	}
	

}
