<%@ page import="cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>

<%
String clienteId = request.getParameter("id");
if(clienteId == null){
	response.sendRedirect("./pedidos.jsp");
}
ClienteDTO cliente = ClienteDelegate.getInstancia().buscarCliente(Integer.valueOf(clienteId));
List<FacturaDTO> facturas = FacturacionDelegate.getInstancia().buscarFacturasPendientesDeCancelacionByCliente(Integer.valueOf(clienteId));
%>
<jsp:include page="./menu.jsp" />
<body style="padding-top: 70px;">

<script>
var deuda = <%=cliente.getCuentaCorriente().getTotal()%>
</script>
<div class="container">

	<div class="col-md-12">
      <div class="row">
      <div class="control-group" id="fields">
     
          <div class="controls">
            <form role="form" autocomplete="off" id="crear" action="../../registrarPagoServlet">
                   
                        
                           <br/>
           	 <label class="control-label" for="arti">
            		Cliente
         		 </label>
                 <select style="display: none;" class="form-control" name="cliente">
                             
                <option value="<%=cliente.getClienteId()%>"><%=cliente.getNombre() %> - Deuda: $<%= cliente.getCuentaCorriente().getTotal() %></option>
           	
            	
            	</select>
            	<br/>
            	
            	<input type="text" disabled value="<%=cliente.getNombre()%>"/>
            	<br/>
            		 <label class="control-label" for="arti">
            		Factura a Cancelar(Opcional)
         		 </label>
                
                 <select  class="form-control" name="factura">
                <option value="-1">(Seleccione)</option>
                <%for(FacturaDTO factura : facturas){ %>             
                <option value="<%=factura.getFacturaId()%>"><%=factura.getNumero() + " ($" + factura.getSaldo() + ")" %></option>
           
           		<% }%>
            	</select>
            	<br/>
                <input class="form-control" name="cant" type="number" placeholder="Saldo a Cancelar">
                
              
              
            </form>
            
          </div>
                  <button onclick="window.history.back()" class="btn" style="margin-top: 10px">Volver</button>
          <button onclick="validar()" class="btn" style="margin-top: 10px;" type="submit">Aceptar</button>
          
         
        </div>
      </div>
    </div>
</div>

<script>
function validar(){
	if($("input[type=number]")[0].value == '' || $("input[type=number]")[0].value <= 0){
		alert('Por favor complete todos los campos con datos válidos.');		
		return;
	}
	
	if($("input[type=number]")[0].value > deuda){
		alert('No es posible saldar ese monto.')
		return;
	}
		
	document.getElementById('crear').submit();
}
</script>
<jsp:include page="./end.jsp" />

<script>
$('#registrarpago').addClass('active');
</script>
</body>

