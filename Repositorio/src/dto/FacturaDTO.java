package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacturaDTO implements Serializable{
	private static final long serialVersionUID = 7513902051320232440L;

	
	private Integer facturaId;
	private String letra;
	private int numero;
	private ClienteSmallDTO cliente;
	private java.math.BigDecimal iva;
	private java.math.BigDecimal total;
	private LocalDate fecha;
	private java.math.BigDecimal saldo;
	private List<FacturaItemDTO> items;
	
	public FacturaDTO(){
		this.items = new ArrayList<>();
	}
	
	public Integer getFacturaId() {
		return facturaId;
	}
	public void setFacturaId(Integer facturaId) {
		this.facturaId = facturaId;
	}
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public ClienteSmallDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteSmallDTO cliente) {
		this.cliente = cliente;
	}
	public java.math.BigDecimal getIva() {
		return iva;
	}
	public void setIva(java.math.BigDecimal iva) {
		this.iva = iva;
	}
	public java.math.BigDecimal getTotal() {
		return total;
	}
	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public java.math.BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(java.math.BigDecimal saldo) {
		this.saldo = saldo;
	}
	public List<FacturaItemDTO> getItems() {
		return items;
	}
	public void setItems(List<FacturaItemDTO> items) {
		this.items = items;
	}
	

	
	
	
	
	
	
}
