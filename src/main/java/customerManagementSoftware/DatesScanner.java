package customerManagementSoftware;

import java.util.*;

public class DatesScanner {

    Map<Customer, List<String>> customerDates = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public void addCustomerDate(Customer customer) {
        CreateDate createDate = CreateDate.inputDate(scanner);
        String date = createDate.getDate();
        Boolean customerExists = false;

        if (customerDates.isEmpty()) {
            addDateToList(customer, date);
        } else {
            customerExists = doesCostumerExist(customer, date);
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

    public Boolean doesCostumerExist(Customer customer, String date) {
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

    public void deleteCustomerDate(Customer customer) {
        if (customerDates.isEmpty()) {
            System.out.println("Terminliste ist leer");
        }
        else {
            findDate(customer);
        }

    }

    public void findDate(Customer customer) {
        CreateDate createDate = CreateDate.inputDate(scanner);
        String date = createDate.getDate();
        for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
            if (entry.getKey().uuid.equals(customer.uuid)) {
                System.out.println("Kunde " + customer.getName() + " nicht in der Terminliste");
            } else {
                entry.getValue().remove(date);
                System.out.println("Termin am " + date + " für Kunde " + customer.getName() + " gelöscht");
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