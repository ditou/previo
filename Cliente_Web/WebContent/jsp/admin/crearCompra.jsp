<%@ page import="cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>

<%
List<OrdenCompraDTO> ordenes = new ArrayList<>();
ordenes = ComprasDelegate.getInstancia().findAllOrdenCompraPendientes();

List<String> proveedores = ComprasDelegate.getInstancia().getUltimosProveedores();
%>
<jsp:include page="./menu.jsp" />
<style>
p{
margin-bottom: 0px;}
</style>
<body style="padding-top: 70px;">
<div class="container">

	<div class="col-md-12">
      <div class="row">
      <div class="control-group" id="fields">
     
          <div class="controls">
           
           	 <label class="control-label" for="arti">
            		Indique las Órdenes que conformarán la compra, el proveedor y los precios.
         		 </label>
         		 
         		 <table class="table table-striped">
         		    
         		       <thead>
         		        <tr>
         		         <th scope="col"></th>
      <th scope="col">#</th>
      <th scope="col">Fecha</th>
      <th scope="col">Artículo</th>
      <th scope="col">Cantidad</th>
      <th scope="col">Precio</th>
       </tr>
      </thead>
        <tbody>
   
         		    	
    
                             <%for(OrdenCompraDTO orden : ordenes){ 
                             String descripcion = orden.getArticulo().getDescripcion() +" ("+orden.getArticulo().getPresentacion() + " " + orden.getArticulo().getTamanio() + orden.getArticulo().getUnidad() + ")";
                             %>
                             <tr>
        					<th scope="row">	<input type="checkbox" value="<%=orden.getOrdenCompraId()%>"></th>
        					<td><%=orden.getOrdenCompraId() %></td>
        						<td><%= orden.getFecha() %></td>
        						<td><%=descripcion %></td>
        						<td><%= orden.getCantidad() %></td>
        						<td><input style="width: 140px;" name="precio" class="precio" type="number" placeholder="Precio">
        						</td>
        						</tr>
           		<%} %>	
           		
           		
           </tbody>
           </table>
           <%if(!proveedores.isEmpty()) {
        	   %><br><b>
               Últimos proveedores:</b><br><%
           	for(String proveedor : proveedores){	
           %>

          <p>-<%=proveedor%></p>
        
           <%}} %>
    <br>
                                  <input class="form-control" id="proveedor" name="proveedor" type="text" placeholder="Proveedor">
           
          </div>
                  <button onclick="window.history.back()" class="btn" style="margin-top: 10px">Volver</button>
          <button  onclick="redirect()" class="btn" style="margin-top: 10px;" type="submit">Aceptar</button>
          
         
        </div>
      </div>
    </div>
</div>

<script>
function redirect(){
	var url = "../../crearCompraServlet?proveedor=" + document.getElementById('proveedor').value;
	
	if($('input[name=proveedor]')[0].value == ''){
		alert('Por favor complete todos los campos con datos válidos.');		
		return;
	}
	
	for(var i = 0; i < $("input[name=precio]").length; i++){
		if($("input[name=precio]")[i].value == '' || $("input[name=precio]")[i].value <= 0 || $("input[name=precio]")[i].value.indexOf('.') >= 0 || $("input[name=precio]")[i].value.indexOf(',') >= 0){
			alert('Por favor complete todos los campos con datos válidos.');		
			return;
		}

	}
	
	if($('input[type=checkbox]:checked').length > 0){
		for(i = 0; i < $('input[type=checkbox]').length; i++){
			url += "&id" + i + "=" + $('input[type=checkbox]')[i].value;
			url += "&precio" + i + "=" + $('input[name=precio]')[i].value;
		}
		window.location.href = url;
		}else{
			alert('No seleccionó ninguna órden para realizar la compra');
		}
}
</script>
<jsp:include page="./end.jsp" />

<script>
$('#compra').addClass('active');
</script>
</body>

