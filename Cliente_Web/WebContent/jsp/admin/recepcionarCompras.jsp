<%@ page import="cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>

<%
List<CompraDTO> compras = new ArrayList<>();
compras = ComprasDelegate.getInstancia().findAllComprasPendientes();
%>
<jsp:include page="./menu.jsp" />
<body style="padding-top: 70px;">
<div class="container">

	<div class="col-md-12">
      <div class="row">
      <div class="control-group" id="fields">
     
          <div class="controls">
           
           	 <label class="control-label" for="arti">
            		Indique la compra que desea recepcionar
         		 </label>
                 <select class="form-control" name="art" id="idcli">
                             <%for(CompraDTO compra : compras){ %>
                <option value="<%=compra.getCompraId()%>"><%=compra.getCompraId() %></option>
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
	var value = document.getElementById('idcli').options[document.getElementById('idcli').selectedIndex];
	if(value == null)
		alert('Seleccione alguna opción!');
	window.location.href = "./registrarcompra.jsp?id=" + value.value;
}
</script>
<jsp:include page="./end.jsp" />

<script>
$('#recepcionar').addClass('active');
</script>
</body>

