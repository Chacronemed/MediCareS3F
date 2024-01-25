<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rendez-vous du médecin</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        var currentDate = new Date();
        
        // Formatez la date en "yyyy-MM-dd"
        var formattedDate = currentDate.toISOString().slice(0,10);
        var cdate = formattedDate;
        
        function filtrerRendezVous() {
            if (isNaN(currentDate.getTime())) {
                console.error("Erreur : currentDate n'est pas valide.");
                return;
            } else {
                console.log("Date actuelle : ", cdate);
            }

            var filtre = $("input[name='filtre']:checked").val();

            $(".rdv-row").hide(); // Masquer toutes les lignes de rendez-vous

            // Afficher les lignes de rendez-vous en fonction du filtre
            if (filtre === 'tous') {
                $(".rdv-row").show();
            } else if (filtre === 'non_traites') {
                $(".non-traites").show();
            } else if (filtre === 'prevus') {
                $(".prevus").show();
            } else if (filtre === 'jour') {
                $(".jour").show();
            } else if (filtre === 'passes') {
                $(".passes").show();
            }
        }
    </script>
</head>
<body>
    <h1>Rendez-vous du médecin</h1>

    <form onsubmit="return false;">
        <!-- Ajouter des boutons radio pour choisir le filtre -->
        <label>
            <input type="radio" name="filtre" value="tous" checked> Tous
        </label>
        <label>
            <input type="radio" name="filtre" value="non_traites"> Non traités
        </label>
        <label>
            <input type="radio" name="filtre" value="prevus"> Prévus
        </label>
        <label>
            <input type="radio" name="filtre" value="jour"> Jour
        </label>
        <label>
            <input type="radio" name="filtre" value="passes"> Passés
        </label>
        <button onclick="filtrerRendezVous()">Filtrer</button>
    </form>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Date début</th>
                <th>Date fin</th>
                <th>Heure début</th>
                <th>Heure fin</th>
                <th>Date rendez-vous</th>
                <th>Heure rendez-vous</th>
                <th>Remarque</th>
                <th>fixer rdv</th>
                <th>traiter</th>
                <!-- Ajoutez d'autres en-têtes de colonne en fonction des attributs de Rdv -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="rdv" items="${rendezVousMedecin}">
                <c:set var="dateRdv" value='<fmt:formatDate value="${rdv.date_rdv}" pattern="yyyy-MM-dd" />' />
                <c:set var="dateFin" value='<fmt:formatDate value="${rdv.date_fin}" pattern="yyyy-MM-dd" />' />

                <tr class="rdv-row ${rdv.date_rdv == null && rdv.heure == null ? 'non-traites' : ''} ${rdv.date_rdv != null && dateFin != null && (dateRdv >= cdate || dateFin >= cdate) ? 'prevus' : ''} ${dateRdv != null && dateRdv == cdate ? 'jour' : ''} ${dateRdv != null && (dateRdv <= cdate || dateFin <= cdate) ? 'passes' : ''}">
                    <td>${rdv.id_rdv}</td>
                    <td>${rdv.date_debut}</td>
                    <td>${rdv.date_fin}</td>
                    <td>${rdv.heure_debut}</td>
                    <td>${rdv.heure_fin}</td>
                    <td>${rdv.date_rdv}</td>
                    <td>${rdv.heure}</td>
                    <td>${rdv.remarque}</td>
                    <td>
                    	<form action="confirmation_rdv" method="post">
                    		<input type="hidden" name="id_rdv" value="${rdv.id_rdv }">
                    		<input type="date" id="date_rdv" name="date_rdv" required>
                    		<input type="time" id="heure_rdv" name="heure_rdv" required>
                    		<input type="submit" value="fixer">
                    	</form>
                    </td>
                    <td>
                    	<a href="">voir</a>
                    </td>
                    <!-- Ajoutez d'autres cellules en fonction des attributs de Rdv -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
