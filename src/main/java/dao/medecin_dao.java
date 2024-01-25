package dao;

import java.util.List;

import beans.medecin;

public interface medecin_dao {
	public boolean ajouter(medecin medecin);
	public void modifierMedecin(medecin medecin);
	public void supprimerMedecin(int id_medecin);
	public List<medecin> listerMedecins();
	public int get_id_medecin(int id_utilisateur);

}
