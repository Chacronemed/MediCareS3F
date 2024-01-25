package beans;

enum type_utilisateur{patient, medecin};

public class utilisateur {
    private int id_utilistaeur;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String num_tel;
    private String type;
    private String sexe;

    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getId_utiliseur() {
        return id_utilistaeur;
    }
    public void setId_utilistaeur(int id_utilistaeur) {
        this.id_utilistaeur = id_utilistaeur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNum_tel() {
        return num_tel;
    }
    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }



}
