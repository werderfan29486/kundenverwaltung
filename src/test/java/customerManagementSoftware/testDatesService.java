package customerManagementSoftware;

import org.junit.jupiter.api.Test;


public class testDatesService {

    DatesService listOfDatesService1 = new DatesService();

    Customer customer = new Customer("Schwarck", "Alex", "An der Fuchsenhütte", "27", "64380");
    Customer customer2 = new Customer("Gantzert", "Sega", "Auf der Schmelz", "27", "64380");
    Customer customer3 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");


    @Test
    public void testAddCustomerDate() {
        listOfDatesService1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDatesService1.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDatesService1.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDatesService1.addCustomerDate(customer2, "15", "04", "2021", "18", "00");
        listOfDatesService1.addCustomerDate(customer2, "10", "12", "2024", "20", "00");
        listOfDatesService1.addCustomerDate(customer2, "30", "07", "2020", "09", "00");
        listOfDatesService1.addCustomerDate(customer3, "14", "06", "2030", "17", "00");
        listOfDatesService1.addCustomerDate(customer3, "16", "03", "2020", "16", "00");
        listOfDatesService1.addCustomerDate(customer3, "30", "09", "2020", "12", "00");
        listOfDatesService1.printCustomerDatesSorted(customer);
        //listOfDates1.printCustomerDates(customer2);
        //listOfDates1.printCustomerDates(customer3);

    }

    @Test

    public void testDeleteDate() {
        listOfDatesService1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDatesService1.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDatesService1.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDatesService1.deleteCustomerDate(customer, "20.10.2020 10:00");
        listOfDatesService1.deleteCustomerDate(customer, "30.09.2020 10.00");
        listOfDatesService1.printCustomerDates(customer);
    }

    @Test
    public void testChangeCustomerDate() {
        listOfDatesService1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDatesService1.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDatesService1.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDatesService1.changeCustomerDate(customer, "20.10.2020 10:00", "21", "10", "2020", "10", "00");
        listOfDatesService1.printCustomerDates(customer);
    }

    @Test
    public void testPrintCustomerDates() {
        listOfDatesService1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDatesService1.printCustomerDates(customer);
    }

    @Test
    public void testPrintCustomerDatesSorted() {
        listOfDatesService1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        listOfDatesService1.addCustomerDate(customer, "30", "09", "2025", "12", "00");
        listOfDatesService1.addCustomerDate(customer, "10", "12", "2023", "20", "00");
        listOfDatesService1.printCustomerDatesSorted(customer);
    }

    @Test
    public void testNumberOfDates() {
        listOfDatesService1.addCustomerDate(customer, "20", "10", "2020", "10", "00");
        System.out.println(listOfDatesService1.numberOfDates(customer));
    }

    @Test
    public void testCheckString() {
        String date = listOfDatesService1.createDateString("31", "12", "2020", "10", "00");
        listOfDatesService1.checkDateString(date);
    }



}
