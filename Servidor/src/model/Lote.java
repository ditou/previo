package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.UbicacionDAO;
import dto.LoteDTO;
import dto.UbicacionDTO;

public class Lote {
	
	private Integer loteId;
	private int nroLote;
	private LocalDate fechaVencimiento;
	private List<Ubicacion> ubicaciones;

	public Lote(){
		this.ubicaciones = new ArrayList<>();
	}
	
	public Lote(LocalDate fechaVto, Integer nroLote, int cantidad) {
		this();
		this.nroLote = nroLote;
		this.fechaVencimiento = fechaVto;
		Ubicacion ubicacion = UbicacionDAO.getInstancia().findUbicacionLibre();
		if (ubicacion != null) {
			ubicacion.asignarUbicacion(cantidad);
			this.getUbicaciones().add(ubicacion);
		}
		
	}

	public Lote(LoteDTO lote) {
		this();
		this.loteId = lote.getLoteId();
		this.nroLote = lote.getNroLote();
		this.fechaVencimiento = lote.getFechaVencimiento();		
		if ((lote.getUbicaciones() != null && lote.getUbicaciones().size() > 0)) {

			for (UbicacionDTO item : lote.getUbicaciones()) {
				this.ubicaciones.add(new Ubicacion(item));
			}
		}
	}
	
	public LoteDTO toDTO () {
		
		LoteDTO lote = new LoteDTO();
		lote.setFechaVencimiento(this.getFechaVencimiento());
		lote.setLoteId(this.getLoteId());
		lote.setNroLote(this.getNroLote());
		
		for (Ubicacion item : this.ubicaciones) {
			lote.getUbicaciones().add(item.toDTO());
		}
		return lote;
	}
	
	public Integer getLoteId() {
		return loteId;
	}
	public void setLoteId(Integer loteId) {
		this.loteId = loteId;
	}
	public int getNroLote() {
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

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}	
	
	public int getStock() {
		return ubicaciones != null && ubicaciones.size() > 0 ? ubicaciones.stream().mapToInt(u->u.getCantidad()).sum() : 0;
	}
	
	public void movimientoStock(int cantidad, Integer ubicacionId) {
		Ubicacion ubicacionToUpdate = null;
		for(Ubicacion ubi : ubicaciones) {
			if(ubi.getUbicacionId().equals(ubicacionId))
				ubicacionToUpdate = ubi;
		}
		ubicacionToUpdate.actualizarStock(cantidad);
	}
	
	public void recepcionarStock(int cantidad) {
		this.ubicaciones.get(0).actualizarStock(cantidad);
	}
	
	public int despacharStock(int cantidad) {
			int posi = 0;
			while (cantidad != 0 && posi < ubicaciones.size()) {
				Ubicacion ubicacion = this.ubicaciones.get(posi);
				cantidad = ubicacion.actualizarStock(cantidad);
				posi++;
			}
	
		return cantidad;
	}

}
