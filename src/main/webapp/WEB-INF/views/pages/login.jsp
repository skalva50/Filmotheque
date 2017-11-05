<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="row">   
	<div class="col-md-4 col-md-offset-4">           
		<c:url var="loginUrl" value="/login" />
		<form action="${loginUrl}" method="post" class="form-horizontal">
		    <c:if test="${param.error != null}">
		        <div class="alert alert-danger">
		            <p>Identifiant et mot de passe invalide.</p>
		        </div>
		    </c:if>
		    <c:if test="${param.logout != null}">
		        <div class="alert alert-success">
		            <p>Vous avez été deconnecté avec succès.</p>
		        </div>
		    </c:if>
		    <div class="input-group input-sm">
		        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
		        <input type="text" class="form-control" id="username" name="identifiant" placeholder="Identifiant" required>
		    </div>
		    <div class="input-group input-sm">
		        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
		        <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required>
		    </div>
		    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		         
		    <div class="form-actions">
		        <input type="submit"
		            class="btn btn-block btn-primary btn-default" value="Valider">
		    </div>
		</form>
	</div>
</div>
                
            
        