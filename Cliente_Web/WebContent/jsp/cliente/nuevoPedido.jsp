<%@ page import="cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>
<%
Integer clienteId = LoginUtils.getInstancia().getClienteBySession(request.getRequestedSessionId());
List<ArticuloDTO> articulos = new ArrayList<>();
if(clienteId == null){
	response.sendRedirect("./login.jsp");
}else{
	 articulos = ArticuloDelegate.getInstancia().findAllArticulos();
}
%>
<style>
.select {
width:  400px !important;
}

.form-control{
	height: 32px !important;
}

</style>
<jsp:include page="./menu.jsp" />
<body style="padding-top: 70px;">
<div class="container">

	<div class="col-md-12">
      <div class="row">
      <div class="control-group" id="fields">
          <h1>Nuevo Pedido</h1>
          <br/>  
          <label class="control-label" for="field1">
            Por favor completá todos los campos. Los precios no incluyen IVA.
          </label>
          <div class="controls">
            <form role="form" id="crear" autocomplete="off" action="../../crearPedidoServlet">
                              <input class="form-control" name="direccion" type="text" placeholder="Dirección Envío">
              	<br/>
              <div class="entry input-group col-xs-3">
   
             
                <select  class="form-control select" name="1art">
                             <%for(ArticuloDTO art : articulos){ %>
                <option value="<%=art.getArticuloId()%>"><%=art.getDescripcion() +" ("+art.getPresentacion() + " " + art.getTamanio() + art.getUnidad() %>) $<%=art.getPrecioVenta() %></option>
           		<%} %>	
            	</select>
            	
            	
                <input class="form-control" name="1cant" type="number" placeholder="Cantidad">
 
                
                
                <span class="input-group-btn">
              <button class="btn btn-success btn-add" type="button">
                                <span class="glyphicon glyphicon-plus">+</span>
                </button>
                </span>
              </div>
              
            </form>
            
          </div>
                  <button onclick="window.history.back()" class="btn" style="margin-top: 10px">Volver</button>
          <button onclick="realizarPedido()" class="btn" style="margin-top: 10px;" type="submit">Aceptar</button>
          <br>
          <small>
           	
            <span class="glyphicon glyphicon-plus gs"></span>
            Presioná el botón + para agregar otro artículo, cuando finalices presioná aceptar.
          </small>
        </div>
      </div>
    </div>
</div>
<jsp:include page="./end.jsp" />

<script>

function realizarPedido(){
	for(var i = 0; i < $("input[type=number]").length; i++){
		if($("input[type=number]")[i].value == '' || $("input[type=number]")[i].value <= 0 || $("input[type=number]")[i].value.indexOf('.') >= 0 || $("input[type=number]")[i].value.indexOf(',') >= 0){
			alert('Por favor complete todos los campos con datos válidos.');		
			return;
		}

	}
	
	if($('input[name=direccion]')[0].value == ''){
		alert('Por favor complete todos los campos con datos válidos.');		
		return;
	}
	
	document.getElementById('crear').submit();
}

$('#nuevopedido').addClass('active');
var i = 1;
$(function()
		{
		    $(document).on('click', '.btn-add', function(e)
		    {
		        e.preventDefault();

		        var controlForm = $('.controls form:first'),
		            currentEntry = $(this).parents('.entry:first'),
		            newEntry = $(currentEntry.clone()).appendTo(controlForm);
				i++;
		        newEntry.find('input').val('');
		        newEntry.find('select')[0].name = i + "art";
		        newEntry.find('input')[0].name = i + "cant";
		        controlForm.find('.entry:not(:last) .btn-add')
		            .removeClass('btn-add').addClass('btn-remove')
		            .removeClass('btn-success').addClass('btn-danger')
		            .html('<span class="glyphicon glyphicon-minus">-</span>');
		    }).on('click', '.btn-remove', function(e)
		    {
		  	$(this).parents('.entry:first').remove();

				e.preventDefault();
				return false;
			});
		});


</script>
</body>

