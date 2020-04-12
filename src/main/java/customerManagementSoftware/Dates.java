package customerManagementSoftware;

import java.util.*;

public class Dates {

    Map<Customer, List<String>> customerDates = new HashMap<Customer, List<String>>();  //--> bleibt


    //ADD CUSTOMER DATE
    public void addCustomerDate(Customer customer, String date) {

        Boolean customerExists = false;

        if (customerDates.isEmpty()) {
            addDateToList(customer, date);
        } else {
            customerExists = doesCustomerExist(customer, date);
          }

        if(customerExists == false) {
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
            if (entry.getKey().uuid != customer.uuid) {
                System.out.println("Kunde " + customer.getName() + " nicht in der Terminliste");
            } else {
                for (int i = 0; i < entry.getValue().size(); i++) {
                    if (entry.getValue().get(i) == date) {
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
            if (entry.getKey().uuid != customer.uuid) {
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
    }

    public int numberOfDates(Customer customer) {
        return customerDates.get(customer).size();
    }
}