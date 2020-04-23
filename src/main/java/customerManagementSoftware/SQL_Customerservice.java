package customerManagementSoftware;

import java.sql.*;

public class SQL_Customerservice implements I_SQL_Customerservice {

    DatabaseService database1 = new DatabaseService();


    @Override
    public void createTable(String dataBaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(dataBaseName);
        String query = SQL_CREATE.replace("$tableName", tableName);
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
        }

        catch (SQLException e) {
            System.out.println("Datenbank " + tableName + " existiert bereits");
        }
        finally {
            conn.close();
        }
    }

    @Override
    public void deleteTable(String databaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        String strQuery = "DROP TABLE $tableName";
        String query = strQuery.replace("$tableName", tableName);

        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
        }
        catch (SQLException e) {
            System.out.println("Tabelle " + tableName + " existiert nicht. Löschvorgang nicht möglich");
        }
        finally {
            conn.close();
        }
    }

    /*public boolean tableExists(String databaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        Statement stmt =  conn.createStatement();
        String strQuery = "SELECT * FROM $database";
        String query = strQuery.replace("$database", databaseName);
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())*/

    private static final String SQL_CREATE = "CREATE TABLE $tableName ("
            + "UID INT NOT NULL AUTO_INCREMENT,"
            + "CUSTOMERNUMBER VARCHAR(45) NOT NULL,"
            + "NAME VARCHAR(45) NOT NULL,"
            + "FIRSTNAME VARCHAR(45) NOT NULL,"
            + "STREET VARCHAR(45) NOT NULL,"
            + "HOUSENUMBER VARCHAR(45) NOT NULL,"
            + "POSTALCODE VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY (UID))";

    public String insertStatement(String tablename) {

        String strQuery = " insert into $tableName (customernumber, name, firstname, street, housenumber, postalcode)"
                + " values (?, ?, ?, ?, ?, ?)";
        String query = strQuery.replace("$tableName", tablename);
        return query;
    }

    @Override
    public void insertCustomer(Customer customer, String databaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        String query = insertStatement(tableName);

        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, customer.getCustomernumber());
            preparedStmt.setString(2, customer.getName());
            preparedStmt.setString(3, customer.getFirstname());
            preparedStmt.setString(4, customer.getStreet());
            preparedStmt.setString(5, customer.getHousenumber());
            preparedStmt.setString(6, customer.getPostalcode());
            preparedStmt.execute();
        }
        catch (SQLException e){
            System.out.println("Tabelle " + tableName + " nicht vorhanden");
        }
        finally {
            database1.closeConnection(conn);
        }

    }

    @Override
    public void deleteCustomer(Customer customer, String databaseName, String tablename) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        String strQuery = "delete from $tableName where customernumber = ?";
        String query = strQuery.replace("$tableName", tablename);

        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, customer.getCustomernumber());
            preparedStmt.execute();
            System.out.println("Kunde " + customer.getName() + " gelöscht");
        }
        catch (SQLException e) {
            System.out.println("Kunde " + customer.getName() + " nicht vorhanden. Löschvorgang nicht möglich");
        }
        finally {
            database1.closeConnection(conn);
        }
    }

    @Override
    public void updateCustomerName(Customer customer, String databaseName, String tableName, String whatToUpdate, String newValue) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        String strQuery = "UPDATE $tableName "
                + "SET $columnToUpdate = ? "
                + "WHERE customernumber = ?";
        String query = strQuery.replace("$tableName", tableName);
        query = query.replace("$columnToUpdate", whatToUpdate);
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, newValue);
        preparedStmt.setString(2, customer.getCustomernumber());
        preparedStmt.execute();
        database1.closeConnection(conn);
    }

    @Override
    public void printAllCustomers(String databaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        Statement st = conn.createStatement();
        String strQuery = "select * from $tableName";
        String query =strQuery.replace("$tableName",tableName);
        ResultSet rs = st.executeQuery(query);

        System.out.println("Kundennummer" + "\t" + "Name" +"\t"+ "Firstname"+"\t"+ "Street"+ "\t" + "Housenumber" + "\t" + "Postalcode");
        while(rs.next())
        {
            String columnName1 = rs.getString("Customernumber");
            String columnName2 = rs.getString("Name");
            String columnName3 = rs.getString("Firstname");
            String columnName4 = rs.getString("Street");
            String columnName5 = rs.getString("Housenumber");
            String columnName6 = rs.getString("Postalcode");

            System.out.println(columnName1+"\t"+ columnName2+"\t"+ columnName3 +"\t" +columnName4+"\t"+ columnName5+"\t"+ columnName6);
        }
    }
}
