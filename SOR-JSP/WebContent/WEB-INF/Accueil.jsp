<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="accueil" value="${true}"/>
</jsp:include>

<div class="page-header">
	<h1>Bienvenue � La Samba du palet</h1>
</div>
<img src="src/bistrot.jpg"  height="500" >

<p>Le bistrot la Samba du palet est un restaurant unique en son genre.
Venez d�couvrir de nouvelles saveur tout en �coutant les meuilleurs musiques.
</p>

<p>Tous nos plats sont �l�bor�s � partir de produits frais et de la r�gion. </p> 

<%@include file="Footer.jsp" %>
