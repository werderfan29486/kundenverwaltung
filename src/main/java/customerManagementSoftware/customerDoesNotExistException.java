package customerManagementSoftware;

public class customerDoesNotExistException extends Exception {

    public customerDoesNotExistException() {
        super("Kunde nicht vorhanden. ");
    }

}
