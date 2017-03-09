<%@include file="Header.jsp" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="Accueil" title="Accueil">Accueil</a></li>
			<li><a href="Menu" title="Menu">Menu</a></li>
			<li><a href="Plat" title="Plat">Plat</a></li>
			<li class="active"><a href="#" title="Administration">Administration</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${admin != null}">
				<li><a>Bienvenue ${admin}</a></li>
			</c:if>
			<li><a href="Connexion" title="Connexion"> 
				<c:choose>
					<c:when test="${empty admin}">Connexion</c:when>
					<c:otherwise>Déconnexion</c:otherwise>
				</c:choose>
			</a></li>
		</ul>
	</div>
	<ul class="nav navbar-nav navbar-right">
		
	</ul>
</nav>

<p>Veuillez vous <a href="Connexion">connecter</a> pour accéder à cette page</p>

<%@include file="Footer.jsp" %>