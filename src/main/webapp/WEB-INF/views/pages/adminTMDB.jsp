<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${listMovie.size() >0}">
<div class="row">
<h2>Nom du fichier:</h2>
<h3>${film.fichier.chemin}</h3>
<hr/>	
<h4 class ="text-center">Films disponibles sur TMDB: </h4>	
	<c:forEach items="${listMovie}" var="movie">
		<div class="row">
			<div class="col-md-2">
				<img src ="https://image.tmdb.org/t/p/w500${movie.poster_path}" class="img-thumbnail img-responsive"/>
			</div>			
			<div class="col-md-2">
				${movie.title}
			</div>
			<div class="col-md-2">
				${movie.release_date}
			</div>
			<c:url value="/administration/majTMDB" var="urlmajTMDB">			
				<c:param name="idTMDB" value="${movie.id}"/>
				<c:param name="idFilm" value="${film.id}"/>
			</c:url>
			<div class ="col-md-2">
				<a class="btn text-right " href="${urlmajTMDB}" role="button">
					<img src="/Filmotheque/resources/images/icone/checked.png" />
				</a>
			</div>			
		</div>
	</c:forEach>
</div>
</c:if>
<c:if test="${listSearchSerie.size() >0}">
<div class="row">
<h2>Nom Serie:</h2>
<h3>${serie.titre}</h3>
<hr/>	
<h4 class ="text-center">Serie disponibles sur TMDB: </h4>	
	<c:forEach items="${listSearchSerie}" var="searchSerie">
		<div class="row">
			<div class="col-md-2">
				<img src ="https://image.tmdb.org/t/p/w500${searchSerie.poster_path}" class="img-thumbnail img-responsive"/>
			</div>			
			<div class="col-md-2">
				${searchSerie.name}
			</div>
			<div class="col-md-2">
				${searchSerie.first_air_date}
			</div>
			<c:url value="/majTMDB" var="urlmajTMDB">			
				<c:param name="idTMDB" value="${searchSerie.id}"/>
				<c:param name="idSerie" value="${serie.id}"/>
			</c:url>
			<div class ="col-md-2">
				<a class="btn text-right " href="${urlmajTMDB}" role="button">
					<img src="/Filmotheque/resources/images/icone/checked.png" />
				</a>
			</div>			
		</div>
	</c:forEach>
</div>
</c:if>