package customerManagementSoftware;

import org.junit.jupiter.api.Test;

public class RechnungTest {

    Customer customer = new Customer("Schwarck", "Alex", "An der Fuchsenhütte", "27", "64380");
    Customer customer2 = new Customer("Gantzert", "Sega", "Auf der Schmelz", "27", "64380");
    Customer customer3 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");

    Bill bill1 = new Bill();
    Bill bill2 = new Bill();
    Bill bill3 = new Bill();
    BillPaymentService billPaymentService1 = new BillPaymentService();
    BillPaymentService billPaymentService2 = new BillPaymentService();
    DatesService listOfDatesService = new DatesService();
    DatesService2 listOfDatesService2 = new DatesService2();
    Account account = new Account();

    @Test
    public void testeErrechneGesamtBetrag() {
        listOfDatesService.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDatesService.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDatesService.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDatesService.deleteCustomerDate(customer, "20. Januar");
        System.out.println(billPaymentService1.calculateTotalSum(customer, listOfDatesService, bill1));
        System.out.println(billPaymentService1.calculateTotalSum(customer, listOfDatesService2, bill1));
    }

    @Test
    public void testeGeneriereRechnung() {
        listOfDatesService.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDatesService.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDatesService.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDatesService.addCustomerDate(customer2, "15", "04", "2021", "18", "00");
        billPaymentService1.generateBill(customer, listOfDatesService, bill1);
        billPaymentService2.generateBill(customer2, listOfDatesService, bill2);
    }

    @Test   //warum wird die Rechnungsnummer nicht hochgezählt?
    public void testeRechnungBezahlt() {
        listOfDatesService.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDatesService.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDatesService.addCustomerDate(customer2, "15", "04", "2021", "18", "00");
        billPaymentService1.billPayed(customer, listOfDatesService, account, bill1);
        listOfDatesService.addCustomerDate(customer, "20", "01", "2020", "10", "00");
        billPaymentService1.billPayed(customer2, listOfDatesService, account, bill2);
        billPaymentService1.billPayed(customer, listOfDatesService, account, bill3);
    }

}
