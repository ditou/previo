package dto;

import java.io.Serializable;

public class PedidoItemDTO implements Serializable {
	
	private static final long serialVersionUID = -2626198845437252545L;
	
	private ArticuloDTO articulo;
	private Integer cantidad;
	private java.math.BigDecimal totalBruto;
	private Integer pedidoItemId;
	
	public Integer getPedidoItemId() {
		return pedidoItemId;
	}
	public void setPedidoItemId(Integer pedidoItemId) {
		this.pedidoItemId = pedidoItemId;
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
	public java.math.BigDecimal getTotalBruto() {
		return totalBruto;
	}
	public void setTotalBruto(java.math.BigDecimal totalBruto) {
		this.totalBruto = totalBruto;
	}

}
