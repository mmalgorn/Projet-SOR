<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="plat" value="${true}"/>
</jsp:include>

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
	<tr>
		<th></th>
		<th>Plat</th>
		<th>Description</th>
		<th>Prix</th>
		<th class="th-right"><a href="AjoutPlat" class="btn btn-primary">Ajouter</a></td>
	</tr>
	<c:forEach items="${Plat}" var="plat">
		<tr>
			<td><a href="Image?ref=${plat.getPlat_id()}"><img class="plat-image" src="Image?ref=${plat.getPlat_id()}" alt=""></a></td>
			<td><strong>${plat.getPlat_nom()}</strong></td>
			<td>${plat.getPlat_description()}</td>
			<td>${plat.getPlat_prix()} &euro;</td>
			<td align="right"><a href="ModificationPlat?id=${plat.getPlat_id()}" class="btn btn-default">Modifier</a>
			<a href="SuppressionPlat?id=${plat.getPlat_id()}" class="btn btn-danger" onclick="return confirm('Voulez vous vraiment supprimer ${plat.getPlat_nom()}')">Supprimer</a></td>
		</tr>
	</c:forEach>
</table>

<%@include file="Footer.jsp" %>