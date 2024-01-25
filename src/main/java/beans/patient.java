package beans;

public class patient extends utilisateur {
	private int id_patient;
	private Boolean est_malade;
	private int id_utilisateur;
	private String contact_urgence;
	public String getContact_urgence() {
		return contact_urgence;
	}
	public void setContact_urgence(String contact_urgence) {
		this.contact_urgence = contact_urgence;
	}
	private int id_dossier_medicale;
	
	public int getId_patient() {
		return id_patient;
	}
	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}
	public Boolean getEst_malade() {
		return est_malade;
	}
	public void setEst_malade(Boolean est_malade) {
		this.est_malade = est_malade;
	}
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public int getId_dossier_medicale() {
		return id_dossier_medicale;
	}
	public void setId_dossier_medicale(int id_dossier_medicale) {
		this.id_dossier_medicale = id_dossier_medicale;
	}
	
	

}
