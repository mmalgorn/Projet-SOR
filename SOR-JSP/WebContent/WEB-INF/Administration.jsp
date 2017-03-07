<%@include file="Header.jsp" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
        	<li><a href="Accueil" title="Accueil">Accueil</a></li>
			  <li><a href="Menu" title="Menu">Menu</a></li>
			  <li class="active"><a href="#" title="Administration">Administration</a></li>
			  <li><a href="Connexion" title="Connexion">Connexion</a></li>
       	</ul>
	</div>
</nav>
<div class="page-header">
	<h1>Page Menu</h1>
</div>
<% Admin admin = (Admin) session.getAttribute("admin");
	if(admin!=null){%> <%=admin.getAdmin_user()%>
	<% } else {%>
	<p>Veuillez vous connecter pour accéder à cette page</p>
<% } %>

<%@include file="Footer.jsp" %>