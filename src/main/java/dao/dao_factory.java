package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dao_factory {
	private String url;
    private String username;
    private String password;

    dao_factory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static dao_factory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        dao_factory instance = new dao_factory(
                "jdbc:mysql://localhost:3306/medicareconnect", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération du Dao
    public utilisateur_dao get_utilisateur_dao() {
        return new utilisateur_dao_impl(this);
    }
    
    public medecin_dao get_medecin_dao() {
        return new medecin_dao_impl(this);
    }
    
    public patient_dao get_patient_dao() {
        return new patient_dao_impl(this);
    }

    public dossier_medicale_dao get_dossier_medicale_dao() {
        return new dossier_medicale_dao_impl(this);
    }
    
    public rdv_dao get_rdv_dao() {
    	return new rdv_dao_impl(this);
    }
}
