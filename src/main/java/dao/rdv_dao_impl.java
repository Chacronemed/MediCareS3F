package dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.rdv;

public class rdv_dao_impl implements rdv_dao{
	
	private dao_factory dao_factory;

    public rdv_dao_impl(dao_factory dao_factory) {
        this.dao_factory = dao_factory;
    }

	@Override
	public void ajouter(rdv rdv) {
		// TODO Auto-generated method stub
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    String query_rdv = "INSERT INTO rendez_vous (id_patient, id_med, date_debut, date_fin, heure_debut, heure_fin) VALUES (?, ?, ?, ?, ?, ?)";
	    try {
	    connexion = dao_factory.getConnection();
		preparedStatement = connexion.prepareStatement(query_rdv, PreparedStatement.RETURN_GENERATED_KEYS);
		
        preparedStatement.setInt(1, rdv.getId_patient());
        preparedStatement.setInt(2, rdv.getId_med());
        preparedStatement.setDate(3, rdv.getDate_debut());
        preparedStatement.setDate(4, rdv.getDate_fin());
        preparedStatement.setString(5, rdv.getHeure_debut());
        preparedStatement.setString(6, rdv.getHeure_fin());

        // Exécution de la requête
        preparedStatement.executeUpdate();
	    }
	    catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée dans votre application
        }
	}
	
	public List<rdv> getTodayMedcineRDV(int id_medcin){
		List<rdv> rendezVousAujourdhui = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		java.sql.Date today_date = new java.sql.Date(new java.util.Date().getTime());
//		System.out.println("############################"+today_date);
//		System.out.println("Executing query with date: " + today_date.toString());

		try {
			connexion = dao_factory.getConnection();

			String query = "SELECT * FROM rendez_vous WHERE id_med = ? AND date_rdv = ?;";

			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(	1, id_medcin);
			preparedStatement.setString(2, today_date.toString() );
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println("RDV_Dao_Impl : No data found for today's date and given medecin ID: " + id_medcin);
			} else {
			while (resultSet.next()) {
				rdv rdv = new rdv();
				rdv.setId_rdv(resultSet.getInt("id_rdv"));
				rdv.setId_patient(resultSet.getInt("id_patient"));
				rdv.setId_med(id_medcin);
				rdv.setDate_debut(resultSet.getDate("date_debut"));
				rdv.setDate_fin(resultSet.getDate("date_fin"));
				rdv.setHeure_debut(resultSet.getString("heure_debut"));
				rdv.setHeure_fin(resultSet.getString("heure_fin"));
				rdv.setDate_rdv(resultSet.getDate("date_rdv"));
				rdv.setHeure(resultSet.getString("heure"));
				rdv.setRemarque(resultSet.getString("remarque"));
				rendezVousAujourdhui.add(rdv);
				rdv.toString();
			}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		return rendezVousAujourdhui;
	}
	public List<rdv> getpatientRDV(int id_patient){
		List<rdv> rendezVous = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = dao_factory.getConnection();

			String query = "SELECT * FROM rendez_vous WHERE id_med = ?;";

			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(	1, id_patient);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println("RDV_Dao_Impl : No data found for rdv of patient ID: " + id_patient);
			} else {
				while (resultSet.next()) {
					rdv rdv = new rdv();
					rdv.setId_rdv(resultSet.getInt("id_rdv"));
					rdv.setId_patient(id_patient);
					rdv.setId_patient(resultSet.getInt("id_med"));
					rdv.setDate_debut(resultSet.getDate("date_debut"));
					rdv.setDate_fin(resultSet.getDate("date_fin"));
					rdv.setHeure_debut(resultSet.getString("heure_debut"));
					rdv.setHeure_fin(resultSet.getString("heure_fin"));
					rdv.setDate_rdv(resultSet.getDate("date_rdv"));
					rdv.setHeure(resultSet.getString("heure"));
					rdv.setRemarque(resultSet.getString("remarque"));
					rendezVous.add(rdv);
					rdv.toString();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		return rendezVous;
	}
	
	
	public List<rdv> getRdvMedecin(int id_medecin) {
	    List<rdv> rendezVous = new ArrayList<>();
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connexion = dao_factory.getConnection();
	        
	        String query = "SELECT * FROM rendez_vous WHERE id_med = ? ";
	        preparedStatement = connexion.prepareStatement(query);
	        preparedStatement.setInt(	1, id_medecin);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            rdv rdv = new rdv();
	            rdv.setId_rdv(resultSet.getInt("id_rdv"));
	            rdv.setId_patient(resultSet.getInt("id_patient"));
	            rdv.setId_med(id_medecin);
	            rdv.setDate_debut(resultSet.getDate("date_debut"));
	            rdv.setDate_fin(resultSet.getDate("date_fin"));
	            rdv.setHeure_debut(resultSet.getString("heure_debut"));
	            rdv.setHeure_fin(resultSet.getString("heure_fin"));
	            rdv.setDate_rdv(resultSet.getDate("date_rdv"));
	            rdv.setHeure(resultSet.getString("heure"));
	            rdv.setRemarque(resultSet.getString("remarque"));
	            rendezVous.add(rdv);
	            rdv.toString();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	    }

	    return rendezVous;
	}

	public void confirmer_rdv(int id_rdv, Date date_rdv, String heure)
	{
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    String query_rdv = "UPDATE rendez_vous SET date_rdv = ?, heure = ? where id_rdv = ?";
	    try {
	    connexion = dao_factory.getConnection();
		preparedStatement = connexion.prepareStatement(query_rdv, PreparedStatement.RETURN_GENERATED_KEYS);
		
        preparedStatement.setDate(1, date_rdv);
        preparedStatement.setString(2, heure);
        preparedStatement.setInt(3, id_rdv);

        // Exécution de la requête
        preparedStatement.executeUpdate();
	    }
	    catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée dans votre application
        }
		
	}
	public int getIDPatientByRDV(int id_rdv) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; // Ajout pour stocker le résultat de la requête
		String query_rdv = "SELECT id_patient FROM rendez_vous WHERE id_rdv = ?;";
		int idPatient = -1; // Valeur par défaut indiquant que l'id n'a pas été trouvé

		try {
			connexion = dao_factory.getConnection();
			preparedStatement = connexion.prepareStatement(query_rdv);
			preparedStatement.setInt(1, id_rdv);
			resultSet = preparedStatement.executeQuery();

			// Vérification si le résultat existe et récupération de l'id_patient
			if (resultSet.next()) { // S'il y a un résultat
				idPatient = resultSet.getInt("id_patient"); // Récupération de l'id_patient
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Gérer l'exception de manière appropriée dans votre application
		} finally {
			// Bloc finally pour s'assurer que toutes les ressources sont libérées
			try {
				if (resultSet != null) resultSet.close();
				if (preparedStatement != null) preparedStatement.close();
				if (connexion != null) connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return idPatient; // Retourne l'id_patient ou -1 si non trouvé
	}
	//----------obtenir le nombre de rendez-vous du jour courant
	public int get_count_rdv(int id_med)
	{
		int nombre =0;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; // Ajout pour stocker le résultat de la requête
		String query_rdv = "SELECT COUNT(*) AS nombre_rendez_vous FROM rendez_vous WHERE DATE(date_rdv) = CURDATE() and id_med=?";
		try {
			connexion = dao_factory.getConnection();
			preparedStatement = connexion.prepareStatement(query_rdv);
			preparedStatement.setInt(1, id_med);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				nombre = resultSet.getInt("nombre_rendez_vous");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (resultSet != null) resultSet.close();
				if (preparedStatement != null) preparedStatement.close();
				if (connexion != null) connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return nombre;
	}

    public int get_count_rdv_NT(int id_med)
    {
        int nombre =0;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; // Ajout pour stocker le résultat de la requête
        String query_rdv = "SELECT COUNT(*) AS nombre_rendez_vous FROM rendez_vous WHERE etat_rdv IS NULL and id_med=?;";
        try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query_rdv);
            preparedStatement.setInt(1, id_med);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nombre = resultSet.getInt("nombre_rendez_vous");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connexion != null) connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return nombre;
    }

}
