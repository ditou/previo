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
import businessDelegate.PedidoDelegate;
import dto.ClienteDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;

/**
 * Servlet implementation class crearPedidoServlet
 */
@WebServlet("/despacharPedidoServlet")
public class despacharPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 8L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public despacharPedidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String id = request.getParameter("id");
			String fecha = request.getParameter("fecha");
			PedidoDelegate.getInstancia().despacharPedido(Integer.valueOf(id), LocalDate.parse(fecha));
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
