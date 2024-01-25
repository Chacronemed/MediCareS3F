package beans;

enum Specialite {
    CARDIOLOGIE,
    PEDIATRIE,
    DERMATOLOGIE,
    
}
public class medecin extends utilisateur {
	@Override
	public String toString() {
		return "medecin [id_medecin=" + id_medecin + ", specialite=" + specialite + ", adresse=" + adresse
				+ ", id_utilisateur=" + id_utilisateur + ", getSexe()=" + getSexe() + ", getType()=" + getType()
				+ ", getId_utiliseur()=" + getId_utiliseur() + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom()
				+ ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getNum_tel()=" + getNum_tel()
				+ "]";
	}

	private int id_medecin;
	private String specialite;
	private String adresse;
	private int id_utilisateur;
	
	

	public int getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public int getId_medecin() {
		return id_medecin;
	}

	public void setId_medecin(int id_medecin) {
		this.id_medecin = id_medecin;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
}
