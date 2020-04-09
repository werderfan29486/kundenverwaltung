package customerManagementSoftware;

import java.util.*;

public class Customerservice {


    List<Customer> customerList = new ArrayList<Customer>();


    public void createCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void updateCustomer(Customer customer) {

        for (int i = 0; i < customerList.size(); i++) {
           //sich den Kunden holen, bei dem sich was geändert hat
            Customer customer1 = customerList.get(i);
            if (customer1.getId() == customer.getId()) {
                if (customer1.getName() != customer.getName()) {
                    customer1.setName(customer.getName());
                    if (customer1.getPostalcode() != customer.getPostalcode()) {
                        customer1.setPostalcode(customer.getPostalcode());
                    }
                    if (customer1.getFirstname() != customer.getFirstname()) {
                        customer1.setFirstname(customer.getFirstname());
                    }
                    if (customer1.getStreet() != customer.getStreet()) {
                        customer1.setStreet(customer.getStreet());
                    }
                    if (customer1.getHousenumber() != customer.getHousenumber()) {
                        customer1.setHousenumber(customer.getHousenumber());
                    }
                }
            }
        }
    }

        public void printCustomerData(Customer customer) {
            System.out.println("Kunde mit der ID: " + customer.getId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Vorname " + customer.getFirstname());
            System.out.println("Straße: " + customer.getStreet());
            System.out.println("Hausnummer " + customer.getHousenumber());
            System.out.println("Plz:  " + customer.getPostalcode());
        }

        public boolean searchCustomer(String searchterm) {
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer1 = customerList.get(i);
            if (customer1.getName() == searchterm) {
                printCustomerData(customer1);
            } else if (customer1.getFirstname() == searchterm) {
                printCustomerData(customer1);
            } else if (customer1.getStreet() == searchterm) {
                printCustomerData(customer1);
            } else if (customer1.getPostalcode() == searchterm) {
                printCustomerData(customer1);
            } else if (customer1.getHousenumber() == searchterm) {
               // printCustomerData(customer1);
            }

        }
        return true;
    }

    public void deleteCustomer(Customer customer) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == customer.getId()) {
                customerList.remove(i);
            }
        }
    }

    public void printCustomerList() {
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer1 = customerList.get(i);
            System.out.println("Kundennummer " + customer1.getCustomernumber());
            System.out.println("Name: " + customer1.getName());
            System.out.println("Vorname: " + customer1.getFirstname());
            System.out.println("Straße: " + customer1.getStreet());
            System.out.println("Hausnummer: " + customer1.getHousenumber());
            System.out.println("PLZ: " + customer1.getPostalcode());
            System.out.println();

        }
    }

}


