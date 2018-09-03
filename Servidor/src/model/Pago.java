package model;

import java.time.LocalDate;

import dao.ClienteDAO;
import dao.PagoDAO;
import dto.PagoDTO;

public class Pago {

	private Integer pagoId;
	private Cliente cliente;
	private java.math.BigDecimal total;
	private Factura factura;
	private LocalDate fecha;
	
	public Pago(){

	}

	
	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public Integer getPagoId() {
		return pagoId;
	}


	public void setPagoId(Integer pagoId) {
		this.pagoId = pagoId;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public java.math.BigDecimal getTotal() {
		return total;
	}

	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Pago(PagoDTO pago) {
		this.cliente= ClienteDAO.getInstancia().findById(pago.getCliente().getClienteId());
		if(pago.getFactura() != null)
			this.factura= new Factura(pago.getFactura());
		this.total= pago.getTotal();
		
		if(pago.getPagoId()==null) {
			this.fecha = LocalDate.now();
		}else {
			this.fecha = pago.getFecha();
		}
		this.pagoId = pago.getPagoId();
	}
	
	public PagoDTO toDTO() {
		PagoDTO pago = new PagoDTO();
		if(this.cliente != null)
			pago.setCliente(this.getCliente().toSmallDTO());
		if(this.getFactura() != null)
			pago.setFactura(this.getFactura().toDTO());
		pago.setFecha(this.getFecha());
		pago.setPagoId(this.getPagoId());
		pago.setTotal(this.getTotal());
		
		return pago;
	}

    public void registrarPago() {
		Pago saved = save();
		saved.getCliente().agregarMovimientoCC(saved);
    }

    public Pago save(){
		return PagoDAO.getInstancia().save(this);
	}
}
