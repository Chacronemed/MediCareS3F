package beans;

import java.sql.Date;

public class maladie {
    private int id_maladie;
    private String nom;
    private String description;
    private Date date_maladie;
    private int id_dossier_medical;

    public int getId_maladie() {
        return id_maladie;
    }

    public void setId_maladie(int id_maladie) {
        this.id_maladie = id_maladie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_maladie() {
        return date_maladie;
    }

    public void setDate_maladie(Date date_maladie) {
        this.date_maladie = date_maladie;
    }

    public int getId_dossier_medical() {
        return id_dossier_medical;
    }

    public void setId_dossier_medical(int id_dossier_medical) {
        this.id_dossier_medical = id_dossier_medical;
    }
}
