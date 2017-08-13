<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="row">

</div>

<div class="row">
	<div class="col-md-2">
		<form:form method="GET" action="films" modelAttribute="filmModel">
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
	        	<form:options items="${filmModel.genres}" itemValue="id" itemLabel="libelle"/>	        		
		    </form:select>		    
		  </div>
		</form:form>
	</div>
	<div class="col-md-2">
		<form:form class="form-horizontal" method="GET" action="films" modelAttribute="filmModel">
			<div class="form-group">
		    	<label for="FilterByRealisateur">
		    		Réalisateurs:		    				    		
		    		<form:button 
		    			id="clearFilter"
		    			name="clearFiltre"
		    			value="realisateur"
		    			class="btn transparent">
		    			<img src="/Filmotheque/resources/images/icone/eraser.png"/>
		    		</form:button>
		    	 </label>				    	   		    
		    	<form:select path="idRealisateur" id="FilterByRealisateur" class="form-control " onchange= "submit()">		                
                	<form:option value="">Tous</form:option>
                	<form:options items="${filmModel.realisateurs}" itemValue="id" itemLabel="concatLibelleNbFilms"/>                                	
		    	</form:select>		    	 		    
		  	</div>
		  	<div class="form-group text-right">
		  			  		
		  	</div>
		</form:form>
	</div>
	<div class="col-md-2">
		<form:form method="GET" action="films" modelAttribute="filmModel">
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
                <form:options items="${filmModel.pays}" itemValue="id" itemLabel="nom"/>			
		    </form:select>		    
		  </div>
		</form:form>
	</div>
	<div class="col-md-2 col-md-offset-4">
		<form:form method="GET" action="films" modelAttribute="filmModel">
		  <div class="form-group">
		    <label for="orderBy">Trier par: </label>
		    <form:select path="orderBy" id="orderBy" class="form-control" onchange= "submit()">		                
		        <form:options items="${filmModel.listOrderBy}" itemLabel="displayName" />
		    </form:select>
		  </div>
		</form:form>
	</div>
</div>

<c:url var="firstUrl" value="/films">
	<c:param name="numPage" value="1"/>
</c:url>
<c:url var="prevUrl" value="/films">
	<c:param name="numPage" value="${filmModel.currentPage - 1}"/>
</c:url>
<c:url var="nextUrl" value="/films">
	<c:param name="numPage" value="${filmModel.currentPage + 1}"/>
</c:url>
<c:url var="lastUrl" value="/films">
	<c:param name="numPage" value="${filmModel.totalPage}"/>
</c:url>
<div class= "row text-center" id="HautPagination">
	<nav aria-label="Page navigation">
	  <ul class="pagination">
	  	<c:choose>
            <c:when test="${filmModel.currentPage == 1}">
                <li class="disabled"><a href="#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li class="disabled"><a href="#HautPagination">&lt;</a></li>               
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li><a href="${prevUrl}#HautPagination">&lt;</a></li>
            </c:otherwise>
        </c:choose>	  
        <c:forEach var="i" begin="${filmModel.beginPagination}" end="${filmModel.endPagination}">
            <c:url var="pageUrl" value="/films">
            	<c:param name="numPage" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i == filmModel.currentPage}">
                    <li class="active"><a href="${pageUrl}#HautPagination">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}#HautPagination">${i}</a></li>                   
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${filmModel.currentPage == filmModel.totalPage}">
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
<c:forEach begin="0" end ="${filmModel.liste.size()/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-2 col-md-4S">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/filmDetails" var="url">
					<c:param name="idFilm" value="${filmModel.liste[k].id}"/>
				</c:url>
				<c:if test="${k<filmModel.liste.size()}">								
					<div class="thumbnail">
						<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${filmModel.liste[k].affiche}" class="img-thumbnail img-responsive"/></a> 
					</div>
					<div class="caption">  						
			    		<h4><a href="${url}" class="titreClickable">${filmModel.liste[k].titre}</a></h4>
			    		<p><a href="${url}" class="titreClickable">${filmModel.liste[k].resumeCourt}</a></p>
			    		<c:forEach begin="0" end ="${filmModel.liste[k].genres.size()}" var="l">
			    			<span>${filmModel.liste[k].genres[l].libelle}</span>
			    			<c:if test="${l<filmModel.liste[k].genres.size()-1}">, </c:if>
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
            <c:when test="${filmModel.currentPage == 1}">
                <li class="disabled"><a href="#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li class="disabled"><a href="#HautPagination">&lt;</a></li>               
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}#HautPagination"><span aria-hidden="true">Premier</span></a></li>
                <li><a href="${prevUrl}#HautPagination">&lt;</a></li>
            </c:otherwise>
        </c:choose>	  
        <c:forEach var="i" begin="${filmModel.beginPagination}" end="${filmModel.endPagination}">
            <c:url var="pageUrl" value="/films">
            	<c:param name="numPage" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i == filmModel.currentPage}">
                    <li class="active"><a href="${pageUrl}#HautPagination">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}#HautPagination">${i}</a></li>                   
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
				<c:when test="${filmModel.currentPage == filmModel.totalPage}">
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

