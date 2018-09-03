package dto;

import java.io.Serializable;

public class CompraItemDTO implements Serializable{

	private static final long serialVersionUID = 6452008944671614942L;
	
	private Integer compraItemId;
    private java.math.BigDecimal precio;
    private OrdenCompraDTO ordenCompra;
	
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
	public OrdenCompraDTO getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(OrdenCompraDTO ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	
    
}
