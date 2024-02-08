package servlets;

import beans.traitementBean;
import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "prescriptions", value = "/prescriptions")
public class prescriptions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao_factory daoFactory = dao_factory.getInstance();
        prescription_dao PrescriptionDao = daoFactory.get_prescription_dao();
        patient_dao PatientDao = daoFactory.get_patient_dao();
        utilisateur_dao UtilisateurDao = daoFactory.get_utilisateur_dao();
        rdv_dao RdvDao = daoFactory.get_rdv_dao();

        int idMedecin = Integer.parseInt(request.getParameter("id")); // Cet ID devrait être récupéré de manière appropriée, par exemple via une session
        List<Integer> rdvsAcceptes = PrescriptionDao.getRendezVousAcceptesParMedecin(idMedecin);
        System.out.println(rdvsAcceptes);
        Map<traitementBean, String> traitementsInfo = new HashMap<>();
        for (Integer idRdv : rdvsAcceptes) {
            List<traitementBean> traitements = PrescriptionDao.getTraitementsParRDV(idRdv);
            for (traitementBean traitement : traitements) {
                int idPatient = RdvDao.getIDPatientByRDV(idRdv);
                int idUtilisateur = PatientDao.getIDUtilisateurByIDPatient(idPatient);
                String userDetails = UtilisateurDao.getNomPrenomNumTelByIdUtilisateur(idUtilisateur);

                // Décomposer la chaîne pour obtenir Nom, Prénom
                String nomPrenom = ""; // Initialiser la chaîne pour stocker nom et prénom
                if (userDetails != null) {
                    String[] parts = userDetails.split(", ");
                    String nom = parts[0].split(": ")[1];
                    String prenom = parts[1].split(": ")[1];
                    String numTel = parts[2].split(": ")[1];

                    nomPrenom = nom + " " + prenom; // Concaténer pour créer une chaîne de nom complet


                }else {
                    System.out.println("Makayn walo alm3lm");
                }

                // Associer le traitement à la chaîne de nom complet
                traitementsInfo.put(traitement, nomPrenom);
            }
        }

        // Passer la map au JSP
        request.setAttribute("traitementsInfo", traitementsInfo);
        request.getRequestDispatcher("/WEB-INF/traitementdoctor.jsp").forward(request, response);
    }
}
