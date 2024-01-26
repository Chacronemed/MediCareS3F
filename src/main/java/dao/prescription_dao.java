package dao;

import beans.ligne_traitement;
import beans.maladie;
import beans.traitementBean;

import java.util.List;

public interface prescription_dao {
    public void ajouterPrescription(maladie maladie, traitementBean traitement, List<ligne_traitement> lignesTraitement);
}
