package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.OrdenCompraDAO;
import dto.OrdenCompraDTO;
import dto.PedidoDTO;

public class OrdenCompra {

	private Integer ordenCompraId;
	private Articulo articulo;
	private int cantidad;
	private List<Pedido> pedidosOrigen;
	private LocalDate fecha;
	private boolean pendiente;
	
	public OrdenCompra() {
		this.pedidosOrigen = new ArrayList<>();

	}

	public OrdenCompra(Pedido pedido, Articulo articulo, int cantidad) {
		this();
		this.articulo = articulo;
		this.cantidad = articulo.getCantidadByPedido(cantidad);
		pedidosOrigen.add(pedido);
		this.fecha = LocalDate.now();
		this.pendiente = true;
	}

	public OrdenCompra(OrdenCompraDTO ordenCompra) {
		this.ordenCompraId = ordenCompra.getOrdenCompraId();
		this.articulo = new Articulo(ordenCompra.getArticulo());
		this.cantidad = ordenCompra.getCantidad();
		this.pedidosOrigen = new ArrayList<>();
		for(PedidoDTO pedido : ordenCompra.getPedidosOrigen()) {
			this.pedidosOrigen.add(new Pedido(pedido));

		}
		this.fecha = ordenCompra.getFecha();
		this.pendiente = ordenCompra.isPendiente();
	}

	public Integer getOrdenCompraId() {
		return ordenCompraId;
	}

	public void setOrdenCompraId(Integer ordenCompraId) {
		this.ordenCompraId = ordenCompraId;
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



	public List<Pedido> getPedidosOrigen() {
		return pedidosOrigen;
	}

	public void setPedidosOrigen(List<Pedido> pedidosOrigen) {
		this.pedidosOrigen = pedidosOrigen;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}

	public OrdenCompra save() {
		return OrdenCompraDAO.getInstancia().save(this);
	}

	public OrdenCompraDTO toDTO() {
		OrdenCompraDTO ordenCompra = new OrdenCompraDTO();
		ordenCompra.setArticulo(this.getArticulo().toDTO());
		ordenCompra.setCantidad(this.getCantidad());
		ordenCompra.setPendiente(pendiente);
		ordenCompra.setFecha(this.getFecha());
		ordenCompra.setOrdenCompraId(this.getOrdenCompraId());
		for(Pedido pedido : this.pedidosOrigen) {
			ordenCompra.getPedidosOrigen().add(pedido.toDTO());
		}

		return ordenCompra;
	}

	public void procesarArticulo(Articulo articulo, int cantidad, Pedido newPedido) {
		int cantidadACubrir = 0;
		for(Pedido pedido :  pedidosOrigen) {
			cantidadACubrir += pedido.getCantidadForArticulo(articulo);
		}
		if(this.cantidad < (cantidad + cantidadACubrir)) {
			this.cantidad += articulo.getCantidadByPedido(cantidad);
		}
		pedidosOrigen.add(newPedido);
		save();
	}

	public void procesar() {
		this.pendiente = false;
		save();
	}
	
	public void recepcionar(LocalDate fechaVto, Integer nroLote) {
		this.articulo.agregarLote(this.cantidad, fechaVto, nroLote);
		for(Pedido pedido : this.pedidosOrigen) {
			pedido.pendienteDespacho();
		}
	}
	
	public void recepcionar(Integer loteId) {
		this.articulo.recepcionarStock(loteId, this.cantidad);
		for(Pedido pedido : this.pedidosOrigen) {
			pedido.pendienteDespacho();
		}
	}
}
