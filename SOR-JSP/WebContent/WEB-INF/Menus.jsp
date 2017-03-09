<%@include file="Header.jsp" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="Accueil" title="Accueil">Accueil</a></li>
			<li class="active"><a href="#" title="Menu">Menu</a></li>
			<li><a href="Plat" title="Plat">Plat</a></li>
			<c:if test="${admin != null}">
				<li><a href="Administration" title="Administration">Administration</a></li>
			</c:if>
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
</nav>

<div class="page-header">
	<h1>Nos menus</h1>
</div>

<table id="menu-table" class="table">
	<thead>
		<tr>
			<th></th>
			<th>Description</th>
			<th>Prix</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${Menus}" var="menu">
			<tr>
				<th>${menu.getMenu_nom()}</th>
				<td>${menu.getMenu_description()}</td>
				<td>${menu.getMenu_prix()} &euro;</td>
				<td><a href="Menu?ref=${menu.getMenu_id()}" class="btn btn-success"
								role="button">Voir le menu</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@include file="Footer.jsp" %>