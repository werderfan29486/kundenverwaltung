package customerManagementSoftware;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;

public class SqlDateServiceTest {

    SqlDateService dateList1 = new SqlDateService();

    @Test
    public void addCustomerDateTest() throws SQLException, ParseException {
        dateList1.addDate("KUNDEN", "Termine", 1, "2013-10-03 18:29:09");
    }

    @Test
    public void showAllDatesTest() throws SQLException {
        dateList1.showAllDates("KUNDEN", "Termine");
    }


}
