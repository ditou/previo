package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.CuentaCorrienteDTO;
import dto.CuentaCorrienteItemDTO;

public class CuentaCorriente {
	private Integer cuentaCorrienteId;
	private java.math.BigDecimal limiteCredito;
	private List<CuentaCorrienteItem> items;

	public CuentaCorriente() {
		items = new ArrayList<>();
	}

	public CuentaCorriente(CuentaCorrienteDTO cuentaCorriente) {
		this();
		if (cuentaCorrienteId != null)
			cuentaCorrienteId = cuentaCorriente.getCuentaCorrienteId();
		limiteCredito = cuentaCorriente.getLimiteCredito();
		for (CuentaCorrienteItemDTO item : cuentaCorriente.getItems()) {
			items.add(new CuentaCorrienteItem(item));
		}
	}

	public CuentaCorrienteDTO toDTO() {
		CuentaCorrienteDTO cuentaCorriente = new CuentaCorrienteDTO();
		cuentaCorriente.setCuentaCorrienteId(cuentaCorrienteId);
		cuentaCorriente.setLimiteCredito(limiteCredito);
		cuentaCorriente.setTotal(getTotal());
		for (CuentaCorrienteItem item : items) {
			cuentaCorriente.getItems().add(item.toDTO());
		}
		return cuentaCorriente;
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

	public List<CuentaCorrienteItem> getItems() {
		return items;
	}

	public void setItems(List<CuentaCorrienteItem> items) {
		this.items = items;
	}

	public java.math.BigDecimal getTotal() {
		java.math.BigDecimal facturado = items.stream().map(CuentaCorrienteItem::getFactura).map(Factura::getTotal).reduce(BigDecimal.ZERO,
				(a, b) -> a.add( b));
		java.math.BigDecimal cobrado = items.stream().map(CuentaCorrienteItem::getPagos).flatMap(List::stream).map(Pago::getTotal)
				.reduce(BigDecimal.ZERO, (a, b) -> a .add(b));
		return facturado.subtract( cobrado);
	}

	public void agregarMovimientoCC(Factura factura) {
		CuentaCorrienteItem item = new CuentaCorrienteItem();
		item.setFactura(factura);
		item.setFecha(LocalDate.now());
		if(this.getTotal().compareTo(BigDecimal.ZERO) < 0) {
			BigDecimal saldo = factura.getSaldo().subtract(this.getTotal());
			if(factura.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
				saldo = BigDecimal.ZERO;
			}
			factura.setSaldo(saldo);
		}
		this.items.add(item);
	}

	public void agregarMovimientoCC(Pago pago)  {
		if (pago.getFactura() != null) {
			for (CuentaCorrienteItem item : items) {
				if (item.getFactura().getFacturaId().equals(pago.getFactura().getFacturaId())) {
					item.getPagos().add(pago);
					item.getFactura().registrarPago(pago.getTotal());
					break;
				}
			}
		} else {
			java.math.BigDecimal aPagar = pago.getTotal();
			int posi = 0;

			while (aPagar.compareTo(BigDecimal.ZERO) > 0 && posi < this.items.size()) {

				CuentaCorrienteItem item = this.items.get(posi);
				if (item.getFactura().getSaldo().compareTo(BigDecimal.ZERO) > 0) {
					item.getPagos().add(pago);
					item.getFactura().registrarPago(aPagar);
					aPagar = aPagar.subtract(item.getFactura().getSaldo());
				}
				posi++;
			}
			if(posi == 0 || aPagar.compareTo(BigDecimal.ZERO) >= 0)
				System.err.println("Error inesperado al pagar");
		}
	}

}
