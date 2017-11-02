<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h1>${user.identifiant}</h1>
<h1 class="text-center">Gestion des films</h1>
<div class="row text-center">
	<div class="col-md-6">
		<a class="btn text-center " href="/Filmotheque/administration/majFilm" role="button">
			<img src="/Filmotheque/resources/images/icone/update.png" />
			<h3 class="text-center">Mise à jour Film</h3>
		</a>
	</div>
	<div class="col-md-6">
		<a class="btn text-center " href="/Filmotheque/administration/deleteFilm" role="button">
			<img src="/Filmotheque/resources/images/icone/delete.png" />
			<h3 class="text-center">Supprimer film obsolètes</h3>
		</a>
	</div>
</div>
<hr/>
<h1 class="text-center">Gestion des séries</h1>
<div class="row text-center">
  <div class="col-md-6 text-center">  	
		<a class="btn text-center" href="/Filmotheque/administration/majSerie" role="button">
			<img src="/Filmotheque/resources/images/icone/update.png" />
			<h3 class="text-center">Mise à jour Series TV</h3>	
		</a>	
	</div>  	
	<div class="col-md-6">
		<a class="btn text-center " href="/Filmotheque/administration/deleteSerie" role="button">
			<img src="/Filmotheque/resources/images/icone/delete.png" />
			<h3 class="text-center">Supprimer serie obsolètes</h3>
		</a>
	</div>	  
</div>
<hr/>
<h1 class="text-center">Gestion des utilisateurs</h1>
<hr/>
<div class="row">
	<div class="col-sm-12">
		<c:url value="/administration/newUser" var="urlNewUser"></c:url>
		<form action="${urlNewUser}"">
			<button type="submit" class="btn btn-success">Ajouter utilisateur</button>
		</form>
	</div>
</div>
<hr/>
<div class="row">
	<div class="col-md-12">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Identifiant</th>
					<th>Statut</th>
					<th>Profiles</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listUser}" var="user">					
					<c:url value="/administration/updateUser" var="urlUpdate">
						<c:param name="idUser" value="${user.id}"/>
					</c:url>
					<c:url value="/administration/deleteUser" var="urlDelete">
						<c:param name="idUser" value="${user.id}"/>
					</c:url> 
					<tr>
						<th scope="row">${user.identifiant}</th>
						<td>${user.state}</td>
						<td>
							<c:forEach items="${user.userProfiles}" var="profil">
								${profil.type}
							</c:forEach>	
						</td>
						<td>
							<form method="get" action="${urlUpdate}">
								<button type="submit" class="btn btn-info" name="idUser" value="${user.id}">Modifier</button>
							</form>
						</td>
						<td>
							<form method="get" action="${urlDelete}">
								<button type="submit" class="btn btn-primary" name="idUser" value="${user.id}">Supprimer</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
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