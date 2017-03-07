<%@include file="Header.jsp" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<ul id="navigation">
  <li><a href="Accueil.jsp" title="Accueil">Accueil</a></li>
  <li><a href="Menu.jsp" title="Menu">Menu</a></li>
  <li><a href="Administration.jsp" title="Administration">Administration</a></li>
  <li><a href="#" title="Connexion">Connexion</a></li>
</ul>
	<h1>Page Connexion</h1>

	<form method="post" action="Connexion">
                <fieldset>
                    <legend>Connexion</legend>
    
                    <label for="user">Nom d'utilisateur <span class="requis"></span></label>
                    <input type="text" id="user" name="user" value="" size="25" maxlength="25" minlength="4" required/>
                    <br />
                    
                    <label for="password">Mot de passe </label>
                    <input type="password" id="password" name="password" value="" size="25" maxlength="25" minlength="8" required />
                    <br />
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
</body>
</html>