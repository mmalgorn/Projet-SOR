<%@include file="Header.jsp" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="Accueil" title="Accueil">Accueil</a></li>
			<li class="active"><a href="Menu" title="Menu">Menu</a></li>
			<li><a href="Plat" title="Plat">Plat</a></li>
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
	<h1>Menu ${menu.getMenu_nom()}</h1>
</div>

<c:forEach items="${plats}" var="pg">
	<div class="media">
		<div class="media-left">
			<img class="plat-image" src="Image?ref=${pg.getKey().getPlat_id()}" alt="">
		</div>
		<div class="media-body">
			<h3 class="media-heading">${pg.getKey().getPlat_nom()}
				<small>${pg.getValue().getGroupe_nom()}</small>
			</h3>
			<p>${pg.getKey().getPlat_description()}</p>
		</div>
		<c:if test="${admin != null}">
			<div class="media-right">
				<a href="ModificationPlat?id=${pg.getKey().getPlat_id()}" class="btn btn-default">Modifier</a>
			</div>
			<div class="media-right">
				<a class="btn btn-danger">Supprimer</a>
			</div>
		</c:if>
	</div>
</c:forEach>

<%@include file="Footer.jsp" %>