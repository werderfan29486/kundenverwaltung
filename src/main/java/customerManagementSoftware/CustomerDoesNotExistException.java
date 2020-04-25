package customerManagementSoftware;

public class CustomerDoesNotExistException extends Exception {

    public CustomerDoesNotExistException() {
        super("Kunde nicht vorhanden. ");
    }

}
