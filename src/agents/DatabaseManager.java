package agents;

import java.sql.*;

public class DatabaseManager {

    private Connection conn = null;

    public void connect() {
        String url = "jdbc:mysql://localhost:3306/sma";
        String user = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
    }

//    public void createTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS agents.statistique (\n"
//                + "    agent VARCHAR(255) NOT NULL,\n"
//                + "    montant_total_envoye INT NOT NULL,\n"
//                + "    montant_total_recu INT NOT NULL,\n"
//                + "    nb_envois INT NOT NULL,\n"
//                + "    nb_receptions INT NOT NULL,\n"
//                + "    PRIMARY KEY (agent)\n"
//                + ");";
//        try {
//            Statement stmt = conn.createStatement();
//            stmt.execute(sql);
//            System.out.println("Table created");
//        } catch (SQLException e) {
//            System.out.println("Error creating table");
//            e.printStackTrace();
//        }
//    }

    public void insertData(String agent, int montantTotalEnvoye, int montantTotalRecu, int nbEnvois, int nbReceptions) {
        String sql = "INSERT INTO statistique (agent, montant_total_envoye, montant_total_recu, nb_envois, nb_receptions)\n"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, agent);
            pstmt.setInt(2, montantTotalEnvoye);
            pstmt.setInt(3, montantTotalRecu);
            pstmt.setInt(4, nbEnvois);
            pstmt.setInt(5, nbReceptions);
            pstmt.executeUpdate();
            System.out.println("Data inserted");
        } catch (SQLException e) {
            System.out.println("Error inserting data");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            conn.close();
            System.out.println("Disconnected from database");
        } catch (SQLException e) {
            System.out.println("Error disconnecting from database");
            e.printStackTrace();
        }
    }

}
