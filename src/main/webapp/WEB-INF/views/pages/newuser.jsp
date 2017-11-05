<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="form-container">
 
 <c:choose>
 	<c:when test="${update}">
 		<h1>Compte de ${user.identifiant}</h1>
 	</c:when> 
 	<c:otherwise>
 		<h1>Creation compte</h1>
 	</c:otherwise>
 </c:choose>
 
<form:form method="POST" modelAttribute="user" class="form-horizontal">
	<form:input type="hidden" path="id" id="id"/>
	<c:choose>
		<c:when test="${update}">
			<form:input type="hidden" path="identifiant" id="identifiant" class="form-control input-sm" />
		</c:when>
		<c:otherwise>
			<div class="row">
		        <div class="form-group col-md-12">
		            <label class="col-md-3 control-lable" for="identifiant">Identifiant</label>
		            <div class="col-md-7">
		               	<form:input type="text" path="identifiant" id="identifiant" class="form-control input-sm"/> 
		               	 <div class="has-error">
		                    <form:errors path="identifiant" class="help-inline"/>
		                </div>            
		            </div>
		        </div>
		    </div>
		</c:otherwise>
	</c:choose> 
	

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="password">Password</label>
            <div class="col-md-7">
                <form:input type="password" path="password" id="password" class="form-control input-sm"/>
                <div class="has-error">
                    <form:errors path="password" class="help-inline"/>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="userProfiles">Roles</label>
                <div class="col-md-7">
                    <form:select path="userProfiles" items="${listeProfiles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="userProfiles" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


    <div class="row">
        <div class="form-actions floatRight">
            <input type="submit" value="Enregistrer" class="btn btn-success">
        </div>
    </div>
</form:form>
</div>