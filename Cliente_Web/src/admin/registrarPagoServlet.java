package admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessDelegate.ArticuloDelegate;
import businessDelegate.ClienteDelegate;
import businessDelegate.DepositoDelegate;
import businessDelegate.FacturacionDelegate;
import businessDelegate.PedidoDelegate;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.ClienteSmallDTO;
import dto.FacturaDTO;
import dto.MovimientoStockDTO;
import dto.PagoDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;

/**
 * Servlet implementation class crearPedidoServlet
 */
@WebServlet("/registrarPagoServlet")
public class registrarPagoServlet extends HttpServlet {
	private static final long serialVersionUID = 99L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrarPagoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String clienteId = request.getParameter("cliente");
			String canti = request.getParameter("cant");
			String facturaId = request.getParameter("factura");
			ClienteDTO cliente = ClienteDelegate.getInstancia().buscarCliente(Integer.valueOf(clienteId));
			PagoDTO pago = new PagoDTO();
			ClienteSmallDTO small = new ClienteSmallDTO();
			small.setClienteId(cliente.getClienteId());
			
			pago.setCliente(small);
			if(facturaId != null && !facturaId.equals("-1") ) {
				pago.setFactura(FacturacionDelegate.getInstancia().buscarFactura(Integer.valueOf(facturaId)));
			}
			
			pago.setTotal(new BigDecimal(canti));
			
			FacturacionDelegate.getInstancia().registrarPago(pago);
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("./jsp/admin/pedidos.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
