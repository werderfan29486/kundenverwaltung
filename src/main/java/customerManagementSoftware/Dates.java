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
        boolean isTrue = true;
        String day = day(scanner, isTrue);
        String month = month(scanner, isTrue);
        String year = year(scanner, isTrue);
        String hours = hours(scanner, isTrue);
        String minutes = minutes(scanner, isTrue);

            String gesamtDatum = day + "." + month + "." + year + " " + hours + ":" + minutes + " Uhr";
            return gesamtDatum;
    }

    public static String day(Scanner scanner, boolean isTrue) {
        String day;
        do {
            if (isTrue) {
                System.out.println("Tag eingeben ");
                day = scanner.next();
                isTrue = false;
            } else {
                System.out.println("Bitte 2 Ziffern zwischen 01 und 31 eingeben: ");
                day = scanner.next();
            }
        }
        while (!day.matches("^(?:0*[1-9]|[12][0-9]|3[01])$"));
        return day;
    }

    public static String month(Scanner scanner, boolean isTrue) {
        String month;
        do {
            if (isTrue) {
                System.out.println("Monat eingeben ");
                month = scanner.next();
                isTrue = false;
            } else {
                System.out.println("Bitte 2 Ziffern zwischen 01 und 12 eingeben: ");
                month = scanner.next();
            }
        }
        while (!month.matches("0*([1-9]|1[0-2])"));
        return month;
    }

    public static String year(Scanner scanner, boolean isTrue) {
        String year;
        do {
            if (isTrue) {
                System.out.println("Jahr eingeben ");
                year = scanner.next();
                isTrue = false;
            } else {
                System.out.println("Bitte eine Jahreszahl zwischen 2020 und 2099 angeben: ");
                year = scanner.next();
            }
        }
        while (!year.matches("0*(20[2-8][0-9]|209[0-9])"));
        return year;
    }

    public static String hours(Scanner scanner, boolean isTrue) {
        String hours;
        do {
            if (isTrue) {
                System.out.println("Stunden eingeben ");
                hours = scanner.next();
                isTrue = false;
            } else {
                System.out.println("Bitte eine Jahreszahl zwischen 2020 und 2099 angeben: ");
                hours = scanner.next();
            }
        }
        while (!hours.matches("0*([0-9]|1[0-9]|2[0-3])"));
        return hours;
    }

    public static String minutes(Scanner scanner, boolean isTrue) {
        String minutes;
        do {

            if (isTrue) {
                System.out.println("Minuten eingeben ");
                minutes = scanner.next();
                isTrue = false;
            } else {
                System.out.println("Bitte eine Jahreszahl zwischen 2020 und 2099 angeben: ");
                minutes = scanner.next();
            }
        }
        while (! minutes.matches("0*([0-9]|[1-4][0-9]|5[0-9])"));
        return minutes;
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