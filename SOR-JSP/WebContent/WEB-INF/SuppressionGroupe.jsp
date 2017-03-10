<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="groupe" value="${true}"/>
</jsp:include>

<div class="page-header">
	<h1>Suppression Groupe</h1>
</div>

<form method="post" action="SuppressionGroupe">
		<div class="form-group">
		<p>
		<label for="pays">Veuillez choisir le groupe qui remplacera celui existant</label><br /> <select
			name="type" id="type">
			<c:forEach items="${Groupe}" var="groupe">
			
				<c:if test="${groupe.getGroupe_id() != id }">
				<option value="${groupe.getGroupe_id()}">${groupe.getGroupe_nom()}</option>
				</c:if>
			</c:forEach>

		</select>
	</p>
		</div>
		<input type="hidden" name="id" id="id" value="${id}"/>
		<div class="form-group validation-btn-group text-center">
			<button type="submit" class="btn btn-default" >Valider</button>
				
			<button type="reset" class="btn btn-default">Remettre à zéro</button>
		</div>
	</form>

<%@include file="Footer.jsp" %>