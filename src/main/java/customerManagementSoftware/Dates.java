package customerManagementSoftware;

import java.util.*;

public class Dates {

    Map<Customer, List<String>> customerDates = new HashMap<Customer, List<String>>();  //--> bleibt

    public void addCustomerDateNew(Customer customer) {

        Optional<Customer> existingKunde = Optional.empty();
        Scanner scanner = new Scanner(System.in);

        if (customerDates.isEmpty()) {
            List<String> dates = new ArrayList<String>();
            System.out.println("Termin für Kunde " + customer.getName() + " anlegen:");
            String date = inputDate(scanner);
            dates.add(date);
            customerDates.put(customer, dates);
            System.out.println("Neuer Termin angelegt für Kunde: " + customer.getFirstname() + " " + customer.getName() + " am " + date);
        } else {
            for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
                if (entry.getKey().uuid.equals(customer.uuid)) {
                    System.out.println("Termin für Kunde " + customer.getName() + " anlegen:");
                    String date = inputDate(scanner);
                    entry.getValue().add(date);
                    System.out.println("Neuer Termin angelegt für Kunde: " + customer.getFirstname() + " " + customer.getName() + " am " + date);
                    existingKunde = Optional.ofNullable(entry.getKey());


                } else {
                    existingKunde = Optional.empty();
                }
            }

            if(existingKunde.isEmpty()) {
                List<String> dates = new ArrayList<>();
                System.out.println("Termin für Kunde " + customer.getName() + " anlegen:");
                String date = inputDate(scanner);
                dates.add(date);
                customerDates.put(customer, dates);
            }
        }
    }

    public void addCustomerDate(Customer customer, String date) {

        Optional<Customer> existingKunde = Optional.empty();

        if (customerDates.isEmpty()) {
            List<String> dates = new ArrayList<String>();
            dates.add(date);
            customerDates.put(customer, dates);
        } else {
           doesCustomerExist(customer, existingKunde, date);
            }

            if(existingKunde.isEmpty()) {
                List<String> dates = new ArrayList<>();
                System.out.println("Termin für Kunde " + customer.getName() + " anlegen:");
                dates.add(date);
                customerDates.put(customer, dates);
            }
        }

    public void doesCustomerExist(Customer customer, Optional<Customer> existingKunde, String date) {
        for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
            if (entry.getKey().uuid.equals(customer.uuid)) {
                System.out.println("Termin für Kunde " + customer.getName() + " anlegen:");
                entry.getValue().add(date);
                existingKunde = Optional.ofNullable(entry.getKey());
            } else {
                existingKunde = Optional.empty();
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



    public void deleteCustomerDate(Customer customer, String date) {

        if (customerDates.isEmpty()) {
            System.out.println("Terminliste ist leer");
        } else {
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
    }


    public void deleteCustomerDateNew(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        if (customerDates.isEmpty()) {
            System.out.println("Terminliste ist leer");
        }
        else {
            customerInDateList(scanner, customer);
        }

    }

    public void customerInDateList(Scanner scanner, Customer customer) {
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
        System.out.println("Kunde: " + customer.getFirstname() + " " + customer.getName() + " hat insgesamt");
        System.out.println(customerDates.get(customer).size() + " Termine");
        System.out.println("Terminliste: " + customerDates.get(customer));
    }

    public int numberOfDates(Customer customer) {
        return customerDates.get(customer).size();
    }

}