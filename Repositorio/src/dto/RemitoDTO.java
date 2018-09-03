package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RemitoDTO implements Serializable{

	private static final long serialVersionUID = 2736329313824913612L;
	
	private Integer remitoId;
	private ClienteDTO cliente;
	private List<RemitoItemDTO> items;
	private LocalDate fecha;
	
	public RemitoDTO(){
		this.items = new ArrayList<>();
	}
	public Integer getRemitoId() {
		return remitoId;
	}
	public void setRemitoId(Integer remitoId) {
		this.remitoId = remitoId;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public List<RemitoItemDTO> getItems() {
		return items;
	}
	public void setItems(List<RemitoItemDTO> items) {
		this.items = items;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}