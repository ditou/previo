package dto;

import java.io.Serializable;

public class ClienteSmallDTO implements Serializable{

	private static final long serialVersionUID = 6373723456112948815L;
	private Integer clienteId;
	private String nombre;
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
