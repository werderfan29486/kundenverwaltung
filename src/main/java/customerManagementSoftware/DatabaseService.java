package customerManagementSoftware;

import java.sql.*;

public class DatabaseService implements IDatabaseService {


    public void initDatabase(Connection conn) throws SQLException {
        Statement stmt = null;
        System.out.println("Creating database...");
        stmt = conn.createStatement();
        String sql = "CREATE DATABASE KUNDEN";
        stmt.executeUpdate(sql);
        System.out.println("Database created successfully...");
    }

    @Override
    public Connection connectToDatabase(String dataBaseName) {
        Statement stmt = null;
        Connection conn = null;
        try {   Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName + "?user=root&password=example&useLegacyDatetimeCode=false&serverTimezone=UTC");
                stmt = conn.createStatement();
            System.out.println("Verbindung hergestellt");
            ResultSet resultSet = conn.getMetaData().getCatalogs();
            boolean dataBaseExists = false;

            while (resultSet.next()) {

                String databaseList = resultSet.getString(1);
                if(databaseList.equals(dataBaseName)) {
                    System.out.println("Datenbank bereits angelegt");
                    dataBaseExists = true;
                }

            }
            resultSet.close();
            if (!dataBaseExists) {
                initDatabase(conn);
            }




        } catch (SQLException | ClassNotFoundException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }

    @Override
    public void closeConnection(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
            System.out.print("Verbindung beendet");
        }
    }
}
