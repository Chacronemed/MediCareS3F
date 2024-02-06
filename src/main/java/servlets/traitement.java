package servlets;

import beans.ligne_traitement;
import beans.maladie;
import beans.traitementBean;
import dao.dao_factory;
import dao.prescription_dao;
import dao.rdv_dao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "traitement", value = "/traitement")
public class traitement extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/traitement.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        dao_factory daoFactory = dao_factory.getInstance();
        prescription_dao prescriptionDAO = daoFactory.get_prescription_dao();

        // Récupérer les paramètres du formulaire JSP

        HttpSession session = request.getSession();
        int id_rdv = (int) session.getAttribute("id_rdv");
        String nomMaladie = request.getParameter("nomMaladie");
        String descriptionMaladie = request.getParameter("descriptionMaladie");
        Date dateMaladie = Date.valueOf(request.getParameter("dateMaladie"));

        String remarqueTraitement = request.getParameter("remarqueTraitement");
        Date dateTraitement = Date.valueOf(request.getParameter("dateTraitement"));

        // Récupérer les listes de lignes de traitement
        String[] nomsMedicament = request.getParameterValues("nomMedicament[]");
        String[] doses = request.getParameterValues("dose[]");
        String[] quantites = request.getParameterValues("quantite[]");
        String[] frequences = request.getParameterValues("frequence[]");
        String[] durees = request.getParameterValues("duree[]");

        // Créer une liste de lignes de traitement
        List<ligne_traitement> lignesTraitement = new ArrayList<>();
        for (int i = 0; i < nomsMedicament.length; i++) {
            ligne_traitement ligne = new ligne_traitement();
            ligne.setNom_medicament(nomsMedicament[i]);
            ligne.setDose(Float.parseFloat(doses[i]));
            ligne.setQuantite( Float.parseFloat(quantites[i]));
            ligne.setFrequence(Integer.parseInt(frequences[i]));
            ligne.setDuree(Integer.parseInt(durees[i]));
            lignesTraitement.add(ligne);
        }

        // Créer une maladie
        maladie maladie = new maladie();
        maladie.setNom(nomMaladie);
        maladie.setDescription(descriptionMaladie);
        maladie.setDate_maladie(dateMaladie);

        // Créer un traitement
        traitementBean traitement = new traitementBean();
        traitement.setRemarque(remarqueTraitement);
        traitement.setDate_traitement(dateTraitement);

        // Appeler la méthode DAO pour insérer les données
        //prescription_dao prescriptionDAO = new prescription_dao();
        int id_dossier_medicale = prescriptionDAO.get_id_dossier_medicale(id_rdv);
        prescriptionDAO.ajouterPrescription(maladie, traitement, lignesTraitement, id_rdv, id_dossier_medicale);

        // Rediriger vers une page de confirmation
        response.sendRedirect("confirmation.jsp");
    }
}