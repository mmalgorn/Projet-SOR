<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="NotConnected" value="${true}"/>
</jsp:include>

<div class="page-header">
	<h1>Non connecté</h1>
</div>

<p>Veuillez vous <a href="Connexion">connecter</a> pour accéder à cette page</p>

<%@include file="Footer.jsp" %>
