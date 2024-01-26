package beans;

import java.sql.Date;

public class traitementBean {
    private int id_traitement;
    private String remarque;
    private Date date_traitement;
    private int id_rdv;
    private int id_suivie;
    // Getters and setters

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
}
