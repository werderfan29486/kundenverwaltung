package customerManagementSoftware;

import java.sql.SQLException;

public interface I_SQL_Customerservice {

    public void createTable(String databaseName, String tableName) throws SQLException;
    public void deleteTable(String databaseName, String tableName) throws SQLException;
    public void insertCustomer(Customer customer, String databaseName, String tableName) throws SQLException;
    public void deleteCustomer(Customer customer, String databaseName, String tableName) throws SQLException;
    public void updateCustomerName(Customer customer, String dataBaseName, String tableName, String whatToUpdate, String newValue) throws SQLException;
    public void printAllCustomers(String dataBaseName, String tableName) throws SQLException;

}
