package entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ClienteObservacion")
public class ClienteObservacionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer clienteObservacionId;
	private String descripcion;
	private LocalDate fecha;

	public ClienteObservacionEntity() {

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
