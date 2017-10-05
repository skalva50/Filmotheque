<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class ="row">		
	<h1 class="text-center">${acteur.nom}</h1>
</div>
<hr/>
<div class ="row">
	<div class ="col-md-6 text-center">
		<img src ="https://image.tmdb.org/t/p/w500${acteur.photo}" class="img-thumbnail img-responsive"/>
	</div>
	<div class ="col-md-6">
		<h4 class ="text-center">Né le: <fmt:formatDate type="date" value="${acteur.dateNaissance}" /> à ${acteur.lieuNaissance} </h4>		
		<hr/>		
		<h4 class ="text-center">Biographie: </h4>
		<p> ${acteur.biographie}</p>		
		<hr/>
		<h4 class ="text-center">Popularité: </h4>
		<p><fmt:formatNumber type="number" maxFractionDigits = "2" value = "${acteur.popularite}"/>/10</p>		
	</div>	
</div>
<hr/>
<h1 class="text-center">Films disponibles</h1>
<c:forEach begin="0" end ="${filmpersonnages.size()/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-4 col-md-2">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/films/filmDetails" var="url">
					<c:param name="idFilm" value="${filmpersonnages[k].film.id}"/>
				</c:url>
				<c:if test="${k<filmpersonnages.size()}">								
					<div class="thumbnail">
						<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${filmpersonnages[k].film.affiche}" alt="Photo indisponible" class="img-thumbnail img-responsive"/></a> 
					</div>
					<div class="caption">  						
			    		<h4><a href="${url}" class="titreClickable">${filmpersonnages[k].film.titre}</a></h4>			    		
		    		</div>
	    		</c:if>
	    	</div>	    
	    </c:forEach>
	</div>	    	   	 
	<hr/>
</c:forEach>
<h1 class="text-center">Series disponibles</h1>
<c:forEach begin="0" end ="${seriePersonnages.size()/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-4 col-md-2">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/series/saisons" var="url">
					<c:param name="idSerie" value="${seriePersonnages[k].serie.id}"/>
				</c:url>
				<c:if test="${k<seriePersonnages.size()}">								
					<div class="thumbnail">
						<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${seriePersonnages[k].serie.affiche}" alt="Photo indisponible" class="img-thumbnail img-responsive"/></a> 
					</div>
					<div class="caption">  						
			    		<h4><a href="${url}" class="titreClickable">${seriePersonnages[k].serie.titre}</a></h4>			    		
		    		</div>
	    		</c:if>
	    	</div>	    
	    </c:forEach>
	</div>	    	   	 
	<hr/>
</c:forEach>
