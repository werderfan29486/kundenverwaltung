package customerManagementSoftware;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DatabaseService implements IDatabaseService {


    public void initDatabase(String databaseName) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=example&useLegacyDatetimeCode=false&serverTimezone=UTC");
        stmt = conn.createStatement();

        ResultSet rs = conn.getMetaData().getCatalogs();
        boolean dataBaseExists = false;

        while (rs.next()) {
            String databaseList = rs.getString(1);
            if (databaseList.equals(databaseName)) {
                dataBaseExists = true;
                System.out.println("Existiert bereits");
            }
        }
        rs.close();

        if (!dataBaseExists) {
            String sql = "CREATE DATABASE $databaseName";
            String query = sql.replace("$databaseName", databaseName);
            stmt.executeUpdate(query);
            System.out.println("Creating database...");
            System.out.println("Database " + databaseName + " created successfully...");
        }

    }

    @Override
    public void dropDatabase(String databaseName) throws SQLException {

        Connection conn = connectToDatabase(databaseName);
        String sql = "DROP DATABASE $database";
        String query = sql.replace("$database", databaseName);
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet rs = conn.getMetaData().getCatalogs();

        while (rs.next()) {
            String databaseList = rs.getString(1);
            if (databaseList.equals(databaseName)) {
                stmt.executeUpdate(query);
            }
        }
        rs.close();
        stmt.close();
        conn.close();
    }

    @Override
    public Connection connectToDatabase(String dataBaseName) {
        Statement stmt = null;
        Connection conn = null;
        try {   Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName + "?user=root&password=example&useLegacyDatetimeCode=false&serverTimezone=UTC");
                stmt = conn.createStatement();

        } catch (SQLException | ClassNotFoundException e) {
            throw new Error("Datenbank nicht vorhanden");
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
        }
    }

    public boolean dataBaseExists(String databaseName) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=example&useLegacyDatetimeCode=false&serverTimezone=UTC");

        ResultSet rs = conn.getMetaData().getCatalogs();
        boolean dataBaseExists = false;

        while (rs.next()) {
            String databaseList = rs.getString(1);
            if (databaseList.equals(databaseName)) {
                dataBaseExists = true;
            }
        }
        rs.close();

        if (!dataBaseExists) {
            return false;
        }
        return true;
    }
}
