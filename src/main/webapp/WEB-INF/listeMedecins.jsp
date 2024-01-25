<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Médecins</title>
</head>
<body>

<h2>Liste des Médecins</h2>

<c:if test="${not empty listeMedecins}">
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Spécialité</th>
                <th>Adresse</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="medecin" items="${listeMedecins}">
                <tr>
                    <td>${medecin.id_medecin}</td>
                    <td>${medecin.nom}</td>
                    <td>${medecin.prenom}</td>
                    <td>${medecin.email}</td>
                    <td>${medecin.specialite}</td>
                    <td>${medecin.adresse}</td>
                    <td>
                    	<form action="" method="get">
                    		<input type="submit" value="fixer rdv">
                    	</form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
