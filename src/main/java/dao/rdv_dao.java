package dao;

import java.sql.Date;
import java.util.List;

import beans.rdv;

public interface rdv_dao {
	public void ajouter(rdv rdv);
	public List<rdv> getRdvMedecin(int idMedecin);
	public void confirmer_rdv(int id_rdv, Date date_rdv, String heure_rdv);

}
