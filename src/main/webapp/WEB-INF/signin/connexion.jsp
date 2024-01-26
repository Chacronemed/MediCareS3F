<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/login.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<%--<!-- Print the complete request URL -->--%>
<%--<p>Request URL: ${pageContext.request.requestURL}</p>--%>

<%--<!-- Print the context path -->--%>
<%--<p>Context Path: ${pageContext.request.contextPath}</p>--%>
<div class="wrapper">
    <form action="connexion" method="post">
        <h1>Login</h1>
        <div class="input-box">
            <input type="email" name="email"  placeholder="Email" required>
            <i class='bx bxs-user'></i>
        </div>
        <div class="input-box">
            <input ttype="password" name="password" placeholder="Password" required>
            <i class='bx bxs-lock-alt' ></i>
        </div>
        <div><%-- Afficher le message d'erreur s'il y en a un --%>
        <c:if test="${not empty requestScope.erreurMessage}">
            <p style="color: red;">${requestScope.erreurMessage}</p>
        </c:if></div>
        <div class="remember-forgot">
            <label><input type="checkbox">Remember Me</label>
            <a href="#">Forgot Password</a>
        </div>
        <button type="submit" class="btn" style="color: #f3f3f3">Login</button>
        <div class="register-link">
            <p>Dont have an account? <a href="inscription">Register</a></p>
        </div>
    </form>
</div>
</body>
</html>