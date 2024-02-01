<%@ page import="beans.traitementBean" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Traitements</title>
</head>
<body>
<h2>Traitements Prescrits</h2>
<table border="1">
    <tr>
        <th>ID RDV</th>
        <th>Nom et Prénom du Patient</th>
        <th>Remarque</th>
        <th>Date de Traitement</th>
    </tr>
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
    </tr>
    <% } %>
</table>
</body>
</html>
