package model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dto.FacturaDTO;
import dto.FacturaItemDTO;

public class Factura {
	
	private Integer facturaId;
	private String letra;
	private int numero;
	private Cliente cliente;
	private java.math.BigDecimal iva;
	private LocalDate fecha;
	private java.math.BigDecimal saldo;
	private List<FacturaItem> items;

	private static java.math.BigDecimal IVA_21 = new BigDecimal(21);
	
	
	public Factura(){
		items= new ArrayList<>();
	}
	
	public Factura(FacturaDTO factura) {
		this();
		this.facturaId = factura.getFacturaId();
		this.letra = factura.getLetra();
		this.numero = factura.getNumero();
		if(this.facturaId == null)
			this.iva = BigDecimal.ZERO;
		else
			this.iva = factura.getIva();
		this.fecha = factura.getFecha();
		this.saldo = factura.getSaldo();
		this.cliente = ClienteDAO.getInstancia().findById(factura.getCliente().getClienteId());
		for (FacturaItemDTO item : factura.getItems()) {
			this.items.add(new FacturaItem(item));
		}
		
	}
	
	public FacturaDTO toDTO() {
		FacturaDTO factura = new FacturaDTO();
		factura.setFacturaId(this.getFacturaId());
		factura.setLetra(this.getLetra());
		factura.setNumero(this.getNumero());
		factura.setIva(this.getIva());
		factura.setTotal(this.getTotal());
		factura.setFecha(this.getFecha());
		factura.setSaldo(this.getSaldo());
		factura.setCliente(this.getCliente().toSmallDTO());
		for (FacturaItem item : this.items) {
			factura.getItems().add(item.toDTO());
		}
		
		return factura;
	}
	
	public List<FacturaItem> getItems() {
		return items;
	}

	public void setItems(List<FacturaItem> items) {
		this.items = items;
	}

	public Integer getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(Integer facturaId) {
		this.facturaId = facturaId;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public java.math.BigDecimal getIva() {
		return iva;
	}

	public void setIva(java.math.BigDecimal iva) {
		this.iva = iva;
	}

	public java.math.BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for(FacturaItem item : items) {
			total = total.add(new BigDecimal(item.getCantidad()).multiply(item.getPrecio())).add(iva);
		}
		return total;
	}


	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public java.math.BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(java.math.BigDecimal saldo) {
		this.saldo = saldo;
	}

    public void facturar(Pedido pedido) {
	    this.cliente = pedido.getCliente();
        this.fecha = LocalDate.now();
        this.iva = BigDecimal.ZERO;
        for(PedidoItem item : pedido.getItems()){
            FacturaItem fitem = new FacturaItem();
            fitem.setArticulo(item.getArticulo());
            fitem.setCantidad(item.getCantidad());
            fitem.setPrecio(item.getTotalBruto());
            this.items.add(fitem);
        }

        if(cliente.getCategoriaFiscal().equals(CategoriaFiscal.RESPONSABLE_INSCRIPTO)) {
            this.letra = "A";
        }else {
            this.letra = "B";
        }
        this.iva = this.getTotal().multiply(IVA_21).divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP);
        this.saldo = this.getTotal();
        this.numero = FacturaDAO.getInstancia().getProximoNumeroEmitirByLetra(this.letra);
        Factura saved = this.save();
        saved.getCliente().agregarMovimientoCC(saved);
    }

    public void registrarPago(java.math.BigDecimal cantidad) {
    	saldo = saldo.subtract( cantidad);
    	save();
    }

    public Factura save(){
		return FacturaDAO.getInstancia().save(this);
	}
}
