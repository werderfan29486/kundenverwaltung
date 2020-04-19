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
        System.out.println(preparedStmt.getParameterMetaData());
        conn.close();
    }

    @Override
    public void deleteTable() throws SQLException {
        Connection conn = database1.connectToDatabase();
        PreparedStatement preparedStmt = conn.prepareStatement("DROP TABLE Kunden ");
        preparedStmt.execute();
        conn.close();
    }

    private static final String SQL_CREATE ="CREATE TABLE Kunden ("
            + "UID INT NOT NULL AUTO_INCREMENT,"
            + "CUSTOMERNUMBER VARCHAR(45) NOT NULL,"
            + "NAME VARCHAR(45) NOT NULL,"
            + "FIRSTNAME VARCHAR(45) NOT NULL,"
            + "STREET VARCHAR(45) NOT NULL,"
            + "HOUSENUMBER VARCHAR(45) NOT NULL,"
            + "POSTALCODE VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY (UID))";

    public String insertStatement() {
        return " insert into Kunden (customernumber, name, firstname, street, housenumber, postalcode)"
                + " values (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        Connection conn = database1.connectToDatabase();
        String query = insertStatement();
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, customer.getCustomernumber());
        preparedStmt.setString (2, customer.getName());
        preparedStmt.setString (3, customer.getFirstname());
        preparedStmt.setString (4, customer.getStreet());
        preparedStmt.setString (5, customer.getHousenumber());
        preparedStmt.setString (6, customer.getPostalcode());
        preparedStmt.execute();
        database1.closeConnection(conn);
    }

    @Override
    public void deleteCustomer(Customer customer) throws SQLException {
        Connection conn = database1.connectToDatabase();
        String query = "delete from Kunden where customernumber = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, customer.getCustomernumber());
        preparedStmt.execute();
        System.out.println("Kunde " + customer.getName() + " gelöscht");
        database1.closeConnection(conn);
    }
}
