package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import beans.rdv;
import dao.dao_factory;
import dao.medecin_dao;
import dao.rdv_dao;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Servlet implementation class rdvs
 */
public class rdvs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rdvs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dao_factory daoFactory = dao_factory.getInstance();
        rdv_dao rdvDao = daoFactory.get_rdv_dao();
        medecin_dao medecinDao = daoFactory.get_medecin_dao();
        int id_medecin = medecinDao.get_id_medecin(Integer.parseInt(request.getParameter("id")));


	    // Récupérer le filtre depuis les paramètres de l'URL
	    //String filtre = request.getParameter("id");

	    // Récupérer les rendez-vous du médecin connecté en fonction du filtre
	    List<rdv> rendezVousMedecin = rdvDao.getRdvMedecin(id_medecin);
	    // Passer les rendez-vous et le filtre à la page JSP
	    request.setAttribute("rendezVousMedecin", rendezVousMedecin);
	    
	 

	    // Redirection vers la page JSP d'affichage des rendez-vous
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/afficher_rdvs.jsp");
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
