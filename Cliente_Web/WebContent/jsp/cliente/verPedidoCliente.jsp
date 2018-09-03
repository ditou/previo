<%@ page import="cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*, java.time.LocalDate" %>
<%
PedidoDTO pedido = null;
Integer clienteId = LoginUtils.getInstancia().getClienteBySession(request.getRequestedSessionId());
String parameter = request.getParameter("id");
String redirect = null;
if(clienteId == null){
	redirect = "./login.jsp";
}else{

	if(parameter != null){
		pedido = PedidoDelegate.getInstancia().buscarPedido(Integer.valueOf(parameter), clienteId);
		if(pedido == null ){
			 redirect = "./misPedidos.jsp";
		}
	}else{
		redirect = "./misPedidos.jsp";	
	}
}

if(redirect != null){
	response.sendRedirect(redirect);
}else{

%>

<jsp:include page="./menu.jsp" />
<body style="padding-top: 70px;">
<div class="container">
<style>
label{
width: 200px;
}
</style>
	<div class="col-md-12">
      <h1>Pedido N° <%=pedido.getPedidoId() %> (<%=pedido.getEstado() %>)</h1> <br/>
      <div class="row">  
      <div class="control-group" id="fields">
 
          <div class="controls">
            <table>
            <thead>
    <tr>
      <th scope="col">Artículo</th>
      <th scope="col">Cantidad</th>
    </tr>
  </thead>
  <tbody>
          <%
          for(PedidoItemDTO item : pedido.getItems()){
        	ArticuloDTO art = item.getArticulo();
        	%>
<tr>       
             <td> <input style="width: 400px"  id="desc" name="desc" type="text" disabled value="<%=art.getDescripcion() +" ("+art.getPresentacion() + " " + art.getTamanio() + art.getUnidad() %>) $<%=art.getPrecioVenta() %>">
			</td>
			<td>
				<input style="width: 80px" type="number" disabled value="<%=item.getCantidad()%>">                              
                </td> 
  			  
        	</tr>
          
          <%}%>
          
         </tbody>
         </table>
          
          <br/>
           
           <label class="control-label" for="fecha">
			Total
          </label>
              <input id="date" name="total" type="text" disabled value="<%=pedido.getTotal() %>">
              
              <br/>
            <label class="control-label" for="fecha">
			Fecha de Entrega
          </label>
              <input id="date" name="fecha" type="text" disabled value="<%=pedido.getFechaEntrega() == null ? "---"  : pedido.getFechaEntrega() %>">
              <br/>
             
                  <label class="control-label" for="fecha">
			Observaciones
          </label>
              <input id="date" name="total" type="text" disabled value="<%=pedido.getAclaracion() != null && pedido.getAclaracion().trim() != ""  ? pedido.getAclaracion() : "---" %>">
          <br/>
          
              <label class="control-label" for="fecha">
			Dirección Entrega
          </label>
              <input id="date" name="total" type="text" disabled value="<%=pedido.getDireccionEnvio() %>">
          
        
              
       <br/>
            
          </div>
                  <button onclick="window.history.back()" class="btn" style="margin-top: 10px">Volver</button>
          
  
          <br>

        </div>
      </div>
    </div>
</div>
<%} %>
<jsp:include page="./end.jsp" />
</body>

