<%@include file="Header.jsp" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
        	<li class="active"><a href="#" title="Accueil">Accueil</a></li>
			<li><a href="Menu" title="Menu">Menu</a></li>
			<li><a href="Administration" title="Administration">Administration</a></li>
			<li><a href="Connexion" title="Connexion">Connexion</a></li>
       	</ul>
	</div>
	<ul class="nav navbar-nav navbar-right">
		<li></li>
	</ul>
</nav>
<div class="page-header">
	<h1>La Samba du palet</h1>
</div>

<div class="row">
	<c:forEach items="${Menu}" var="menu">
		<div class="col-sm-6 col-md-4">
			<div class="thumbnail">
				<img src="" alt="">
				<div class="caption">
					<h3>${menu.getMenu_nom()}</h3>
					<p><%-- ${menu.getMenu_description()} --%>Description</p>
					<p>
						<a href="Menu?ref=${menu.getMenu_id() }" class="btn btn-primary"
							role="button">Voir le menu</a>
					</p>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<%@include file="Footer.jsp" %>
