package model;

import dao.UbicacionDAO;
import dto.UbicacionDTO;

public class Ubicacion {

	private Integer ubicacionId;
	private int cantidad;
	private boolean libre;
	private String codigoUbicacion;

	public Ubicacion(){

	}

	public Ubicacion(UbicacionDTO ubicacion) { 
		
		this.cantidad = ubicacion.getCantidad();
		this.ubicacionId = ubicacion.getUbicacionId();
		this.libre = ubicacion.isLibre();
		this.codigoUbicacion = ubicacion.getCodigoUbicacion();
	} 	
	
	public UbicacionDTO toDTO() {
		
		UbicacionDTO ubicacion = new UbicacionDTO();
		ubicacion.setCantidad(this.getCantidad());
		ubicacion.setCodigoUbicacion(this.getCodigoUbicacion());
		ubicacion.setLibre(this.isLibre());
		ubicacion.setUbicacionId(this.getUbicacionId());
		
		return ubicacion;	
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public boolean isLibre() {
		return libre;
	}
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	public Integer getUbicacionId() {
		return ubicacionId;
	}
	public void setUbicacionId(Integer ubicacionId) {
		this.ubicacionId = ubicacionId;
	}
	public String getCodigoUbicacion() {
		return codigoUbicacion;
	}
	public void setCodigoUbicacion(String codigoUbicacion) {
		this.codigoUbicacion = codigoUbicacion;
	}

    public int actualizarStock(int valor) {
		boolean suma = valor > 0;
		if(suma) {
			this.cantidad += valor;
			valor = 0;
		}else {
			//Resta stock
			if(Math.abs(valor) > this.cantidad) {
				valor += this.cantidad;
				this.cantidad = 0;
			}else {
				this.cantidad += valor;
				valor = 0;
			}
		}
		
		save();
		return valor;
	
    }

	public Ubicacion save() {
		return UbicacionDAO.getInstancia().save(this);
	}

	public void asignarUbicacion(int cantidad) {
		this.setCantidad(cantidad);
		this.setLibre(false);
		save();
	}
}
