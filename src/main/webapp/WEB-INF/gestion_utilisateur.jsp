        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Modifier/Supprimer le Profil</title>
            <style>

                form {
                    margin: 50px; /* This centers the form horizontally */
                    max-width: 500px; /* This controls the width of the form */
                    display: flex;
                    flex-direction: column;
                    /* rest of your form styles... */
                }

                form p {
                    margin: 10px 0;
                }
                input[type="text"] {
                    width: 100%;
                    padding: 10px;
                    margin-top: 5px;
                    border: 1px solid #ddd;
                    border-radius: 4px;
                }
                input[type="submit"] {
                    padding: 10px 15px;
                    background-color: #5cb85c;
                    color: white;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                    margin-top: 10px;
                    transition: background-color 0.3s;
                }
                input[type="submit"]:hover {
                    background-color: #4cae4c;
                }
                input[type="submit"]:nth-child(2) {
                    background-color: #d9534f;
                    margin-left: 10px;
                }
                input[type="submit"]:nth-child(2):hover {
                    background-color: #c9302c;
                }

            </style>
        </head>
        <body>
        <div><c:choose>
            <c:when test="${sessionScope.utilisateur.type == 'medecine'}">
                <jsp:include page="/WEB-INF/navbar.jsp" />
            </c:when>
            <c:when test="${sessionScope.utilisateur.type == 'patient'}">
                <jsp:include page="/WEB-INF/navbarpatient.jsp" />
            </c:when>
        </c:choose>
                <div class="center-container" >

                    <c:if test="${sessionScope.utilisateur ne null}">
                    <h2 style="margin-left: 200px; margin-bottom: 200px">Modifier/Supprimer le Profil</h2>
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
                </div>
        </div>
        </body>
        </html>
