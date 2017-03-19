<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class ="row">		
	<h1 class="text-center">${episode.saison.serie.titre}</h1>
	<h1 class="text-center">Saison ${episode.saison.numero} Episode ${episode.numero}</h1>	
</div>
<hr/>
<div class ="row">
	<div class ="col-md-6 text-center">
		<img src ="https://image.tmdb.org/t/p/w500${episode.affiche}" class="img-thumbnail img-responsive"/>
	</div>
	<div class ="col-md-6">
		<h4 class ="text-center">Titre épisode: </h4>
		<p> ${episode.titre}</p>		
		<hr/>
		<h4 class ="text-center">Date de sortie: </h4>
			<p>${episode.dateSortie}</p>	 
		<hr/>
		<h4 class ="text-center">Résumé: </h4>
		<p> ${episode.resume}</p>		
		<hr/>		
		<h4 class ="text-center">Bande annonce: </h4>
		<c:if test="${episode.cleVideo != String.Empty}">		
			<iframe  width="100%" height="60%" src="https://www.youtube.com/embed/${episode.cleVideo}" frameborder="0" allowfullscreen></iframe>
		</c:if>	
		<c:if test="${episode.cleVideo == String.Empty}">		
			<p>Aucune bande annonce disponible pour cet épisode</p>
		</c:if>	
	</div>	
</div>
<hr/>
