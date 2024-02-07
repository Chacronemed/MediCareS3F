<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fixer un rendez-vous</title>
    <link rel="stylesheet" href="assets/css/TableauCSS.css" type="text/css">
     <style>
         h1 {
             text-align: center;
             color: #333;
             margin: 20px 0;
         }

         .doctor-list {
             max-height: 300px;
             overflow-y: auto;
             background-color: #fff;
             border: 1px solid #ddd;
             border-radius: 4px;
             box-shadow: 0 2px 4px rgba(0,0,0,0.1);
             margin-bottom: 20px;
         }

         table {
             width: 100%;
             border-collapse: collapse;
         }

         th, td {
             padding: 10px;
             text-align: left;
         }

         th {
             background-color: #5cb85c;
         }

         tr:hover {
             background-color: #f1f1f1;
         }

         form {
             background-color: #fff;
             padding: 20px;
             max-width: 90%;
             margin: 20px auto;
             border-radius: 4px;
             box-shadow: 0 2px 4px rgba(0,0,0,0.1);
         }

         label {
             display: block;
             margin-bottom: 5px;
             font-weight: bold;
         }

         input[type="date"],
         input[type="time"],
         input[type="submit"] {
             width: 100%;
             padding: 10px;
             margin-bottom: 15px;
             border-radius: 4px;
             box-sizing: border-box; /* Adds padding without increasing the width */
         }

         input[type="submit"] {
             background-color: #5cb85c;
             color: white;
             cursor: pointer;
             border: none;
         }

         input[type="submit"]:hover {
             background-color: #4cae4c;
         }

         input[type="radio"] {
             margin-right: 5px;
         }

         @media (max-width: 768px) {
             h1 {
                 font-size: 24px;
             }

             .doctor-list,
             form {
                 width: 95%;
                 margin: 20px auto;
             }
         }
    </style>
    
</head>
<body>

<%@include file="/WEB-INF/navbarpatient.jsp" %>
<div style="flex: 1;">
    <h1>Fixer un rendez-vous avec le médecin</h1>
    <c:if test="${sessionScope.utilisateur ne null}">

    <form action="fixer_rdv" method="post">
        <div class="right-table">
            <section class="table__header">
                <h1>Traitements Prescrits</h1>
                <div class="input-group">
                    <input type="search" placeholder="Search Data...">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
            </section>
            <section class="table__body">
                <table>
                    <thead>
                    <tr>
                        <th> ID <span class="icon-arrow">&UpArrow;</span></th>
                        <th> Nom <span class="icon-arrow">&UpArrow;</span></th>
                        <th> Prénom<span class="icon-arrow">&UpArrow;</span></th>
                        <th> Email<span class="icon-arrow">&UpArrow;</span></th>
                        <th> Spécialité<span class="icon-arrow">&UpArrow;</span></th>
                        <th> Adresse<span class="icon-arrow">&UpArrow;</span></th>
                        <th></th>


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
                                <input type="radio" name="choix_med" value="${medecin.id_medecin}">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </section>
        </div>
    <!--/******************************************************/-->

    	<input type="hidden" name="id" value="${sessionScope.utilisateur.id_utilisateur}">
        <label for="startDate">Date de début de disponibilité :</label>
        <input type="date" id="date_debut" name="date_debut" required><br>

        <label for="endDate">Date de fin de disponibilité :</label>
        <input type="date" id="date_fin" name="date_fin" required onchange="setMinMaxDate()"><br>

        <label for="startTime">Heure de début de disponibilité :</label>
        <input type="time" id="heure_debut" name="heure_debut" required><br>

        <label for="endTime">Heure de fin de disponibilité :</label>
        <input type="time" id="heure_fin" name="heure_fin" required onchange="setMinMaxTime()"><br>


        <input type="submit" value="Fixer le rendez-vous">
    </form>
    </c:if>
</div>
<script src="assets/JS/TableauJS.js"></script>
</body>
</html>
