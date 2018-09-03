package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class MovimientoStockDTO implements Serializable{
	
	private static final long serialVersionUID = -751991069847171886L;
	
	private Integer movimientoStockId;
	private ArticuloDTO articulo;
	private int cantidad;
	private LocalDate fecha;
	private String tipoMovimiento;
	private String encargado;
	private String autorizadoPor;
	private String destino;
	
	public Integer getMovimientoStockId() {
		return movimientoStockId;
	}
	
	public void setMovimientoStockId(Integer movimientoStockId) {
		this.movimientoStockId = movimientoStockId;
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
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getEncargado() {
		return encargado;
	}
	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}
	public String getAutorizadoPor() {
		return autorizadoPor;
	}
	public void setAutorizadoPor(String autorizadoPor) {
		this.autorizadoPor = autorizadoPor;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	
	
	
	

}
