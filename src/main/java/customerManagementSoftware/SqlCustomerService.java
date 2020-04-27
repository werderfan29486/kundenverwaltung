package customerManagementSoftware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SqlCustomerService implements ISqlCustomerService {

    DatabaseService database1 = new DatabaseService();
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement preparedStmt = null;
    ResultSet rs = null;
    Logger logger = LoggerFactory.getLogger("");

// MAIN METHODS

    @Override
    public void insertCustomer(Customer customer, String databaseName, String tableName) throws SQLException {
        conn = database1.connectToDatabase(databaseName);

        try {
            if (customerExists(databaseName, tableName, customer)) {
                logger.info("Kunde schon vorhanden");
            }
            else {
                setTableColumns(conn, customer, tableName);
            }
        }
        catch (SQLException e){
            logger.info("Tabelle " + tableName + " nicht vorhanden");
        }
        finally {
            database1.closeConnection(conn);
        }

    }



    @Override
    public void deleteCustomer(Customer customer, String databaseName, String tablename) throws SQLException {
        conn = database1.connectToDatabase(databaseName);

        try {
           if (!customerExists(databaseName, tablename, customer)) {
               throw new CustomerDoesNotExistException();
           }
               deleteQuery(conn, customer, tablename);
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (CustomerDoesNotExistException e) {
            logger.info(e.getMessage() + " Löschvorgang nicht möglich.");
        } finally {
            database1.closeConnection(conn);
        }
    }

    @Override
    public void updateCustomer(Customer customer, String databaseName, String tableName, String whatToUpdate, String newValue) throws SQLException {
        conn = database1.connectToDatabase(databaseName);

        try {
            if (!customerExists(databaseName, tableName, customer)) {
                throw new CustomerDoesNotExistException();
            }
            updateQuery(conn, tableName, customer, whatToUpdate, newValue);
        }
        catch (CustomerDoesNotExistException e) {
            logger.info(e.getMessage() + " Updatevorgang nicht möglich.");
        }
        finally {
            database1.closeConnection(conn);
        }
    }

    @Override
    public void showAllCustomers(String databaseName, String tableName) throws SQLException {
        conn = database1.connectToDatabase(databaseName);
        logger.info("Kundennummer" + "\t" + "Name" +"\t\t"+ "Firstname"+"\t"+ "Street"+ "\t\t\t\t" + "Housenumber" + "\t\t" + "Postalcode");
        printColumns(conn, tableName);
        conn.close();
    }

    //HELP METHODS

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
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, customer.getCustomerNumber());
        preparedStmt.execute();
        preparedStmt.close();
        logger.info("Kunde " + customer.getName() + " gelöscht");
    }

    //for updateCustomer();
    public void updateQuery(Connection conn, String tableName, Customer customer, String whatToUpdate, String newValue) throws SQLException {
        String strQuery = "UPDATE $tableName "
                + "SET $columnToUpdate = ? "
                + "WHERE customernumber = ?";
        String query = strQuery.replace("$tableName", tableName);
        query = query.replace("$columnToUpdate", whatToUpdate);

        preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, newValue);
        preparedStmt.setString(2, customer.getCustomerNumber());
        preparedStmt.execute();
    }

    public String checkUpdatedValue(String databaseName, String tableName, Customer customer, String whatToCheck) throws SQLException {
        conn = database1.connectToDatabase(databaseName);
        String sql = "SELECT $column FROM $tableName WHERE customernumber = ?";
        String query1 = sql.replace("$tableName", tableName);
        String query = query1.replace("$column", whatToCheck);

        preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, customer.getCustomerNumber());
        rs = preparedStmt.executeQuery();
        String value = null;

        while (rs.next()) {
            value = rs.getString(whatToCheck);
        }
        preparedStmt.close();
        conn.close();
        return value;
    }

    //for printAllCustomers();
    public void printColumns(Connection conn, String tableName) throws SQLException {
        stmt = conn.createStatement();
        String strQuery = "select * from $tableName";
        String query =strQuery.replace("$tableName",tableName);
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            String columnName1 = rs.getString("Customernumber");
            String columnName2 = rs.getString("Name");
            String columnName3 = rs.getString("Firstname");
            String columnName4 = rs.getString("Street");
            String columnName5 = rs.getString("Housenumber");
            String columnName6 = rs.getString("Postalcode");

            logger.info(columnName1 + "\t\t\t" + columnName2 + "\t" + columnName3 + "\t" + columnName4 + "" + columnName5 + "\t\t\t\t" + columnName6);
        }
        stmt.close();
        rs.close();
    }

    //for insert, delete, updateCustomer();
    public boolean customerExists(String databaseName, String tableName, Customer customer) throws SQLException {
        conn = database1.connectToDatabase(databaseName);
        stmt = conn.createStatement();
        String strQuery = "select * from $tableName";
        String query = strQuery.replace("$tableName", tableName);
        rs = stmt.executeQuery(query);
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

    //for insertCustomerTest();
        public boolean checkCustomerDates(String databaseName, String tableName, Customer customer) throws SQLException {
            conn = database1.connectToDatabase(databaseName);
            String strQuery = "select * from $tableName WHERE customernumber = ?";
            String query = strQuery.replace("$tableName", tableName);
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, customer.getCustomerNumber());
            preparedStmt.execute();
            rs = preparedStmt.executeQuery();

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
