package customerManagementSoftware;

import java.util.*;

public class Customerservice {


    List<Customer> customerList = new ArrayList<Customer>();


    public void createCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void updateCustomer(Customer customer, String oldValue, String newValue) {

                if (oldValue.equals(customer.getName())) {
                updateCustomerName(customer, newValue);
                }  else if (oldValue.equals(customer.getFirstname()))
                   updateCustomerFirstName(customer, newValue);
                   else if (oldValue.equals(customer.getStreet()))
                   updateCustomerStreet(customer, newValue);
                   else if (oldValue.equals(customer.getHousenumber()))
                   updateCustomerHouseNumber(customer, newValue);
                   else if (oldValue.equals(customer.getPostalcode()))
                   updateCustomerPostalCode(customer, newValue);
                   else {System.out.println("Wert nicht gefunden");}
   }

    public void updateCustomerName(Customer customer, String updateWord) {
                if (!updateWord.equals(customer.getName())) {
                    customer.setName(updateWord);
                    System.out.print(customer.getName());
                }
    }

    public void updateCustomerFirstName(Customer customer, String updateFirstName) {
                customer.setFirstname(updateFirstName);
                System.out.print(customer.getFirstname());
    }

    public void updateCustomerStreet(Customer customer, String updateStreet) {
                customer.setStreet(updateStreet);
                System.out.print(customer.getStreet());
    }

    public void updateCustomerHouseNumber(Customer customer, String updateHouseNumber) {
                customer.setHousenumber(updateHouseNumber);
                System.out.print(customer.getHousenumber());
    }

    public void updateCustomerPostalCode(Customer customer, String updatePostalCode) {
                customer.setPostalcode(updatePostalCode);
                System.out.print(customer.getPostalcode());
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
                for (Customer customer : customerList) {
                    if (searchterm.equals(customer.getName())  || searchterm.equals(customer.getFirstname())
                        || searchterm.equals(customer.getStreet()) || searchterm.equals(customer.getHousenumber())
                        || searchterm.equals(customer.getPostalcode())) {
                    printCustomerData(customer);
                }
            }
        return true;
    }

    public void deleteCustomer(Customer customer) {
                for (int i = 0; i < customerList.size(); i++) {
                    if (customerList.get(i).getId().equals(customer.getId())) {
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


