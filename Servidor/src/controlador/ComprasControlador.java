package controlador;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import dao.CompraDAO;
import dao.OrdenCompraDAO;
import dto.CompraDTO;
import dto.OrdenCompraDTO;
import model.Compra;
import model.OrdenCompra;

public class ComprasControlador {
	private static ComprasControlador instancia;

	private ComprasControlador() {
	
	}

	public static ComprasControlador getInstancia() {
		if (instancia == null) {
			instancia = new ComprasControlador();
		}
		return instancia;
	}

	public void crearCompra(List<Integer> ordenesCompraId, String proveedor, List<BigDecimal> precios) {
		Compra compra = new Compra(ordenesCompraId, proveedor, precios);
		compra.save();
	}

	public List<OrdenCompraDTO> findAllOrdenCompraPendientes() {
		List<OrdenCompra> ordenes = OrdenCompraDAO.getInstancia().findAllOrdenCompraPendientes();
		return ordenes.stream().map(OrdenCompra::toDTO).collect(Collectors.toList());
	}

	public List<CompraDTO> findAllComprasPendientes() {
		List<Compra> compras = CompraDAO.getInstancia().findAllComprasPendientes();
		return compras.stream().map(Compra::toDTO).collect(Collectors.toList());
	}

	public CompraDTO findCompraById(Integer compraId) {
		return CompraDAO.getInstancia().findById(compraId).toDTO();
	}
	
	public List<String> getUltimosProveedores(){
		return CompraDAO.getInstancia().getUltimosProveedores();
	}
}
