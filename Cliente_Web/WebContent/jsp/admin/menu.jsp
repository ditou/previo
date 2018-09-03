<%@ page import="cliente.LoginUtils, java.util.*, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>

<jsp:include page="./head.jsp" />
<%
List<CompraDTO>  recepciones = ComprasDelegate.getInstancia().findAllComprasPendientes();
List<OrdenCompraDTO> ordenes = ComprasDelegate.getInstancia().findAllOrdenCompraPendientes();
List<PedidoDTO> pedidos = PedidoDelegate.getInstancia().buscarPedidosPendientesDespacho();

boolean hayCompras = ordenes.size() > 0;
boolean hayRecepciones = recepciones.size() > 0;
boolean hayDespachos = pedidos.size() > 0;
%>
<header>
<style>
a b{
color: #FEE500;
}
</style>
      <!-- Fixed navbar -->
      
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary" style="background-color: #78a4da !important">
        <ul class="nav navbar-nav navbar-right">
        <li><span style="color: #081e2fa8; font-weight:600">Das Verrückte Lagerhaus</span></li>
        </ul>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a class="nav-link" href="pedidos.jsp" id="pedidos">Pedidos<%if(hayDespachos){ %><b>*</b><%} %> <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="movimientostock.jsp" id="movimientostock">Movimiento Stock <span class="sr-only">(current)</span></a>
            </li>
              <li class="nav-item">
              <a class="nav-link" href="registrarPagoCliente.jsp" id="registrarpago">Registrar Pago <span class="sr-only">(current)</span></a>
            </li>
                </li> 
              <li class="nav-item">
              <a class="nav-link" href="crearCompra.jsp" id="compra">Confeccionar Compra<%if(hayCompras){ %><b>*</b><%} %><span class="sr-only">(current)</span></a>
            </li>
                    </li>
              <li class="nav-item">
              <a class="nav-link" href="recepcionarCompras.jsp" id="recepcionar">Recepcionar Compra<%if(hayRecepciones){ %><b>*</b><%} %><span class="sr-only">(current)</span></a>
            </li>
            
                  <li class="nav-item">
              <a class="nav-link" href="ubicaciones.jsp" id="ubicaciones">Inventario<span class="sr-only">(current)</span></a>
            </li>
          </ul>
        </div>
      </nav>
    </header>

