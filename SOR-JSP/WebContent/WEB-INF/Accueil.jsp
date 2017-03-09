<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="accueil" value="${true}"/>
</jsp:include>

<div class="page-header">
	<h1>Bienvenue à La Samba du palet</h1>
</div>
<img src="src/bistrot.jpg"  height="500" >

<p>Le bistrot la Samba du palet est un restaurant unique en son genre.
Venez découvrir de nouvelles saveur tout en écoutant les meuilleurs musiques.
</p>

<p>Tous nos plats sont éléborés à partir de produits frais et de la région. </p> 

<%@include file="Footer.jsp" %>
