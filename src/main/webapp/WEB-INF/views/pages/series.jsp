<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="row">
	<div class="col-md-2">
		<form:form method="GET" action="series" modelAttribute="serieModel">
		  <div class="form-group">
		    <label for="FilterByGenre">Genre:
		    	<form:button 
		    			id="clearFilter"
		    			name="clearFiltre"
		    			value="genre"
		    			class="btn transparent">
		    			<img src="/Filmotheque/resources/images/icone/eraser.png"/>
		    	</form:button>
		     </label>
		    <form:select path="idGenre" id="FilterByGenre" class="form-control" onchange= "submit()">		                
		    	<form:option value="">Tous</form:option>
	        	<form:options items="${serieModel.genres}" itemValue="id" itemLabel="libelle"/>	        		
		    </form:select>		    
		  </div>
		</form:form>
	</div>
	<div class="col-md-2">
		<form:form method="GET" action="series" modelAttribute="serieModel">
		  <div class="form-group">
		    <label for="FilterByPays">Pays:
		    	<form:button 
		    			id="clearFilter"
		    			name="clearFiltre"
		    			value="pays"
		    			class="btn transparent">
		    			<img src="/Filmotheque/resources/images/icone/eraser.png"/>
		    	</form:button>
		    </label>
		    <form:select path="idPays" id="FilterByPays" class="form-control" onchange= "submit()">		                
                <form:option value="">Tous</form:option>
                <form:options items="${serieModel.pays}" itemValue="id" itemLabel="nom"/>			
		    </form:select>		    
		  </div>
		</form:form>
	</div>
	<div class="col-md-2 col-md-offset-6">
		<form:form method="GET" action="series" modelAttribute="serieModel">
		  <div class="form-group">
		    <label for="orderBy">Trier par: </label>
		    <form:select path="orderBy" id="orderBy" class="form-control" onchange= "submit()">		                
		        <form:options items="${serieModel.listOrderBy}" itemLabel="displayName" />
		    </form:select>
		  </div>
		</form:form>
	</div>
</div>

<c:url var="firstUrl" value="/series">
	<c:param name="numPage" value="1"/>
</c:url>
<c:url var="prevUrl" value="/series">
	<c:param name="numPage" value="${serieModel.currentPage - 1}"/>
</c:url>
<c:url var="nextUrl" value="/series">
	<c:param name="numPage" value="${serieModel.currentPage + 1}"/>
</c:url>
<c:url var="lastUrl" value="/series">
	<c:param name="numPage" value="${serieModel.totalPage}"/>
</c:url>
<div class= "row text-center" id="HautPagination">
	<nav aria-label="Page navigation">
	  <ul class="pagination">
	  	<c:choose>
            <c:when test="${serieModel.currentPage == 1}">
                <li class="disabled"><a href="#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li class="disabled"><a href="#HautPagination">&lt;</a></li>               
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li><a href="${prevUrl}#HautPagination">&lt;</a></li>
            </c:otherwise>
        </c:choose>	  
        <c:forEach var="i" begin="${serieModel.beginPagination}" end="${serieModel.endPagination}">
            <c:url var="pageUrl" value="/series">
            	<c:param name="numPage" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i == serieModel.currentPage}">
                    <li class="active"><a href="${pageUrl}#HautPagination">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}#HautPagination">${i}</a></li>                   
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${serieModel.currentPage == serieModel.totalPage}">
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


<c:forEach begin="0" end ="${serieModel.liste.size()/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-4 col-md-2">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/saisons" var="url">
					<c:param name="idSerie" value="${serieModel.liste[k].id}"/>
				</c:url>	
				<c:if test="${k<serieModel.liste.size()}">						
					<div class="thumbnail">
						<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${serieModel.liste[k].affiche}" class="img-thumbnail img-responsive"/></a> 
					</div>
					<div class="caption">  						
			    		<h4><a href="${url}" class="titreClickable">${serieModel.liste[k].titre}</a></h4>
			    		<p><a href="${url}" class="titreClickable">${serieModel.liste[k].resumeCourt}</a></p>
			    		<c:forEach begin="0" end ="${serieModel.liste[k].genres.size()}" var="l">
			    			<span>${serieModel.liste[k].genres[l].libelle}</span>
			    			<c:if test="${l<serieModel.liste[k].genres.size()-1}">, </c:if>
			    		</c:forEach>
		    		</div>
	    		</c:if>
	    	</div>	    
	    </c:forEach>
	</div>	    	   	 
	<hr/>
</c:forEach>
<div class= "row text-center">
	<nav aria-label="Page navigation">
	  <ul class="pagination">
	  	<c:choose>
            <c:when test="${serieModel.currentPage == 1}">
                <li class="disabled"><a href="#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li class="disabled"><a href="#HautPagination">&lt;</a></li>               
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li><a href="${prevUrl}#HautPagination">&lt;</a></li>
            </c:otherwise>
        </c:choose>	  
        <c:forEach var="i" begin="${serieModel.beginPagination}" end="${serieModel.endPagination}">
            <c:url var="pageUrl" value="/series">
            	<c:param name="numPage" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i == serieModel.currentPage}">
                    <li class="active"><a href="${pageUrl}#HautPagination">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}#HautPagination">${i}</a></li>                   
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${serieModel.currentPage == serieModel.totalPage}">
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
