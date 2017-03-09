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
	<h1>Plats</h1>
</div>

<c:if test="${error != null}">
	<div class="alert alert-danger" role="alert">
		<strong>Erreur :</strong> ${error}
	</div>
</c:if>
<c:if test="${success != null}">
	<div class="alert alert-success" role="alert">
		<strong>Succès :</strong> ${success}
	</div>
</c:if>

<table class="table">
	<c:forEach items="${Plat}" var="plat">
		<tr>
			<td><img class="plat-image thumbnail img-responsive" src="Image?ref=${plat.getPlat_id()}" alt=""></td>
			<td><strong>${plat.getPlat_nom()}</strong></td>
			<td>${plat.getPlat_description()}</td>
			<td>${plat.getPlat_prix()} &euro;</td>
			<td><a href="ModificationPlat?id=${plat.getPlat_id()}" class="btn btn-default">Modifier</a>
			<a href="SuppressionPlat?id=${plat.getPlat_id()}" class="btn btn-danger">Supprimer</a></td>
		</tr>
	</c:forEach>
</table>

<%@include file="Footer.jsp" %>