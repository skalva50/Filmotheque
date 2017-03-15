<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<h1 class="text-center">Film</h1><br/>

<c:forEach begin="0" end ="${nbFilms/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-4 col-md-2">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/filmDetails" var="url">
					<c:param name="idFilm" value="${films[k].id}"/>
				</c:url>
				<c:if test="${k<nbFilms}">								
					<div class="thumbnail">
						<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${films[k].affiche}" class="img-thumbnail img-responsive"/></a> 
					</div>
					<div class="caption">  						
			    		<h4><a href="${url}" class="titreClickable">${films[k].titre}</a></h4>
			    		<p><a href="${url}" class="titreClickable">${films[k].resumeCourt}</a></p>
			    		<c:forEach items="${films[k].genres}" var="genre">
			    			<span>${genre.libelle}</span>
			    		</c:forEach>
		    		</div>
	    		</c:if>
	    	</div>	    
	    </c:forEach>
	</div>	    	   	 
	<hr/>
</c:forEach>
<br/>

