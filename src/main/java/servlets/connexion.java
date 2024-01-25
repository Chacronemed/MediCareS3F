package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import beans.utilisateur;
import dao.dao_factory;
import dao.utilisateur_dao;

/**
 * Servlet implementation class connexion
 */
public class connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// Récupérer les paramètres du formulaire
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Initialiser la factory DAO
        dao_factory daoFactory = dao_factory.getInstance();
        utilisateur_dao utilisateurDao = daoFactory.get_utilisateur_dao();

        // Appeler la méthode de connexion dans le DAO
        utilisateur user = utilisateurDao.connexion(email, password);

        if (user != null) {
            // Créer une session et stocker l'utilisateur connecté
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", user);

            // Rediriger vers la page d'accueil (ou autre page après connexion réussie)
            request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
        } else {
            // Afficher un message d'erreur si la connexion échoue
            request.setAttribute("erreurMessage", "Adresse e-mail ou mot de passe incorrect");
            request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
        }
    }
}

