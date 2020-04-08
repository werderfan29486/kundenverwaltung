package customerManagementSoftware;

import java.util.*;

public class Dates {

    Map<Customer, List<String>> customerDates = new HashMap<Customer, List<String>>();  //--> bleibt

    public void addCustomerDate(Customer customer, String date) {

        Optional<Customer> existingKunde = Optional.empty();

        if (customerDates.isEmpty()) {
            List<String> dates = new ArrayList<String>();
            dates.add(date);
            customerDates.put(customer, dates);
        } else {
            for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
                if (entry.getKey().uuid.equals(customer.uuid)) {
                    entry.getValue().add(date);
                    existingKunde = Optional.ofNullable(entry.getKey());
                } else {
                    existingKunde = Optional.empty();
                }
            }

            if(existingKunde.isEmpty()) {
                List<String> dates = new ArrayList<>();
                dates.add(date);
                customerDates.put(customer, dates);
            }
        }
    }

    public void deleteCustomerDate(Customer customer, String date) {

        if (customerDates.isEmpty()) {
            System.out.println("Terminliste ist leer");
        }
         else {
            for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
                if (entry.getKey().uuid != customer.uuid) {
                    System.out.println("Kunde " + customer.getName() + " nicht in der Terminliste");
                } else {
                    entry.getValue().remove(date);
                    System.out.println("Termin am " + date + " für Kunde " + customer.getName() + " gelöscht");
                }
            }
        }
    }


    public void changeCustomerDate(Customer customer, String oldDate, String newDate) {
        if (customerDates.isEmpty()) {
            System.out.println("keine Termine zum Ändern vorhanden");
        }
        else  {
            for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
                if (entry.getKey().uuid != customer.uuid) {
                    System.out.println("Kunde " + customer.getName() + " nicht in der Terminliste");
                }  else  {
                    deleteCustomerDate(customer, oldDate);
                    addCustomerDate(customer, newDate);
                }
            }
        }
    }

    public void printCustomerDates(Customer customer) {
        System.out.println("Kunde: " + customer.getPrename() + " " + customer.getName() + " hat insgesamt");
        System.out.println(customerDates.get(customer).size() + " Termine");
        System.out.println("Terminliste: " + customerDates.get(customer));
    }

    public int numberOfDates(Customer customer) {
        return customerDates.get(customer).size();
    }

}