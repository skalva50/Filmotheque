<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class ="row">	
	<h1 class="text-center">${film.titre}</h1>
</div>
<hr/>
<div class ="row">
	<div class ="col-md-6 text-center">
		<img src ="https://image.tmdb.org/t/p/w500${film.affiche}" class="img-thumbnail img-responsive"/>
	</div>
	<div class ="col-md-6">
		<h4 class ="text-center">Titre Original: </h4>
			<p>${film.titreOriginal}</p>	 
		<hr/>
		<h4 class ="text-center">Résumé: </h4>
			<p> ${film.resume}</p>			
		<hr/>
		Chemin: ${film.fichier.chemin}<br/>	
		<hr/>
		<h4 class ="text-center">Bande annonce: </h4>	
		<c:if test="${film.cleVideo != String.Empty}">		
			<iframe  width="100%" height="60%" src="https://www.youtube.com/embed/${film.cleVideo}" frameborder="0" allowfullscreen></iframe>
		</c:if>	
		<c:if test="${film.cleVideo == String.Empty}">		
			<p>Aucune bande annonce disponible pour ce film</p>
		</c:if>	
	</div>	
</div>
<hr/>
