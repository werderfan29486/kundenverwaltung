package customerManagementSoftware;

import org.junit.jupiter.api.Test;


public class testDates {

    Dates listOfDates1 = new Dates();

    Customer customer = new Customer("Schwarck", "Alex", "An der Fuchsenhütte", "27", "64380");
    Customer customer2 = new Customer("Gantzert", "Sega", "Auf der Schmelz", "27", "64380");
    Customer customer3 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");


    @Test
    public void testAddCustomerDate() {
        listOfDates1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDates1.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDates1.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDates1.addCustomerDate(customer2, "15", "04", "2021", "18", "00");
        listOfDates1.addCustomerDate(customer2, "10", "12", "2024", "20", "00");
        listOfDates1.addCustomerDate(customer2, "30", "07", "2020", "09", "00");
        listOfDates1.addCustomerDate(customer3, "14", "06", "2030", "17", "00");
        listOfDates1.addCustomerDate(customer3, "16", "03", "2020", "16", "00");
        listOfDates1.addCustomerDate(customer3, "30", "09", "2020", "12", "00");
        listOfDates1.printCustomerDates(customer);
        listOfDates1.printCustomerDates(customer2);
        listOfDates1.printCustomerDates(customer3);

    }

    @Test

    public void testDeleteDate() {
        listOfDates1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDates1.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDates1.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDates1.deleteCustomerDate(customer, "20.10.2020 10:00");
        listOfDates1.deleteCustomerDate(customer, "30.09.2020 10.00");
        listOfDates1.printCustomerDates(customer);
    }

    @Test
    public void testChangeCustomerDate() {
        listOfDates1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDates1.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDates1.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDates1.changeCustomerDate(customer, "20.10.2020 10:00", "21", "10", "2020", "10", "00");
        listOfDates1.printCustomerDates(customer);
    }

    @Test
    public void testPrintCustomerDates() {
        listOfDates1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDates1.printCustomerDates(customer);
    }

    @Test
    public void testPrintCustomerDatesSorted() {
        listOfDates1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDates1.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDates1.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDates1.printCustomerDatesSorted(customer);
    }

    @Test
    public void testNumberOfDates() {
        listOfDates1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        System.out.println(listOfDates1.numberOfDates(customer));
    }

    @Test
    public void testCheckString() {
        String date = listOfDates1.createDateString("31", "12", "2020", "10", "00");
        listOfDates1.checkDateString(date);
    }



}
