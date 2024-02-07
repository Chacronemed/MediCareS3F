package dao;

import beans.*;

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
        ResultSet resultSet = null;

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

            // Récupérer l'id de la maladie généré
            resultSet = preparedStatement.getGeneratedKeys();
            int id_maladie = -1;
            if (resultSet.next()) {
                id_maladie = resultSet.getInt(1);
            }

            // Insérer le traitement
            String queryTraitement = "INSERT INTO traitements (remarque, date_traitements, id_rdv) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryTraitement, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, traitement.getRemarque());
            preparedStatement.setDate(2, traitement.getDate_traitement());
            preparedStatement.setInt(3, id_rdv);
            preparedStatement.executeUpdate();

            // Récupérer l'id du traitement généré
            resultSet = preparedStatement.getGeneratedKeys();
            int id_traitement = -1;
            if (resultSet.next()) {
                id_traitement = resultSet.getInt(1);
            }

            // Insérer les lignes de traitement
            String queryLigneTraitement = "INSERT INTO ligne_traitement (id_traitement, nom_medicament, dose, quantite, frequence, duree) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryLigneTraitement);
            for (ligne_traitement ligne : lignesTraitement) {
                preparedStatement.setInt(1, id_traitement);
                preparedStatement.setString(2, ligne.getNom_medicament());
                preparedStatement.setFloat(3, ligne.getDose());
                preparedStatement.setFloat(4, ligne.getQuantite());
                preparedStatement.setInt(5, ligne.getFrequence());
                preparedStatement.setInt(6, ligne.getDuree());
                preparedStatement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
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

    public int get_count_prescription(int id_med)
    {
        int nombre =0;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; // Ajout pour stocker le résultat de la requête
        String query_rdv = "SELECT COUNT(*) AS nombre_prescription FROM traitements WHERE id_rdv in (SELECT id_rdv from rendez_vous where id_med = ?);";
        try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query_rdv);
            preparedStatement.setInt(1, id_med);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nombre = resultSet.getInt("nombre_prescription");
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

    public int get_count_soigne(int id_med)
    {
        int nombre =0;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; // Ajout pour stocker le résultat de la requête
        String query_rdv = "SELECT COUNT(*) AS nombre_soigne FROM rendez_vous WHERE id_med = ? and etat_rdv = '1';";
        try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query_rdv);
            preparedStatement.setInt(1, id_med);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nombre = resultSet.getInt("nombre_soigne");
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

    public List<prescription> get_prescription(int id_rdv)
    {
        List<prescription> list_pres = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT t.*, m.*, lt.* FROM traitements t\n INNER JOIN ligne_traitement lt ON t.id_traitement = lt.id_traitement INNER JOIN maladies m ON m.id_dossier_medicale = ( SELECT dm.id_dossier_medicale FROM dossier_medicale dm INNER JOIN rendez_vous rv ON dm.id_patient = rv.id_patient WHERE rv.id_rdv = t.id_rdv LIMIT 1) WHERE t.id_rdv = ? AND m.date_maladie = t.date_traitements;";
        try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, id_rdv);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                String remarque = resultSet.getString("remarque");
                String nom_medicament = resultSet.getString("nom_medicament");

                prescription prescription = new prescription();

                prescription.setNom(nom);
                prescription.setDescription(description);
                prescription.setRemarque(remarque);
                prescription.setNom_medicament(nom_medicament);
                prescription.setDate_maladie(resultSet.getDate("date_maladie"));
                prescription.setId_rdv(resultSet.getInt("id_rdv"));
                prescription.setDose(resultSet.getFloat("dose"));
                prescription.setQuantite(resultSet.getFloat("quantite"));
                prescription.setFrequence(resultSet.getInt("frequence"));
                prescription.setDuree(resultSet.getInt("duree"));
                prescription.setId_traitement(resultSet.getInt("id_traitement"));
                list_pres.add(prescription);

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

        return list_pres;
    }

}

//public List<traitementBean> ()
//récuperer l'id du dossier medicale à atravers l'id du patient
