        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Modifier/Supprimer le Profil</title>
            <style>


                .center-container {
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    justify-content: center;
                    width: 100%;
                    height: 100vh; /* Utilise toute la hauteur de la fenêtre */
                    flex: 1;
                }

                form {
                    display: flex;
                    flex-direction: column;
                    width: 500px; /* Largeur fixe pour le formulaire */
                    padding: 20px;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Une légère ombre pour le style */
                    background: #fffef9;
                    border-radius: 5px;
                }

                form p {
                    display: flex;
                    align-items: center;
                    margin-bottom: 10px; /* Espace entre les champs */
                }

                form p label {
                    width: 30%; /* Largeur fixe pour les étiquettes */
                    margin-right: 10px; /* Espace entre l'étiquette et le champ de saisie */
                    text-align: right; /* Alignement du texte à droite pour les étiquettes */
                }

                input[type="text"] {
                    flex: 1; /* Prend le reste de l'espace disponible */
                    padding: 10px;
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
                form p.buttons {
                    justify-content: center; /* Centre les boutons */
                    padding-top: 20px; /* Ajoutez un peu d'espace au-dessus des boutons */
                }

            </style>
            <script>
                function previewImage() {
                    var input = document.getElementById('image');
                    var preview = document.getElementById('imagePreview');
                    var file = input.files[0];
                    var reader = new FileReader();

                    reader.onload = function(e) {
                        preview.src = e.target.result;
                        preview.style.display = 'block';
                    };

                    reader.readAsDataURL(file);
                }
            </script>
        </head>
        <body>

        <div><c:choose>
            <c:when test="${sessionScope.utilisateur.type == 'medecin'}">
                <jsp:include page="/WEB-INF/navbar.jsp" />
            </c:when>
            <c:when test="${sessionScope.utilisateur.type == 'patient'}">
                <jsp:include page="/WEB-INF/navbarpatient.jsp" />
            </c:when>
        </c:choose>
                <div class="center-container"  >

                    <c:if test="${sessionScope.utilisateur ne null}">
                    <h2 >Modifier/Supprimer le Profil</h2>
                        <form action="gestion_utilisateur" method="post" enctype="multipart/form-data" >
                            <input type="hidden" name="id" value="${sessionScope.utilisateur.id_utilisateur}">
                            <input type="hidden" name="type" value="${sessionScope.utilisateur.type}">
                            <p><label for="nom">Nom:</label><input type="text" id="nom" name="nom" value="${sessionScope.utilisateur.nom}"></p>
                            <p><label for="prenom">Prénom:</label><input type="text" id="prenom" name="prenom" value="${sessionScope.utilisateur.prenom}"></p>
                            <p><label for="email">Email:</label><input type="text" id="email" name="email" value="${sessionScope.utilisateur.email}"></p>
                            <p><label for="numTel">Numéro de téléphone:</label><input type="text" id="numTel" name="numTel" value="${sessionScope.utilisateur.num_tel}"></p>
                            <c:if test="${sessionScope.utilisateur.type eq 'medecin'}">
                                <p><label for="specialite">Spécialité:</label><input type="text" id="specialite" name="specialite" value="${sessionScope.utilisateur.specialite}"></p>
                                <p><label for="adresse">Adresse:</label><input type="text" id="adresse" name="adresse" value="${sessionScope.utilisateur.adresse}"></p>
                                <div class="form-group">
                                    <div class="mb-3">
                                        <label for="image" class="form-label">Image:</label>
                                        <input class="form-control" type="file" id="image" name="image" accept="image/*" required onchange="previewImage()"/>
                                    </div>

                                    <br>

                                    <img id="imagePreview" src="#" alt="Aper?u de l'image" style="display: none; max-height: 200px; max-width: 200px;">
                                    <br>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.utilisateur.type eq 'patient'}">
                                <p><label for="contact_urgence">Contact d'urgence:</label><input type="text" id="contact_urgence" name="contact_urgence" value="${sessionScope.utilisateur.contact_urgence}"></p>
                            </c:if>

                            <p class="buttons">
                                <input type="submit" name="action" value="Enregistrer les modification">
                                <input type="submit" name="action" value="Supprimer le profil">
                            </p>
                        </form>
                </c:if>
                </div>
        </div>
        </body>
        </html>
