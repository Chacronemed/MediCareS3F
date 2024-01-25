package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import dao.dao_factory;
import dao.medecin_dao;
import dao.rdv_dao;

/**
 * Servlet implementation class confirmation_rdv
 */
public class confirmation_rdv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	dao_factory daoFactory = dao_factory.getInstance();
	 medecin_dao medecinDao = daoFactory.get_medecin_dao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmation_rdv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Récupération des autres informations du formulaire
		int id_rdv = Integer.parseInt(request.getParameter("id_rdv"));
	    String date_rdv = request.getParameter("date_rdv");
	    String heure_rdv = request.getParameter("heure_rdv");
	    
	    rdv_dao rdv_dao = daoFactory.get_rdv_dao();
        rdv_dao.confirmer_rdv(id_rdv, Date.valueOf(date_rdv), heure_rdv);
	    
		
	}

}
