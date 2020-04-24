package customerManagementSoftware;

import java.sql.*;

public class SqlCustomerservice implements IsqlCustomerservice {

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
            if (customerExists(databaseName, tableName, customer)) {
                System.out.println("Kunde schon vorhanden");
            }
            else {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, customer.getCustomernumber());
                preparedStmt.setString(2, customer.getName());
                preparedStmt.setString(3, customer.getFirstname());
                preparedStmt.setString(4, customer.getStreet());
                preparedStmt.setString(5, customer.getHousenumber());
                preparedStmt.setString(6, customer.getPostalcode());
                preparedStmt.execute();
            }
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
           if (!customerExists(databaseName, tablename, customer)) {
               throw new customerDoesNotExistException();
           }
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, customer.getCustomernumber());
                preparedStmt.execute();
                System.out.println("Kunde " + customer.getName() + " gelöscht");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (customerDoesNotExistException e) {
            System.out.println(e.getMessage() + " Löschvorgang nicht möglich.");
        } finally {
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

        try {
            if (!customerExists(databaseName, tableName, customer)) {
                throw new customerDoesNotExistException();
            }

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newValue);
            preparedStmt.setString(2, customer.getCustomernumber());
            preparedStmt.execute();
        }
        catch (customerDoesNotExistException e) {
            System.out.println(e.getMessage() + " Updatevorgang nicht möglich.");
        }
        finally {
            database1.closeConnection(conn);
        }
    }

    @Override
    public void printAllCustomers(String databaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        Statement st = conn.createStatement();
        String strQuery = "select * from $tableName";
        String query =strQuery.replace("$tableName",tableName);
        ResultSet rs = st.executeQuery(query);

        System.out.println("Kundennummer" + "\t" + "Name" +"\t\t"+ "Firstname"+"\t"+ "Street"+ "\t\t\t\t" + "Housenumber" + "\t\t" + "Postalcode");
        while(rs.next())
        {
            String columnName1 = rs.getString("Customernumber");
            String columnName2 = rs.getString("Name");
            String columnName3 = rs.getString("Firstname");
            String columnName4 = rs.getString("Street");
            String columnName5 = rs.getString("Housenumber");
            String columnName6 = rs.getString("Postalcode");

            System.out.println(columnName1+"\t\t\t"+ columnName2+"\t"+ columnName3 +"\t\t" +columnName4+"\t\t"+ columnName5+"\t\t\t\t"+ columnName6);
        }
    }

    public void printTablesInDatabase(String databaseName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        Statement stmt = conn.createStatement();

        ResultSet rs = null;
        DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
        rs = meta.getTables(null, null, null, new String[] {
                "TABLE"
        });
        int count = 0;
        System.out.println("All table names are in test database:");
        while (rs.next()) {
            String tblName = rs.getString("TABLE_NAME");
            System.out.println(tblName);
            count++;
        }
        System.out.println(count + " Rows in set ");
        stmt.close();
        conn.close();
    }

    public boolean tableExists(String databaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);

        ResultSet rs = null;
        DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
        rs = meta.getTables(null, null, null, new String[] {
                "TABLE"
        });
        boolean tableExists = false;

        while (rs.next()) {

            String tableList = rs.getString("TABLE_NAME");
            if(tableList.equals(tableName)) {
                System.out.println("Tabelle existiert");
                tableExists = true;
            }

        }
        rs.close();
        if (!tableExists) {
            System.out.println("Tabelle existiert nicht");
            return false;
        }
        return true;
    }

    public boolean customerExists(String databaseName, String tableName, Customer customer) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        Statement stmt = conn.createStatement();
        String strQuery = "select * from $tableName";
        String query = strQuery.replace("$tableName", tableName);
        ResultSet rs = stmt.executeQuery(query);
        boolean exists = false;

        while (rs.next()) {
            String customerList = rs.getString("Customernumber");
            if (customerList.equals(customer.getCustomernumber())) {
                exists = true;
            }
        }
           rs.close();

            if (!exists) {
                return false;
            }

            return true;
        }

}
