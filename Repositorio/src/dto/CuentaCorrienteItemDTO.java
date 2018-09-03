package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CuentaCorrienteItemDTO implements Serializable{
	
	private static final long serialVersionUID = 7545602051320232440L;
	
	private Integer cuentaCorrienteItemId;
	private LocalDate fecha;
	private FacturaDTO factura;
	private List<PagoDTO> pagos;
	
	public CuentaCorrienteItemDTO() {
		pagos = new ArrayList<>();
	}
	

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public FacturaDTO getFactura() {
		return factura;
	}
	public void setFactura(FacturaDTO factura) {
		this.factura = factura;
	}
	public List<PagoDTO> getPagos() {
		return pagos;
	}
	public void setPagos(List<PagoDTO> pagos) {
		this.pagos = pagos;
	}


	public Integer getCuentaCorrienteItemId() {
		return cuentaCorrienteItemId;
	}


	public void setCuentaCorrienteItemId(Integer cuentaCorrienteItemId) {
		this.cuentaCorrienteItemId = cuentaCorrienteItemId;
	}	
}
