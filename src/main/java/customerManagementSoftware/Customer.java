package customerManagementSoftware;
import java.util.UUID;

public class Customer {

    final String uuid = UUID.randomUUID().toString().replace("-", "");
    private String id;
    private String customerNumber;
    private String name;
    private String firstName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private static int count = 0;


    public Customer(String name, String firstName, String street, String houseNumber, String postalCode) {
        ++count;
        this.id = uuid;
        this.customerNumber = String.format("%04d", count);
        this.name = name;
        this.firstName = firstName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getId() {
        return id;
    }

    public String getCustomerNumber() {return customerNumber;}

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

}
