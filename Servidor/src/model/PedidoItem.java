package model;

import dao.ArticuloDAO;
import dto.PedidoItemDTO;

public class PedidoItem {
    private Integer pedidoItemId;
    private Articulo articulo;
    private int cantidad;
    private java.math.BigDecimal totalBruto;

    public PedidoItem(){

    }

	public PedidoItem(PedidoItemDTO item) {
		Articulo articulo = ArticuloDAO.getInstancia().findById(item.getArticulo().getArticuloId());
		this.articulo = articulo;
		this.cantidad = item.getCantidad();
		this.totalBruto = articulo.getPrecioVenta();
		this.pedidoItemId = item.getPedidoItemId();
	}

    public Integer getPedidoItemId() {
        return pedidoItemId;
    }

    public void setPedidoItemId(Integer pedidoItemId) {
        this.pedidoItemId = pedidoItemId;
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

    public java.math.BigDecimal getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(java.math.BigDecimal totalBruto) {
        this.totalBruto = totalBruto;
    }

	public PedidoItemDTO toDTO() {
		PedidoItemDTO item = new PedidoItemDTO();
		item.setArticulo(this.getArticulo().toDTO());
		item.setCantidad(this.getCantidad());
		item.setTotalBruto(this.getTotalBruto());
		item.setPedidoItemId(item.getPedidoItemId());

		return item;
	}

}
