<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="row">
		<div class="col-md-2 col-md-offset-8">
			<form:form method="GET" action="realisateurs" modelAttribute="realisateursModel">
			  <div class="form-group">
			    <label for="SearchByNom">Chercher par nom:
			    </label>
			    <form:input path="nomLike" class="form-control" onchange= "submit()"/>	                                	    
			  </div>
			</form:form>
		</div>
		<div class="col-md-2">
		<form:form method="GET" action="realisateurs" modelAttribute="realisateursModel">
		  <div class="form-group">
		    <label for="orderBy">Trier par: </label>
		    <form:select path="orderBy" id="orderBy" class="form-control" onchange= "submit()">		                
		        <form:options items="${realisateursModel.listOrderBy}" itemLabel="displayName" />
		    </form:select>
		  </div>
		</form:form>
	</div>
</div>

<c:url var="firstUrl" value="/realisateurs">
	<c:param name="numPage" value="1"/>
</c:url>
<c:url var="prevUrl" value="/realisateurs">
	<c:param name="numPage" value="${realisateursModel.currentPage - 1}"/>
</c:url>
<c:url var="nextUrl" value="/realisateurs">
	<c:param name="numPage" value="${realisateursModel.currentPage + 1}"/>
</c:url>
<c:url var="lastUrl" value="/realisateurs">
	<c:param name="numPage" value="${realisateursModel.totalPage}"/>
</c:url>
<div class= "row text-center" id="HautPagination">
	<nav aria-label="Page navigation">
	  <ul class="pagination">
	  	<c:choose>
            <c:when test="${realisateursModel.currentPage == 1}">
                <li class="disabled"><a href="#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li class="disabled"><a href="#HautPagination">&lt;</a></li>               
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li><a href="${prevUrl}#HautPagination">&lt;</a></li>
            </c:otherwise>
        </c:choose>	  
        <c:forEach var="i" begin="${realisateursModel.beginPagination}" end="${realisateursModel.endPagination}">
            <c:url var="pageUrl" value="/realisateurs">
            	<c:param name="numPage" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i == realisateursModel.currentPage}">
                    <li class="active"><a href="${pageUrl}#HautPagination">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}#HautPagination">${i}</a></li>                   
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${realisateursModel.currentPage == realisateursModel.totalPage}">
                <li class="disabled"><a href="#HautPagination">&gt;</a></li>
                <li class="disabled"><a href="#HautPagination">Dernier</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}#HautPagination">&gt;</a></li>
                <li><a href="${lastUrl}#HautPagination">Dernier</a></li>                
            </c:otherwise>
        </c:choose>
	  </ul>
	</nav>
</div>
<c:forEach begin="0" end ="${realisateursModel.liste.size()/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-2 col-md-4S">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/realisateur" var="url">
					<c:param name="idRealisateur" value="${realisateursModel.liste[k].id}"/>
				</c:url>
				<c:if test="${k<realisateursModel.liste.size()}">							
					<c:if test="${not empty realisateursModel.liste[k].photo}">
						<div class="thumbnail">
							<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${realisateursModel.liste[k].photo}" class="img-thumbnail img-responsive"/></a>
						</div>
					</c:if>											 
					<div class="caption">  						
			    		<h4><a href="${url}" class="titreClickable">${realisateursModel.liste[k].nom}</a></h4>	
			    		<p><a href="${url}" class="titreClickable">${realisateursModel.liste[k].lieuNaissance}</a></p>		    		
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
            <c:when test="${realisateursModel.currentPage == 1}">
                <li class="disabled"><a href="#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li class="disabled"><a href="#HautPagination">&lt;</a></li>               
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li><a href="${prevUrl}#HautPagination">&lt;</a></li>
            </c:otherwise>
        </c:choose>	  
        <c:forEach var="i" begin="${realisateursModel.beginPagination}" end="${realisateursModel.endPagination}">
            <c:url var="pageUrl" value="/realisateurs">
            	<c:param name="numPage" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i == realisateursModel.currentPage}">
                    <li class="active"><a href="${pageUrl}#HautPagination">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}#HautPagination">${i}</a></li>                   
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${realisateursModel.currentPage == realisateursModel.totalPage}">
                <li class="disabled"><a href="#HautPagination">&gt;</a></li>
                <li class="disabled"><a href="#HautPagination">Dernier</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}#HautPagination">&gt;</a></li>
                <li><a href="${lastUrl}#HautPagination">Dernier</a></li>                
            </c:otherwise>
        </c:choose>
	  </ul>
	</nav>
</div>
<br/>