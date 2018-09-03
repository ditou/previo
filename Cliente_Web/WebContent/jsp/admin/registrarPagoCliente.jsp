<%@ page import="cliente.LoginUtils, java.util.*, java.util.stream.*, java.math.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>

<%
List<ClienteDTO> clientes = new ArrayList<>();
clientes = ClienteDelegate.getInstancia().findAllClientes().stream().filter(c->c.getCuentaCorriente().getTotal().compareTo(new BigDecimal(0)) > 0).collect(Collectors.toList());
%>
<jsp:include page="./menu.jsp" />
<body style="padding-top: 70px;">
<div class="container">

	<div class="col-md-12">
      <div class="row">
      <div class="control-group" id="fields">
     
          <div class="controls">
           
           	 <label class="control-label" for="arti">
            		Indique el Cliente al que se le cargará un pago
         		 </label>
                 <select class="form-control" name="art" id="idcli">
                             <%for(ClienteDTO cliente : clientes){ %>
                <option value="<%=cliente.getClienteId()%>"><%=cliente.getNombre() %> - Deuda: $<%= cliente.getCuentaCorriente().getTotal() %></option>
           		<%} %>	
            	</select>
                
          </div>
                  <button onclick="window.history.back()" class="btn" style="margin-top: 10px">Volver</button>
          <button onclick="redirect();" class="btn" style="margin-top: 10px;" type="submit">Aceptar</button>
          
         
        </div>
      </div>
    </div>
</div>

<script>
function redirect(){
	if(document.getElementById('idcli').selectedIndex == -1)
		alert("Elija algún cliente");
	window.location.href = "./registrarpago.jsp?id=" + document.getElementById('idcli').options[document.getElementById('idcli').selectedIndex].value;
}
</script>
<jsp:include page="./end.jsp" />

<script>
$('#registrarpago').addClass('active');
</script>
</body>

