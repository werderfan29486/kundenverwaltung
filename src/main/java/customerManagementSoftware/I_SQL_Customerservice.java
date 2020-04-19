package customerManagementSoftware;

import java.sql.SQLException;

public interface I_SQL_Customerservice {

    public void createTable() throws SQLException;
    public void deleteTable() throws SQLException;
    public void insertCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(Customer customer) throws SQLException;
    public void printAllCustomers() throws SQLException;

}
