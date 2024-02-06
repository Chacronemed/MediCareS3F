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
  <script>
    function myFunction() {
document.getElementById('myButton').addEventListener('click', function() {
  var rdvId = this.getAttribute('data-parametre');
  //alert("La valeur du paramètre est : " + rdvId);
  var form = document.createElement('form');
  form.method = 'get';
  form.action = 'traitement';

  var input = document.createElement('input');
  input.type = 'hidden';
  input.name = 'id_rdv';
  input.value = rdvId;

  form.appendChild(input)
  document.body.appendChild(form);
  form.submit();
});
}
</script>
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
        <li><a href="traitement">
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


    <section class="main">
      <div class="main-top">
        <h1>Attendance</h1>
        <i class="fas fa-user-cog"></i>
      </div>
      <div class="users">
        <div class="card">
          <img src="assets/images/calendrier8.png">
          <h4>Vous avez Aujourd'hui</h4>
          <div class="per">
            <table>
              <tr>
                <td><span>${nombre_rdv}</span></td>
              </tr>
              <tr>
                <td>Rendez-vous</td>
              </tr>
            </table>
          </div>
          <button>Voir les details</button>
        </div>
        <div class="card">
          <img src="assets/images/programme.png">
          <h4>Vous avez maintenant </h4>
          <div class="per">
            <table>
              <tr>
                <td><span>${nombre_rdv_NT}</span></td>
              </tr>
              <tr>
                <td>demande de RDV non traite</td>
              </tr>
            </table>
          </div>
          <button>Traiter</button>
        </div>
        <div class="card">
          <img src="assets/images/sante-et-medecine.png">
          <h4>Vous avez soigne</h4>
          <div class="per">
            <table>
              <tr>
                <td><span>${nombre_soigne}</span></td>
              </tr>
              <tr>
                <td>personne</td>
              </tr>
            </table>
          </div>
          <button>voir mes prescriptions</button>
        </div>
        <div class="card">
          <img src="assets/images/prescription.png">
          <h4>Vous avez ecris</h4>
          <div class="per">
            <table>
              <tr>
                <td><span>${nombre_prescription}</span></td>
              </tr>
              <tr>
                <td>ligne de traitement</td>
              </tr>
            </table>
          </div>
          <button>consulter</button>
        </div>
      </div>

      <section class="attendance">
        <div class="attendance-list">
          <h1>Attendance List</h1>
          <table class="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Num Tel</th>
                <th>Date rdv</th>
                <th>heure rdv</th>
                <th>Details</th>
              </tr>
            </thead>
            <tbody>

                  <c:forEach var="rdv" items="${rendezVousMedecin}">
                  <c:set var="dateRdv" value='<fmt:formatDate value="${rdv.date_rdv}" pattern="yyyy-MM-dd" />' />
                  <c:set var="dateFin" value='<fmt:formatDate value="${rdv.date_fin}" pattern="yyyy-MM-dd" />' />
                    <tr>
                    <td>${rdv.id_rdv}</td>
                    <td>${rdv.nom}</td>
                    <td>${rdv.prenom}</td>
                    <td>${rdv.num_tel}</td>
                    <td>${rdv.date_rdv}</td>
                    <td>${rdv.heure}</td>
                    <td><button id="myButton" data-parametre="${rdv.id_rdv}" onclick="myFunction()">View</button></td>
                    </tr>
                  </c:forEach>
<%--              <tr>--%>
<%--                <td>01</td>--%>
<%--                <td>Sam David</td>--%>
<%--                <td>Design</td>--%>
<%--                <td>03-24-22</td>--%>
<%--                <td>8:00AM</td>--%>
<%--                <td>3:00PM</td>--%>
<%--                <td><button>View</button></td>--%>
<%--              </tr>--%>
<%--              <tr class="active">--%>
<%--                <td>02</td>--%>
<%--                <td>Balbina Kherr</td>--%>
<%--                <td>Coding</td>--%>
<%--                <td>03-24-22</td>--%>
<%--                <td>9:00AM</td>--%>
<%--                <td>4:00PM</td>--%>
<%--                <td><button>View</button></td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--                <td>03</td>--%>
<%--                <td>Badan John</td>--%>
<%--                <td>testing</td>--%>
<%--                <td>03-24-22</td>--%>
<%--                <td>8:00AM</td>--%>
<%--                <td>3:00PM</td>--%>
<%--                <td><button>View</button></td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--                <td>04</td>--%>
<%--                <td>Sara David</td>--%>
<%--                <td>Design</td>--%>
<%--                <td>03-24-22</td>--%>
<%--                <td>8:00AM</td>--%>
<%--                <td>3:00PM</td>--%>
<%--                <td><button>View</button></td>--%>
<%--              </tr>--%>
              <!-- <tr >
                <td>05</td>
                <td>Salina</td>
                <td>Coding</td>
                <td>03-24-22</td>
                <td>9:00AM</td>
                <td>4:00PM</td>
                <td><button>View</button></td>
              </tr>
              <tr >
                <td>06</td>
                <td>Tara Smith</td>
                <td>Testing</td>
                <td>03-24-22</td>
                <td>9:00AM</td>
                <td>4:00PM</td>
                <td><button>View</button></td>
              </tr> -->
            </tbody>
          </table>
        </div>
      </section>
    </section>
  </div>
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
