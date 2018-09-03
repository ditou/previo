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
import businessDelegate.ComprasDelegate;
import businessDelegate.PedidoDelegate;
import dto.ClienteDTO;
import dto.CompraDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;

/**
 * Servlet implementation class crearPedidoServlet
 */
@WebServlet("/crearCompraServlet")
public class crearCompraServlet extends HttpServlet {
	private static final long serialVersionUID = 8L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crearCompraServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Enumeration<String> parameterNames = request.getParameterNames();
			List<Integer> ids = new ArrayList<>();
			String proveedor = "";
			List<BigDecimal> precios = new ArrayList<>();
	        while (parameterNames.hasMoreElements()) {
	        	String paramName = parameterNames.nextElement();
	            String parameter = request.getParameter(paramName);
	            if(paramName.equals("proveedor")) {
	            	proveedor = parameter;
	            	continue;
	            }else if(paramName.contains("id")){
		            ids.add(Integer.valueOf(parameter));
	            }else if (paramName.contains("precio")) {
	            	precios.add(new BigDecimal(parameter));
	            }
	        }
	        ComprasDelegate.getInstancia().crearCompra(ids, proveedor, precios);
            
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
