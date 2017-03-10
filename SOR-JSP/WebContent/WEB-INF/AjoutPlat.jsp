<%@include file="Header.jsp"%>

<jsp:include page='Navigation.jsp'>
	<jsp:param name="plat" value="${true}" />
</jsp:include>

<div class="page-header">
	<h1>Ajout Plat</h1>
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

<form method="POST" action="AjoutPlat" enctype="multipart/form-data">
	<div class="form-group">
		<label for="plat">Nom du plat</label> <input type="text"
			class="form-control" name="nom" placeholder="Nom du plat" size="25"
			maxlength="25" minlength="4" required>
	</div>
	<div class="form-group">
		<label for="plat">Description</label> <input type="text"
			class="form-control" name="description" placeholder="Desciption"
			size="1000" maxlength="1000" required>
	</div>
	<div class="form-group">
		<label for="plat">Prix</label> <input type="number"
			class="form-control" name="prix" placeholder="Prix" min="0"
			max="99999" required>
	</div>
	<div class="form-group">
		<label for="plat">Photo</label> <input type="file" name="photo">
	</div>

	<p>
		<label for="pays">Choisisez le type de plat</label><br /> <select
			name="type" id="type">
			<c:forEach items="${groupes}" var="groupe">
				<option value="${groupe.getGroupe_id()}">${groupe.getGroupe_nom()}</option>
			</c:forEach>

		</select>
	</p>

	<div class="form-group validation-btn-group text-center">
		<button type="submit" class="btn btn-default">Valider</button>
		<button type="reset" class="btn btn-default">Remettre à zéro</button>
	</div>
</form>

<%@include file="Footer.jsp"%>
