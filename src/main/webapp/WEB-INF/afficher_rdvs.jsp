<%@ page import="beans.traitementBean" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rendez-vous du médecin</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link rel="stylesheet" href="assets/css/TableauCSS.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
<div class="right-table">
    <section class="table__header">
        <h1>Rendez-vous du médecin</h1>
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
                <th> ID <span class="icon-arrow">&UpArrow;</span></th>
                <th> Date début <span class="icon-arrow">&UpArrow;</span></th>
                <th> Date final <span class="icon-arrow">&UpArrow;</span></th>
                <th> Heure début <span class="icon-arrow">&UpArrow;</span></th>
                <th> Heure fin <span class="icon-arrow">&UpArrow;</span></th>
                <th> Date rendez-vous <span class="icon-arrow">&UpArrow;</span></th>
                <th> Heure rendez-vous <span class="icon-arrow">&UpArrow;</span></th>
                <th> Remarque <span class="icon-arrow">&UpArrow;</span></th>
                <th> fixer rdv <span class="icon-arrow">&UpArrow;</span></th>
<%--                <th> traiter <span class="icon-arrow">&UpArrow;</span></th>--%>


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
<%--                    <td>--%>
<%--                        <a href="">voir</a>--%>
<%--                    </td>--%>
                    <!-- Ajoutez d'autres cellules en fonction des attributs de Rdv -->
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</div>
<%--    <section class="main">--%>
<%--        <section class="attendance">--%>
<%--            <div class="attendance-list">--%>
<%--                <h1>Rendez-vous du médecin</h1>--%>
<%--                <table >--%>
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th>ID</th>--%>
<%--                        <th>Date début</th>--%>
<%--                        <th>Date fin</th>--%>
<%--                        <th>Heure début</th>--%>
<%--                        <th>Heure fin</th>--%>
<%--                        <th>Date rendez-vous</th>--%>
<%--                        <th>Heure rendez-vous</th>--%>
<%--                        <th>Remarque</th>--%>
<%--                        <th>fixer rdv</th>--%>
<%--                        <th>traiter</th>--%>
<%--                        <!-- Ajoutez d'autres en-têtes de colonne en fonction des attributs de Rdv -->--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    --%>
<%--                </table>--%>
<%--            </div>--%>
<%--        </section>--%>
<%--    </section>--%>
</div>
<!--**********************************************************-->
<%--    <h1>Rendez-vous du médecin</h1>--%>

<%--    <form onsubmit="return false;">--%>
<%--        <!-- Ajouter des boutons radio pour choisir le filtre -->--%>
<%--        <label>--%>
<%--            <input type="radio" name="filtre" value="tous" checked> Tous--%>
<%--        </label>--%>
<%--        <label>--%>
<%--            <input type="radio" name="filtre" value="non_traites"> Non traités--%>
<%--        </label>--%>
<%--        <label>--%>
<%--            <input type="radio" name="filtre" value="prevus"> Prévus--%>
<%--        </label>--%>
<%--        <label>--%>
<%--            <input type="radio" name="filtre" value="jour"> Jour--%>
<%--        </label>--%>
<%--        <label>--%>
<%--            <input type="radio" name="filtre" value="passes"> Passés--%>
<%--        </label>--%>
<%--        <button onclick="filtrerRendezVous()">Filtrer</button>--%>
<%--    </form>--%>
<script src="assets/JS/TableauJS.js"></script>

</body>
</html>
