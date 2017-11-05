<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h2>Liste des fichiers</h2>  
<table>
    <tr>
        <td>ID</td><td>Chemin</td>
    </tr>
    <c:forEach items="${fichiers}" var="fichier">
        <tr>
        	<td>${fichier.id}</td>
        	<td>${fichier.chemin}</td>
        </tr>
    </c:forEach>
</table>  

