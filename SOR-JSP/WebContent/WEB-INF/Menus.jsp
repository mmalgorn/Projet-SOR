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
			<th></th>
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
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@include file="Footer.jsp" %>
