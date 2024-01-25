package dao;
import beans.utilisateur;

public interface utilisateur_dao {
	
	public int inscription(utilisateur utilisateur, String typeUtilisateur);
	public utilisateur connexion(String email, String password);
	

}
