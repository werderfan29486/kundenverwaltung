package customerManagementSoftware;

import java.util.List;
import java.util.Map;

public interface IdatesService {

    Map<Customer, List<String>> getCustomerDates();

    void addCustomerDate(Customer customer, String day, String month, String year, String hours, String minutes);

    void deleteCustomerDate(Customer customer, String date);

    void changeCustomerDate(Customer customer, String oldDate, String day, String month, String year, String hours, String minutes);

    void printCustomerDates(Customer customer);

    int numberOfDates(Customer customer);

}
