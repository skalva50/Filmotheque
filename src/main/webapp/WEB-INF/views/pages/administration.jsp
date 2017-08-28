<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row text-center">
	<div class="col-md-6">
		<a class="btn text-center " href="/Filmotheque/majFilm" role="button">
			<img src="/Filmotheque/resources/images/icone/update.png" />
			<h2 class="text-center">Mise à jour Film</h2>
		</a>
	</div>
	<div class="col-md-6 text-center">  	
		<a class="btn text-center" href="/Filmotheque/majSerie" role="button">
			<img src="/Filmotheque/resources/images/icone/update.png" />
			<h2 class="text-center">Mise à jour Series TV</h2>	
		</a>	
	</div>  	  
</div>
<div class="row text-center">
	<div class="col-md-6">
		<a class="btn text-center " href="/Filmotheque/deleteFilm" role="button">
			<img src="/Filmotheque/resources/images/icone/update.png" />
			<h2 class="text-center">Supprimer film obsolètes</h2>
		</a>
	</div>	  
	<div class="col-md-6">
		<a class="btn text-center " href="/Filmotheque/deleteSerie" role="button">
			<img src="/Filmotheque/resources/images/icone/update.png" />
			<h2 class="text-center">Supprimer serie obsolètes</h2>
		</a>
	</div>	  
</div>
<c:if test="${listAjout.size() >0}">
	<h2 class="text-center">Liste des ajouts</h2>
	<c:forEach items="${listAjout}" var="ajout">	
	<div class="row">
		<div class="col-md-2">
			<img src ="https://image.tmdb.org/t/p/w500${ajout.affiche}" class="img-thumbnail img-responsive"/>
		</div>	
		<div class="col-md-10">
			${ajout.titre}
		</div>
	</div>
	</c:forEach>
</c:if>
<c:if test="${listDelete.size() >0}">
	<h2 class="text-center">Liste des suppressions</h2>
	<c:forEach items="${listDelete}" var="delete">	
	<div class="row">
		<div class="col-md-2">
			<img src ="https://image.tmdb.org/t/p/w500${delete.affiche}" class="img-thumbnail img-responsive"/>
		</div>	
		<div class="col-md-10">
			${delete.titre}
		</div>
	</div>
	</c:forEach>
</c:if>