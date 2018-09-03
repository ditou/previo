package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PagoDTO implements Serializable{

	private static final long serialVersionUID = 6376185785168659528L;

	private ClienteSmallDTO cliente;
	private java.math.BigDecimal total;
	private FacturaDTO factura;
	private Integer pagoId;
	private LocalDate fecha;
	
	public ClienteSmallDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteSmallDTO cliente) {
		this.cliente = cliente;
	}
	
	
	public Integer getPagoId() {
		return pagoId;
	}
	public void setPagoId(Integer pagoId) {
		this.pagoId = pagoId;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public java.math.BigDecimal getTotal() {
		return total;
	}
	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}
	public FacturaDTO getFactura() {
		return factura;
	}
	public void setFactura(FacturaDTO factura) {
		this.factura = factura;
	}
}
