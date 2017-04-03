<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="form-container">    
 	<form:form method="POST" action="filtreFilm" modelAttribute="filtreFilmModel" class="form-horizontal"> 
		<div class="row">
	    	<div class="form-group col-md-6">
		        <label class="col-md-2 control-lable" for="sortBy">Trier par:</label>
		        <div class="col-md-4">
		            <form:select path="sortBy" id="sortBy" class="form-control input-sm" onchange= "submit()">		                
		                <form:options items="${listTri}" />
		            </form:select>	            
		        </div>
	        </div>
	   	</div>
	   	<div class="row">
	    	<div class="form-group col-md-6">
		        <label class="col-md-2 control-lable" for="sortBy">Trier par:</label>
		        <div class="col-md-4">
		            <form:select path="idRealisateur" id="idRealisateur" class="form-control input-sm" onchange= "submit()">
		                <form:option value="">Tous</form:option>
		                <form:options items="${filtreFilmModel.realisateurs}" itemValue="id" itemLabel="nom"/>		                
		            </form:select>	            
		        </div>
	        </div>
	   	</div>
	</form:form>
</div>
 

<c:url var="firstUrl" value="/films">
	<c:param name="numPage" value="1"/>
</c:url>
<c:url var="prevUrl" value="/films">
	<c:param name="numPage" value="${filtreFilmModel.currentPage - 1}"/>
</c:url>
<c:url var="nextUrl" value="/films">
	<c:param name="numPage" value="${filtreFilmModel.currentPage + 1}"/>
</c:url>
<c:url var="lastUrl" value="/films">
	<c:param name="numPage" value="${totalPage}"/>
</c:url>
<div class= "row text-center">
	<nav aria-label="Page navigation">
	  <ul class="pagination">
	  	<c:choose>
            <c:when test="${filtreFilmModel.currentPage == 1}">
                <li class="disabled"><a href="#"><span aria-hidden="true">Premier</span></a></li>
                <li class="disabled"><a href="#">&lt;</a></li>               
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}"><span aria-hidden="true">Premier</span></a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>	  
        <c:forEach var="i" begin="${begin}" end="${end}">
            <c:url var="pageUrl" value="/films">
            	<c:param name="numPage" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i == filtreFilmModel.currentPage}">
                    <li class="active"><a href="${pageUrl}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}">${i}</a></li>                   
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${filtreFilmModel.currentPage == totalPage}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">Dernier</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">Dernier</a></li>                
            </c:otherwise>
        </c:choose>
	  </ul>
	</nav>
</div>


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
			    		<c:forEach begin="0" end ="${films[k].genres.size()}" var="l">
			    			<span>${films[k].genres[l].libelle}</span>
			    			<c:if test="${l<films[k].genres.size()-1}">, </c:if>
			    		</c:forEach>
		    		</div>
	    		</c:if>
	    	</div>	    
	    </c:forEach>
	</div>	    	   	 
</c:forEach>
<div class= "row text-center">
	<nav aria-label="Page navigation">
	  <ul class="pagination">
	  	<c:choose>
            <c:when test="${filtreFilmModel.currentPage == 1}">
                <li class="disabled"><a href="#"><span aria-hidden="true">Premier</span></a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}"><span aria-hidden="true">Premier</span></a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>	  
        <c:forEach var="i" begin="${begin}" end="${end}">
            <c:url var="pageUrl" value="/films">
            	<c:param name="numPage" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i == filtreFilmModel.currentPage}">
                    <li class="active"><a href="${pageUrl}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${filtreFilmModel.currentPage == totalPage}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">Dernier</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">Dernier</a></li>
            </c:otherwise>
        </c:choose>
	  </ul>
	</nav>
</div>
<br/>

