package customerManagementSoftware;
import java.util.Scanner;
import java.util.UUID;

public class Customer {

    final String uuid = UUID.randomUUID().toString().replace("-", "");
    private String id;
    private String customernumber;
    private String name;
    private String prename;
    private String street;
    private String housenumber;
    private String postalcode;
    private static int count = 0;


    public Customer(String name, String prename, String street, String housenumber, String postalcode) {
        this.id = uuid;
        ++count;
        String formatValue = String.format("%04d", count);
        this.customernumber = formatValue;
        this.name = name;
        this.prename = prename;
        this.street = street;
        this.housenumber = housenumber;
        this.postalcode = postalcode;
    }

    public static Customer createFromScanner(final Scanner scanner) {
        String name = scanner.next();
        String prename = scanner.next();
        String street = scanner.next();
        String housenumber = scanner.next();
        String postalcode = scanner.next();
        return new Customer(name, prename, street, housenumber, postalcode);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrename(String prename) {
        this.prename = prename;
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

    public String getPrename() {
        return prename;
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
