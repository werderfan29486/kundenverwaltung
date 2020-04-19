package customerManagementSoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL_Customerservice implements I_SQL_Customerservice {

    DatabaseService database1 = new DatabaseService();


    @Override
    public void createTable() throws SQLException {
        Connection conn = database1.connectToDatabase();
        PreparedStatement preparedStmt = conn.prepareStatement(SQL_CREATE);
        preparedStmt.execute();
        conn.close();
    }

    private static final String SQL_CREATE ="CREATE TABLE Kunden ("
            + "UID INT NOT NULL AUTO_INCREMENT,"
            + "NAME VARCHAR(45) NOT NULL,"
            + "FIRSTNAME VARCHAR(45) NOT NULL,"
            + "STREET VARCHAR(45) NOT NULL,"
            + "HOUSENUMBER VARCHAR(45) NOT NULL,"
            + "POSTALCODE VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY (UID))";

    @Override
    public String insertStatement() {
        return " insert into Kunden (name, firstname, street, housenumber, postalcode)"
                + " values (?, ?, ?, ?, ?)";
    }

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        Connection conn = database1.connectToDatabase();
        String query = insertStatement();
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, customer.getName());
        preparedStmt.setString (2, customer.getFirstname());
        preparedStmt.setString (3, customer.getStreet());
        preparedStmt.setString (4, customer.getHousenumber());
        preparedStmt.setString (5, customer.getPostalcode());
        preparedStmt.execute();
        database1.closeConnection(conn);
    }

}
