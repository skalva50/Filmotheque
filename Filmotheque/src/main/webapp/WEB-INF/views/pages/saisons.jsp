<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class ="row">	
	<h1 class="text-center">${serie.titre}</h1>
</div>
<hr/>
<div class = "row">
	<div class ="col-md-6 text-center">
		<img src ="https://image.tmdb.org/t/p/w500${serie.affiche}" class="img-thumbnail img-responsive"/>
	</div>
	<div class ="col-md-6">
		<h4 class ="text-center">Titre Original: </h4>
			<p>${serie.titreOriginal}</p>	 
		<hr/>
		<h4 class ="text-center">Date de sortie: </h4>
			<p>${serie.dateSortie}</p>	 
		<hr/>
		<h4 class ="text-center">Résumé: </h4>
			<p> ${serie.resume}</p>			
		<hr/>		
		<h4 class ="text-center">Bande annonce: </h4>
		<c:if test="${serie.cleVideo != String.Empty}">		
			<iframe  width="100%" height="60%" src="https://www.youtube.com/embed/${serie.cleVideo}" frameborder="0" allowfullscreen></iframe>
		</c:if>	
		<c:if test="${serie.cleVideo == String.Empty}">		
			<p>Aucune bande annonce disponible pour cette serie</p>
		</c:if>	
	</div>
</div>
<hr/>
<h1 class="text-center">Saisons</h1>
<div class = "row">
	<table class="table table-hover">
		<thead>
		    <tr>
		        <th></th><th>Saison</th><th>Date sortie</th><th>Résumé</th>
		    </tr>
		</thead>
		<tbody>    
		    <c:forEach items="${serie.saison}" var="saison">		    		
				<c:url value="/episodes" var="url">
					<c:param name="idSaison" value="${saison.id}"/>
				</c:url>   	
		        <tr> 
		        	<td  width="10%">				
		        		<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${saison.affiche}" class="img-thumbnail img-responsive"/></a>    		        		
		        	</td>
		        	<td width="10%">Saison ${saison.numero}</td>
		        	<td width="10%">${saison.dateSortie}</td>
		        	<td>${saison.resume}</td>     	
		        </tr>		       	     	
		    </c:forEach>
	    </tbody>
	</table>  
</div>
<hr/>
<c:if test="${realisateurs.size()>0}">
	<h1 class="text-center">Realisation</h1>
	<c:forEach begin="0" end ="${realisateurs.size()/6}" var="i">	
		<div class="row">
			<c:forEach begin="0" end ="5" var="j">
				<div class="col-sm-4 col-md-2">
					<c:set var="k" scope="session" value="${(i*6)+j}"/>		
					<c:url value="/acteur" var="url">
						<c:param name="idActeur" value="${realisateurs[k].id}"/>
					</c:url>
					<c:if test="${k<realisateurs.size()}">								
						<div class="thumbnail">
							<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${realisateurs[k].photo}" alt="Photo indisponible" class="img-thumbnail img-responsive"/></a> 
						</div>
						<div class="caption">  						
				    		<h4><a href="${url}" class="titreClickable">${realisateurs[k].nom}</a></h4>			    		
			    		</div>
		    		</c:if>
		    	</div>	    
		    </c:forEach>
		</div>	    	   	 
		<hr/>
	</c:forEach>
</c:if>
<h1 class="text-center">Acteurs</h1>
<c:forEach begin="0" end ="${personnages.size()/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-4 col-md-2">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/acteur" var="url">
					<c:param name="idActeur" value="${personnages[k].id}"/>
				</c:url>
				<c:if test="${k<personnages.size()}">								
					<div class="thumbnail">
						<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${personnages[k].acteur.photo}" alt="Photo indisponible" class="img-thumbnail img-responsive"/></a> 
					</div>
					<div class="caption">  						
			    		<h4><a href="${url}" class="titreClickable">${personnages[k].acteur.nom}</a></h4>
			    		<p><a href="${url}" class="titreClickable">${personnages[k].nom}</a></p>
		    		</div>
	    		</c:if>
	    	</div>	    
	    </c:forEach>
	</div>	    	   	 
	<hr/>
</c:forEach>