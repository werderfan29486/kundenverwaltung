package customerManagementSoftware;

import java.sql.SQLException;

public interface ISqlCustomerService {

    public void insertCustomer(Customer customer, String databaseName, String tableName) throws SQLException;
    public void deleteCustomer(Customer customer, String databaseName, String tableName) throws SQLException;
    public void updateCustomer(Customer customer, String dataBaseName, String tableName, String whatToUpdate, String newValue) throws SQLException;
    public void showAllCustomers(String dataBaseName, String tableName) throws SQLException;

}
