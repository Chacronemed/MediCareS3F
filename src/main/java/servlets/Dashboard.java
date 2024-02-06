package servlets;

import beans.rdv;
import beans.rdv_dash;
import beans.utilisateur;
import dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Dashboard", value = "/Dashboard")
public class Dashboard extends HttpServlet {
    private utilisateur_dao utilisateurDao;

    public void init() throws ServletException{
        dao_factory daoFactory = dao_factory.getInstance();
        this.utilisateurDao = daoFactory.get_utilisateur_dao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao_factory daoFactory = dao_factory.getInstance();
        rdv_dao rdvDao = daoFactory.get_rdv_dao();
        medecin_dao medecinDao = daoFactory.get_medecin_dao();
        patient_dao patientDao = daoFactory.get_patient_dao();

        utilisateur userbean = utilisateurDao.get_session(request);
        if(userbean == null){
            response.sendRedirect("connexion");
        }
        else {
            System.out.println("le type de user est "+ userbean.getType());
            if(userbean.getType().equals("medecin")) {
                //int id_medecin = medecinDao.get_id_medecin(userbean.getId_utiliseur());
                System.out.println("Dashboard.java : l'id de l'utilisateur conntecté est : "+userbean.getId_utiliseur());
                // Récupérer les rendez-vous du médecin connecté en fonction du filtre
                //List<rdv> rendezVousMedecin = rdvDao.getTodayMedcineRDV(userbean.getId_utiliseur());
//                // Passer les rendez-vous et le filtre à la page JSP
//                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@"+rendezVousMedecin.size());
                //request.setAttribute("rendezVousMedecin", rendezVousMedecin);
                //HttpSession session = request.getSession();
                //int id_utilisateur = (int) session.getAttribute("id_utilistaeur");
                List<rdv_dash> list_rdv_dash = medecinDao.get_all_rdv_med(userbean.getId_utiliseur());
                request.setAttribute("rendezVousMedecin", list_rdv_dash);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
                dispatcher.forward(request, response);
            }
            else if (userbean.getType().equals("patient")) {
                System.out.println("ple type de user est "+ userbean.getType());
                List<rdv_dash> list_rdv_dash = patientDao.get_all_rdv_patient(userbean.getId_utiliseur());
                request.setAttribute("rendezVousPatient", list_rdv_dash);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/DashboardPatient.jsp");
                dispatcher.forward(request, response);
            }
        }
        // Redirection vers la page JSP d'affichage des rendez-vous
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/afficher_rdvs.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}