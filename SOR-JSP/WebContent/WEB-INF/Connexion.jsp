<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="connexion" value="${true}"/>
</jsp:include>

<div id="authentication-menu">
	<div class="page-header">
		<h1>Connexion</h1>
	</div>
	<form method="post" action="Connexion">
		<c:if test="${error != null}">
			<div class="alert alert-danger" role="alert">
				<strong>Erreur de connexion :</strong> ${error}
			</div>
		</c:if>
		<div class="form-group">
			<label for="user">Nom d'utilisateur</label> 
			<input type="text" class="form-control" name="user" placeholder="Nom d'utilisateur" size="25" maxlength="25" minlength="4" required>
		</div>
		<div class="form-group">
			<label for="password">Mot de passe</label>
			<input type="password" class="form-control" name="password" placeholder="Mot de passe" size="25" maxlength="25" minlength="8" required>
		</div>
		<div class="form-group validation-btn-group text-center">
			<button type="submit" class="btn btn-default">Valider</button>
			<button type="reset" class="btn btn-default">Remettre à zéro</button>
		</div>
	</form>
</div>

<%@include file="Footer.jsp" %>