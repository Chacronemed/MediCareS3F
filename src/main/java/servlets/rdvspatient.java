package servlets;

import beans.rdv;
import beans.rdv_dash;
import dao.dao_factory;
import dao.medecin_dao;
import dao.patient_dao;
import dao.rdv_dao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class rdvs
 */
public class rdvspatient extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public rdvspatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dao_factory daoFactory = dao_factory.getInstance();
        rdv_dao rdvDao = daoFactory.get_rdv_dao();
        patient_dao patientDao = daoFactory.get_patient_dao();
        int id_patient = patientDao.get_id_patient(Integer.parseInt(request.getParameter("id")));


	    // Récupérer le filtre depuis les paramètres de l'URL
	    //String filtre = request.getParameter("id"); get_all_rdv_patient

	    // Récupérer les rendez-vous du médecin connecté en fonction du filtre
		List<rdv_dash> rendezVousPatient = patientDao.get_all_rdv_patient(id_patient);
	    // Passer les rendez-vous et le filtre à la page JSP
	    request.setAttribute("rendezVousPatient", rendezVousPatient);
	    
	 

	    // Redirection vers la page JSP d'affichage des rendez-vous
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/afficher_rdvsPatient.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
