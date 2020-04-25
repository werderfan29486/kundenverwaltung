package customerManagementSoftware;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatesService implements IdatesService {

    Map<Customer, List<String>> customerDates = new HashMap<>();

    public Map<Customer, List<String>> getCustomerDates() {
        return customerDates;
    }

    //ADD CUSTOMER DATE

    @Override
    public void addCustomerDate(Customer customer, String day, String month, String year, String hours, String minutes) {
        String date = createDateString(day, month, year, hours, minutes);
        Boolean customerExists = false;
    if (checkDateString(date)) {
        if (customerDates.isEmpty()) {
            addDateToList(customer, date);
        } else {
            customerExists = doesCustomerExist(customer, date);
        }

        if (!customerExists) {
            addDateToList(customer, date);
        }
    }
    else {
        System.out.println("Datum falsch eingegeben");
    }
    }

    public void addDateToList(Customer customer, String date) {
        List<String> dates = new ArrayList<>();
        dates.add(date);
        customerDates.put(customer, dates);
    }

    public Boolean doesCustomerExist(Customer customer, String date) {
        Boolean customerExists = false;
        for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
            if (entry.getKey().uuid.equals(customer.uuid)) {
                entry.getValue().add(date);
                customerExists = true;
            }
            else {
            customerExists = false;
            }
        }
        return customerExists;
    }

    public String createDateString(String day, String month, String year, String hours, String minutes) {

        String date = day + "." + month + "." + year + " " + hours + ":" + minutes;
        return date;
    }

    public boolean checkDateString(String stringToCheck) {
        if (stringToCheck.matches("^([1-9]|([012][0-9])|(3[01]))\\.([0]{0,1}[1-9]|1[012])\\.\\d\\d\\d\\d\\s([0-1]?[0-9]|2?[0-3]):([0-5]\\d)$")) {
            return true;
        }
        else {
            return false;
        }
    }



    //DELETE CUSTOMER DATE
    @Override
    public void deleteCustomerDate(Customer customer, String date) {

        if (customerDates.isEmpty()) {
            System.out.println("Terminliste ist leer");
        } else {
            findDate(customer, date);
        }
    }

    public void findDate(Customer customer, String date ) {
        for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
            if (!entry.getKey().uuid.equals(customer.uuid)) {
                System.out.println("Kunde " + customer.getName() + " nicht in der Terminliste");
            } else {
                for (int i = 0; i < entry.getValue().size(); i++) {
                    if (entry.getValue().get(i).equals(date)) {
                        entry.getValue().remove(date);
                        System.out.println("Termin am " + date + " für Kunde " + customer.getName() + " gelöscht");
                    } else if (!entry.getValue().contains(date)){
                        System.out.println("Termin nicht vorhanden");
                    }
                }
            }
        }
    }


    // CHANGE CUSTOMER DATE
    @Override
    public void changeCustomerDate(Customer customer, String oldDate, String day, String month, String year, String hours, String minutes) {
        if (customerDates.isEmpty()) {
            System.out.println("keine Termine zum Ändern vorhanden");
        }
        else  {
            findDateForChange(customer, oldDate, day, month, year, hours, minutes);
        }
    }

    public void findDateForChange(Customer customer, String oldDate, String day, String month, String year, String hours, String minutes) {

        for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
            if (!entry.getKey().uuid.equals(customer.uuid)) {
                System.out.println("Kunde " + customer.getName() + " nicht in der Terminliste");
            } else {
                deleteCustomerDate(customer, oldDate);
                addCustomerDate(customer, day, month, year, hours, minutes);
            }
        }
    }

    @Override
    public void printCustomerDates(Customer customer) {
        System.out.println("Kunde: " + customer.getFirstName() + " " + customer.getName() + " hat insgesamt");
        System.out.println(customerDates.get(customer).size() + " Termine");
        printCustomerDatesSorted(customer);
    }

    public void printCustomerDatesSorted(Customer customer) {
        List<String> dateList = customerDates.get(customer);

        dateList.sort(new Comparator<>() {
            DateFormat f = new SimpleDateFormat("dd.MM.yyyy hh:mm");

            @Override
            public int compare(String o1, String o2) {
                try {
                    return f.parse(o1).compareTo(f.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        for (String datelistStr : dateList)
            System.out.println(datelistStr + " Uhr");
    }

    public int numberOfDates(Customer customer) {
        return customerDates.get(customer).size();
    }
}