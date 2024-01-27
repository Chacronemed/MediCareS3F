<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registre</title>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/register.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<div class="wrapper">
    <form action="inscription" method="post">
        <h1>Login</h1>
        <div class="input-box">
            <input type="text" name="nom"  placeholder="Nom" required>
        </div>
        <div class="input-box">
            <input type="text" name="prenom"  placeholder="Prénom" required>
        </div>
        <div class="input-box">
            <input type="email" name="email"  placeholder="Email" required>
            <i class='bx bxs-user'></i>
        </div>
        <div class="input-box">
            <input ttype="password" name="password" placeholder="Password" required>
            <i class='bx bxs-lock-alt' ></i>
        </div>
        <!-- Add new sexe fields -->
        <div class="input-box">
            <label for="sexe" class="form-label">Sexe :</label>
            <div class="radio-group">
                <input type="radio" id="masculin" name="sexe" value="M" checked>
                <label for="masculin" class="radio-label">Masculin</label>
                <input type="radio" id="feminin" name="sexe" value="F">
                <label for="feminin" class="radio-label">Féminin</label>
            </div>
        </div>

        <!-- Add new type dropdown -->
        <div class="input-box box2">
            <label for="type" class="form-label">Type :</label>
            <select name="type" id="type" onchange="afficherChampsSpecifiques()">
                <option value="patient">Patient</option>
                <option value="medecin">Médecin</option>
            </select>
        </div>

        <!-- Champs spécifiques pour le type "Médecin" -->
        <div id="champsMedecin" class="input-box" style="display: none;">
            <label for="specialite" class="form-label">Spécialité:</label>
            <input type="text" name="specialite" class="input-field">
            <label for="adresse" class="form-label">Adresse:</label>
            <input type="text" name="adresse" class="input-field">
        </div>

        <!-- Champs spécifiques pour le type "Patient" -->
        <div id="champsPatient" class="input-box" style="display: none;">
            <label for="contact_urgence" class="form-label">Contact d'urgence:</label>
            <input type="text" name="numTel" class="input-field">
        </div>
        <div><%-- Afficher le message d'erreur s'il y en a un --%>
            <c:if test="${not empty requestScope.erreurMessage}">
                <p style="color: red;">${requestScope.erreurMessage}</p>
            </c:if>
        </div>
        <div class="remember-forgot">
            <label><input type="checkbox">Remember Me</label>
            <a href="#">Forgot Password</a>
        </div>
        <button type="submit" class="btn" style="color: #f3f3f3">Register</button>
        <div class="register-link">
            <p>Already have an account? <a href="connexion">Log In</a></p>
        </div>
    </form>
</div>
<script src="assets/JS/register.js"></script>
</body>
</html>
