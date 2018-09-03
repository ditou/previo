package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraDTO implements Serializable{

	private static final long serialVersionUID = 3156644184000169708L;

	private Integer compraId;
	private String proveedor;
	private LocalDate fecha;
	private List<CompraItemDTO> items;
	private boolean recepcionada;
	
	public CompraDTO() {
		this.items = new ArrayList<>();
	}
	
	public Integer getCompraId() {
		return compraId;
	}
	public void setCompraId(Integer compraId) {
		this.compraId = compraId;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public List<CompraItemDTO> getItems() {
		return items;
	}
	public void setItems(List<CompraItemDTO> items) {
		this.items = items;
	}

	public boolean isRecepcionada() {
		return recepcionada;
	}
	public void setRecepcionada(boolean recepcionada) {
		this.recepcionada = recepcionada;
	}
	
	

	
	
}
