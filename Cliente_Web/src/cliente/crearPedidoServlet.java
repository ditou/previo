package cliente;

import java.io.IOException;
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
@WebServlet("/crearPedidoServlet")
public class crearPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crearPedidoServlet() {
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
        PedidoDTO pedido = new PedidoDTO();
        PedidoItemDTO item = new PedidoItemDTO();
        List<PedidoItemDTO> items = new ArrayList<>();
        Integer clienteId = LoginUtils.getInstancia().getClienteBySession(request.getRequestedSessionId());
        ClienteDTO cliente = ClienteDelegate.getInstancia().buscarCliente(clienteId);
        pedido.setCliente(cliente);
        while (parameterNames.hasMoreElements()) {
        	String paramName = parameterNames.nextElement();
            String parameter = request.getParameter(paramName);
            
            if(paramName.equals("direccion")) {
            	pedido.setDireccionEnvio(parameter);
            	
            }else if(paramName.contains("cant")) {
        		item.setCantidad(Integer.valueOf(parameter));
        		items.add(item);
            }else if(paramName.contains("art")) {
            	item = new PedidoItemDTO();
            	item.setArticulo(ArticuloDelegate.getInstancia().buscarArticulo(Integer.valueOf(parameter)));
            }

        }
        pedido.setItems(items);
        PedidoDelegate.getInstancia().altaPedido(pedido);
		response.sendRedirect("./jsp/cliente/misPedidos.jsp");
		}catch(Exception e) {
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
