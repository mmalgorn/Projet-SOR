<%@include file="Header.jsp" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
        	<li><a href="Accueil" title="Accueil">Accueil</a></li>
			  <li class="active"><a href="#" title="Menu">Menu</a></li>
			  <li ><a href="Administration" title="Administration">Administration</a></li>
			  <li><a href="Connexion" title="Connexion">Connexion</a></li>
       	</ul>
	</div>
</nav>

	<h1>Menu ${Menu.getMenu_nom()}</h1>

	<c:forEach items="${Plats}" var="Plat">
		<div class="panel panel-default">
			<div class="panel-body"><h2>${Plat.getPlat_nom()}</h2></div>
			<div class="panel-footer">
				<p>${Plat.getPlat_description()}</p>
			</div>
		</div>
	</c:forEach>
	
<%@include file="Header.jsp" %>