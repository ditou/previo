<%@ page import="java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>

<%
List<ArticuloUbicacionDTO> articulos = DepositoDelegate.getInstancia().findAllUbicaciones();

%>

<jsp:include page="./menu.jsp" />
<body style="padding-top: 70px;">
<div class="container">
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col"># Ubicación</th>
      <th scope="col">Artículo</th>
      <th scope="col">Cód. Barras</th>
      <th scope="col"># Lote</th>
      <th scope="col">Vencimiento</th>
      <th scope="col">Cantidad</th>
    </tr>
  </thead>
  <tbody>
    <% 
    for(ArticuloUbicacionDTO art : articulos){ %>
    	
    <tr>
      <th scope="row"><%=art.getCodUbicacion()%></th>
      <td><%=art.getDescripcion() %></td>
          <td><%=art.getCodigoBarras()%></td>

      <td><%=art.getNroLote() %></td>
      <td><%=art.getVencimiento()%></td>
      <td><%= art.getCantidad() %></td>
      
      
    </tr>
     <% } %>
  </tbody>
</table>
</div>
</body>

<script>
$('#ubicaciones').addClass('active');
</script>
<jsp:include page="./end.jsp" />