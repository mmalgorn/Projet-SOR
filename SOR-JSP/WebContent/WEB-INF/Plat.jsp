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
	<thead>
		<tr>
			<th></th>
			<th>Plat</th>
			<th>Description</th>
			<th>Prix</th>
			<th class="th-right">
				<c:if test="${admin != null}">
					<a href="AjoutPlat" class="btn btn-primary">Ajouter</a>
				</c:if>
			</td>
		</tr>
	</thead>
	<c:forEach items="${Plat}" var="plat">
		<tr>
			<td><a href="Image?id=${plat.getPlat_id()}"><img class="plat-image" src="Image?id=${plat.getPlat_id()}" alt=""></a></td>
			<td><strong>${plat.getPlat_nom()}</strong></td>
			<td>${plat.getPlat_description()}</td>
			<td>${plat.getPlat_prix()} &euro;</td>
			<td align="right">
				<c:if test="${admin != null}">
					<a href="ModificationPlat?id=${plat.getPlat_id()}" class="btn btn-default">Modifier</a>
					<a href="SuppressionPlat?id=${plat.getPlat_id()}" class="btn btn-danger" onclick="return confirm('Voulez vous vraiment supprimer ${plat.getPlat_nom()}')">Supprimer</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>

<%@include file="Footer.jsp" %>