<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" type="image/png" href="<c:url value='/resources/images/icone/film-reel.png'/>"  />
    <title><tiles:getAsString name="title" /></title>     
    <meta name="robots" content="noindex, nofollow">
    <link href="<c:url value='/resources/bootstrap/dist/css/bootstrap.min.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/resources/bootstrap/dist/css/themeJournal.css' />"  rel="stylesheet"></link>    
   	<link href="<c:url value='/resources/css/app.css' />"  rel="stylesheet"></link> 
</head>
  
<body>
	<div class="row">
		<div class="col-md-12">
            <tiles:insertAttribute name="header" />
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
        	<tiles:insertAttribute name="menu" />            
        </div>     	
	</div>
        
             
   	<div class="row">
     	<div class="col-md-12" id="corps">
            <tiles:insertAttribute name="body" />
        </div>     	
	</div>
	<footer>
 	<nav class="navbar navbar-default" id="footer">
		<div class="container">		     	
  	    	<tiles:insertAttribute name="footer" />
  		</div>
  	</nav>	
  	</footer>             	
</body>
</html>