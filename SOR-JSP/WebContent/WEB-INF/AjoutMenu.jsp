<%@include file="Header.jsp"%>

	<h1>Ajout Menu</h1>

	<c:if test="${present == 1}">
		<p>Plat déja présent. Veuillez reccomencer.</p> 
	  	${present = "null"}
   	</c:if>
	<c:if test="${insert == 1}">
		<p>Plat ajouté avec succès</p>
		${insert = "null"}
	</c:if>
	<c:if test="${insert == 0}">
		<p>Erreur lors de la création du plat</p>
		${insert = "null"}
	</c:if>

	<form method="post" action="AjoutMenu">
		<div class="form-group">
			<label for="plat">Nom du Menu</label> <input type="text"
				class="form-control" name="nom" placeholder="Nom du Menu" size="25"
				maxlength="25" minlength="4" required>
		</div>
		<div class="form-group">
			<label for="plat">Description</label> <input type="text"
				class="form-control" name="description" placeholder="Desciption"
				size="1000" maxlength="1000" required>
		</div>
		<div class="form-group">
			<label for="plat">Prix</label> <input type="number"
				class="form-control" name="prix" placeholder="Prix" size="25"
				maxlength="5" required>
		</div>
		<p>
			Cochez les aliments que vous aimez manger :<br /> 
		
			<input type="checkbox" name="steak" id="steak" onchange='doAfficheListe(this,"div")' />
				<label for="steak">Steak haché</label>
				<input type="text" id="div" name="div" value="div" style="visibility:hidden"><br /> 
		</p>

		<div class="form-group validation-btn-group text-center">
			<button type="submit" class="btn btn-default">Valider</button>
			<button type="reset" class="btn btn-default">Remettre à zéro</button>
		</div>
	</form>

<%@include file="Footer.jsp"%>
