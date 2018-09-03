package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.OrdenCompraDAO;
import dao.PedidoDAO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;

public class Pedido {
	private Integer pedidoId;
	private Cliente cliente;
	private String direccionEnvio;
	private LocalDate fechaCreacion;
	private LocalDate fechaEntrega;
	private EstadoPedido estado;
	private String aclaracion;
	private List<PedidoItem> items;

	public Pedido() {
		this.items = new ArrayList<>();
	}

	public Pedido(PedidoDTO pedido) {
		this();
		if (pedido.getPedidoId() == null) {
			this.setEstado(EstadoPedido.EN_REVISION);
			this.setFechaCreacion(LocalDate.now());
		}else {
			this.pedidoId = pedido.getPedidoId();
			this.fechaCreacion = pedido.getFechaCreacion();
			this.estado = EstadoPedido.valueOf(pedido.getEstado());

		}
		this.cliente = new Cliente(pedido.getCliente());
		this.direccionEnvio = pedido.getDireccionEnvio();

		this.fechaEntrega = pedido.getFechaEntrega();

		this.aclaracion = pedido.getAclaracion();
		
		for (PedidoItemDTO item : pedido.getItems()) {
			this.agregarItem(new PedidoItem(item));
		}
	}

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public String getAclaracion() {
		return aclaracion;
	}

	public void setAclaracion(String aclaracion) {
		this.aclaracion = aclaracion;
	}

	public java.math.BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for(PedidoItem item : items) {
			total = total.add(new BigDecimal(item.getCantidad()).multiply(item.getTotalBruto()));
		}
		return total;
	}

	public List<PedidoItem> getItems() {
		return items;
	}

	public void setItems(List<PedidoItem> items) {
		this.items = items;
	}

	public PedidoDTO toDTO() {
		PedidoDTO pedido = new PedidoDTO();
		pedido.setPedidoId(this.getPedidoId());
		pedido.setCliente(this.getCliente().toDTO());
		pedido.setDireccionEnvio(this.getDireccionEnvio());
		pedido.setFechaCreacion(this.getFechaCreacion());
		pedido.setFechaEntrega(this.getFechaEntrega());
		pedido.setEstado(this.getEstado().toString());
		pedido.setAclaracion(this.getAclaracion());
		pedido.setTotal(this.getTotal());
		for (PedidoItem item : this.items) {
			pedido.getItems().add(item.toDTO());
		}
		return pedido;
	}

	public void aprobar(String aclaracion) {
		if (EstadoPedido.EN_REVISION.equals(estado)) {
			//if (aclaracion != null && !aclaracion.trim().equals(""))
				this.setAclaracion(aclaracion);

			if (hayStock()) {
				this.pendienteDespacho();
			} else {
				this.faltaStock();
			}
		}
	}

	public void pendienteDespacho() {
		if (EstadoPedido.EN_REVISION.equals(estado) || EstadoPedido.FALTA_STOCK.equals(estado)) {
			this.setEstado(EstadoPedido.PENDIENTE_DESPACHO);
			save();
		}
	}

	public void faltaStock() {
		if (EstadoPedido.EN_REVISION.equals(estado)) {
			this.setEstado(EstadoPedido.FALTA_STOCK);
			save();
		}
	}

	private boolean hayStock() {
		return checkArticulosFaltantes().size() == 0;
	}

	private List<Articulo> checkArticulosFaltantes() {
		List<Articulo> faltantes = new ArrayList<>();
		for (PedidoItem item : this.items) {
			int cantidad = item.getCantidad();
			Articulo articulo = item.getArticulo();
			if (isArticuloPendienteDeCompra(articulo, cantidad)) {
				faltantes.add(articulo);
			} else if (!articulo.hayStock(cantidad)) {
				faltantes.add(articulo);
				new OrdenCompra(this, articulo, cantidad).save();
			}
		}
		return faltantes;
	}

	private boolean isArticuloPendienteDeCompra(Articulo articulo, int cantidad) {
		Integer ordenCompraId = OrdenCompraDAO.getInstancia().findOrdenCompraPendienteByArticulo(articulo);
		if(ordenCompraId != null) {
			OrdenCompra ordenCompraPendiente = OrdenCompraDAO.getInstancia().findById(ordenCompraId);
			ordenCompraPendiente.procesarArticulo(articulo, cantidad, this);
			return true;
		}
		return false;
	}

	public void despachar(LocalDate fechaEntrega) {
		if (EstadoPedido.PENDIENTE_DESPACHO.equals(estado)) {
			this.setFechaEntrega(fechaEntrega);
			this.setEstado(EstadoPedido.EN_TRANSITO);
			despacharStock();
			facturar();
			save();
		}
	}

	public void rechazar(String aclaracion) {
		if (EstadoPedido.EN_REVISION.equals(estado)) {
			this.setAclaracion(aclaracion);
			this.setEstado(EstadoPedido.RECHAZADO);
			save();
		}
	}

	private void despacharStock() {
		items.forEach(i -> i.getArticulo().despacharStock(i.getCantidad() * -1));
	}

	public void facturar() {
		Factura factura = new Factura();
		factura.facturar(this);
		remitir();
	}

	private void remitir() {
		Remito remito = new Remito();
		remito.remitir(this);
	}

	public Pedido save() {
		return PedidoDAO.getInstancia().save(this);
	}
	
	public void agregarItem(PedidoItem item) {
		boolean existe = false;
		for(PedidoItem i : this.items) {
			if(i.getArticulo().getArticuloId().equals((item.getArticulo().getArticuloId()))){
				i.setCantidad(i.getCantidad() + item.getCantidad());
				existe = true;
				break;
			}
		}
		if(!existe)
			this.items.add(item);
	}

	public int getCantidadForArticulo(Articulo articulo) {
		int cantidad = 0;
		for(PedidoItem i : this.items) {
			if(articulo.getArticuloId().equals(i.getArticulo().getArticuloId())) {
				cantidad += i.getCantidad();
			}
		}
		return cantidad;
	}
}
