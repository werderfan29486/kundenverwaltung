package customerManagementSoftware;

import jdk.jshell.spi.SPIResolutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class SqlDateService {

        Connection conn = null;
        PreparedStatement preparedStmt = null;
        DatabaseService database1 = new DatabaseService();
        Statement stmt = null;
        ResultSet rs = null;
        Logger logger = LoggerFactory.getLogger("");

    public void addDate(String databaseName, String tableName, int customerUID, String date) throws SQLException, ParseException {
        conn = database1.connectToDatabase(databaseName);

        preparedStmt = conn.prepareStatement(insertDate(tableName));
        preparedStmt.setInt(1, customerUID);
        preparedStmt.setTimestamp(2, java.sql.Timestamp.valueOf(date));
        preparedStmt.execute();

        preparedStmt.close();
        conn.close();
    }


    private static String insertDate(String tableName) {
        String query = " insert into $tableName (UID, dt)"
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
            conn.close();
        }
    }

