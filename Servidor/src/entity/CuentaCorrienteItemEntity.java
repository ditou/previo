package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CuentaCorrienteItem")
public class CuentaCorrienteItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer cuentaCorrienteItemId;
	private LocalDate fecha;
	@OneToOne
	private FacturaEntity factura;
	@OneToMany
	private List<PagoEntity> pagos;
	
	public CuentaCorrienteItemEntity() {
		pagos = new ArrayList<>();
	}


	public Integer getCuentaCorrienteItemId() {
		return cuentaCorrienteItemId;
	}

	public void setCuentaCorrienteItemId(Integer cuentaCorrienteItemId) {
		this.cuentaCorrienteItemId = cuentaCorrienteItemId;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public FacturaEntity getFactura() {
		return factura;
	}

	public void setFactura(FacturaEntity factura) {
		this.factura = factura;
	}

	public List<PagoEntity> getPagos() {
		return pagos;
	}

	public void setPagos(List<PagoEntity> pagos) {
		this.pagos = pagos;
	}

	
}
