package admin;

import java.io.IOException;
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
import businessDelegate.PedidoDelegate;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.MovimientoStockDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;

/**
 * Servlet implementation class crearPedidoServlet
 */
@WebServlet("/movimientoStockServlet")
public class movimientoStockServlet extends HttpServlet {
	private static final long serialVersionUID = 8L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public movimientoStockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String enca = request.getParameter("enca");
			String tipo = request.getParameter("tipo");
			String auth = request.getParameter("auth");
			String arti = request.getParameter("art");
			String lote = request.getParameter("lote");
			String ubi = request.getParameter("ubi");
			String canti = request.getParameter("cant");
			String dest = request.getParameter("dest");
			
			MovimientoStockDTO mov = new MovimientoStockDTO();
			mov.setEncargado(enca);
			mov.setTipoMovimiento(tipo);
			mov.setCantidad(Integer.valueOf(canti));
			mov.setAutorizadoPor(auth);
			mov.setDestino(dest);
			DepositoDelegate.getInstancia().altaMovimiento(mov, Integer.valueOf(arti), Integer.valueOf(lote), Integer.valueOf(ubi));
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
