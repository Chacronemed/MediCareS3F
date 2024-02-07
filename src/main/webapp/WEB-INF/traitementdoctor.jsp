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

<%@include file="/WEB-INF/navbar.jsp" %>

<div class="right-table">
    <section class="table__header">
    <h1>Traitements Prescrits</h1>
    <div class="input-group">
        <input type="search" placeholder="Search Data...">
        <i class="fa-solid fa-magnifying-glass"></i>
    </div>
    <%--        <div class="export__file">--%>
    <%--            <label for="export-file" class="export__file-btn" title="Export File"></label>--%>
    <%--            <input type="checkbox" id="export-file">--%>
    <%--            <div class="export__file-options">--%>
    <%--                <label>Export As &nbsp; &#10140;</label>--%>
    <%--                <label for="export-file" id="toPDF">PDF <img src="images/pdf.png" alt=""></label>--%>
    <%--                <label for="export-file" id="toJSON">JSON <img src="images/json.png" alt=""></label>--%>
    <%--                <label for="export-file" id="toCSV">CSV <img src="images/csv.png" alt=""></label>--%>
    <%--                <label for="export-file" id="toEXCEL">EXCEL <img src="images/excel.png" alt=""></label>--%>
    <%--            </div>--%>
    <%--        </div>--%>
</section>
    <section class="table__body">
        <table>
            <thead>
            <tr>
                <th> ID RDV <span class="icon-arrow">&UpArrow;</span></th>
                <th> Nom et Prénom du Patient <span class="icon-arrow">&UpArrow;</span></th>
                <th> Remarque <span class="icon-arrow">&UpArrow;</span></th>
                <th> Date de Traitement <span class="icon-arrow">&UpArrow;</span></th>
                <th> Voir le traitemnt <span class="icon-arrow">&UpArrow;</span></th>


            </tr>
            </thead>
            <tbody>
            <%
                Map<traitementBean, String> traitementsInfo = (Map<traitementBean, String>) request.getAttribute("traitementsInfo");
                for (Map.Entry<traitementBean, String> entry : traitementsInfo.entrySet()) {
                    traitementBean traitement = entry.getKey();
                    String nomPrenom = entry.getValue();
            %>
            <tr>
                <td><%= traitement.getId_rdv() %></td>
                <td><%= nomPrenom %></td>
                <td><%= traitement.getRemarque() %></td>
                <td><%= traitement.getDate_traitement() %></td>
                <td>
                    <a href="http://localhost:8080/MediCare_war_exploded/affiche_prescription?id_rdv=<%=traitement.getId_rdv()%>"><p class="status delivered">Prescription</p></a>
                    <%--            <p class="status cancelled">Cancelled</p> rouge--%>
                    <%--            <p class="status shipped">Shipped</p> Orange--%>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </section>
    </div>


</div>
<script>
    document.getElementById('prescription-link').addEventListener('click', function() {
        var form = document.createElement('form');
        form.method = 'get';
        form.action = 'rdvs';

        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'id';
        input.value = '${sessionScope.utilisateur.id_utilisateur}';

        form.appendChild(input)
        document.body.appendChild(form);
        form.submit();
    });
</script>
<script src="assets/JS/TableauJS.js"></script>
 </body>
</html>
