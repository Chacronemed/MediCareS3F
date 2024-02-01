package dao;
import beans.utilisateur;
import jakarta.servlet.http.HttpServletRequest;

public interface utilisateur_dao {
	
	public int inscription(utilisateur utilisateur, String typeUtilisateur);
	public utilisateur connexion(String email, String password);

	public utilisateur get_session(HttpServletRequest request);
	public String getNomPrenomNumTelByIdUtilisateur(int id_utilisateur);

}
