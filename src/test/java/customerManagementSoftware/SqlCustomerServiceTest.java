package customerManagementSoftware;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class SqlCustomerServiceTest {

    SqlCustomerService sqlCustomerService = new SqlCustomerService();
    Customer customer1 = new Customer("Gantzert", "Sebastian", "Auf der Schmelz", "30", "64380");
    Customer customer2 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");
    Customer customer3 = new Customer("Schwarck", "Martin", "Keineahnung", "11", "42380");

    @Test
    public void insertCustomerTest() throws SQLException {
        sqlCustomerService.insertCustomer(customer1, "KUNDEN", "Kunden");
        sqlCustomerService.insertCustomer(customer2, "KUNDEN", "Kunden");
        sqlCustomerService.insertCustomer(customer3, "KUNDEN", "Kunden");
        boolean customerExists = sqlCustomerService.customerExists("KUNDEN", "Kunden", customer1);
        Assertions.assertTrue(customerExists);
    }


    @Test
    public void deleteCustomerTest() throws SQLException {
        //sqlCustomerService.deleteCustomer(customer1, "KUNDEN", "Kunden");
        sqlCustomerService.deleteCustomer(customer2, "KUNDEN", "Kunden");
        boolean customerExists = sqlCustomerService.customerExists("KUNDEN", "Kunden", customer2);
        Assertions.assertFalse(customerExists);
    }

    @Test
    public void updateCustomerNameTest() throws SQLException {
        sqlCustomerService.updateCustomer(customer1, "KUNDEN", "Kunden", "firstname", "Alex");
        String updatedValue = sqlCustomerService.checkUpdatedValue("KUNDEN", "Kunden", customer1, "firstname");
        Assertions.assertEquals("Alex", updatedValue);
    }

    @Test
    public void customerExistsTest() throws SQLException {
        boolean customerExists = sqlCustomerService.customerExists("KUNDEN", "Kunden", customer1);
        Assertions.assertTrue(customerExists);
    }

    @Test
    public void checkUpdatedValueTest() throws SQLException {
        String test = sqlCustomerService.checkUpdatedValue("KUNDEN", "Kunden", customer1, "firstname");
        Assertions.assertEquals("Sebastian", test);
    }

    @Test
    public void printAllCustomersTest() throws SQLException {
        sqlCustomerService.showAllCustomers("KUNDEN", "Kunden");
    }

}
