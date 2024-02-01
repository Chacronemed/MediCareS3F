package dao;

import beans.ligne_traitement;
import beans.maladie;
import beans.traitementBean;

import java.util.List;

public interface prescription_dao {
    public void ajouterPrescription(maladie maladie, traitementBean traitement, List<ligne_traitement> lignesTraitement);
    public Integer getIDRDV(int id_traitement);
    public List<Integer> getRendezVousAcceptesParMedecin(int idMedecin);
    public List<traitementBean> getTraitementsParRDV(int idRdv);
}
