package customerManagementSoftware;

public class Bill {

    private int hourlyPrice = 80;
    private int numberOfHours;
    private String billNumber;
    private static int count = 0;

    public Bill() {
        ++count;
        this.billNumber = String.format("%03d", count);
    }

    public double calculateTotalSum(Customer customer, Dates listOfDates) {
        numberOfHours = listOfDates.numberOfDates(customer);
        return numberOfHours * hourlyPrice;
    }

    public void generateBill(Customer customer, Dates listOfDates) {
        System.out.println("Rechnungsnummer: " + billNumber);
        System.out.println("Kundennummer:   " + customer.getCustomernumber() + "              Name: " + customer.getFirstname() + " " + customer.getName());
        numberOfHours = listOfDates.numberOfDates(customer);
        System.out.println("-----------------------------------------------------");
        System.out.println("Anzahl der Stunden:                               " + numberOfHours);
        System.out.println("Stundenpreis:                                    "+ hourlyPrice);
        System.out.println("-----------------------------------------------------");
        System.out.println("                                  Gesamtbetrag: " + calculateTotalSum(customer, listOfDates));
    }

    public void billPayed(Customer customer, Dates customerDates, Account account) {
       account.accountBalance += calculateTotalSum(customer, customerDates);
        System.out.println("Kunde " + customer.getName() + " hat Rechnung bezahlt");
        System.out.println("Betrag: " + calculateTotalSum(customer, customerDates) + " Rechnungsnummer: " + billNumber);
        System.out.println("Neuer Kontostand " + account.getAccountBalance());
        customerDates.customerDates.get(customer).clear();
    }

}
