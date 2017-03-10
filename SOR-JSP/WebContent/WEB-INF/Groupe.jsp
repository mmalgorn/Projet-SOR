<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="groupe" value="${true}"/>
</jsp:include>

<div class="page-header">
	<h1>Groupe</h1>
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
		
		<th>Groupe</th>
		<th class="th-right"><a href="AjoutModifGroupe" class="btn btn-primary">Ajouter</a></td>
	</tr>
	<c:forEach items="${Groupe}" var="groupe">
		<tr>
			<td><strong>${groupe.getGroupe_nom()}</strong></td>
			<td align="right"><a href="AjoutModifGroupe?id=${groupe.getGroupe_id()}" class="btn btn-default">Modifier</a>
				<a href="SuppressionGroupe?id=${groupe.getGroupe_id()}" class="btn btn-danger" onclick="return confirm('Voulez vous vraiment supprimer ${groupe.getGroupe_nom()}\nAttention tout les plats utilisant le groupes seront supprimer des menus')">Supprimer</a>
			</td>
		</tr>
	</c:forEach>
</table>

<%@include file="Footer.jsp" %>