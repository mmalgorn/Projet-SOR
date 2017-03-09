<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav navbar-left">
			<li class="${param.accueil ? 'active' : ''}"><a href="Accueil" title="Accueil">Accueil</a></li>
			<li class="${param.menu ? 'active' : ''}"><a href="Menu" title="Menu">Menu</a></li>
			<li class="${param.groupe ? 'active' : ''}"><a href="Groupe" title="Groupe">Groupe</a></li>
			<li class="${param.plat ? 'active' : ''}"><a href="Plat" title="Plat">Plat</a></li>
			<c:if test="${admin != null}">
				<li class="${param.administration ? 'active' : ''}"><a href="Administration" title="Administration">Administration</a></li>
			</c:if>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${admin != null}">
				<li><a>Bienvenue ${admin}</a></li>
			</c:if>
			<li class="${param.connexion ? 'active' : ''}"><a href="Connexion" title="Connexion"> 
				<c:choose>
					<c:when test="${empty admin}">Connexion</c:when>
					<c:otherwise>Déconnexion</c:otherwise>
				</c:choose>
			</a></li>
		</ul>
	</div>
</nav>