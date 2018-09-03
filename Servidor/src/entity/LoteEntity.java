package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Lote")
public class LoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer loteId;
    private int nroLote;
    private LocalDate fechaVencimiento;
	@OneToMany
	private List<UbicacionEntity> ubicaciones;

    public LoteEntity() {
    	this.ubicaciones = new ArrayList<>();
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

	public List<UbicacionEntity> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<UbicacionEntity> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	

}
