package customerManagementSoftware;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class sqlcustomerservicetest {

    SqlCustomerservice sqlCustomerService = new SqlCustomerservice();
    Customer customer1 = new Customer("Gantzert", "Sebastian", "Auf der Schmelz", "30", "64380");
    Customer customer2 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");

    @Test
    public void createTableTest() throws SQLException {
        sqlCustomerService.createTable("KUNDEN", "Kunden");
    }

    @Test
    public void deleteTableTest() throws SQLException {
       // sqlCustomerService.createTable("KUNDEN");
        sqlCustomerService.deleteTable("KUNDEN", "Test");
    }

    @Test
    public void insertCustomer() throws SQLException {
        //sqlCustomerService.createTable("KUNDEN", "Kunden");
        sqlCustomerService.insertCustomer(customer2, "KUNDEN", "Kunden");
        sqlCustomerService.printAllCustomers("KUNDEN", "Kunden");
    }

    @Test
    public void deleteCustomerTest() throws SQLException {
        sqlCustomerService.deleteCustomer(customer2, "KUNDEN", "Kunden");
        sqlCustomerService.printAllCustomers("KUNDEN", "Kunden");
    }

    @Test
    public void updateCustomerNameTest() throws SQLException {
        sqlCustomerService.updateCustomerName(customer1, "KUNDEN", "Kunden", "firstname", "Alex");
        sqlCustomerService.printAllCustomers("KUNDEN", "Kunden");
    }

    @Test
    public void testPrintAllCustomers() throws SQLException {
        sqlCustomerService.printAllCustomers("KUNDEN", "Kunden");
    }
}
