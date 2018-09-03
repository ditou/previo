package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.CuentaCorrienteItemDTO;
import dto.PagoDTO;

public class CuentaCorrienteItem {
	
	private Integer cuentaCorrienteItemId;
	private LocalDate fecha;
	private Factura factura;
	private List<Pago> pagos;
	
	public CuentaCorrienteItem() {
		pagos = new ArrayList<>();
	}
	
	public CuentaCorrienteItem(CuentaCorrienteItemDTO item) {
		this();
		cuentaCorrienteItemId = item.getCuentaCorrienteItemId();
		fecha = item.getFecha();
		factura = new Factura(item.getFactura());
		for(PagoDTO pago : item.getPagos()) {
			pagos.add(new Pago(pago));
		}
	}

	public CuentaCorrienteItemDTO toDTO() {
		CuentaCorrienteItemDTO item = new CuentaCorrienteItemDTO();
		item.setCuentaCorrienteItemId(cuentaCorrienteItemId);
		item.setFecha(fecha);
		item.setFactura(factura.toDTO());
		for(Pago pago : pagos) {
			item.getPagos().add(pago.toDTO());
		}
		return item;
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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	
	

}
