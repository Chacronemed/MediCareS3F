<%@ page import="beans.traitementBean" %>
<%@ page import="java.util.Map" %>
<%@ page import="beans.maladie" %>
<%@ page import="java.util.List" %>
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
                <th> Nom Maladie <span class="icon-arrow">&UpArrow;</span></th>
                <th> Description du maladie <span class="icon-arrow">&UpArrow;</span></th>
                <th> Date de Maladie<span class="icon-arrow">&UpArrow;</span></th>


            </tr>
            </thead>
            <tbody>
            <% List<maladie> maladies = (List<maladie>) request.getAttribute("maladies");
                for (maladie mal : maladies) {
            %>
            <tr>
                <td><%= mal.getNom() %></td>
                <td><%= mal.getDescription() %></td>
                <td><%= mal.getDate_maladie() %></td>
                <td>Button</td>

            </tr>
            <% } %>
            </tbody>
        </table>
    </section>
    </div>


</div>

<script src="assets/JS/TableauJS.js"></script>
 </body>
</html>
