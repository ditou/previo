package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ClienteObservacionDTO implements Serializable{

	private static final long serialVersionUID = 2529306571530570336L;
	
	private Integer clienteObservacionId;
	private String descripcion;
	private LocalDate fecha;
	
	public ClienteObservacionDTO() {
	
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getClienteObservacionId() {
		return clienteObservacionId;
	}

	public void setClienteObservacionId(Integer clienteObservacionId) {
		this.clienteObservacionId = clienteObservacionId;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}	
	
	
}
