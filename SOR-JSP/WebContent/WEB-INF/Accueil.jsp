<%@include file="Header.jsp" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
        	<li><a href="#" title="Accueil">Accueil</a></li>
			  <li><a href="Menu" title="Menu">Menu</a></li>
			  <li><a href="Administration" title="Administration">Administration</a></li>
			  <li><a href="Connexion" title="Connexion">Connexion</a></li>
       	</ul>
	</div>
</nav>
<div class="page-header">
	<h1>La Samba du palet</h1>
</div>
<c:forEach items="${Menu}" var="menu">
	
	<a href="Menu?ref=${menu.getMenu_id() }">${menu.getMenu_nom()}</a>
	
</c:forEach>

<%@include file="Footer.jsp" %>
