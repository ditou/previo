package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CompraDAO;
import dao.OrdenCompraDAO;
import dto.CompraDTO;
import dto.CompraItemDTO;

public class Compra {

	private Integer compraId;
	private String proveedor;
	private LocalDate fecha;
	private List<CompraItem> items;
	private boolean recepcionada;
	
	public Compra() {
		items = new ArrayList<>();
	}

	public Compra(CompraDTO compra) {
		this();
		this.compraId = compra.getCompraId();
		this.proveedor = compra.getProveedor();
		this.fecha = compra.getFecha();
		for (CompraItemDTO item : compra.getItems()) {
			this.items.add(new CompraItem(item));
		}

		this.recepcionada = compra.isRecepcionada();
	}

	public Compra(List<Integer> ordenesCompraId, String proveedor, List<BigDecimal> precios) {
		this();
		int i = 0;
		for(Integer id : ordenesCompraId) {
			OrdenCompra ordenCompra = OrdenCompraDAO.getInstancia().findById(id);
			this.items.add(new CompraItem(ordenCompra, precios.get(i)));
			ordenCompra.procesar();
			i++;
		}
		this.recepcionada = false;
		this.setFecha(LocalDate.now());
		this.setProveedor(proveedor);
	}

	public CompraDTO toDTO() {
		CompraDTO compra = new CompraDTO();
		compra.setCompraId(this.compraId);
		compra.setFecha(this.fecha);
		compra.setProveedor(this.proveedor);
		for (CompraItem item : this.items) {
			compra.getItems().add(item.toDTO());
		}
		compra.setRecepcionada(this.recepcionada);
		return compra;
	}

	public Compra save() {
		return CompraDAO.getInstancia().save(this);
	}

	public Integer getCompraId() {
		return compraId;
	}

	public void setCompraId(Integer compraId) {
		this.compraId = compraId;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<CompraItem> getItems() {
		return items;
	}

	public void setItems(List<CompraItem> items) {
		this.items = items;
	}


	public boolean isRecepcionada() {
		return recepcionada;
	}

	public void setRecepcionada(boolean recepcionada) {
		this.recepcionada = recepcionada;
	}

	public void recepcionar(List<LocalDate> fechas, List<Integer> nroLotes, List<Integer> lotesId) {
		this.recepcionada = true;
		int i = 0;
		for(CompraItem item : items) {
			if(lotesId.get(i) != null)
				item.recepcionar(lotesId.get(i));
			else
				item.recepcionar(fechas.get(i), nroLotes.get(i));
			
			i++;
		}
		save();
	}
	
}
