<%@include file="Header.jsp"%>

	<h1>Ajout Menu</h1>

	<c:if test="${present == 1}">
		<p>Plat d�ja pr�sent. Veuillez reccomencer.</p> 
	  	${present = "null"}
   	</c:if>
	<c:if test="${insert == 1}">
		<p>Plat ajout� avec succ�s</p>
		${insert = "null"}
	</c:if>
	<c:if test="${insert == 0}">
		<p>Erreur lors de la cr�ation du plat</p>
		${insert = "null"}
	</c:if>




	<form method="post" action="AjoutMenu">
		<div class="form-group">
			<label for="plat">Nom du Menu</label> <input type="text"
				class="form-control" name="nom" placeholder="Nom du Menu" size="25" value="${menu.getMenu_nom()}"
				maxlength="25" minlength="4" required>
		</div>
		<div class="form-group">
			<label for="plat">Description</label> <input type="text"
				class="form-control" name="description" placeholder="Desciption" value="${menu.getMenu_description()}"
				size="1000" maxlength="1000" required>
		</div>
		<div class="form-group">
			<label for="plat">Prix</label> <input type="number"
				class="form-control" name="prix" placeholder="Prix" size="25" value="${menu.getMenu_prix()}"
				maxlength="5" required>
		</div>
		<p>
			Cochez les plat que vous voulez rajouter au menu<br /> 
			<table>
			<tr><th>Plat</th><th>Groupe du plat</th></tr>
			<c:set var="i" value="0"/>
			<c:forEach items="${Plat}" var="plat">
					<c:set var="comp" value="${plat.getPlat_id()}"/>
					<c:set var="grp" value="0"/>
					<c:if test="${comp > i }">
					<c:set var="i" value="${plat.getPlat_id()}"/>
					</c:if>
			<tr>
				<td><input type="checkbox" name="${plat.getPlat_id()}" id="${plat.getPlat_id()}" onchange='doAfficheListe(this,"type${plat.getPlat_id()}")' 
				<c:forEach items="${menuPlat}" var="platGroupe">
					<c:if test="${platGroupe.getKey().getPlat_id() == plat.getPlat_id() }"> 
						checked
						<c:set var="grp" value="${platGroupe.getValue().getGroupe_id()}"/>
					</c:if>
				</c:forEach>
				>
				<label for="${plat.getPlat_id()}">${plat.getPlat_nom()}</label></td>
				<td>
				<select	name="type${plat.getPlat_id()}" id="type${plat.getPlat_id()}" 
				<c:if test="${grp ==0 }">
				style="visibility:hidden"
				</c:if>
				>
				<c:forEach items="${Groupe}" var="groupe">
							<option
								<c:choose>
    								<c:when test="${grp != 0}">
       									<c:if test="${groupe.getGroupe_id() == grp}">
											selected
										</c:if>						
    								</c:when>
    								<c:otherwise>
 		 								<c:if test="${groupe.getGroupe_id() == plat.getPlat_id_groupe()}">
											selected
										</c:if>						
    								</c:otherwise>
								</c:choose>
								
								value="${groupe.getGroupe_id()}">${groupe.getGroupe_nom()}</option>
						</c:forEach>
			
				</select>	
			</c:forEach>
			</table>
		</p>
		<input type="hidden" name="i" id="i" value="${i}">
		<div class="form-group validation-btn-group text-center">
			<button type="submit" class="btn btn-default">Valider</button>
			<button type="reset" class="btn btn-default">Remettre � z�ro</button>
		</div>
	</form>

<%@include file="Footer.jsp"%>