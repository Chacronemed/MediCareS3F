<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier/Supprimer le Profil</title>
</head>
<body>

<c:if test="${sessionScope.utilisateur ne null}">
    <h2>Modifier/Supprimer le Profil</h2>

    <form action="gestion_utilisateur" method="post">
        <input type="hidden" name="id" value="${sessionScope.utilisateur.id_utilisateur}">
        <input type="hidden" name="type" value="${sessionScope.utilisateur.type}">
        <p>Nom: <input type="text" name="nom" value="${sessionScope.utilisateur.nom}"></p>
        <p>Prénom: <input type="text" name="prenom" value="${sessionScope.utilisateur.prenom}"></p>
        <p>Email: <input type="text" name="email" value="${sessionScope.utilisateur.email}"></p>
        <p>Numéro de téléphone: <input type="text" name="numTel" value="${sessionScope.utilisateur.num_tel}"></p>
        <c:if test="${sessionScope.utilisateur.type eq 'medecin'}">
	    	<p>Spécialité: <input type="text" name="specialite" value="${sessionScope.utilisateur.specialite}"></p>
	    	<p>Adresse: <input type="text" name="adresse" value= "${sessionScope.utilisateur.adresse}"></p>
	    </c:if>
	    <c:if test="${sessionScope.utilisateur.type eq 'patient'}">
	        <p>Contact d'urgence:<input type="text" name="contact_urgence" value="${sessionScope.utilisateur.contact_urgence}"></p>
	    </c:if>
        <p>
            <input type="submit" name="action" value="Enregistrer les modification">
            <input type="submit" name="action" value="Supprimer le profil">
        </p>
        <!-- 
        <script>
		    var action = "${param.action}"; // Récupérer le paramètre d'URL 'action'
		
		    // Vérifie si la confirmation est nécessaire
		    var confirmationRequise = ${requestScope.confirmationRequise};
		
		    if (confirmationRequise) {
		        var messageConfirmation = "";
		
		        if (action === "Enregistrer les modification") {
		            messageConfirmation = "Êtes-vous sûr de vouloir enregistrer les modifications?";
		        } else if (action === "Supprimer le profil") {
		            messageConfirmation = "Êtes-vous sûr de vouloir supprimer le profil?";
		        }
		
		        var confirmation = confirm(messageConfirmation);
		
		        if (!confirmation) {
		            // Annule la soumission du formulaire pour la suppression
		            document.getElementById("formulaireGestion").onsubmit = function() {
		                return false;
		            };
		        }
		    }
		</script>
         -->
        
    </form>
</c:if>

</body>
</html>
