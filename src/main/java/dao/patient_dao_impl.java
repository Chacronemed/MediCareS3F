package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.patient;
import beans.rdv_dash;

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

	public int getIDUtilisateurByIDPatient(int id_patient) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; // Ajout pour stocker le résultat de la requête
		String query_rdv = "SELECT id_utilisateur FROM patients WHERE id_patient = ?;";
		int idUtilisateur = -1; // Valeur par défaut indiquant que l'id n'a pas été trouvé

		try {
			connexion = dao_factory.getConnection();
			preparedStatement = connexion.prepareStatement(query_rdv);
			preparedStatement.setInt(1, id_patient);
			resultSet = preparedStatement.executeQuery();

			// Vérification si le résultat existe et récupération de l'id_patient
			if (resultSet.next()) { // S'il y a un résultat
				idUtilisateur = resultSet.getInt("id_utilisateur"); // Récupération de l'id_patient
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

		return idUtilisateur; // Retourne l'id_utilisateur ou -1 si non trouvé
	}

	public List<rdv_dash> get_all_rdv_patient(int id_patient) {
		List<rdv_dash> list_rdv_dash = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT r.*,  p.*, u.* FROM rendez_vous r INNER JOIN medecins p ON r.id_med = p.id_med INNER JOIN utilisateurs u ON p.id_utilisateur = u.id_utilisateur WHERE r.id_patient=?;";
		try {
			connexion = dao_factory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_patient);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String numTel = resultSet.getString("num_tel");
				String email = resultSet.getString("email");
				String sexe = resultSet.getString("sexe");

				rdv_dash rdv_dash = new rdv_dash();

				rdv_dash.setNom(nom);
				rdv_dash.setPrenom(prenom);
				rdv_dash.setNum_tel(numTel);
				rdv_dash.setEmail(email);
				rdv_dash.setSexe(sexe);
				rdv_dash.setDate_rdv(resultSet.getDate("date_rdv"));
				rdv_dash.setHeure(resultSet.getString("heure"));
				rdv_dash.setId_rdv(resultSet.getInt("id_rdv"));
				rdv_dash.setId_patient(resultSet.getInt("id_patient"));
				rdv_dash.setId_med(resultSet.getInt("id_med"));
				list_rdv_dash.add(rdv_dash);

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

		return list_rdv_dash;
	}
}
