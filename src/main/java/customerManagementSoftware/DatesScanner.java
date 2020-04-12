package customerManagementSoftware;

import java.util.*;

public class DatesScanner {

    Map<Customer, List<String>> customerDates = new HashMap<Customer, List<String>>();

    public void addCustomerDate(Customer customer) {

        Boolean customerExists = false;
        Scanner scanner = new Scanner(System.in);

        if (customerDates.isEmpty()) {
            addDateToListNew(customer, scanner);
        } else {
            customerExists = doesCostumerExist(customer, scanner);
        }
        if(customerExists == false) {
            addDateToListNew(customer, scanner);
        }
    }

    public void addDateToListNew(Customer customer, Scanner scanner) {
        List<String> dates = new ArrayList<>();
        String date = inputDate(scanner);
        dates.add(date);
        customerDates.put(customer, dates);
    }

    public Boolean doesCostumerExist(Customer customer, Scanner scanner) {
        Boolean customerExists = false;
        for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
            if (entry.getKey().uuid.equals(customer.uuid)) {
                String date = inputDate(scanner);
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
        Scanner scanner = new Scanner(System.in);
        if (customerDates.isEmpty()) {
            System.out.println("Terminliste ist leer");
        }
        else {
            findDate(scanner, customer);
        }

    }

    public void findDate(Scanner scanner, Customer customer) {
        for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
            if (entry.getKey().uuid != customer.uuid) {
                System.out.println("Kunde " + customer.getName() + " nicht in der Terminliste");
            } else {
                String date = inputDate(scanner);
                entry.getValue().remove(date);
                System.out.println("Termin am " + date + " für Kunde " + customer.getName() + " gelöscht");
            }
        }
    }

    public String inputDate (final Scanner scanner) {
        boolean isTrue = true;
        String day = dateValues(scanner, isTrue, "Tag eingeben: ", "Bitte 2 Ziffern zwischen 01 und 31 eingeben: ", "^(?:0*[1-9]|[12][0-9]|3[01])$" );
        String month = dateValues(scanner, isTrue, "Monat eingeben ", "Bitte 2 Ziffern zwischen 01 und 12 eingeben: ", "0*([1-9]|1[0-2])");
        String year = dateValues(scanner, isTrue, "Jahr eingeben ", "Bitte eine Jahreszahl zwischen 2020 und 2099 angeben: ", "0*(20[2-8][0-9]|209[0-9])" );
        String hours = dateValues(scanner, isTrue, "Stunden eingeben ", "Bitte eine Zahl zwischen 0 und 23 eingeben: ", "0*([0-9]|1[0-9]|2[0-3])");
        String minutes = dateValues(scanner, isTrue, "Minuten eingeben ", "Bitte Zahl zwischen 00 und 60 eingeben ", "0*([0-9]|[1-5][0-9]|60)" );

        String gesamtDatum = day + "." + month + "." + year + " " + hours + ":" + minutes + " Uhr";
        return gesamtDatum;
    }

    public static String dateValues(Scanner scanner, boolean isTrue, String expression1, String expression2, String regex) {
        String dateExpression;
        do {
            if (isTrue) {
                System.out.println(expression1);
                dateExpression = scanner.next();
                isTrue = false;
            } else {
                System.out.println(expression2);
                dateExpression = scanner.next();
            }
        }
        while (!dateExpression.matches(regex));
        return dateExpression;
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
