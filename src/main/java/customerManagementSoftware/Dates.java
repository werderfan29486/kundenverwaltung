package customerManagementSoftware;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Dates {

    Map<Customer, List<String>> customerDates = new HashMap<>();


    //ADD CUSTOMER DATE
    public void addCustomerDate(Customer customer, String date) {

        Boolean customerExists = false;

        if (customerDates.isEmpty()) {
            addDateToList(customer, date);
        } else {
            customerExists = doesCustomerExist(customer, date);
          }

        if(!customerExists) {
            addDateToList(customer, date);
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



    //DELETE CUSTOMER DATE
    public void deleteCustomerDate(Customer customer, String date) {

        if (customerDates.isEmpty()) {
            System.out.println("Terminliste ist leer");
        } else {
            findDate(customer, date);
        }
    }

    public void findDate(Customer customer, String date ) {
        for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
            if (entry.getKey().uuid.equals(customer.uuid)) {
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
    public void changeCustomerDate(Customer customer, String oldDate, String newDate) {
        if (customerDates.isEmpty()) {
            System.out.println("keine Termine zum Ändern vorhanden");
        }
        else  {
            findDateForChange(customer, oldDate, newDate);
        }
    }

    public void findDateForChange(Customer customer, String oldDate, String newDate) {
        for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
            if (entry.getKey().uuid.equals(customer.uuid)) {
                System.out.println("Kunde " + customer.getName() + " nicht in der Terminliste");
            } else {
                deleteCustomerDate(customer, oldDate);
                addCustomerDate(customer, newDate);
            }
        }
    }


    public void printCustomerDates(Customer customer) {
        System.out.println("Kunde: " + customer.getFirstname() + " " + customer.getName() + " hat insgesamt");
        System.out.println(customerDates.get(customer).size() + " Termine");
        System.out.println("Terminliste: " + customerDates.get(customer));
        List<String> test = customerDates.get(customer);
    }

    public void printCustomerDatesSorted(Customer customer) {
        List<String> dateList = customerDates.get(customer);

        System.out.println("Unsortiert: ");
        for (String datelistStr : dateList)
            System.out.println(datelistStr);
        dateList.sort(new Comparator<>() {
            DateFormat f = new SimpleDateFormat("dd MM yyyy hh:mm");

            @Override
            public int compare(String o1, String o2) {
                try {
                    return f.parse(o1).compareTo(f.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        System.out.println("Sortiert: ");
        for (String datelistStr : dateList)
            System.out.println(datelistStr);
    }

    public int numberOfDates(Customer customer) {
        return customerDates.get(customer).size();
    }
}