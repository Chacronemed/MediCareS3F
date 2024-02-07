package servlets;

import dao.dao_factory;
import dao.medecin_dao_impl;
import dao.patient_dao;
import dao.medecin_dao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.http.Part;
import beans.medecin;
import beans.patient;

/**
 * Servlet implementation class gestion_utilisateur
 */
@MultipartConfig
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
	private medecin_dao medecinDao;
	private static final String WEB_CONTENT_DIR   = "C:/Users/Chacrone/Desktop/ProjetIltelij/MediCare/src/main/webapp/";

	private static final String BLOGS_IMAGES_DIR = "photos/";
	public void init() {
		dao_factory dao_Factory = dao.dao_factory.getInstance();
		this.medecinDao = new medecin_dao_impl(dao_Factory);
	}
	private static String getMeidaExt(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			System.out.println("Content-Disposition Header: " + cd); // Ajout de cette ligne pour débogue
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");

				return filename.substring(filename.lastIndexOf('.') + 1);
			}
		}
		return null;
	}

	private static String savePart(Part image) {
		long id = System.currentTimeMillis();
		String ext = getMeidaExt(image);
		String uploadPath = WEB_CONTENT_DIR + BLOGS_IMAGES_DIR + id + "." + ext;

		try (InputStream input = image.getInputStream();
			 OutputStream output = new FileOutputStream(uploadPath)) {

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return BLOGS_IMAGES_DIR + id + "." + ext;
	}

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
		System.out.println("666666");
		int idUtilisateur = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        dao_factory daoFactory = dao_factory.getInstance();
        medecin_dao medecinDao = daoFactory.get_medecin_dao();
        patient_dao patientDao = daoFactory.get_patient_dao();
        if ("Enregistrer les modification".equals(action)) {
			System.out.println("777777");
            // Modification de l'utilisateur
            String nom = request.getParameter("nom");
			System.out.println(nom);
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
				Part image = request.getPart("image");

				String imageLink = savePart(image);
				System.out.println(imageLink);
				medecin.setImageLink(imageLink);
            	
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
            response.sendRedirect("Dashboard");
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
