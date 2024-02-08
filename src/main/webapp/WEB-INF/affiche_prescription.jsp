<%--
  Created by IntelliJ IDEA.
  User: zoube
  Date: 07/02/2024
  Time: 04:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Affichage des prescriptions</title>
    <link rel="stylesheet" href="assets/css/TableauCSS.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>
<body>

<%@include file="/WEB-INF/navbar.jsp" %>
<div style="flex: 1">
    <div class="right-table" >
        <section class="table__header" >
            <h1>Liste des prescriptions</h1>
            <div class="input-group">
                <input type="search" placeholder="Search Data...">
                <i class="fa-solid fa-magnifying-glass"></i>
            </div>
        </section>
        <section class="table__body">
            <table>
                <thead>
                <tr>
                    <th> Nom de la maladie <span class="icon-arrow">&UpArrow;</span></th>
                    <th> Description <span class="icon-arrow">&UpArrow;</span></th>
                    <th> Remarque <span class="icon-arrow">&UpArrow;</span></th>
                    <th> Nom du médicament <span class="icon-arrow">&UpArrow;</span></th>
                    <th> Dose <span class="icon-arrow">&UpArrow;</span></th>


                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list_pres}" var="prescription">
                    <tr>
                        <td>${prescription.nom}</td>
                        <td>${prescription.description}</td>
                        <td>${prescription.remarque}</td>
                        <td>${prescription.nom_medicament}</td>
                        <td>${prescription.dose}</td>
                        <!-- Ajoutez les autres colonnes ici -->
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </div>
</div>
</body>
</html>

