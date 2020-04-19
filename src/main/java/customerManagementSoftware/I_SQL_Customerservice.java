package customerManagementSoftware;

import java.sql.SQLException;

public interface I_SQL_Customerservice {

    public void createTable() throws SQLException;
    public String insertStatement();
    public void insertCustomer(Customer customer) throws SQLException;

}
