package customerManagementSoftware;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class databaseservicetest {

    DatabaseService database1 = new DatabaseService();

    @Test
    public void connectToDataBase() {
        database1.connectToDatabase();
    }

    @Test
    public void closeConnection() throws SQLException {
        Connection conn = database1.connectToDatabase();
        database1.closeConnection(conn);
    }

}
