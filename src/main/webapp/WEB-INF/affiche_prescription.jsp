<%--
  Created by IntelliJ IDEA.
  User: zoube
  Date: 07/02/2024
  Time: 04:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Affichage des prescriptions</title>
</head>
<body>

<%@include file="/WEB-INF/navbar.jsp" %>
<div>
    <h2>Liste des prescriptions</h2>
    <table border="1">
        <thead>
        <tr>
            <th>Nom de la maladie</th>
            <th>Description</th>
            <th>Remarque</th>
            <th>Nom du médicament</th>
            <th>Dose</th>
            <!-- Ajoutez les autres colonnes ici -->
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list_pres}" var="prescription">
            <tr>
                <td>${prescription.nom}</td>
                <td>${prescription.description}</td>
                <td>${prescription.remarque}</td>
                <td>${prescription.nom_medicament}</td>
                <td>${prescription.dose}</td>
                <!-- Ajoutez les autres colonnes ici -->
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

