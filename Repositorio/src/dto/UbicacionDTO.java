package dto;

import java.io.Serializable;

public class UbicacionDTO implements Serializable{
	
	private static final long serialVersionUID = 7274546175423239827L;
	
	private Integer ubicacionId;
	private String codigoUbicacion;
	private int cantidad;
	private boolean libre;
	
	public boolean isLibre() {
		return libre;
	}
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
	public int getCantidad() {
		return cantidad;
	}

	
}
