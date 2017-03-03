<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
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

<br/>
<a href="<c:url value='/maj' />">Maj Fichier</a>
