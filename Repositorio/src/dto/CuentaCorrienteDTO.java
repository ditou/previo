package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CuentaCorrienteDTO implements Serializable{
	
	private static final long serialVersionUID = -8373335223408229111L;
	
	private Integer cuentaCorrienteId;
	private java.math.BigDecimal limiteCredito;
	private List<CuentaCorrienteItemDTO> items;
	private java.math.BigDecimal total;
	
	public CuentaCorrienteDTO() {
		this.items = new ArrayList<>();
	}

	public java.math.BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(java.math.BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public List<CuentaCorrienteItemDTO> getItems() {
		return items;
	}

	public void setItems(List<CuentaCorrienteItemDTO> items) {
		this.items = items;
	}

	public java.math.BigDecimal getTotal() {
		return total;
	}

	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}

	public Integer getCuentaCorrienteId() {
		return cuentaCorrienteId;
	}

	public void setCuentaCorrienteId(Integer cuentaCorrienteId) {
		this.cuentaCorrienteId = cuentaCorrienteId;
	}
}
