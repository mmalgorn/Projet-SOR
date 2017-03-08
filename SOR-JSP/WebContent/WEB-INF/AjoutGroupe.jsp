<%@include file="Header.jsp"%>


<body>
	<h1>Ajout Groupe</h1>

	<c:if test="${present == 1}">
	   	<p>Groupe déja présent. Veuillez reccomencer.</p> 
   	</c:if>
   	<c:if test="${absent == 1}">
		<p>Groupe absent de la base. Veuillez reccomencer</p>
	</c:if>
   	<c:if test="${successC == 1}">
		<p>Groupe ajouté avec succès</p>
 	</c:if>
   	<c:if test="${successM == 1}">
		<p>Groupe modifié avec succès</p>
	</c:if>
   	
	<form method="post" action="AjoutModifGroupe">
		<div class="form-group">
			<label for="groupe">Nom du Groupe</label> <input type="text"
				class="form-control" name="nom" 
				<c:if test="${grp != null}">
					value="${grp.getGroupe_nom()}"
				</c:if>
				placeholder="Nom du Groupe" size="25"
				maxlength="25" required>
		</div>
		
		<div class="form-group validation-btn-group text-center">
			<button type="submit" class="btn btn-default">Valider</button>
			<button type="reset" class="btn btn-default">Remettre à zéro</button>
		</div>
	</form>


</body>


<%@include file="Footer.jsp"%>
