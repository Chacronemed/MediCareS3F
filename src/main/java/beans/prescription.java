package beans;

import java.sql.Date;

public class prescription {
    private int id_traitement;
    private String remarque;
    private Date date_traitement;
    private int id_rdv;
    private int id_suivie;
    private int id_maladie;
    private String nom;
    private String description;
    private Date date_maladie;
    private int id_dossier_medical;
    private String nom_medicament;
    private float dose;
    private float quantite;

    public String getNom_medicament() {
        return nom_medicament;
    }

    public void setNom_medicament(String nom_medicament) {
        this.nom_medicament = nom_medicament;
    }

    public float getDose() {
        return dose;
    }

    public void setDose(float dose) {
        this.dose = dose;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    private int frequence;
    private int duree;
    public int getId_traitement() {
        return id_traitement;
    }

    public void setId_traitement(int id_traitement) {
        this.id_traitement = id_traitement;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Date getDate_traitement() {
        return date_traitement;
    }

    public void setDate_traitement(Date date_traitement) {
        this.date_traitement = date_traitement;
    }

    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    public int getId_suivie() {
        return id_suivie;
    }

    public void setId_suivie(int id_suivie) {
        this.id_suivie = id_suivie;
    }
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
