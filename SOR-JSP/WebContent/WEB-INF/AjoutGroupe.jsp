<%@include file="Header.jsp"%>

<jsp:include page='Navigation.jsp'>
	<jsp:param name="groupe" value="${true}" />
</jsp:include>

<div class="page-header">
	<h1>Ajout groupe</h1>
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

<form method="post" action="AjoutModifGroupe">
	<div class="form-group">
		<label for="groupe">Nom du Groupe</label> <input type="text" class="form-control" name="nom"
			<c:if test="${grp != null}">
					value="${grp.getGroupe_nom()}"
				</c:if>
			placeholder="Nom du Groupe" size="25" maxlength="25" required>
	</div>

	<div class="form-group validation-btn-group text-center">
		<button type="submit" class="btn btn-default">Valider</button>
		<button type="reset" class="btn btn-default">Remettre à zéro</button>
	</div>
</form>

<%@include file="Footer.jsp"%>
