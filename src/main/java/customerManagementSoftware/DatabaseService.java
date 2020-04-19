package customerManagementSoftware;

import java.sql.*;

public class DatabaseService implements IDatabaseService {

    @Override
    public Connection connectToDatabase() {
        Statement stmt = null;
        Connection conn = null;
        try {   Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Kunden?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC");
            stmt = conn.createStatement();
            System.out.println("Verbindung hergestellt");

            // stmt.executeUpdate(CREATE_TABLE_SQL);
            // stmt.executeUpdate(INSERT_INTO_TABLE);

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
