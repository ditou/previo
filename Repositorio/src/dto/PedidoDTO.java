package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDTO implements Serializable {
	
	private static final long serialVersionUID = 3752189688416983296L;
	
	private Integer pedidoId;
	private ClienteDTO cliente;
	private String direccionEnvio;
	private LocalDate fechaCreacion;
	private LocalDate fechaEntrega;
	private String estado;
	private String aclaracion;
	private java.math.BigDecimal total;
	private List<PedidoItemDTO> items;
	
	public PedidoDTO() {
		this.items = new ArrayList<PedidoItemDTO>();
	}
	
	public Integer getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAclaracion() {
		return aclaracion;
	}
	public void setAclaracion(String aclaracion) {
		this.aclaracion = aclaracion;
	}
	public java.math.BigDecimal getTotal() {
		return total;
	}
	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}
	public List<PedidoItemDTO> getItems() {
		return items;
	}
	public void setItems(List<PedidoItemDTO> items) {
		this.items = items;
	}

}
