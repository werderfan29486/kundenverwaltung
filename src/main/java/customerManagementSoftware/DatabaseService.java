package customerManagementSoftware;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

import com.mysql.cj.protocol.Resultset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseService implements IDatabaseService {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    Logger logger = LoggerFactory.getLogger("");

    public void initDatabase(String databaseName) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=example&useLegacyDatetimeCode=false&serverTimezone=UTC");
        stmt = conn.createStatement();

        ResultSet rs = conn.getMetaData().getCatalogs();
        boolean dataBaseExists = false;

        while (rs.next()) {
            String databaseList = rs.getString(1);
            if (databaseList.equals(databaseName)) {
                dataBaseExists = true;
                logger.info("Datenbank " + databaseName + " existiert bereits");
            }
        }
        rs.close();

        if (!dataBaseExists) {
            String sql = "CREATE DATABASE $databaseName";
            String query = sql.replace("$databaseName", databaseName);
            stmt.executeUpdate(query);
            logger.info("Creating database...");
            logger.info("Database " + databaseName + " created successfully...");
        }

    }

    @Override
    public void dropDatabase(String databaseName) throws SQLException {
        conn = connectToDatabase(databaseName);
        String sql = "DROP DATABASE $database";
        String query = sql.replace("$database", databaseName);
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

        try {   Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName + "?user=root&password=example&useLegacyDatetimeCode=false&serverTimezone=UTC");
                stmt = conn.createStatement();

        } catch (SQLException | ClassNotFoundException | NullPointerException  e) {
            throw new Error("Datenbank nicht vorhanden");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                throw new Error("Datenbank nicht vorhanden");
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
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=example&useLegacyDatetimeCode=false&serverTimezone=UTC");

        rs = conn.getMetaData().getCatalogs();
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
