package dao;

import beans.patient;
import beans.rdv_dash;

import java.util.List;

public interface patient_dao {
	public int ajouter(patient patient);
	public void modifierPatient(patient patient);
	public void supprimerPatient(int id_patient);
	public void ajouterDossierMedical(int id_patient, int id_dm);
	public int get_id_patient(int id_utilisateur);
	public int getIDUtilisateurByIDPatient(int id_patient);
	public List<rdv_dash> get_all_rdv_patient(int id_patient);

}
