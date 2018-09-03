<%@ page import="java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>

<%
List<PedidoDTO> pedidos = new ArrayList<>();
pedidos = PedidoDelegate.getInstancia().buscarPedidosPendientesDespacho();
%>

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
      <th scope="row"><a href="./verPedido.jsp?id=<%=pedido.getPedidoId()%>"><%=pedido.getPedidoId()%></a></th>
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
</body>

<script>
$('#pedidos').addClass('active');
</script>
<jsp:include page="./end.jsp" />