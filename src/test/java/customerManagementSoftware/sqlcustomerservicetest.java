package customerManagementSoftware;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class sqlcustomerservicetest {

    SQL_Customerservice sqlCustomerService = new SQL_Customerservice();
    Customer customer1 = new Customer("Gantzert", "Sebastian", "Auf der Schmelz", "30", "64380");

    @Test
    public void createTableTest() throws SQLException {
        sqlCustomerService.createTable();
    }

    @Test
    public void deleteTableTest() throws SQLException {
        sqlCustomerService.createTable();
        sqlCustomerService.deleteTable();
    }

    @Test
    public void deleteCustomer() throws SQLException {
        sqlCustomerService.deleteCustomer(customer1);
    }

    @Test
    public void testPrintAllCustomers() throws SQLException {
        sqlCustomerService.printAllCustomers();
    }
}
