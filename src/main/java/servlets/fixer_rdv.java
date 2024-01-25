package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import beans.medecin;
import beans.rdv;
import dao.dao_factory;
import dao.medecin_dao;
import dao.patient_dao;
import dao.rdv_dao;
import dao.rdv_dao_impl;

/**
 * Servlet implementation class fixer_rdv
 */
public class fixer_rdv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fixer_rdv() {
        super();
        // TODO Auto-generated constructor stub
    }
    dao_factory daoFactory = dao_factory.getInstance();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // Récupérer la liste des médecins depuis le DAO
        medecin_dao medecinDao = daoFactory.get_medecin_dao();
        List<medecin> listeMedecins = medecinDao.listerMedecins();

        // Ajouter la liste des médecins à la requête
        request.setAttribute("listeMedecins", listeMedecins);

        // Rediriger vers la page JSP d'affichage
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/rdv_form.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de l'ID du médecin sélectionné
	    int idMedecinSelected = Integer.parseInt(request.getParameter("choix_med"));
	 // Récupération de l'ID du patient depuis la session
	    int idUtilisateur = Integer.parseInt(request.getParameter("id"));
	    
	    patient_dao patient_dao = daoFactory.get_patient_dao();
	    int id_patient = patient_dao.get_id_patient(idUtilisateur);
	    

	    // Récupération des autres informations du formulaire
	    String date_debut = request.getParameter("date_debut");
	    String date_fin = request.getParameter("date_fin");
	    String heure_debut = request.getParameter("heure_debut");
	    String heure_fin = request.getParameter("heure_fin");

	    // ... récupérez d'autres informations du formulaire si nécessaire ...

	    // Création de l'objet Rdv avec les informations récupérées
	    rdv rdv = new rdv();
	    rdv.setId_med(idMedecinSelected);
	    rdv.setId_patient(id_patient);
	    rdv.setDate_debut(Date.valueOf(date_debut));
	    rdv.setDate_fin(Date.valueOf(date_fin));
	    rdv.setHeure_debut(heure_debut);
	    rdv.setHeure_fin(heure_fin);
	    
	    

	    // Appel de la méthode ajouter(rdv)
	    rdv_dao rdv_dao = daoFactory.get_rdv_dao();
        rdv_dao.ajouter(rdv);

	    // Redirection ou traitement supplémentaire
	}

}
