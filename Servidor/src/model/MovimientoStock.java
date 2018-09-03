package model;

import java.time.LocalDate;

import dao.ArticuloDAO;
import dao.MovimientoStockDAO;
import dto.MovimientoStockDTO;

public class MovimientoStock {

	private Integer movimientoStockId;
	private Articulo articulo;
	private int cantidad;
	private LocalDate fecha;
	private TipoMovimiento tipoMovimiento;
	private String encargado;
	private String autorizadoPor;
	private String destino;

	public MovimientoStock(){

	}

	public MovimientoStock(MovimientoStockDTO movimientoStock, Integer articuloId){
		if(movimientoStock.getMovimientoStockId() == null) {
			this.fecha = LocalDate.now();
		}else {
			this.fecha= movimientoStock.getFecha();
		}
		this.movimientoStockId= movimientoStock.getMovimientoStockId();
		
		this.articulo= ArticuloDAO.getInstancia().findById(articuloId);
		this.cantidad= movimientoStock.getCantidad();
	
		this.tipoMovimiento= TipoMovimiento.valueOf(movimientoStock.getTipoMovimiento());
		this.encargado= movimientoStock.getEncargado();
		this.autorizadoPor= movimientoStock.getAutorizadoPor();
		this.destino= movimientoStock.getDestino();
	}
	public Integer getMovimientoStockId() {
		return movimientoStockId;
	}

	public void setMovimientoStockId(Integer movimientoStockId) {
		this.movimientoStockId = movimientoStockId;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
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

	public MovimientoStockDTO toDTO() {

		MovimientoStockDTO movimiento = new MovimientoStockDTO();
		movimiento.setArticulo(this.getArticulo().toDTO());
		movimiento.setAutorizadoPor(this.getAutorizadoPor());
		movimiento.setCantidad(this.getCantidad());
		movimiento.setDestino(this.getDestino());
		movimiento.setFecha(this.getFecha());
		movimiento.setMovimientoStockId(this.getMovimientoStockId());
		movimiento.setTipoMovimiento(this.getTipoMovimiento().toString());
		movimiento.setEncargado(this.encargado);
		return movimiento;
	}

	public MovimientoStock moverStock(Integer loteId, Integer ubicacionId) {
		this.articulo.movimientoStock(this.cantidad, loteId, ubicacionId);
		return save();
	}
	
    public MovimientoStock save() {
		return MovimientoStockDAO.getInstancia().save(this);
    }
}

