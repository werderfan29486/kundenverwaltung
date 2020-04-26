package customerManagementSoftware;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseServiceTest {

    DatabaseService database1 = new DatabaseService();

    @Test
    public void connectToDataBaseTest() throws SQLException {
        Connection conn = database1.connectToDatabase("KUNDEN");
        Assertions.assertTrue(conn.isValid(1));
        database1.closeConnection(conn);
    }

    @Test
    public void closeConnectionTest() throws SQLException {
        Connection conn = database1.connectToDatabase("KUNDEN");
        database1.closeConnection(conn);
        Assertions.assertTrue(conn.isClosed());
    }

    @Test
    public void initDataBaseTest() throws SQLException, ClassNotFoundException {
        database1.initDatabase("TEST");
        Connection conn = database1.connectToDatabase("TEST");
        Assertions.assertTrue(conn.isValid(1));
    }

    @Test
    public void dropDatabaseTest() throws SQLException, ClassNotFoundException {
        database1.initDatabase("TEST");
        database1.dropDatabase("TEST");
        boolean dataBaseExists = database1.dataBaseExists("TEST");
        Assertions.assertFalse(dataBaseExists);
    }

    @Test
    public void dataBaseExistsTest() throws SQLException, ClassNotFoundException {
        boolean exists = database1.dataBaseExists("KUNDEN");
        Assertions.assertTrue(exists);
    }

}
