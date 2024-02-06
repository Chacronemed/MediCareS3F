package dao;

import beans.ligne_traitement;
import beans.maladie;
import beans.traitementBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class prescription_dao_impl implements prescription_dao{
    private dao_factory dao_factory;

    public prescription_dao_impl(dao_factory dao_factory) {
        this.dao_factory = dao_factory;
    }
    @Override
    public void ajouterPrescription(maladie maladie, traitementBean traitement, List<ligne_traitement> lignesTraitement, int id_rdv, int id_dossier_medicale) {

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
            preparedStatement.setInt(4, id_dossier_medicale);
            preparedStatement.executeUpdate();

            // Insérer le traitement
            String queryTraitement = "INSERT INTO traitements (remarque, date_traitements, id_rdv) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryTraitement);
            preparedStatement.setString(1, traitement.getRemarque());
            preparedStatement.setDate(2, traitement.getDate_traitement());
            preparedStatement.setInt(3, id_rdv);
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

    public void updateMaladie(maladie maladie, traitementBean traitement, List<ligne_traitement> lignesTraitement){

    }
    public Integer getIDRDV(int id_traitement) {
        Integer id_rdv = null;
        String query = "SELECT id_rdv FROM traitements WHERE id_traitement = ?";

        try (Connection connection = dao_factory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id_traitement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No data found for treatment ID: " + id_traitement);
                } else {
                    while (resultSet.next()) {
                        if (id_rdv != null) {
                            throw new SQLException("More than one RDV found for treatment ID: " + id_traitement);
                        }
                        id_rdv = resultSet.getInt("id_rdv");
                    }
                }
            }
            // If you're managing a transaction, uncomment the next line
            // connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // If you're managing a transaction, consider adding rollback logic here
        }

        return id_rdv;
    }

    //pour recupere les rendez vous que le medcin a accepté
    public List<Integer> getRendezVousAcceptesParMedecin(int idMedecin) {
        List<Integer> listeRdvAcceptes = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT id_rdv FROM rendez_vous WHERE id_med = ? AND etat_rdv = '1';";

        try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, idMedecin);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("result : " + resultSet.getInt("id_rdv"));
                listeRdvAcceptes.add(resultSet.getInt("id_rdv"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            // Fermeture des ressources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connexion != null) connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listeRdvAcceptes;
    }

    public List<traitementBean> getTraitementsParRDV(int idRdv) {
        List<traitementBean> traitements = new ArrayList<>();
        String sql = "SELECT * FROM traitements WHERE id_rdv = ?;";

        try (Connection connection = dao_factory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idRdv);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    traitementBean traitement = new traitementBean();
                    traitement.setId_traitement(resultSet.getInt("id_traitement"));
                    traitement.setRemarque(resultSet.getString("remarque"));
                    traitement.setDate_traitement(resultSet.getDate("date_traitements"));
                    traitement.setId_rdv(resultSet.getInt("id_rdv"));
                    // L'attribut id_suivie semble ne pas être présent dans votre schéma SQL actuel
                    // traitement.setId_suivie(resultSet.getInt("id_suivie"));
                    traitements.add(traitement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }

        return traitements;
    }
    //récuperer l'id du dossier medicale à atravers l'id du patient
    public int  get_id_dossier_medicale(int id_rdv)
    {
        int id_dossier_medicale=-1;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = dao_factory.getConnection();
            String query = "SELECT id_dossier_medicale FROM dossier_medicale where id_patient in (SELECT id_patient from rendez_vous where id_rdv= ?);";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, id_rdv);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                id_dossier_medicale = resultSet.getInt("id_dossier_medicale");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id_dossier_medicale;

    }

}

//public List<traitementBean> ()
//récuperer l'id du dossier medicale à atravers l'id du patient
