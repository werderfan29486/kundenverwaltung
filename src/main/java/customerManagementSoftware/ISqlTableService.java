package customerManagementSoftware;

import java.sql.SQLException;

public interface ISqlTableService {

    void createTable(String databaseName, String tableName) throws SQLException;
    void deleteTable(String databaseName, String tableName) throws SQLException;

}
