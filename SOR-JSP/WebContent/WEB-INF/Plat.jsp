<%@include file="Header.jsp" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="Accueil" title="Accueil">Accueil</a></li>
			<li><a href="Menu" title="Menu">Menu</a></li>
			<li class="active"><a href="Plat" title="Plat">Plat</a></li>
			<c:if test="${admin != null}">
				<li><a href="Administration" title="Administration">Administration</a></li>
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
	<h1>Plats</h1>
</div>

<table class="table">
	<c:forEach items="${Plat}" var="plat">
		<tr>
			<td><img class="plat-image thumbnail img-responsive" src="Image?ref=${plat.getPlat_id()}" alt=""></td>
			<td><strong>${plat.getPlat_nom()}</strong></td>
			<td>${plat.getPlat_description()}</td>
			<td>${plat.getPlat_prix()} &euro;</td>
		</tr>
	</c:forEach>
</table>

<%@include file="Footer.jsp" %>