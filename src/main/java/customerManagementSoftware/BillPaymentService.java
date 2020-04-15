package customerManagementSoftware;

public class BillPaymentService {

    public double calculateTotalSum(Customer customer, IdatesService listOfDatesService, Bill bill) {
        int numberOfHours = listOfDatesService.numberOfDates(customer);
        return numberOfHours * bill.getHourlyPrice();
    }

    public void generateBill(Customer customer, IdatesService listOfDatesService, Bill bill) {
        System.out.println("Rechnungsnummer: " + bill.getBillNumber());
        System.out.println("Kundennummer:   " + customer.getCustomernumber() + "              Name: " + customer.getFirstname() + " " + customer.getName());
        int numberOfHours = listOfDatesService.numberOfDates(customer);
        System.out.println("-----------------------------------------------------");
        System.out.println("Anzahl der Stunden:                               " + numberOfHours);
        System.out.println("Stundenpreis:                                    "+ bill.getHourlyPrice());
        System.out.println("-----------------------------------------------------");
        System.out.println("                                  Gesamtbetrag: " + calculateTotalSum(customer, listOfDatesService, bill));
    }

    public void billPayed(Customer customer, IdatesService customerDatesService, Account account, Bill bill) {
        account.accountBalance += calculateTotalSum(customer, customerDatesService, bill);
        System.out.println("Kunde " + customer.getName() + " hat Rechnung bezahlt");
        System.out.println("Betrag: " + calculateTotalSum(customer, customerDatesService, bill) + " Rechnungsnummer: " + bill.getBillNumber());
        System.out.println("Neuer Kontostand " + account.getAccountBalance());
        customerDatesService.getCustomerDates().get(customer).clear();
    }

}