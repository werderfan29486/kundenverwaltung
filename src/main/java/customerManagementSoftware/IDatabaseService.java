package customerManagementSoftware;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseService {

    Connection connectToDatabase(String dataBaseName);
    void closeConnection(Connection conn) throws SQLException;

}
