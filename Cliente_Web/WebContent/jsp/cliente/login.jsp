<jsp:include page="./head.jsp" />
 <link rel="stylesheet" href="../../css/login.css" >
 <form class="form-signin" action="../../loginServlet">
      <img class="mb-4" src="../../images/logo.png" alt="">
      <h1 class="h3 mb-3 font-weight-normal">¡Bienvenido!</h1>
      <%if(request.getParameter("badCredentials") != null) {%>
      	<p style="color:#FF0000">Usuario o Contraseña incorrecta.</p>
      <%} %>
      <label for="inputEmail" class="sr-only">Email</label>
      <input name="email" type="text" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus="" oninvalid="this.setCustomValidity('Ingresá un correo válido')">
      <label for="inputPassword" class="sr-only">Password</label>
      <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Contraseña" required="" oninvalid="this.setCustomValidity('Ingresá una contraseña')">
      <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
      <p class="mt-5 mb-3 text-muted">© 2018</p>
    </form>
<jsp:include page="./end.jsp" />
