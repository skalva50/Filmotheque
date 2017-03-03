<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h2>Liste des Series</h2>  
<table class="table table-hover">
	<thead>
	    <tr>
	        <th></th><th>Titre</th><th>Résumé</th>
	    </tr>
	</thead>
	<tbody>    
	    <c:forEach items="${series}" var="serie">	   	
	        <tr> 
	        	<td>						
	        	<!--	<c:url value="/filmDetails" var="url">
					<c:param name="idFilm" value="${film.id}"/>
					</c:url>
					<a href="${url}">	-->			
	        			<img src ="https://image.tmdb.org/t/p/w500${serie.affiche}" class="img-thumbnail img-responsive"/>
	        	<!--	</a>  	-->	      		        		
	        	</td> 
	        	<td>${serie.titre}</td>
	        	<td>${serie.resume}</td> 
	        	<td>

				</td>     	
	        </tr>		       	     	
	    </c:forEach>
    </tbody>
</table>  

<br/>
<a href="<c:url value='/maj' />">Maj Fichier</a>
