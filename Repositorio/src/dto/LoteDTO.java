package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoteDTO implements Serializable {
	
	private static final long serialVersionUID = -758550574165365960L;
	
	private Integer loteId;
	private int nroLote;
	private LocalDate fechaVencimiento;
	private List<UbicacionDTO> ubicaciones;
	
	public LoteDTO() {
		this.ubicaciones = new ArrayList<>();
	}
	
	public Integer getLoteId() {
		return loteId;
	}
	public void setLoteId(Integer loteId) {
		this.loteId = loteId;
	}
	public Integer getNroLote() {
		return nroLote;
	}
	public void setNroLote(int nroLote) {
		this.nroLote = nroLote;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public List<UbicacionDTO> getUbicaciones() {
		return ubicaciones;
	}
	public void setUbicaciones(List<UbicacionDTO> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}	
	
	
	
}
