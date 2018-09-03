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

import businessDelegate.DepositoDelegate;

/**
 * Servlet implementation class crearPedidoServlet
 */
@WebServlet("/recepcionarCompraServlet")
public class recepcionarCompraServlet extends HttpServlet {
	private static final long serialVersionUID = 8L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recepcionarCompraServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<LocalDate> fechasVto = new ArrayList<>();
			List<Integer> nroLotes = new ArrayList<>();
			List<Integer> lotesId = new ArrayList<>();
			String id = "";
			Enumeration<String> parameterNames = request.getParameterNames();
			 while (parameterNames.hasMoreElements()) {
		        	String paramName = parameterNames.nextElement();
		            String parameter = request.getParameter(paramName);
		            if(paramName.equals("id")) {
		            	id = parameter;
		            }else {
		            	if(paramName.contains("loteid")) {
		            		lotesId.add(Integer.valueOf(parameter));
		            		nroLotes.add(null);
		            		fechasVto.add(null);
		            	}else if (paramName.contains("nroLote")) {
		            		nroLotes.add(Integer.valueOf(parameter));
		            		lotesId.add(null);
		            	}else if (paramName.contains("fecha")) {
		            		fechasVto.add(LocalDate.parse(parameter));
		            	}
		            }
			 }

			DepositoDelegate.getInstancia().recepcionarCompra(Integer.valueOf(id), fechasVto, nroLotes, lotesId);
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
