<%@ page import="java.time.LocalDate, cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>

<%
String id = request.getParameter("id");
if(id == null){
	response.sendRedirect("./pedidos.jsp");
}
CompraDTO compra = ComprasDelegate.getInstancia().findCompraById(Integer.valueOf(id));
%>
<jsp:include page="./menu.jsp" />
<style>
label{
width: 130px;
padding-left: 10px;
}
</style>
<body style="padding-top: 70px;">

<div class="container">

	<div class="col-md-12">
      <div class="row">
      <div class="control-group" id="fields">
     
          <div class="controls">
          <h1>Compra N° <%=compra.getCompraId() %></h1>  
          <br/>
             <label class="control-label" for="arti">
			Proveedor
          </label>
          
              <input id="desc" name="desc" type="text" value="<%=compra.getProveedor() %>" disabled>      
        <label class="control-label" for="arti">
			Fecha
          </label>
          
              <input id="desc" name="desc" type="text" value="<%=compra.getFecha() %>" disabled>
      
             
    <br><br/>
             <%
          for(CompraItemDTO item : compra.getItems()){
          String descripcion = item.getOrdenCompra().getArticulo().getDescripcion() +" ("+item.getOrdenCompra().getArticulo().getPresentacion() + " " + item.getOrdenCompra().getArticulo().getTamanio() + item.getOrdenCompra().getArticulo().getUnidad() + ")";
        	  
          %>
        	  <label class="control-label" for="arti">
  			Artículo
            </label>
                <input id="desc" name="desc" type="text" value="<%= descripcion %>" disabled>
        	 <label class="control-label" for="arti">
  			Cantidad
            </label>
                <input id="desc" name="desc" type="text" value="<%= item.getOrdenCompra().getCantidad() %>" disabled>
        <br>
        	  <label class="control-label" for="arti">
  			Lote (Ubicación)
            </label>
         
        	  
        	    <select onchange="validar(this)" class="form-control" style="width: 207px !important; display: inline" name="ubicacion">
        	         <option value="-1">Nuevo Lote</option>
        	    
        	  <%
        	  
        	  for(LoteDTO lote : item.getOrdenCompra().getArticulo().getLotes()){
                  if(lote.getUbicaciones() == null){%>
                      <option value="<%=lote.getLoteId()%>">Lote N° <%=lote.getNroLote()%> - Vto: <%=lote.getFechaVencimiento()%> - Ubicación: Se asignará nueva ubicación</option>
<%
                  }else{
        		  for(UbicacionDTO ubicacion : lote.getUbicaciones()){%>
                                 <option value="<%=lote.getLoteId()%>">Lote N° <%=lote.getNroLote()%> - Vto: <%=lote.getFechaVencimiento()%> - Ubicación: <%=ubicacion.getCodigoUbicacion() %></option>
                    
                 
               
        	  <%}}}
        	  
        	  %></select>
        	   <input style="width: 181px; display:inline;" class="form-control opcional" name="nroLote" type="number" placeholder="N° Lote">
        	                <input style="width: 150px; display:inline;" class="opcional" id="date" name="fecha" type="date" value="<%=LocalDate.now() %>">
        	  <br/>
        	    <br/>  
        	 
        	  <%}%>
                
            
              
            
          </div>
                  <button onclick="window.history.back()" class="btn" style="margin-top: 10px">Volver</button>
          <button onclick="redirect()" class="btn" style="margin-top: 10px;" type="submit">Aceptar</button>
          
         
        </div>
      </div>
    </div>
</div>
<jsp:include page="./end.jsp" />

<script>
$('#recepcionar').addClass('active');

function validar(asd){
	var enabled = asd.value >= 1;
	for(i = 0; i < $('.opcional').length; i++){
		$('.opcional')[i].disabled = enabled;
		if(enabled){
			$('.opcional')[i].style.display = 'none';
		}else{
			$('.opcional')[i].style.display = 'inline';
		}
	}
}

function redirect(){
	for(var i = 0; i < $("input[name=nroLote]").length; i++){
		if($("input[name=nroLote]")[i].value == '' || $("input[name=nroLote]")[i].value <= 0 || $("input[name=nroLote]")[i].value.indexOf('.') > 0 || $("input[name=nroLote]")[i].value.indexOf(',') > 0){
			if($("select")[0].value == '-1'){
				alert('Por favor complete todos los campos con datos válidos.');		
				return;
			}
		}

	}
	
	var url = "../../recepcionarCompraServlet?id=" + <%=id%>;

	for(i = 0; i < $('select').length; i++){
		if($('select')[i].value >= 1){
			url += "&loteid" + i + "=" + $('select')[i].value;
		}else{
			url += "&nroLote" + i + "=" + $('input[name=nroLote]')[i].value;
			url += "&fecha" + i + "=" + $('input[name=fecha]')[i].value;
		}

	}
	window.location.href = url;
}
</script>
</body>

