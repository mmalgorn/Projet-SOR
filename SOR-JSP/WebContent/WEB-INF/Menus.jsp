<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="menu" value="${true}"/>
</jsp:include>

<div class="page-header">
	<h1>Nos menus</h1>
</div>

<table id="menu-table" class="table">
	<thead>
		<tr>
			<th></th>
			<th>Description</th>
			<th>Prix</th>
			<th class="th-right">
				<c:if test="${admin != null}">
					</th>
					<th class="th-right">
						<a href="AjoutMenu" class="btn btn-primary">Ajouter</a>
				</c:if>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${Menus}" var="menu">
			<tr>
				<th>${menu.getMenu_nom()}</th>
				<td>${menu.getMenu_description()}</td>
				<td>${menu.getMenu_prix()} &euro;</td>
				<td align="right"><a href="Menu?ref=${menu.getMenu_id()}" class="btn btn-success"
								role="button">Voir le menu</a></td>
				<td align="right">
					<c:if test="${admin != null}">
							<a href="ModificationMenu?id=${menu.getMenu_id()}" class="btn btn-default">Modifier</a>
							<a  href="SuppressionMenu?id=${menu.getMenu_id()}" class="btn btn-danger" onclick="return confirm('Voulez vous vraiment supprimer ${menu.getMenu_nom()}')">Supprimer</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@include file="Footer.jsp" %>
