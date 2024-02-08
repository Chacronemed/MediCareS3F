<%@ page import="beans.traitementBean" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Liste des Traitements</title>
    <link rel="stylesheet" href="assets/css/TableauCSS.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>
<body>

<%@include file="/WEB-INF/navbarpatient.jsp" %>

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
                <th> ID RDV <span class="icon-arrow">&UpArrow;</span></th>
                <th> Nom et Prénom du Medecin <span class="icon-arrow">&UpArrow;</span></th>
                <th> Remarque <span class="icon-arrow">&UpArrow;</span></th>
                <th> Date de Traitement <span class="icon-arrow">&UpArrow;</span></th>
                <th> Voir le traitemnt <span class="icon-arrow">&UpArrow;</span></th>


            </tr>
            </thead>
            <tbody>

            <tr>
                <td>1</td>
                <td>Anass Elmansouri</td>
                <td>lorem ipsum test text remarque111 </td>
                <td>08-02-2024</td>
                <td id="suivie"><a href="http://localhost:8080/MediCare_war_exploded/suivie"><p class="status delivered" >Prescription</p></a></td>
            </tr>
            <tr>
                <td>8</td>
                <td>Karim Merzaq</td>
                <td>lorem ipsum test text remarque222 </td>
                <td>17-12-2022</td>
                <td><a href="http://localhost:8080/MediCare_war_exploded/suivie"><p class="status delivered" >Prescription</p></a></td>

            </tr>
            <tr>
                <td>4</td>
                <td>Ahmed moutia</td>
                <td>lorem ipsum test text remarque333 </td>
                <td>25-04-2023</td>
                <td><a href="http://localhost:8080/MediCare_war_exploded/suivie"><p class="status delivered" >Prescription</p></a></td>

            </tr>

            </tbody>
        </table>
    </section>
    </div>


</div>
<script>
    document.getElementById('suivie').addEventListener('click', function() {
        var form = document.createElement('form');
        form.method = 'get';
        form.action = 'suivie';

        form.appendChild(input)
        document.body.appendChild(form);
        form.submit();
    });
</script>
<script src="assets/JS/TableauJS.js"></script>
 </body>
</html>
