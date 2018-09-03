package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CuentaCorriente")
public class CuentaCorrienteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer cuentaCorrienteId;
	private java.math.BigDecimal limiteCredito;
	@OneToMany(cascade = CascadeType.ALL)
	private List<CuentaCorrienteItemEntity> items;
	private java.math.BigDecimal total;

	public CuentaCorrienteEntity() {
		items = new ArrayList<>();
	}

	public Integer getCuentaCorrienteId() {
		return cuentaCorrienteId;
	}

	public void setCuentaCorrienteId(Integer cuentaCorrienteId) {
		this.cuentaCorrienteId = cuentaCorrienteId;
	}

	public java.math.BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(java.math.BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public List<CuentaCorrienteItemEntity> getItems() {
		return items;
	}

	public void setItems(List<CuentaCorrienteItemEntity> items) {
		this.items = items;
	}

	public java.math.BigDecimal getTotal() {
		return total;
	}

	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}

}
