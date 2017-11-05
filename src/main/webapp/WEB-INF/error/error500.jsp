<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bad Request</title>
	<link rel="icon" type="image/png" href="/Filmotheque/resources/images/icone/icon.png"  />   
    <link href="/Filmotheque/resources/bootstrap/dist/css/bootstrap.min.css"  rel="stylesheet"></link>
    <link href="/Filmotheque/resources/bootstrap/dist/css/themeJournal.css"  rel="stylesheet"></link>    
   	<link href="/Filmotheque/resources/css/app.css"  rel="stylesheet"></link> 
</head>
<body>
<div class="row header">
	<div class= "col-md-2 text-center">
		<a href="/Filmotheque"><img src="/Filmotheque/resources/images/icone/film-reel.png" /></a>
	</div>
	<div class= "col-md-10">
        <h1 class="text-center titreSite">Nuage de Films</h1>
    </div>
</div>
<nav class="navbar navbar-default"> 
    <div class="collapse navbar-collapse" id ="menu" >
      <ul class="nav navbar-nav">
      	<li><a href="/Filmotheque" id="navHover">Accueil</a></li>
        <li><a href="/Filmotheque/films" id="navHover">Film</a></li>
        <li><a href="/Filmotheque/series" id="navHover">Serie</a></li>
        <li><a href="/Filmotheque/acteurs" id="navHover">Acteur</a></li> 
        <li><a href="/Filmotheque/realisateurs" id="navHover">Réalisateur</a></li>     
        <li><a href="/Filmotheque/administration" id="navHover">Administration</a></li>
        <li><a href="/Filmotheque/apropos" id="navHover">A propos</a></li>
        <li><a href="/Filmotheque/logout" id="navHover">Deconnexion</a></li>      
      </ul>      
    </div><!-- /.navbar-collapse -->

</nav>
<h1 class = "text-center">OOOPS ! Une erreur est survenue</h1>
<h3 class = "text-center">Type erreur : 500 Erreur interne</h3>
<h3 class = "text-center">Erreur du serveur</h3>
</body>
</html>