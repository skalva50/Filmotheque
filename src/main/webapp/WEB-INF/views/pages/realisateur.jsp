<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class ="row">		
	<h1 class="text-center">${realisateur.nom}</h1>
</div>
<hr/>
<div class ="row">
	<div class ="col-md-6 text-center">
		<img src ="https://image.tmdb.org/t/p/w500${realisateur.photo}" class="img-thumbnail img-responsive"/>
	</div>
	<div class ="col-md-6">
		<h4 class ="text-center">Né le: <fmt:formatDate type="date" value="${realisateur.dateNaissance}" /> à ${realisateur.lieuNaissance} </h4>		
		<hr/>		
		<h4 class ="text-center">Biographie: </h4>
		<p> ${realisateur.biographie}</p>		
		<hr/>
		<h4 class ="text-center">Popularité: </h4>
		<p><fmt:formatNumber type="number" maxFractionDigits = "2" value = "${realisateur.popularite}"/>/10</p>		
	</div>	
</div>
<hr/>
<h1 class="text-center">Films disponibles</h1>
<c:forEach begin="0" end ="${films.size()/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-4 col-md-2">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/films/filmDetails" var="url">
					<c:param name="idFilm" value="${films[k].id}"/>
				</c:url>
				<c:if test="${k<films.size()}">								
					<div class="thumbnail">
						<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${films[k].affiche}" alt="Photo indisponible" class="img-thumbnail img-responsive"/></a> 
					</div>
					<div class="caption">  						
			    		<h4><a href="${url}" class="titreClickable">${films[k].titre}</a></h4>			    		
		    		</div>
	    		</c:if>
	    	</div>	    
	    </c:forEach>
	</div>	    	   	 
</c:forEach>
<hr/>
<h1 class="text-center">Series disponibles</h1>
<c:forEach begin="0" end ="${series.size()/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-4 col-md-2">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/series/saisons" var="url">
					<c:param name="idSerie" value="${series[k].id}"/>
				</c:url>
				<c:if test="${k<series.size()}">								
					<div class="thumbnail">
						<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${series[k].affiche}" alt="Photo indisponible" class="img-thumbnail img-responsive"/></a> 
					</div>
					<div class="caption">  						
			    		<h4><a href="${url}" class="titreClickable">${series[k].titre}</a></h4>			    		
		    		</div>
	    		</c:if>
	    	</div>	    
	    </c:forEach>
	</div>	    	   	 
	<hr/>
</c:forEach>
