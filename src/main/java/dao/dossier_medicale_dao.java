package dao;

import beans.maladie;

import java.util.List;

public interface dossier_medicale_dao {
	
	public int ajouter(int id_patient);

	public List<maladie> listerMaladiesParPatient(int id_patient);

}
