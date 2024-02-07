package dao;
import beans.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dossier_medicale_dao_impl implements dossier_medicale_dao{
	
	private dao_factory dao_factory;

    public dossier_medicale_dao_impl(dao_factory dao_factory) {
        this.dao_factory = dao_factory;
    }

	@Override
	public int ajouter(int id_patient) {
		// TODO Auto-generated method stub
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Requête SQL pour insérer un dossier médical et récupérer l'ID généré
        String query = "INSERT INTO dossier_medicale (id_patient) VALUES (?)";

        try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id_patient);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
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
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            
        }

        return -1; // Retourner -1 en cas d'échec
    }

    public List<maladie> listerMaladiesParPatient(int id_patient) {
        List<maladie> maladies = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Requête SQL pour récupérer les maladies d'un patient spécifique
        String query = "SELECT m.nom, m.description, m.date_maladie FROM maladies m " +
                       "JOIN dossier_medicale dm ON m.id_dossier_medicale = dm.id_dossier_medicale " +
                       "WHERE dm.id_patient = ?;";

        try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, id_patient);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                Date dateMaladie = resultSet.getDate("date_maladie");

                maladie maladie = new maladie();
                maladie.setNom(nom);
                maladie.setDescription(description);
                maladie.setDate_maladie(dateMaladie);
                maladies.add(maladie);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            // Fermer les ressources
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { /* Gérer l'exception */ }
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { /* Gérer l'exception */ }
            if (connexion != null) try { connexion.close(); } catch (SQLException e) { /* Gérer l'exception */ }
        }

        return maladies;
    }

}
