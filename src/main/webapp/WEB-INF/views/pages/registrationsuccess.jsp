<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="success">
    Confirmation message : ${success}
    <br>
    Would you like to <a href="<c:url value='/newUser' />">Add More Users</a>?
    <br/>
    Go to <a href="<c:url value='/administration' />">Admin Page   
</div>
