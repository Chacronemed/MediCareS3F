package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
