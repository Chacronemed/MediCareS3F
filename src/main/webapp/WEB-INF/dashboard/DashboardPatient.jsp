<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profil Utilisateur</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="assets/css/dashboard.css" type="text/css">
    <style>
        #myChart {
            max-height: 350px;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbarpatient.jsp" %>
<section class="main">
    <div class="main-top">
        <h1>Attendance</h1>
        <i class="fas fa-user-cog"></i>
    </div>
    <div class="users" >
        <div class="card" style="flex: 1;">
            <img src="assets/images/calendrier8.png">
            <h4>Vous avez Aujourd'hui</h4>
            <div class="per">
                <table>
                    <tr>
                        <td><span>1</span></td>
                    </tr>
                    <tr>
                        <td>Rendez-vous</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="card" style="flex: 1;">
            <img src="assets/images/programme.png">
            <h4>Vous devez prendre </h4>
            <div class="per">
                <table>
                    <tr>
                        <td><span>2</span></td>
                    </tr>
                    <tr>
                        <td>medicament</td>
                    </tr>
                </table>
            </div>
        </div>

    </div>

    <section class="attendance">
        <div class="attendance-list">
            <h1>prise de medicament</h1>
            <canvas id="myChart" ></canvas>
        </div>
    </section>
</section>
</div>
<c:if test="${sessionScope.utilisateur ne null}">
    <h2>Profil de ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h2>
    <p> totalooos ${sessionScope.utilisateur.id_utilisateur}</p>
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
<script>
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line', // Change this from 'bar' to 'line'
        data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [{
                label: 'nombre de medicaments prise',
                data: [12, 19, 3, 5, 2, 3, 8],
                backgroundColor: 'rgba(54, 162, 235, 0.2)', // For a line chart, a single color or gradient could be more suitable
                borderColor: 'rgba(54, 162, 235, 1)', // The color of the line
                borderWidth: 2,
                fill: false // Specify if the area under the line should be filled
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>

</body>
</html>
