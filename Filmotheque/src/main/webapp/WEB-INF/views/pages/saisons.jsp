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
		<h4 class ="text-center">Résumé: </h4>
			<p> ${serie.resume}</p>			
		<hr/>
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
		        <tr> 
		        	<td  width="10%">				
		        		<img src ="https://image.tmdb.org/t/p/w500${saison.affiche}" class="img-thumbnail img-responsive"/>    		        		
		        	</td>
		        	<td width="10%">Saison ${saison.numero}</td>
		        	<td width="10%">${saison.dateSortie}</td>
		        	<td>${saison.resume}</td>     	
		        </tr>		       	     	
		    </c:forEach>
	    </tbody>
	</table>  
</div>
