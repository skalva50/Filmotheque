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
