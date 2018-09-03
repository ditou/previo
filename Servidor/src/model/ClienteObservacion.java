package model;

import java.time.LocalDate;

import dto.ClienteObservacionDTO;

public class ClienteObservacion {
	
	private Integer clienteObservacionId;
	private String descripcion;
	private LocalDate fecha;
	
	public ClienteObservacion(){

	}

	public ClienteObservacion(ClienteObservacionDTO observacion) {
		this.clienteObservacionId = observacion.getClienteObservacionId();
		this.descripcion = observacion.getDescripcion();
		this.fecha = LocalDate.now();
	}

	public ClienteObservacionDTO toDTO() {
		ClienteObservacionDTO clienteObservacion = new ClienteObservacionDTO();
		clienteObservacion.setClienteObservacionId(clienteObservacionId);
		clienteObservacion.setDescripcion(descripcion);
		clienteObservacion.setFecha(fecha);
		return clienteObservacion;
	}

	public Integer getClienteObservacionId() {
		return clienteObservacionId;
	}

	public void setClienteObservacionId(Integer clienteObservacionId) {
		this.clienteObservacionId = clienteObservacionId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}	
	
	
}
