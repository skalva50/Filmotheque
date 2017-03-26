<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class ="row">	
	<h1 class="text-center">${saison.serie.titre}, Saison ${saison.numero}</h1>
</div>
<hr/>
<div class = "row">
	<div class ="col-md-6 text-center">
		<img src ="https://image.tmdb.org/t/p/w500${saison.affiche}" class="img-thumbnail img-responsive"/>
	</div>
	<div class ="col-md-6">
		<h4 class ="text-center">Date de sortie: </h4>
			<p>${saison.dateSortie}</p>	 
		<hr/>
		<h4 class ="text-center">Résumé: </h4>
			<p> ${saison.resume}</p>			
		<hr/>		
		<h4 class ="text-center">Bande annonce: </h4>	
		<c:if test="${saison.cleVideo != String.Empty}">		
			<iframe  width="100%" height="60%" src="https://www.youtube.com/embed/${saison.cleVideo}" frameborder="0" allowfullscreen></iframe>
		</c:if>	
		<c:if test="${saison.cleVideo == String.Empty}">		
			<p>Aucune bande annonce disponible pour cette saison</p>
		</c:if>
	</div>
</div>
<hr/>
<h1 class="text-center">Episodes</h1>
<div class = "row">
	<table class="table table-hover">
		<thead>
		    <tr>
		        <th></th><th>Episode</th><th>Titre</th><th>Résumé</th>
		    </tr>
		</thead>
		<tbody>    
		    <c:forEach items="${saison.episodes}" var="episode">
		    	<c:url value="/episode" var="url">
					<c:param name="idEpisode" value="${episode.id}"/>
				</c:url> 	   	
		        <tr> 
		        	<td  width="10%">				
		        		<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${episode.affiche}" class="img-thumbnail img-responsive"/></a>   		        		
		        	</td>
		        	<td width="10%">${episode.numero}</td>
		        	<td width="10%">${episode.titre}</td>
		        	<td>${episode.resume}</td>     	
		        </tr>		       	     	
		    </c:forEach>
	    </tbody>
	</table>  
</div>
