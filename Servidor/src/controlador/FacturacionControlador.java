package controlador;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dto.FacturaDTO;
import dto.PagoDTO;
import model.Cliente;
import model.Factura;
import model.Pago;

public class FacturacionControlador {
	private static FacturacionControlador instancia;

	private FacturacionControlador() {}
	
	public static FacturacionControlador getInstancia() {
		if (instancia == null) {
			instancia = new FacturacionControlador();
		}
		return instancia;
	}


    public void registrarPago(PagoDTO pago)  {
		Pago pagoReg = new Pago(pago);
		pagoReg.registrarPago();
	}


	public List<FacturaDTO> buscarFacturasPendientesDeCancelacionByCliente(Integer clienteId) {
		List<FacturaDTO> facturas = new ArrayList<FacturaDTO>();
		Cliente cliente = ClienteDAO.getInstancia().findById(clienteId);
		List<Factura> facturasPendientes = FacturaDAO.getInstancia().getFacturasPendienteDeCancelacionPorCliente(cliente);
		for (Factura facturaP : facturasPendientes) {
			facturas.add(facturaP.toDTO());
		}

		return facturas;
	}
	
	public FacturaDTO buscarFactura(Integer facturaId) {
        Factura factura = FacturaDAO.getInstancia().findById(facturaId);
		return factura != null ? factura.toDTO() : null;
	}
}
