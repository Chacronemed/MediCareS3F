package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.patient;

public class patient_dao_impl implements patient_dao {
	
	private dao_factory dao_factory;

    public patient_dao_impl(dao_factory dao_factory) {
        this.dao_factory = dao_factory;
    }
    
    public int  get_id_patient(int id_utilisateur)
    {
    	int id_patient=-1;
    	Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
	        connexion = dao_factory.getConnection();
	        
	        String query = "SELECT id_patient FROM patients where id_utilisateur = ?";
	        preparedStatement = connexion.prepareStatement(query);
	        preparedStatement.setInt(1, id_utilisateur);
	        resultSet = preparedStatement.executeQuery();
	        if(resultSet.next())
	        	id_patient = resultSet.getInt("id_patient");
		} catch (SQLException e) {
	        e.printStackTrace();
		}
		
    	return id_patient;
    	
    }
	@Override
	public int ajouter(patient patient) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query_patient = "INSERT INTO patients (id_utilisateur, contact_urgence) VALUES (?, ?)";
		try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query_patient,PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, patient.getId_utilisateur());
            preparedStatement.setString(2, patient.getContact_urgence());
            int rowsAffected = preparedStatement.executeUpdate();

            //return rowsAffected > 0;
            
            if (rowsAffected == 0) {
                throw new SQLException("Échec de la création du dossier médical. Aucune ligne affectée.");
            }

            // Récupérer l'ID généré pour le dossier médical
            resultSet = preparedStatement.getGeneratedKeys();
            
            
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Échec de la récupération de l'ID du dossier médical généré.");
            }
            
            
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		
	    
		return -1;
	}
	@Override
	public void modifierPatient(patient patient) {
		// TODO Auto-generated method stub
		Connection connexion = null;
	    PreparedStatement preparedStatementPatients = null;
	    PreparedStatement preparedStatementUtilisateurs = null;

	    try {
	        connexion = dao_factory.getConnection();
	        connexion.setAutoCommit(false);

	        // Mise à jour de la table medecins
	        preparedStatementPatients = connexion.prepareStatement("UPDATE patients SET contact_urgence = ? WHERE id_utilisateur = ?");
	        preparedStatementPatients.setString(1, patient.getContact_urgence());
	        preparedStatementPatients.setInt(2, patient.getId_utilisateur());
	        preparedStatementPatients.executeUpdate();
	        
	        // Mise à jour de la table utilisateurs
	        preparedStatementUtilisateurs = connexion.prepareStatement("UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, num_tel = ? WHERE id_utilisateur = ?");
	        preparedStatementUtilisateurs.setString(1, patient.getNom());
	        preparedStatementUtilisateurs.setString(2, patient.getPrenom());
	        preparedStatementUtilisateurs.setString(3, patient.getEmail());
	        preparedStatementUtilisateurs.setString(4, patient.getNum_tel());
	        preparedStatementUtilisateurs.setInt(5, patient.getId_utilisateur());
	        preparedStatementUtilisateurs.executeUpdate();

	        // Commit de la transaction
	        connexion.commit();
	    } catch (SQLException e) {
	        // En cas d'erreur, rollback la transaction
	        if (connexion != null) {
	            try {
	                connexion.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        e.printStackTrace(); // Gérer l'exception de manière appropriée
	    } finally {
	        // Fermeture des ressources
	        try {
	            if (preparedStatementPatients != null) {
	            	preparedStatementPatients.close();
	            }
	            if (preparedStatementUtilisateurs != null) {
	                preparedStatementUtilisateurs.close();
	            }
	            if (connexion != null) {
	                connexion.setAutoCommit(true);
	                connexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	@Override
	/*public void supprimerPatient(int id_patient) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = dao_factory.getConnection();
	        preparedStatement = connexion.prepareStatement("DELETE FROM utilisateurs WHERE id_utilisateur = ?");
	        preparedStatement.setInt(1, id_patient);
	        preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace(); // Gérer l'exception de manière appropriée
	        }
		
	}
	*/
	public void supprimerPatient(int id_utilisateur) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;

    try {
        connexion = dao_factory.getConnection();

        // Supprimer d'abord les enregistrements dans la table `dossier_medicale`
        preparedStatement = connexion.prepareStatement("DELETE FROM dossier_medicale WHERE id_patient IN (SELECT id_patient FROM patients WHERE id_utilisateur = ?)");
        preparedStatement.setInt(1, id_utilisateur);
        preparedStatement.executeUpdate();

        // Ensuite, supprimer les enregistrements dans la table `patients`
        preparedStatement = connexion.prepareStatement("DELETE FROM patients WHERE id_utilisateur = ?");
        preparedStatement.setInt(1, id_utilisateur);
        preparedStatement.executeUpdate();

        // Enfin, supprimer l'utilisateur dans la table `utilisateurs`
        preparedStatement = connexion.prepareStatement("DELETE FROM utilisateurs WHERE id_utilisateur = ?");
        preparedStatement.setInt(1, id_utilisateur);
        preparedStatement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace(); // Gérer l'exception de manière appropriée
    } finally {
        // Fermer les ressources (connexion, preparedStatement, etc.) dans le bloc "finally"
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


	@Override
	public void ajouterDossierMedical(int id_patient, int id_dm) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connexion = dao_factory.getConnection();
	        

	        // Mise à jour de la table patients
	        preparedStatement = connexion.prepareStatement("UPDATE patients SET id_dossier_medicale = ? WHERE id_patient = ?");
	        preparedStatement.setInt(1, id_dm);
	        preparedStatement.setInt(2, id_patient);
	        preparedStatement.executeUpdate();

	        // Validation de la transaction
	        
	    } catch (SQLException e) {
	        // En cas d'erreur, rollback la transaction
	        if (connexion != null) {
	            try {
	                connexion.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        e.printStackTrace(); // Gérer l'exception de manière appropriée
	    } finally {
	        // Fermeture des ressources
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connexion != null) {
	            try {
	                connexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


}
