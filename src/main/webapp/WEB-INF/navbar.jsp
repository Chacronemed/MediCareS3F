<%--
  Created by IntelliJ IDEA.
  User: Chacrone
  Date: 27/01/2024
  Time: 01:18
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="assets/css/dashboard.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<span style="font-family: verdana, geneva, sans-serif;"><!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Dashboard</title>
  <link rel="stylesheet" href="style.css" />
    <!-- Font Awesome Cdn Link -->
</head>
<body>
  <div class="container">
    <nav>
      <ul>
        <li><a href="#" class="logo">
          <img src="./pic/logo.jpg">
          <span class="nav-item">Admin</span>
        </a></li>
        <li><a href="Dashboard">
          <i class='fas bx bxs-dashboard' ></i>
          <span class="nav-item">Dashboard</span>
        </a></li>
        <li id="rdv-link"><a href="#" >
          <i class='fas bx bxs-calendar'></i>
          <span class="nav-item">Mes Rendez-Vous</span>
        </a></li>
        <li><a href="#">
          <i class='fas bx bxs-book-content'></i>
          <span class="nav-item">Prescriptions</span>
        </a></li>
<%--        <li><a href="#">--%>
<%--          <i class="fas fa-chart-bar"></i>--%>
<%--          <span class="nav-item">Attendance</span>--%>
<%--        </a></li>--%>
<%--        <li><a href="#">--%>
<%--          <i class="fas fa-cog"></i>--%>
<%--          <span class="nav-item">Setting</span>--%>
<%--        </a></li>--%>

        <li><a href="#" class="logout">
          <i class="fas fa-sign-out-alt"></i>
          <span class="nav-item">Log out</span>
        </a></li>
      </ul>
    </nav>


<script>
document.getElementById('rdv-link').addEventListener('click', function() {
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

</body>
</html>
</span>
</body>
</html>