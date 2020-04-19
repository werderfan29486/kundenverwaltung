package customerManagementSoftware;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class sql_customerservicetest {

    SQL_Customerservice sql_customerservice = new SQL_Customerservice();
    Customer customer1 = new Customer("Gantzert", "Sebastian", "Auf der Schmelz", "30", "64380");

    @Test
    public void createtabletest() throws SQLException {
        sql_customerservice.createTable();
    }

    @Test
    public void deletetabletest() throws SQLException {
        sql_customerservice.createTable();
        sql_customerservice.deleteTable();
    }

    @Test
    public void deleteCustomer() throws SQLException {
        sql_customerservice.deleteCustomer(customer1);
    }
}
