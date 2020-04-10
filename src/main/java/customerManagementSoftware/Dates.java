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
            System.out.println("Termin für Kunde " + customer.getName() + " anlegen:");
            dates.add(date);
            customerDates.put(customer, dates);
        } else {
            for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
                if (entry.getKey().uuid.equals(customer.uuid)) {
                    System.out.println("Termin für Kunde " + customer.getName() + " anlegen:");
                    entry.getValue().add(date);
                    existingKunde = Optional.ofNullable(entry.getKey());


                } else {
                    existingKunde = Optional.empty();
                }
            }

            if(existingKunde.isEmpty()) {
                List<String> dates = new ArrayList<>();
                System.out.println("Termin für Kunde " + customer.getName() + " anlegen:");
                dates.add(date);
                customerDates.put(customer, dates);
            }
        }
    }


    public String inputDate (final Scanner scanner) {
        String day;
        boolean dayIsTrue = true;

        do {

            if (dayIsTrue) {
                System.out.println("Tag eingeben ");
                day = scanner.next();
                dayIsTrue = false;
            } else {
                System.out.println("Bitte 2 Ziffern zwischen 01 und 31 eingeben: ");
                day = scanner.next();
            }
        }
        while (! day.matches("^(?:0*[1-9]|[12][0-9]|3[01])$"));

        String month;
        boolean monthIsTrue = true;

        do {

            if (monthIsTrue) {
                System.out.println("Monat eingeben ");
                month = scanner.next();
                monthIsTrue = false;
            } else {
                System.out.println("Bitte 2 Ziffern zwischen 01 und 12 eingeben: ");
                month = scanner.next();
            }
        }
        while (! month.matches("0*([1-9]|1[0-2])"));

        String year;
        boolean yearIsTrue = true;

        do {

            if (yearIsTrue) {
                System.out.println("Jahr eingeben ");
                year = scanner.next();
                yearIsTrue = false;
            } else {
                System.out.println("Bitte eine Jahreszahl zwischen 2020 und 2099 angeben: ");
                year = scanner.next();
            }
        }
        while (! year.matches("0*(20[2-8][0-9]|209[0-9])"));

        String hours;
        boolean hoursIsTrue = true;

        do {

            if (hoursIsTrue) {
                System.out.println("Stunden eingeben ");
                hours = scanner.next();
                hoursIsTrue = false;
            } else {
                System.out.println("Bitte eine Jahreszahl zwischen 2020 und 2099 angeben: ");
                hours = scanner.next();
            }
        }
        while (! hours.matches("0*([0-9]|1[0-9]|2[0-3])"));

        String minutes;
        boolean minutesIsTrue = true;

        do {

            if (minutesIsTrue) {
                System.out.println("Minuten eingeben ");
                minutes = scanner.next();
                minutesIsTrue = false;
            } else {
                System.out.println("Bitte eine Jahreszahl zwischen 2020 und 2099 angeben: ");
                minutes = scanner.next();
            }
        }
        while (! minutes.matches("0*([0-9]|[1-4][0-9]|5[0-9])"));

            String gesamtDatum = day + "." + month + "." + year + " " + hours + ":" + minutes + " Uhr";
            return gesamtDatum;

    }

    public void deleteCustomerDate(Customer customer, String date) {

        if (customerDates.isEmpty()) {
            System.out.println("Terminliste ist leer");
        } else {
            for (Map.Entry<Customer, List<String>> entry : customerDates.entrySet()) {
                if (entry.getKey().uuid != customer.uuid) {
                    System.out.println("Kunde " + customer.getName() + " nicht in der Terminliste");
                } else {
                    entry.getValue().remove(date);
                    // Diese Ausgabe kommt immer. Egal ob er einen Termin gelöscht hat oder nicht
                    // Deshalb meine Plan, diese Ausgabe nur zu machen, wenn der übergebene Termin auch vorhanden ist 
                    System.out.println("Termin am " + date + " für Kunde " + customer.getName() + " gelöscht");
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