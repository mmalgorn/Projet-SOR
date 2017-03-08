<%@include file="Header.jsp"%>


<body>
	<h1>Ajout Plat</h1>

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
   	
   	
   	
	
	<form method="post" action="AjoutPlat">
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
				class="form-control" name="prix" placeholder="Prix" size="25"
				maxlength="5" required>
		</div>
		<div class="form-group">
			<label for="plat">Photo</label> <input type="text"
				class="form-control" name="photo" placeholder="Photo" size="25"
				maxlength="25" required>
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


</body>


<%@include file="Footer.jsp"%>
