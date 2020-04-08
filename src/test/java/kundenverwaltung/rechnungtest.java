package kundenverwaltung;

import customerManagementSoftware.Customer;
import customerManagementSoftware.Dates;
import customerManagementSoftware.Account;
import customerManagementSoftware.Bill;
import org.junit.jupiter.api.Test;

public class rechnungtest {

    Customer customer = new Customer("Schwarck", "Alex", "An der Fuchsenhütte", "27", "64380");
    Customer customer2 = new Customer("Gantzert", "Sega", "Auf der Schmelz", "27", "64380");
    Customer customer3 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");

    Bill bill1 = new Bill();
    Bill bill2 = new Bill();
    Bill bill3 = new Bill();
    Dates listOfDates = new Dates();
    Account account = new Account();

    @Test
    public void testeErrechneGesamtBetrag() {
        listOfDates.addCustomerDate(customer, "20. Januar");
        listOfDates.addCustomerDate(customer, "19. Mai");
        listOfDates.addCustomerDate(customer, "21. November");
        listOfDates.deleteCustomerDate(customer, "20. Januar");
        System.out.println(bill1.calculateTotalSum(customer, listOfDates));
    }

    @Test
    public void testeGeneriereRechnung() {
        listOfDates.addCustomerDate(customer, "20. Januar");
        listOfDates.addCustomerDate(customer, "31. März");
        listOfDates.addCustomerDate(customer, "20. Oktober");
        listOfDates.addCustomerDate(customer2, "30. April");
        bill1.generateBill(customer, listOfDates);
        bill2.generateBill(customer2, listOfDates);
    }

    @Test   //warum wird die Rechnungsnummer nicht hochgezählt?
    public void testeRechnungBezahlt() {
        listOfDates.addCustomerDate(customer, "20. Januar");
        listOfDates.addCustomerDate(customer, "31. März");
        listOfDates.addCustomerDate(customer2, "24. Februar");
        bill1.billPayed(customer, listOfDates, account);
        listOfDates.addCustomerDate(customer, "20. Januar");
        bill2.billPayed(customer2, listOfDates, account);
        bill3.billPayed(customer, listOfDates, account);
    }


}
