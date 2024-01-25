package servlets;

import dao.dao_factory;
import dao.patient_dao;
import dao.medecin_dao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import beans.medecin;
import beans.patient;

/**
 * Servlet implementation class gestion_utilisateur
 */
public class gestion_utilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestion_utilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestion_utilisateur.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idUtilisateur = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        dao_factory daoFactory = dao_factory.getInstance();
        medecin_dao medecinDao = daoFactory.get_medecin_dao();
        patient_dao patientDao = daoFactory.get_patient_dao();
        if ("Enregistrer les modification".equals(action)) {
            // Modification de l'utilisateur
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String numTel = request.getParameter("numTel");
            if("medecin".equals(request.getParameter("type"))){
            	medecin medecin = new medecin();
            	medecin.setId_utilisateur(idUtilisateur);
            	medecin.setNom(nom);
            	medecin.setPrenom(prenom);
            	medecin.setEmail(email);
            	medecin.setNum_tel(numTel);
            	medecin.setSpecialite(request.getParameter("specialite"));
            	medecin.setAdresse(request.getParameter("adresse"));
            	
            	medecinDao.modifierMedecin(medecin);
            }
            		
            else if("patient".equals(request.getParameter("type"))){
            	patient patient = new patient();
            	patient.setId_utilisateur(idUtilisateur);//la méthode setId_utilisateur() est de la patient
            	patient.setNom(nom);
            	patient.setPrenom(prenom);
            	patient.setEmail(email);
            	patient.setNum_tel(numTel);
            	patient.setContact_urgence(request.getParameter("contact_urgence"));
            			
            	patientDao.modifierPatient(patient);
            }

            // Rediriger l'utilisateur vers la page de profil après la modification
            response.sendRedirect("profil.jsp");
        } else if ("Supprimer le profil".equals(action)) {
        	
        	if("medecin".equals(request.getParameter("type"))){
        		medecinDao.supprimerMedecin(idUtilisateur);
        	}
        	else if("patient".equals(request.getParameter("type"))){
        		patientDao.supprimerPatient(idUtilisateur);
        		
        	}
            // Rediriger l'utilisateur vers une page appropriée après la suppression
            response.sendRedirect("deconnexion.jsp");
        }
    }
	

}
