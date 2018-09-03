<%@ page import="cliente.LoginUtils, java.util.*, java.math.BigDecimal, businessDelegate.*,businessDelegate.*,dto.*,exception.*" %>
<%
Integer clienteId = LoginUtils.getInstancia().getClienteBySession(request.getRequestedSessionId());
ClienteDTO cliente = ClienteDelegate.getInstancia().buscarCliente(clienteId);
BigDecimal saldo = BigDecimal.ZERO;
if(cliente != null){
	saldo = cliente.getCuentaCorriente().getTotal();
}
%>
<jsp:include page="./head.jsp" />

<header>
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
              <a class="nav-link" href="misPedidos.jsp" id="mispedidos">Mis Pedidos <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="nuevoPedido.jsp" id="nuevopedido">Nuevo Pedido</a>
            </li> 
               <li class="nav-item">
        		<a class="nav-link" href="verCC.jsp" id="vercc" style="font-weight: 600">Cuenta Corriente (Saldo: $<%=saldo%>)</a>
        	
            </li>
          
          </ul>
        </div>
        
        
      </nav>
    </header>

