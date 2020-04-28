package customerManagementSoftware;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TableServiceTest {

    SqlTableService tableService = new SqlTableService();

    @Test
    public void createTableTest() throws SQLException {
        tableService.createTable("KUNDEN", "Kunden");
        boolean tableExists = tableService.tableExists("KUNDEN", "Kunden");
        Assertions.assertTrue(tableExists);
    }

    @Test
    public void createReferenceTableTest() throws SQLException {
        tableService.createReferenceTable("KUNDEN", "Termine", "Kunden");
        boolean tableExists = tableService.tableExists("KUNDEN", "Termine");
        Assertions.assertTrue(tableExists);
    }

    @Test
    public void deleteTableTest() throws SQLException {
        tableService.deleteTable("KUNDEN", "Termine");
        boolean tableExists = tableService.tableExists("KUNDEN", "Termine");
        Assertions.assertFalse(tableExists);
    }

    @Test
    public void tableExistsTest() throws SQLException {
        boolean tableExists = tableService.tableExists("KUNDEN", "Kunden");
        Assertions.assertTrue(tableExists);
    }

    @Test
    public void printTablesInDatabaseTest() throws SQLException {
        tableService.printTablesInDatabase("KUNDEN");
    }

}
