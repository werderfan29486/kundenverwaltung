package customerManagementSoftware;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Customer {

    final String uuid = UUID.randomUUID().toString().replace("-", "");
    private String id;
    private String customernumber;
    private String name;
    private String firstname;
    private String street;
    private String housenumber;
    private String postalcode;
    private static int count = 0;


    public Customer(String name, String firstname, String street, String housenumber, String postalcode) {
        this.id = uuid;
        ++count;
        String formatValue = String.format("%04d", count);
        this.customernumber = formatValue;
        this.name = name;
        this.firstname = firstname;
        this.street = street;
        this.housenumber = housenumber;
        this.postalcode = postalcode;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getId() {
        return id;
    }

    public String getCustomernumber() {return customernumber;}

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getStreet() {
        return street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public String getPostalcode() {
        return postalcode;
    }

}
