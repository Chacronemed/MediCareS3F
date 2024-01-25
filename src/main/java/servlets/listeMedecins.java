package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import beans.medecin;
import dao.dao_factory;
import dao.medecin_dao;

/**
 * Servlet implementation class listeMedecins
 */
public class listeMedecins extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listeMedecins() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la liste des médecins depuis le DAO
        dao_factory daoFactory = dao_factory.getInstance();
        medecin_dao medecinDao = daoFactory.get_medecin_dao();
        List<medecin> listeMedecins = medecinDao.listerMedecins();

        // Ajouter la liste des médecins à la requête
        request.setAttribute("listeMedecins", listeMedecins);

        // Rediriger vers la page JSP d'affichage
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listeMedecins.jsp");
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
