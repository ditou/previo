<%@ page import="cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>


<%
Integer clienteId = LoginUtils.getInstancia().getClienteBySession(request.getRequestedSessionId());
List<PedidoDTO> pedidos = new ArrayList<>();
if(clienteId == null){
	response.sendRedirect("./login.jsp");
}else{
	 pedidos = PedidoDelegate.getInstancia().buscarPedidosByCliente(clienteId);
}
%>
<style>
.boton {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
}
</style>

<jsp:include page="./menu.jsp" />
<body style="padding-top: 70px;">
<div class="container">
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Fecha Creación</th>
      <th scope="col">Fecha Entrega</th>
      <th scope="col">Total</th>
      <th scope="col">Estado</th>
      <th scope="col">Observaciones</th>
    </tr>
  </thead>
  <tbody>
    <% 
    for(PedidoDTO pedido : pedidos){ %>
    	
    <tr>
      <th scope="row"><a href="./verPedidoCliente.jsp?id=<%=pedido.getPedidoId()%>"><%=pedido.getPedidoId()%></a></th>
      <td><%=pedido.getFechaCreacion() %></td>
      <td><% out.print(pedido.getFechaEntrega()!=null ? pedido.getFechaEntrega() : "---"); %></td>
      <td><%=pedido.getTotal() %></td>
      <td><%=pedido.getEstado() %></td>
      <td><% out.print(pedido.getAclaracion()!=null ? pedido.getAclaracion() : ""); %></td>
      
      
    </tr>
     <% } %>
  </tbody>
</table>
</div>
<div style="float:right; padding-right:150px"><a class="nav-link boton" href="nuevoPedido.jsp" id="nuevopedido">Nuevo Pedido</a></div>
</body>

<script>
$('#mispedidos').addClass('active');
</script>
<jsp:include page="./end.jsp" />