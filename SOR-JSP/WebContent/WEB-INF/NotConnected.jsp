<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="NotConnected" value="${true}"/>
</jsp:include>

<p>Veuillez vous <a href="Connexion">connecter</a> pour acc�der � cette page</p>

<%@include file="Footer.jsp" %>
