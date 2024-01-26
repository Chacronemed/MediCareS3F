package dao;

import beans.ligne_traitement;
import beans.maladie;
import beans.traitementBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class prescription_dao_impl implements prescription_dao{
    private dao_factory dao_factory;

    public prescription_dao_impl(dao_factory dao_factory) {
        this.dao_factory = dao_factory;
    }
    @Override
    public void ajouterPrescription(maladie maladie, traitementBean traitement, List<ligne_traitement> lignesTraitement) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dao_factory.getConnection();
            connection.setAutoCommit(false);
            // Insérer la maladie
            String queryMaladie = "INSERT INTO maladies (nom, description, date_maladie, id_dossier_medicale) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryMaladie, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, maladie.getNom());
            preparedStatement.setString(2, maladie.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(maladie.getDate_maladie().getTime()));
            preparedStatement.setInt(4, 12);
            preparedStatement.executeUpdate();

            // Insérer le traitement
            String queryTraitement = "INSERT INTO traitements (remarque, date_traitements, id_rdv) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryTraitement);
            preparedStatement.setString(1, traitement.getRemarque());
            preparedStatement.setDate(2, traitement.getDate_traitement());
            preparedStatement.setInt(3, 5);
            preparedStatement.executeUpdate();

            // Insérer les lignes de traitemet

            for (ligne_traitement ligne : lignesTraitement) {
                String queryLigneTraitement = "INSERT INTO ligne_traitement (id_traitement, nom_medicament, dose, quantite, frequence, duree) VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(queryLigneTraitement);
                preparedStatement.setInt(1, 2);
                preparedStatement.setString(2, ligne.getNom_medicament());
                preparedStatement.setFloat(3, ligne.getDose());
                preparedStatement.setFloat(4, ligne.getQuantite());
                preparedStatement.setInt(5, ligne.getFrequence());
                preparedStatement.setInt(6, ligne.getDuree());
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            // Gérer les erreurs de base de données
            e.printStackTrace();
            try {
                // En cas d'erreur, annuler la transaction
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // Fermer la connexion
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
