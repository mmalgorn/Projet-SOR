<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="menu" value="${true}"/>
</jsp:include>

<div class="page-header">
	<h1>Menu ${menu.getMenu_nom()}</h1>
</div>

<c:if test="${error != null}">
	<div class="alert alert-danger" role="alert">
		<strong>Erreur :</strong> ${error}
	</div>
</c:if>
<c:if test="${success != null}">
	<div class="alert alert-success" role="alert">
		<strong>Succ�s :</strong> ${success}
	</div>
</c:if>

<c:forEach items="${plats}" var="pg">
	<div class="media">
		<div class="media-left">
			<a href="Image?id=${pg.getKey().getPlat_id()}">
				<img class="plat-image" src="Image?id=${pg.getKey().getPlat_id()}" alt="">
			</a>
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
