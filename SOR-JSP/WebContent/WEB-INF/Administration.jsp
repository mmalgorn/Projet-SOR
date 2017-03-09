<%@include file="Header.jsp" %>

<jsp:include page='Navigation.jsp'>
    <jsp:param name="administration" value="${true}"/>
</jsp:include>

<div class="page-header">
	<h1>Administration</h1>
</div>

<div class="panel panel-default">
  <div class="panel-heading">Logs de connexion</div>
	<table id="logs-table">
		<tr>
			<th>Utilisateur</th>
			<th>Dernière connexion</th>
		<tr>
		<c:forEach items="${logs}" var="log">
			<tr>
				<td><samp>${log.getValue().getAdmin_user()}</samp></td>
				<td><samp>${log.getKey().getLog_date().toString()}</samp></td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@include file="Footer.jsp" %>