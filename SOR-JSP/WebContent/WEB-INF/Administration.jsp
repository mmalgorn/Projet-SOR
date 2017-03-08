<%@include file="Header.jsp" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="Accueil" title="Accueil">Accueil</a></li>
			<li><a href="Menu?ref=all" title="Menu">Menu</a></li>
			<li><a href="Plat" title="Plat">Plat</a></li>
			<c:if test="${admin != null}">
				<li class="active"><a href="Administration" title="Administration">Administration</a></li>
			</c:if>
			<li><a href="Connexion" title="Connexion">Connexion</a></li>
		</ul>
		<c:if test="${admin != null}">
			<ul class="nav navbar-nav navbar-right">
				<li><a>Bienvenue ${admin}</a></li>
			</ul>
		</c:if>
	</div>
</nav>

<div class="page-header">
	<h1>Administration</h1>
</div>


<%@include file="Footer.jsp" %>