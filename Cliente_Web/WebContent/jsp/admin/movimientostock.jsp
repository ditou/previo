<%@ page import="cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>

<%
List<ArticuloDTO> articulos = new ArrayList<>();
articulos = ArticuloDelegate.getInstancia().findAllArticulos();
 
%>
<style>
.ctl{
margin-top: 10px;
margin-bottom: 2px;
} 
</style> 
<jsp:include page="./menu.jsp" />
<body style="padding-top: 70px;">
<div class="container">

	<div class="col-md-12">
      <div class="row">
      <div class="control-group" id="fields">
    
    
          <div class="controls">
            <form role="form" autocomplete="off" id="crear" action="../../movimientoStockServlet">
            		Tipo
                 <select class="form-control" name="tipo">
                                 <option value="VENCIMIENTO">Vencimiento</option>
                                    <option value="AJUSTE">Ajuste</option>
                                       <option value="ROTURA">Rotura</option>
                 
                 </select>
                 <br/>
                       <input class="form-control" name="enca" type="text" placeholder="Encargado">
                        <input class="form-control" name="auth" type="text" placeholder="Autorizado Por">
                                                <input class="form-control" name="dest" type="text" placeholder="Destino">
                        
                        
           	 <div class="ctl">
            		Artículo
         		 </div>
                 <select id="artik" class="form-control" name="art" onChange="changeArticulo(this)">
                             <%for(ArticuloDTO articulo : articulos){ 
                             if(articulo.getLotes().size() == 0) continue;
                             %>
                <option value="<%=articulo.getArticuloId()%>"><%=articulo.getDescripcion() +" ("+articulo.getPresentacion() + " " + articulo.getTamanio() + articulo.getUnidad() %>) </option>
           		<%} %>	
            	</select>
            	
             <div class="ctl">
            		Lote
         		 </div>
                 <select id="lotek" onChange="changeLote(this)" class="form-control" name="lote">
                             <%for(LoteDTO lote : articulos.get(0).getLotes()){ %>
                <option value="<%=lote.getLoteId()%>"><%=lote.getNroLote()%> (<%=lote.getFechaVencimiento() %>) </option>
           		<%} %>	
            	</select>
            	
            	 
            	   	 <div class="ctl">
            		Ubicación 
         		 </div>
            	    <select id="ubika" class="form-control" name="ubi">
                             <%
                             if(articulos.get(0).getLotes().size() > 0){
                             for(UbicacionDTO ubica : articulos.get(0).getLotes().get(0).getUbicaciones()){ %>
                <option value="<%=ubica.getUbicacionId()%>"><%=ubica.getCodigoUbicacion()%> </option>
           		<%}} %>	
            	</select>
            	
                <input style="margin-top: 8px;" class="form-control" name="cant" type="number" placeholder="Cantidad">
                
                
              
            </form>
            
          </div>
          <button onclick="realizarMov()" class="btn" style="margin-top: 10px;" type="submit">Aceptar</button>
          
         
        </div>
      </div>
    </div>
</div>
<jsp:include page="./end.jsp" />



<script>

function realizarMov(){
	for(var i = 0; i < $("input[type=number]").length; i++){
		if($("input[type=number]")[i].value == '' || $("input[type=number]")[i].value == 0 || $("input[type=number]")[i].value.indexOf('.') >= 0 || $("input[type=number]")[i].value.indexOf(',') >= 0){
			alert('Por favor complete todos los campos con datos válidos.');		
			return;
		}
		
		if($('#ubika').val() == null || $('#lotek').val() == null || $('#artik').val() == null){
			alert('Por favor complete todos los campos con datos válidos.');		
			return;
		}

	}
	
	
	document.getElementById('crear').submit();
	}
	
	function changeArticulo(wdg){
		$.ajax({
		    url: '../../lotesByArticuloServlet?articuloId=' + $("#artik").val(),
			success: function(respuesta) {
				var $lote = $("#lotek");
				var $el = $("#ubika");
				$lote.empty();
				$el.empty(); 
				console.log(respuesta);
				for(var i = 0; i < respuesta.lotes.length; i++){
					var o = respuesta.lotes[i];
					var fecha = o.fechaVencimiento.year + "-" + o.fechaVencimiento.month + "-" + o.fechaVencimiento.day;
					$lote.append($("<option></option>").attr("value", o.loteId).text(o.nroLote + " (" + fecha + ")"));
  
					if(i == 0){
						for(var u = 0; u < respuesta.lotes[i].ubicaciones.length; u++){
							$el.append($("<option></option>").attr("value", respuesta.lotes[i].ubicaciones[u].ubicacionId).text(respuesta.lotes[i].ubicaciones[u].codigoUbicacion));
						}
					}
				}
				
			}
		});
	}
	
	function changeLote(wdg){
		$.ajax({
		    url: '../../lotesByArticuloServlet?articuloId=' + $("#artik").val(),
			success: function(respuesta) {
				var $el = $("#ubika");
				$el.empty();
				console.log(respuesta);
				for(var i = 0; i < respuesta.lotes.length; i++){
					if(respuesta.lotes[i].loteId == $('#lote').val()){
						for(var u = 0; u < respuesta.lotes[i].ubicaciones.length; u++){
							$el.append($("<option></option>").attr("value", respuesta.lotes[i].ubicaciones[u].ubicacionId).text(respuesta.lotes[i].ubicaciones[u].codigoUbicacion));
						}
					} 
			
				}
				
			}
		});
	}

$('#movimientostock').addClass('active');
</script>
</body>

