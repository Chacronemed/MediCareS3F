<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profil Utilisateur</title>
</head>
<body>

<c:if test="${sessionScope.utilisateur ne null}">
    <h2>Profil de ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h2>
    
    <p><strong>Nom:</strong> ${sessionScope.utilisateur.nom}</p>
    <p><strong>Prénom:</strong> ${sessionScope.utilisateur.prenom}</p>
    <p><strong>Email:</strong> ${sessionScope.utilisateur.email}</p>
    <p><strong>Type:</strong> ${sessionScope.utilisateur.type}</p>
    <p><strong>Numéro de téléphone:</strong> ${sessionScope.utilisateur.num_tel}</p>
    
    <c:if test="${sessionScope.utilisateur.sexe eq 'M'}">
        <p><strong>Sexe:</strong> Masculin</p>
    </c:if>
    <c:if test="${sessionScope.utilisateur.sexe eq 'F'}">
        <p><strong>Sexe:</strong> Féminin</p>
    </c:if>
    
    <c:if test="${sessionScope.utilisateur.type eq 'medecin'}">
    	<p><strong>Spécialité:</strong> ${sessionScope.utilisateur.specialite}</p>
    	<p><strong>Adresse:</strong> ${sessionScope.utilisateur.adresse}</p>
    </c:if>
    <c:if test="${sessionScope.utilisateur.type eq 'patient'}">
        <p><strong>Contact d'urgence:</strong> ${sessionScope.utilisateur.contact_urgence}</p>
    </c:if>
    
   
</c:if>
	<a href="/MediCareConnect/gestion_utilisateur">Gérer utilisateur</a>
	<form action="gestion_utilisateur" method="get">
		<input type="submit" value="gérer profile">
	</form>
	<c:if test="${sessionScope.utilisateur.type eq 'patient'}">
		<form action="fixer_rdv" method="get">
			<input type="submit" value="fixer rendez-vous">
		</form>
	</c:if>
	<c:if test="${sessionScope.utilisateur.type eq 'medecin'}">
		<form action="rdvs" method="get">
			 <input type="hidden" name="id" value="${sessionScope.utilisateur.id_utilisateur}">
			<input type="submit" value="consulter rendez-vous">
		</form>
	</c:if>
</body>
</html>
