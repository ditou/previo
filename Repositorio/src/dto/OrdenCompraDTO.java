package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompraDTO implements Serializable{

	private static final long serialVersionUID = 3156644185310169708L;

	private Integer ordenCompraId;
	private ArticuloDTO articulo;
	private int cantidad;
	private List<PedidoDTO> pedidosOrigen;
	private LocalDate fecha;
	private boolean pendiente;	

	public OrdenCompraDTO() {
		this.pedidosOrigen = new ArrayList<>();
	}
	
	public boolean isPendiente() {
		return pendiente;
	}
	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}
	public Integer getOrdenCompraId() {
		return ordenCompraId;
	}
	public void setOrdenCompraId(Integer ordenCompraId) {
		this.ordenCompraId = ordenCompraId;
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
	
	public List<PedidoDTO> getPedidosOrigen() {
		return pedidosOrigen;
	}
	public void setPedidosOrigen(List<PedidoDTO> pedidosOrigen) {
		this.pedidosOrigen = pedidosOrigen;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	} 
	
}
