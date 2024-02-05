package dao;

import beans.ligne_traitement;
import beans.maladie;
import beans.traitementBean;

import java.util.List;

public interface prescription_dao {
    public void ajouterPrescription(maladie maladie, traitementBean traitement, List<ligne_traitement> lignesTraitement, int id_rdv, int id_dossier_medicale);
    public Integer getIDRDV(int id_traitement);
    public List<Integer> getRendezVousAcceptesParMedecin(int idMedecin);
    public List<traitementBean> getTraitementsParRDV(int idRdv);
    public int  get_id_dossier_medicale(int id_patient);

}
