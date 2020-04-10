package customerManagementSoftware;

import org.junit.jupiter.api.Test;


public class testDates {

    Dates listOfDates1 = new Dates();

    Customer customer = new Customer("Schwarck", "Alex", "An der Fuchsenhütte", "27", "64380");
    Customer customer2 = new Customer("Gantzert", "Sega", "Auf der Schmelz", "27", "64380");
    Customer customer3 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");


    @Test
    public void testAddCustomerDate() {
        listOfDates1.addCustomerDate(customer, "20. Juni");
        listOfDates1.addCustomerDate(customer, "30. August");
        listOfDates1.addCustomerDate(customer, "10. August");
        listOfDates1.addCustomerDate(customer2, "15. März");
        listOfDates1.addCustomerDate(customer2, "30. März");
        listOfDates1.addCustomerDate(customer2, "30. August");
        listOfDates1.addCustomerDate(customer3, "15. März");
        listOfDates1.addCustomerDate(customer3, "30. März");
        listOfDates1.addCustomerDate(customer3, "30. August");
        listOfDates1.printCustomerDates(customer);
        listOfDates1.printCustomerDates(customer2);
        listOfDates1.printCustomerDates(customer3);

    }

    //@Test
   // public void testeDatum() {
     //   listOfDates1.inputDate("30", "10", "2020", "20", "00");
    //}

    @Test

    public void testDeleteDate() {
        listOfDates1.addCustomerDate(customer, "20. Juni");
        listOfDates1.addCustomerDate(customer, "30. April");
        listOfDates1.addCustomerDate(customer, "10. April");
        listOfDates1.deleteCustomerDate(customer, "30. April");
        listOfDates1.printCustomerDates(customer);
    }

    @Test
    public void testChangeCustomerDate() {
        listOfDates1.addCustomerDate(customer, "20. Juni");
        listOfDates1.addCustomerDate(customer, "30. April");
        listOfDates1.changeCustomerDate(customer, "20. Juni", "21.Juni");
        listOfDates1.printCustomerDates(customer);
    }

    @Test
    public void testPrintCustomerDates() {
        listOfDates1.addCustomerDate(customer, "20. Juni");
        listOfDates1.printCustomerDates(customer);
    }

    @Test
    public void testNumberOfDates() {
        listOfDates1.addCustomerDate(customer, "20. Juni");
        listOfDates1.addCustomerDate(customer, "23. August");
        System.out.println(listOfDates1.numberOfDates(customer));
    }


}
