package customerManagementSoftware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SqlTableService implements ISqlTableService {

    DatabaseService database1 = new DatabaseService();
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement preparedStmt = null;
    Logger logger = LoggerFactory.getLogger("");
    ResultSet rs = null;

    @Override
    public void createTable(String dataBaseName, String tableName) throws SQLException {
        conn = database1.connectToDatabase(dataBaseName);
        String query = CREATE_TABLE_CUSTOMERS.replace("$tableName", tableName);
        try {
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
        }

        catch (SQLException e) {
            logger.info("Datenbank " + tableName + " existiert bereits");
        }
        finally {
            conn.close();
        }
    }

    public void createReferenceTable(String dataBaseName, String tableName, String reference) throws SQLException {
        conn = database1.connectToDatabase(dataBaseName);
        String query = CREATE_TABLE_CUSTOMERDATES.replace("$tableName", tableName);
        String query2 = query.replace("$referencetable", reference);
        try {
            preparedStmt = conn.prepareStatement(query2);
            preparedStmt.execute();
        }

        catch (SQLException e) {
            logger.info("Datenbank " + tableName + " existiert bereits");
        }
        finally {
            conn.close();
        }
    }

    @Override
    public void deleteTable(String databaseName, String tableName) throws SQLException {
        conn = database1.connectToDatabase(databaseName);
        String strQuery = "DROP TABLE $tableName";
        String query = strQuery.replace("$tableName", tableName);

        try {
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            logger.info("Tabelle " + tableName + " gelöscht");
        }
        catch (SQLException e) {
            logger.info("Tabelle " + tableName + " existiert nicht. Löschvorgang nicht möglich");
        }
        finally {
            conn.close();
        }
    }

    public void printTablesInDatabase(String databaseName) throws SQLException {
        conn = database1.connectToDatabase(databaseName);
        printTables(conn);
        conn.close();
    }

    public void printTables(Connection conn) throws SQLException {
        DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
        rs = meta.getTables(null, null, null, new String[] {
                "TABLE"
        });
        logger.info("All table names are in test database:");
        while (rs.next()) {
            String tblName = rs.getString("TABLE_NAME");
            logger.info(tblName);
        }
    }

    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE $tableName ("
            + "UID INT NOT NULL AUTO_INCREMENT,"
            + "CUSTOMERNUMBER VARCHAR(45) NOT NULL, "
            + "NAME VARCHAR(45) NOT NULL, "
            + "FIRSTNAME VARCHAR(45) NOT NULL, "
            + "STREET VARCHAR(45) NOT NULL, "
            + "HOUSENUMBER VARCHAR(45) NOT NULL, "
            + "POSTALCODE VARCHAR(45) NOT NULL, "
            + "PRIMARY KEY (UID))";

    private static final String CREATE_TABLE_CUSTOMERDATES = "CREATE TABLE $tableName ("
            + "DATE_ID INT NOT NULL AUTO_INCREMENT, "
            + "UID INT, "
            + "DOB DATE NOT NULL,"
            + "PRIMARY KEY (DATE_ID)), "
            + "CONSTRAINT fk_uid FOREIGN KEY (UID) REFERENCES $referencetable(UID)";
            //+  "ON DELETE CASCADE"
            //+  "ON UPDATE CASCADE";
            // "FOREIGN KEY (UID) REFERENCES $referencetable(UID)";

    //HELPMETHODS for test classes

    //for createTableTest(), deleteTableTest();
    public boolean tableExists(String databaseName, String tableName) throws SQLException {
        conn = database1.connectToDatabase(databaseName);
        DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
        rs = meta.getTables(null, null, null, new String[] {
                "TABLE"
        });
        boolean tableExists = false;

        return searchTable(rs, tableExists, tableName);
    }

    //for tableExists();
    public boolean searchTable(ResultSet rs, boolean tableExists, String tableName) throws SQLException {
        while (rs.next()) {

            String tableList = rs.getString("TABLE_NAME");
            if(tableList.equals(tableName)) {
                tableExists = true;
            }

        }
        rs.close();
        if (!tableExists) {
            return false;
        }
        return true;

    }


}
