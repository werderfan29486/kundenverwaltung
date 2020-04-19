package customerManagementSoftware;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseService {

    Connection connectToDatabase();
    void closeConnection(Connection conn) throws SQLException;

}
