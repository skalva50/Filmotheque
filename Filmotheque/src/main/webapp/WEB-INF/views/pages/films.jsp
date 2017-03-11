<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<h2>Liste des films</h2>

<c:forEach begin="0" end ="${nbFilms/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-4 col-md-2">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/filmDetails" var="url">
					<c:param name="idFilm" value="${films[k].id}"/>
				</c:url>							
				<div class="thumbnail">
					<a href="${url}">
		    			<img src ="https://image.tmdb.org/t/p/w500${films[k].affiche}" class="img-thumbnail img-responsive"/>  
		    		</a>   						
	    		</div>
	    	</div>	    
	    </c:forEach>
	</div>	    	 
	<div class="row"> 
	    <c:forEach begin="0" end ="5" var="l">
	    	<c:set var="m" scope="session" value="${(i*6)+l}"/>	
	    	<c:url value="/filmDetails" var="url2">
				<c:param name="idFilm" value="${films[m].id}"/>
			</c:url>
	    	<div class="col-sm-4 col-md-2">    		 
    			<h4><a href="${url2}" class="titreClickable">${films[m].titre}</a></h4>			
			</div>    
	    </c:forEach>
	</div>    	 
	<hr/>
</c:forEach>
<br/>
<a href="<c:url value='/maj' />">Maj Fichier</a>
