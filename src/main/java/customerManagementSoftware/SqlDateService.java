package customerManagementSoftware;

import jdk.jshell.spi.SPIResolutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlDateService {

        Connection conn = null;
        PreparedStatement preparedStmt = null;
        DatabaseService database1 = new DatabaseService();
        Statement stmt = null;
        ResultSet rs = null;
        Logger logger = LoggerFactory.getLogger("");

    public void addDate(String databaseName, String tableName, int customerUID, String date) throws SQLException, ParseException {
        conn = database1.connectToDatabase(databaseName);
        Date date1 = createDate(date);

        preparedStmt = conn.prepareStatement(insertDate(tableName));
        preparedStmt.setInt(1, customerUID);
        preparedStmt.setDate(2, date1);

        preparedStmt.close();
        conn.close();
    }

    public Date createDate(String date) throws ParseException {
        String input = "06/10/2013 18:29:09";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        java.util.Date dt = sdf.parse(date);
        java.sql.Date dtSql = new java.sql.Date(dt.getTime());
        return dtSql;
    }

    private static String insertDate(String tableName) {
        String query = " insert into $tableName (uid, date)"
                + " values (?, ?)";
        return query.replace("$tableName", tableName);
    }

    public void showAllDates(String databaseName, String tableName) throws SQLException {
        conn = database1.connectToDatabase(databaseName);
        logger.info("Date_ID" + "\t" + "UID" + "\t" + "Date");
        printTableDates(conn, tableName);
        conn.close();
    }

    public void printTableDates(Connection conn, String tableName) throws SQLException {
            stmt = conn.createStatement();
            String strQuery = "select * from $tableName";
            String query =strQuery.replace("$tableName",tableName);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String columnDateId = rs.getString("DATE_ID");
                String columnUID = rs.getString("UID");
                String columnDate = rs.getString("DATE");

                logger.info(columnDateId + "\t" + columnUID + "\t\t\t" + columnDate);
            }
            stmt.close();
            rs.close();
        }
    }

