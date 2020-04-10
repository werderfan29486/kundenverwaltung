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

    public static Customer createFromScanner(final Scanner scanner) {


      try {

           System.out.println("Bitte Kundendaten eingeben: ");

          //name
          String name;
          boolean nameIsTrue = true;

          do {

              if (nameIsTrue) {
                  System.out.println("Nachname ");
                  name = scanner.next();
                  nameIsTrue = false;
              } else {
                  System.out.println("Bitte nur Buchstaben eingeben: ");
                  name = scanner.next();
              }
          }
          while (! name.matches("[A-Z, a-z]+"));


          //firstName
          String firstname;
          boolean firstNameIsTrue = true;
          do {

              if (firstNameIsTrue) {
                  System.out.println("Vorname ");
                  firstname = scanner.next();
                  firstNameIsTrue = false;
              } else {
                  System.out.println("Bitte nur Buchstaben eingeben: ");
                  firstname = scanner.next();
              }
          }
          while (! firstname.matches("[A-Z, a-z]+"));




          // Straße
          String street;
          boolean streetIsTrue = true;
          do {

              if (streetIsTrue) {
                  System.out.println("Straße: ");
                  street = scanner.next();
                  streetIsTrue = false;
              } else {
                  System.out.println("Bitte nur Buchstaben eingeben: ");
                  street = scanner.next();
              }
          }
          while (! street.matches("[A-Z, a-z]+"));

           //Hausnummer
          String housenumber;
          boolean housenumberIsTrue = true;
          do {
              if (housenumberIsTrue) {
                  System.out.println("Hausnummer: ");
                  housenumber = scanner.next();
                  housenumberIsTrue = false;
              } else {
                  System.out.println("Bitte eine Zahl zwischen 1 und 3 Ziffern eingeben");
                  housenumber = scanner.next();
              }
          }
          while (! housenumber.matches("[0-9]{1,3}"));

           //PLZ
          String postalcode;
          boolean postalIsTrue = true;
          do {
              if (postalIsTrue) {
                  System.out.println("Postleitzahl: ");
                  postalcode = scanner.next();
                  postalIsTrue = false;
              }
              else {
                  System.out.println("Bitte eine 5-stellige Zahl eingeben");
                  postalcode = scanner.next();
              }
          }
          while (! postalcode.matches("[0-9]{5}"));


           return new Customer(name, firstname, street, housenumber, postalcode);
      }

        catch (InputMismatchException e)  {
            System.err.println("Falsche Eingabe. Bitte wiederholen");
            scanner.nextLine();
            createFromScanner(scanner);
            return null;
        }

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
