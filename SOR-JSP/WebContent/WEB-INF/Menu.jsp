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

	<h1>Page Menu</h1>

	<c:forEach items="${Plat}" var="Plats">

		<p>${Plats.getPlat_nom()}</p>

	</c:forEach>
</body>
</html>