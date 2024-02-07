package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération et invalidation de la session courante
        HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session
        if (session != null) {
            session.invalidate(); // Invalider la session pour déconnecter l'utilisateur
        }

        // Redirection vers la page de connexion ou toute autre page de votre choix
        response.sendRedirect("connexion"); // Assurez-vous que le chemin est correct selon votre structure de projet
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // La méthode POST peut rediriger vers la méthode GET pour gérer la déconnexion
        doGet(request, response);
    }
}