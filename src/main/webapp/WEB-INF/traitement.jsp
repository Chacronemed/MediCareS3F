<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter Prescription Médicale</title>
    <script>
        function ajouterLigneTraitement() {
            var container = document.getElementById("lignesTraitement");
            var ligneTraitement = document.createElement("div");

            ligneTraitement.innerHTML = `
                <label for="nomMedicament">Nom du médicament :</label><br>
                <input type="text" name="nomMedicament[]"><br>
                <label for="dose">Dose :</label><br>
                <input type="number" name="dose[]"><br>
                <label for="quantite">Quantité :</label><br>
                <input type="number" name="quantite[]"><br>
                <label for="frequence">Fréquence :</label><br>
                <input type="number" name="frequence[]"><br>
                <label for="duree">Durée :</label><br>
                <input type="number" name="duree[]"><br><br>
            `;

            container.appendChild(ligneTraitement);
        }
    </script>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>

<h2>Ajouter Prescription Médicale</h2>
<form action="traitement" method="post">
    <!-- Informations sur la maladie -->
    <!-- Champs pour la maladie... -->
    <label for="nomMaladie">Nom de la maladie :</label><br>
    <input type="text" id="nomMaladie" name="nomMaladie"><br>
    <label for="descriptionMaladie">Description de la maladie :</label><br>
    <textarea id="descriptionMaladie" name="descriptionMaladie" rows="4" cols="50"></textarea><br>
    <label for="dateMaladie">Date de la maladie :</label><br>
    <input type="date" id="dateMaladie" name="dateMaladie"><br>

    <!-- Informations sur le traitement -->
    <!-- Champs pour le traitement... -->
    <label for="remarqueTraitement">Remarque sur le traitement :</label><br>
    <textarea id="remarqueTraitement" name="remarqueTraitement" rows="4" cols="50"></textarea><br>
    <label for="dateTraitement">Date du traitement :</label><br>
    <input type="date" id="dateTraitement" name="dateTraitement"><br>

    <!-- Informations sur les lignes de traitement -->
    <div id="lignesTraitement">
        <!-- Champs pour une ligne de traitement (générés dynamiquement en JavaScript)... -->
        <label for="nomMedicament">Nom du médicament :</label><br>
        <input type="text" id="nomMedicament" name="nomMedicament[]"><br>
        <label for="dose">Dose :</label><br>
        <input type="number" id="dose" name="dose[]"><br>
        <label for="quantite">Quantité :</label><br>
        <input type="number" id="quantite" name="quantite[]"><br>
        <label for="frequence">Fréquence :</label><br>
        <input type="number" id="frequence" name="frequence[]"><br>
        <label for="duree">Durée :</label><br>
        <input type="number" id="duree" name="duree[]"><br>
    </div>
    <button type="button" onclick="ajouterLigneTraitement()">Ajouter Ligne de Traitement</button><br><br>

    <input type="submit" value="Ajouter Prescription">
</form>
</body>
</html>
