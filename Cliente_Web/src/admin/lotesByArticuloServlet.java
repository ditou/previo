package admin;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import businessDelegate.ArticuloDelegate;
import dto.ArticuloDTO;
import exception.GenericRemoteException;

/**
 * Servlet implementation class lotesByArticuloServlet
 */
@WebServlet("/lotesByArticuloServlet")
public class lotesByArticuloServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lotesByArticuloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articuloId = request.getParameter("articuloId");

		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			ArticuloDTO art = ArticuloDelegate.getInstancia().buscarArticulo(Integer.valueOf(articuloId));
			response.getWriter().write(new Gson().toJson(art));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GenericRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
