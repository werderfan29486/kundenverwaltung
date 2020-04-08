package kundenverwaltung;

import customerManagementSoftware.Customer;
import customerManagementSoftware.Customerservice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class customerservicetest {

    List<Customer> kundenliste = new ArrayList<Customer>();
    Customer customer1 = new Customer("Gantzert", "Sebastian", "Auf der Schmelz", "30", "64380");
    Customer customer2 = new Customer("Schwarck", "Alex", "Fuchsenhütte", "27", "64380");
    Customer customer3 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");
    Customer customer4 = new Customer("Schwarck", "Martin", "Keineahnung", "11", "64380");
    Customer customer5 = new Customer("Gantzert", "Sebastian", "Auf der Schmelz", "30", "64380");
    Customer customer6 = new Customer("Schwarck", "Alex", "Fuchsenhütte", "27", "64380");
    Customer customer7 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");
    Customer customer8 = new Customer("Schwarck", "Martin", "Keineahnung", "11", "64380");
    Customer customer9 = new Customer("Gantzert", "Sebastian", "Auf der Schmelz", "30", "64380");
    Customer customer10 = new Customer("Schwarck", "Alex", "Fuchsenhütte", "27", "64380");
    Customer customer11 = new Customer("Jüttner", "Thomas", "Lessingstraße", "9", "64283");
    Customer customer12 = new Customer("Schwarck", "Martin", "Keineahnung", "11", "64380");
    Customerservice customerservice = new Customerservice();

    @Test
    public void testCreateCustomer() {
        customerservice.createCustomer(customer1);
        System.out.print(customerservice.customerList);
    }

    @Test
    public void testDeleteCustomer() {
        customerservice.createCustomer(customer1);
        customerservice.createCustomer(customer2);
        System.out.println("Kunden in der Liste: " + customerservice.customerList.size());
        customerservice.deleteCustomer(customer2);
        if (customerservice.customerList.isEmpty()) {
            System.out.println("gelöscht");
        } else {
            System.out.println("Kunden in der Liste: " + customerservice.customerList.size());
        }
        customerservice.printCustomerList();
    }

    @Test
    public void testeSearchCustomer() {
        customerservice.createCustomer(customer4);
        customerservice.createCustomer(customer2);
        customerservice.searchCustomer("27");
    }

    @Test
    public void testePrintCustomer() {
        customerservice.createCustomer(customer1);
        customerservice.createCustomer(customer2);
        customerservice.printCustomerData(customer1);
    }

    @Test
    public void testeUpdateCustomer() {
        customerservice.createCustomer(customer1);

        System.out.println(customerservice.customerList.get(0).getId());
        System.out.println(customerservice.customerList.get(0).getName());
        System.out.println(customerservice.customerList.get(0).getPostalcode());
        System.out.println(customerservice.customerList.get(0).getHousenumber());
        System.out.println(customerservice.customerList.get(0).getStreet());

        customer1.setName("Schwarck");
        customer1.setPrename("Sebastian");
        customer1.setPostalcode("64380");
        customer1.setHousenumber("30");
        customer1.setStreet("Auf der Schmelz");

        customerservice.updateCustomer(customer2);
        System.out.println(customerservice.customerList.get(0).getId());
        System.out.println(customerservice.customerList.get(0).getName());
        System.out.println(customerservice.customerList.get(0).getPostalcode());
        System.out.println(customerservice.customerList.get(0).getHousenumber());
        System.out.println(customerservice.customerList.get(0).getStreet());
    }

    @Test
    public void testPrintCustomerList() {
        customerservice.createCustomer(customer1);
        customerservice.createCustomer(customer2);
        customerservice.createCustomer(customer3);
        customerservice.createCustomer(customer4);
        customerservice.createCustomer(customer5);
        customerservice.createCustomer(customer6);
        customerservice.createCustomer(customer7);
        customerservice.createCustomer(customer8);
        customerservice.createCustomer(customer9);
        customerservice.createCustomer(customer10);
        customerservice.createCustomer(customer11);
        customerservice.createCustomer(customer12);
        customerservice.printCustomerList();
    }

}
