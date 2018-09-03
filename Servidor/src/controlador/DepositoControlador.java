package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CompraDAO;
import dao.UbicacionDAO;
import dto.ArticuloUbicacionDTO;
import dto.MovimientoStockDTO;
import dto.UbicacionDTO;
import model.Articulo;
import model.Compra;
import model.MovimientoStock;
import model.Ubicacion;

public class DepositoControlador {

	private static DepositoControlador instancia;

	private DepositoControlador() {
	}

	public static DepositoControlador getInstancia() {
		if (instancia == null) {
			instancia = new DepositoControlador();
		}
		return instancia;
	}

	public void recepcionarCompra(Integer compraId, List<LocalDate> fechas, List<Integer> nroLotes, List<Integer> lotesId) {
		Compra compra = CompraDAO.getInstancia().findById(compraId);
		compra.recepcionar(fechas, nroLotes, lotesId);
	}

	public int altaMovimiento(MovimientoStockDTO movimientoStockDTO, Integer articuloId, Integer loteId, Integer ubicacionId) {
		return new MovimientoStock(movimientoStockDTO, articuloId).moverStock(loteId, ubicacionId).getMovimientoStockId();
	}
	
	public List<ArticuloUbicacionDTO> findAllUbicaciones(){
		return Articulo.getArticulosWithUbicacion();
	}
}
