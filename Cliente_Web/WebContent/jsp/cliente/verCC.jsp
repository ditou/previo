<%@ page import="cliente.LoginUtils,java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>


<%
Integer clienteId = LoginUtils.getInstancia().getClienteBySession(request.getRequestedSessionId());
CuentaCorrienteDTO cc = new CuentaCorrienteDTO();
if(clienteId == null){
	response.sendRedirect("./login.jsp");
}else{
	 ClienteDTO cliente = ClienteDelegate.getInstancia().buscarCliente(clienteId);
	 cc = cliente.getCuentaCorriente();
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
<div class="container" style="max-height: 450px; overflow:auto">
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Fecha</th>
      <th scope="col">Documento</th>
      <th scope="col">Debe</th>
      <th scope="col">Haber</th>
    </tr>
  </thead>
  <tbody>
    <% 
    for(CuentaCorrienteItemDTO item : cc.getItems()){ %>
    	            	
			  <tr>
			      <th scope="row"><%=item.getFecha()%></th>
			      <td>Factura N° <%= item.getFactura().getNumero() %></td>
			    	      <td><%=item.getFactura().getTotal() %></td>
			    <td></td>
		
			  </tr>
    	    <% if(item.getPagos() != null){ %>
	 
      			<%for(PagoDTO pago : item.getPagos()){ %>
				<tr>
				      <th scope="row"><%=pago.getFecha()%></th>
				      <td>Pago N° <%=pago.getPagoId()%></td>
				      
				    
				      <td></td>
				        <td><%=pago.getTotal()%></td>
				 </tr>	 			     
		       <%} %>
      		<%} %>
      		  
   		<% } %>
  </tbody>
</table>
</div> 
<div style="float:right; font-weight: 600; padding-right:150px">Saldo: $<%=cc.getTotal() %></div>
</body>

<script>
$('#vercc').addClass('active');
</script>
<jsp:include page="./end.jsp" />