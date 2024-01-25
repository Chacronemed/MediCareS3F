package dao;

import beans.patient;

public interface patient_dao {
	public int ajouter(patient patient);
	public void modifierPatient(patient patient);
	public void supprimerPatient(int id_patient);
	public void ajouterDossierMedical(int id_patient, int id_dm);
	public int get_id_patient(int id_utilisateur);

}
