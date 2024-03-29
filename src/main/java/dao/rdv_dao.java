package dao;

import java.sql.Date;
import java.util.List;

import beans.rdv;

public interface rdv_dao {
	public void ajouter(rdv rdv);
	public List<rdv> getRdvMedecin(int idMedecin);
	public void confirmer_rdv(int id_rdv, Date date_rdv, String heure_rdv);
	public List<rdv> getTodayMedcineRDV(int id_medcin);
	public int getIDPatientByRDV(int id_rdv);
	public int get_count_rdv(int id_med);
	public int get_count_rdv_NT(int id_med);

}
