package customerManagementSoftware;

import java.util.*;

public class Customerservice {


    List<Customer> customerList = new ArrayList<Customer>();

    public static Customer createFromScanner(final Scanner scanner) throws InputMismatchException {

        try {

            System.out.println("Bitte Kundendaten eingeben: ");

            String name = customerValues(scanner, true, "Nachname: ", "Bitte nur Buchstaben eingeben: ", "[A-Z, a-z]+");
            String firstName = customerValues(scanner, true, "Vorname: ", "Bitte nur Buchstaben eingeben: ", "[A-Z, a-z]+" );
            String street = customerValues(scanner, true, "Straße: ", "Bitte nur Buchstaben eingeben: ", "[A-Z, a-z]+" );
            String houseNumber = customerValues(scanner, true, "Hausnummer: ", "Bitte eine Zahl zwischen 1 und 3 Ziffern eingeben", "[0-9]{1,3}");
            String postalCode = customerValues(scanner, true, "Postleitzahl: ", "Bitte eine 5-stellige Zahl eingeben", "[0-9]{5}");

            return new Customer(name, firstName, street, houseNumber, postalCode);
        }

        catch (InputMismatchException e)  {
            System.err.println("Falsche Eingabe. Bitte wiederholen");
            scanner.nextLine();
            createFromScanner(scanner);
            return null;
        }

    }

    public static String customerValues(Scanner scanner, boolean isTrue, String expression1 , String expression2, String regex) {
        //valueExpression
        String valueExpression;
        do {
            if (isTrue) {
                System.out.println(expression1);
                valueExpression = scanner.next();
                isTrue = false;
            } else {
                System.out.println(expression2);
                valueExpression = scanner.next();
            }
        }
        while (! valueExpression.matches(regex));
        return valueExpression;
    }


    public void createCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void updateCustomer(Customer customer, String oldValue, String newValue) {

                if (oldValue.equals(customer.getName())) {
                updateCustomerName(customer, newValue);
                }  else if (oldValue.equals(customer.getFirstName()))
                   updateCustomerFirstName(customer, newValue);
                   else if (oldValue.equals(customer.getStreet()))
                   updateCustomerStreet(customer, newValue);
                   else if (oldValue.equals(customer.getHouseNumber()))
                   updateCustomerHouseNumber(customer, newValue);
                   else if (oldValue.equals(customer.getPostalCode()))
                   updateCustomerPostalCode(customer, newValue);
                   else {System.out.println("Wert nicht gefunden");}
   }

    public void updateCustomerName(Customer customer, String updateWord) {
                if (!updateWord.equals(customer.getName())) {
                    customer.setName(updateWord);
                    System.out.print(customer.getName());
                }
    }

    public void updateCustomerFirstName(Customer customer, String updateFirstName) {
                customer.setFirstName(updateFirstName);
                System.out.print(customer.getFirstName());
    }

    public void updateCustomerStreet(Customer customer, String updateStreet) {
                customer.setStreet(updateStreet);
                System.out.print(customer.getStreet());
    }

    public void updateCustomerHouseNumber(Customer customer, String updateHouseNumber) {
                customer.setHouseNumber(updateHouseNumber);
                System.out.print(customer.getHouseNumber());
    }

    public void updateCustomerPostalCode(Customer customer, String updatePostalCode) {
                customer.setPostalCode(updatePostalCode);
                System.out.print(customer.getPostalCode());
    }

    public void printCustomerData(Customer customer) {
                System.out.println("Kunde mit der ID: " + customer.getId());
                System.out.println("Name: " + customer.getName());
                System.out.println("Vorname " + customer.getFirstName());
                System.out.println("Straße: " + customer.getStreet());
                System.out.println("Hausnummer " + customer.getHouseNumber());
                System.out.println("Plz:  " + customer.getPostalCode());
    }

    public boolean searchCustomer(String searchterm) {
                for (Customer customer : customerList) {
                    if (searchterm.equals(customer.getName())  || searchterm.equals(customer.getFirstName())
                        || searchterm.equals(customer.getStreet()) || searchterm.equals(customer.getHouseNumber())
                        || searchterm.equals(customer.getPostalCode())) {
                    printCustomerData(customer);
                }
            }
        return true;
    }

    public void deleteCustomer(Customer customer) {
                for (int i = 0; i < customerList.size(); i++) {
                    if (customerList.get(i).getId().equals(customer.getId())) {
                    customerList.remove(i);
                    }
                }
    }

    public void printCustomerList() {
                for (int i = 0; i < customerList.size(); i++) {
                    Customer customer1 = customerList.get(i);
                    System.out.println("Kundennummer " + customer1.getCustomerNumber());
                    System.out.println("Name: " + customer1.getName());
                    System.out.println("Vorname: " + customer1.getFirstName());
                    System.out.println("Straße: " + customer1.getStreet());
                    System.out.println("Hausnummer: " + customer1.getHouseNumber());
                    System.out.println("PLZ: " + customer1.getPostalCode());
                    System.out.println();

                }
    }

}


