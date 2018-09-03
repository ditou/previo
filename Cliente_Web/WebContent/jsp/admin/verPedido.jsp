<%@ page import="cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*, java.time.LocalDate" %>
<%
PedidoDTO pedido = null;

String parameter = request.getParameter("id");
if(parameter != null){
	pedido = PedidoDelegate.getInstancia().buscarPedido(Integer.valueOf(parameter));
}

if(pedido == null || !"PENDIENTE_DESPACHO".equals(pedido.getEstado())){
	response.sendRedirect("./pedidos.jsp");
}
%>

<jsp:include page="./menu.jsp" />
<body style="padding-top: 70px;">
<div class="container">

	<div class="col-md-12">
      <h1>Pedido N° <%=pedido.getPedidoId() %></h1> 
      <div class="row">
      <div class="control-group" id="fields">
 
          <div class="controls">
          
          <%
          for(PedidoItemDTO item : pedido.getItems()){
        	ArticuloDTO articulo = item.getArticulo();
        	%>
        	</br>
        	 <label class="control-label" for="arti">
			Artículo
          </label>
              <input id="desc" name="desc" type="text" value="<%=articulo.getDescripcion() +" ("+articulo.getPresentacion() + " " + articulo.getTamanio() + articulo.getUnidad() %>)" disabled>
                      <label class="control-label" for="arti">
			Cantidad
          </label>
              <input id="desc" name="desc" type="text" value="<%=item.getCantidad()%>" disabled>
                          <br/>    
                      Ubicación    
                 
  			<%
        	for(LoteDTO lote : articulo.getLotes()){
             	 for(UbicacionDTO ubicacion : lote.getUbicaciones()){
             	 %>
     			        	 <label class="control-label" for="ubi">
     			 
                   <input id="ubi" name="ubi" type="text" value="<%=ubicacion.getCodigoUbicacion()%>" disabled>
                   </label>

               
        	<%}}
          
          }
          
          
          %><br/>
            <form role="form" id="crear" autocomplete="off" action="../../despacharPedidoServlet">
            <label class="control-label" for="fecha">
			Fecha de Despacho
          </label>
              <input id="date" name="fecha" type="date" value="<%=LocalDate.now() %>">
              
              <input style="display: none;" type="text" name="id" value="<%=pedido.getPedidoId()%>"></input>
          </form>
         
              
       <br/>
            
          </div>
                  <button onclick="window.history.back()" class="btn" style="margin-top: 10px">Volver</button>
          <button onclick="document.getElementById('crear').submit();" class="btn" style="margin-top: 10px;" type="submit">Despachar</button>
  
          <br>

        </div>
      </div>
    </div>
</div>
<jsp:include page="./end.jsp" />
</body>

