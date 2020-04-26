package customerManagementSoftware;

import java.sql.*;

public class SqlCustomerService implements ISqlCustomerService {

    DatabaseService database1 = new DatabaseService();

// MAIN METHODS
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
            System.out.println("Tabelle " + tableName + " gelöscht");
        }
        catch (SQLException e) {
            System.out.println("Tabelle " + tableName + " existiert nicht. Löschvorgang nicht möglich");
        }
        finally {
            conn.close();
        }
    }



    @Override
    public void insertCustomer(Customer customer, String databaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);

        try {
            if (customerExists(databaseName, tableName, customer)) {
                System.out.println("Kunde schon vorhanden");
            }
            else {
                setTableColumns(conn, customer, tableName);
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

        try {
           if (!customerExists(databaseName, tablename, customer)) {
               throw new CustomerDoesNotExistException();
           }
               deleteQuery(conn, customer, tablename);
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (CustomerDoesNotExistException e) {
            System.out.println(e.getMessage() + " Löschvorgang nicht möglich.");
        } finally {
            database1.closeConnection(conn);
        }
    }

    @Override
    public void updateCustomer(Customer customer, String databaseName, String tableName, String whatToUpdate, String newValue) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);

        try {
            if (!customerExists(databaseName, tableName, customer)) {
                throw new CustomerDoesNotExistException();
            }
            updateQuery(conn, tableName, customer, whatToUpdate, newValue);
        }
        catch (CustomerDoesNotExistException e) {
            System.out.println(e.getMessage() + " Updatevorgang nicht möglich.");
        }
        finally {
            database1.closeConnection(conn);
        }
    }

    @Override
    public void printAllCustomers(String databaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        System.out.println("Kundennummer" + "\t" + "Name" +"\t\t"+ "Firstname"+"\t"+ "Street"+ "\t\t\t\t" + "Housenumber" + "\t\t" + "Postalcode");
        printColumns(conn, tableName);
        conn.close();
    }

    //HELP METHODS

    //for createTable();
    private static final String SQL_CREATE = "CREATE TABLE $tableName ("
            + "UID INT NOT NULL AUTO_INCREMENT,"
            + "CUSTOMERNUMBER VARCHAR(45) NOT NULL,"
            + "NAME VARCHAR(45) NOT NULL,"
            + "FIRSTNAME VARCHAR(45) NOT NULL,"
            + "STREET VARCHAR(45) NOT NULL,"
            + "HOUSENUMBER VARCHAR(45) NOT NULL,"
            + "POSTALCODE VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY (UID))";

    //for insertCustomer();
    public void setTableColumns(Connection conn, Customer customer, String tableName) throws SQLException {
        String query = insertStatement(tableName);
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, customer.getCustomerNumber());
        preparedStmt.setString(2, customer.getName());
        preparedStmt.setString(3, customer.getFirstName());
        preparedStmt.setString(4, customer.getStreet());
        preparedStmt.setString(5, customer.getHouseNumber());
        preparedStmt.setString(6, customer.getPostalCode());
        preparedStmt.execute();
        preparedStmt.close();
    }

    public String insertStatement(String tablename) {

        String strQuery = " insert into $tableName (customernumber, name, firstname, street, housenumber, postalcode)"
                + " values (?, ?, ?, ?, ?, ?)";
        return strQuery.replace("$tableName", tablename);
    }

    //for deleteCustomer();
    public void deleteQuery(Connection conn, Customer customer, String tableName) throws SQLException {
        String strQuery = "delete from $tableName where customernumber = ?";
        String query = strQuery.replace("$tableName", tableName);
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, customer.getCustomerNumber());
        preparedStmt.execute();
        preparedStmt.close();
        System.out.println("Kunde " + customer.getName() + " gelöscht");
    }

    //for updateCustomer();
    public void updateQuery(Connection conn, String tableName, Customer customer, String whatToUpdate, String newValue) throws SQLException {
        String strQuery = "UPDATE $tableName "
                + "SET $columnToUpdate = ? "
                + "WHERE customernumber = ?";
        String query = strQuery.replace("$tableName", tableName);
        query = query.replace("$columnToUpdate", whatToUpdate);

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, newValue);
        preparedStmt.setString(2, customer.getCustomerNumber());
        preparedStmt.execute();
    }

    public String checkUpdatedValue(String databaseName, String tableName, Customer customer, String whatToCheck) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        String sql = "SELECT $column FROM $tableName WHERE customernumber = ?";
        String query1 = sql.replace("$tableName", tableName);
        String query = query1.replace("$column", whatToCheck);

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, customer.getCustomerNumber());
        ResultSet rs = prepStmt.executeQuery();
        String value = null;

        while (rs.next()) {
            value = rs.getString(whatToCheck);
        }
        prepStmt.close();
        conn.close();
        return value;
    }

    //for printAllCustomers();
    public void printColumns(Connection conn, String tableName) throws SQLException {
        Statement st = conn.createStatement();
        String strQuery = "select * from $tableName";
        String query =strQuery.replace("$tableName",tableName);
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String columnName1 = rs.getString("Customernumber");
            String columnName2 = rs.getString("Name");
            String columnName3 = rs.getString("Firstname");
            String columnName4 = rs.getString("Street");
            String columnName5 = rs.getString("Housenumber");
            String columnName6 = rs.getString("Postalcode");

            System.out.println(columnName1 + "\t\t\t" + columnName2 + "\t" + columnName3 + "\t\t" + columnName4 + "\t\t" + columnName5 + "\t\t\t\t" + columnName6);
        }
        st.close();
        rs.close();
    }



    public void printTablesInDatabase(String databaseName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        printTables(conn);
        conn.close();
    }

    public void printTables(Connection conn) throws SQLException {
        ResultSet rs = null;
        DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
        rs = meta.getTables(null, null, null, new String[] {
                "TABLE"
        });
        System.out.println("All table names are in test database:");
        while (rs.next()) {
            String tblName = rs.getString("TABLE_NAME");
            System.out.println(tblName);
        }
    }


    //for insert, delete, updateCustomer();
    public boolean customerExists(String databaseName, String tableName, Customer customer) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);
        Statement stmt = conn.createStatement();
        String strQuery = "select * from $tableName";
        String query = strQuery.replace("$tableName", tableName);
        ResultSet rs = stmt.executeQuery(query);
        boolean exists = false;
        return searchCustomer(rs, exists, customer);
    }

     public boolean searchCustomer(ResultSet rs, boolean exists, Customer customer ) throws SQLException {
         while (rs.next()) {
             String customerList = rs.getString("Customernumber");
             if (customerList.equals(customer.getCustomerNumber())) {
                 exists = true;
             }
         }
         rs.close();
            if (!exists) {
                return false;
            }
         return true;
     }

     //HELPMETHODS for test classes

    //for createTableTest(), deleteTableTest();
    public boolean tableExists(String databaseName, String tableName) throws SQLException {
        Connection conn = database1.connectToDatabase(databaseName);

        ResultSet rs = null;
        DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
        rs = meta.getTables(null, null, null, new String[] {
                "TABLE"
        });
        boolean tableExists = false;

        return searchTable(rs, tableExists, tableName);
    }

    //for tableExists();
    public boolean searchTable(ResultSet rs, boolean tableExists, String tableName) throws SQLException {
        while (rs.next()) {

            String tableList = rs.getString("TABLE_NAME");
            if(tableList.equals(tableName)) {
                tableExists = true;
            }

        }
        rs.close();
        if (!tableExists) {
            return false;
        }
        return true;

    }


    //for insertCustomerTest();
        public boolean checkCustomerDates(String databaseName, String tableName, Customer customer) throws SQLException {
            Connection conn = database1.connectToDatabase(databaseName);
            String strQuery = "select * from $tableName WHERE customernumber = ?";
            String query = strQuery.replace("$tableName", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customer.getCustomerNumber());
            stmt.execute();
            ResultSet rs = stmt.executeQuery();

            return datesEqual(rs, customer);

        }
        //for checkCustomerDates
        public boolean datesEqual(ResultSet rs, Customer customer) throws SQLException {
            while (rs.next()) {
                String customerNumber = rs.getString("Customernumber");
                String name = rs.getString("Name");
                String firstName = rs.getString("Firstname");
                String street = rs.getString("Street");
                String houseNumber = rs.getString("Housenumber");
                String postalCode = rs.getString("Postalcode");

                if (customerNumber.equals(customer.getCustomerNumber()) && name.equals(customer.getName())
                        && firstName.equals(customer.getFirstName()) && street.equals(customer.getStreet()) &&
                        houseNumber.equals(customer.getHouseNumber()) && postalCode.equals(customer.getPostalCode())) {
                    return true;
                }
            }
            return false;
        }



}
