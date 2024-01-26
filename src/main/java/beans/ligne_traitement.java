package beans;

public class ligne_traitement {

    private int id_traitement;
    private String nom_medicament;
    private float dose;
    private float quantite;
    private int frequence;
    private int duree;

    public int getId_traitement() {
        return id_traitement;
    }

    public void setId_traitement(int id_traitement) {
        this.id_traitement = id_traitement;
    }

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
}
