package BussinessObject;

import Presentation.Menu;
import Utilities.DataInput;
import Core.Entities.Customer;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import Core.Interfaces.ICustomerDAO;

/**
 *
 * @author SwordLake
 */
public class CustomerManagement {

    ICustomerDAO customerDAO;

    //--------------------------------------------------  
    public CustomerManagement(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    //--------------------------------------------------  
    public void processMenuForCustomer() {
        boolean stop = true;
        try {
            do {
                Menu.print("******Customer Management******|1.Add Customer|2.Update Customer|3.Remove Customer"
                        + "|4.Search Customers|5.Print Customer List|6.Export to file|7.Back to main menu|Select :");
                int choice = Menu.getUserChoice();
                switch (choice) {
                    case 1:
                        addNewCustomer();
                        break;
                    case 2:
                        updateCustomer();
                        break;
                    case 3:
                        deleteCustomer();
                        break;
                    case 4:
                        searchCustomers();
                        break;
                    case 5:
                        System.out.println(">>Customer List:");
                        printList(customerDAO.getCustomers());
                        break;
                    case 6:
                        exportToFile();
                        System.out.println(">>The customer list has successfully exported.");
                        break;
                    case 7:
                        stop = false;
                    default:
                        System.out.println(">>Choice invalid");
                }
            } while (stop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //--------------------------------------------------    
    public Customer inputCustomer() throws Exception {
        String id = DataInput.getString("Enter customer id:");
        String name = DataInput.getString("Enter customer name:");
        String address = DataInput.getString("Enter customer address:");
        return new Customer(id, name, address);
    }

    //--------------------------------------------------    
    public void setNewCustomerInfo(Customer customer) throws Exception {
        String name = DataInput.getString("Enter new name:");
        if (!name.isEmpty()) {
            customer.setName(name);
        }
        String address = DataInput.getString("Enter new address:");
        if (!address.isEmpty()) {
            customer.setAddress(address);
        }
    }

    //--------------------------------------------------  
    public void addNewCustomer() {
        try {
            Customer customer = inputCustomer();
            if (customerDAO.getCustomerById(customer.getId()) != null) {
                System.out.println(">>The customer already exists.");
                return;
            }
            customerDAO.addCustomer(customer);
            System.out.println(">>The customer has updated successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void updateCustomer() {
        try {
            String id = DataInput.getString("Enter customer id:");
            Customer customer = customerDAO.getCustomerById(id);
            if (customer == null) {
                System.out.println(">>The customer not found.");
                return;
            }
            setNewCustomerInfo(customer);
            customerDAO.updateCustomer(customer);
            System.out.println(">>The customer has updated successfully.");

        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void deleteCustomer() {
        try {
            String id = DataInput.getString("Enter customer id:");
            Customer customer = customerDAO.getCustomerById(id);
            if (customer == null) {
                System.out.println(">>The customer not found.");
                return;
            }
            customerDAO.removeCustomer(customer);
            System.out.println(">>The customer has deleted successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void findCustomerById() {
        Customer customer;
        try {
            String id = DataInput.getString("Enter customer id:");
            customer = customerDAO.getCustomerById(id);
            if (customer != null) {
                System.out.println(customer);
            } else {
                System.out.format("The customer id : %s not found.%n", id);
            }
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void searchCustomers() throws Exception {
        int choice;
        boolean stop = true;
        String value;
        try {
            do {
                Menu.print("1.Search by Name|2.Search by Address|3.Back|Select:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                        value = DataInput.getString("Enter customer name:");
                        List<Customer> customers = searchCustomerByName(value);
                        if (!customers.isEmpty()) {
                            printList(customers);
                        } else {
                            System.out.format("The customers with name:%s not found.%n", value);
                        }
                        break;
                    case 2:
                        value = DataInput.getString("Enter customer address:");
                        customers = searchCustomerByAddress(value);
                        if (!customers.isEmpty()) {
                            printList(customers);
                        } else {
                            System.out.format("The customers with address:%s not found.%n", value);
                        }
                        break;
                    case 3:
                        stop = false;
                        break;
                    default:
                        System.out.println(">>Choice invalid");
                        break;
                }

            } while (stop);
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

//--------------------------------------------------  
    public void printList(List<Customer> customers) throws Exception {
        //customerDAO.getCustomerList().forEach(obj -> System.out.println(obj));
        //or
        System.out.format("%-10s | %-20s | %s%n", "Customer Id", "Customer Name", "Address");
        System.out.println(String.join("", Collections.nCopies(55, "-")));
        for (Customer customer : customers) {
            System.out.format("%-11s | %-20s | %s%n",
                    customer.getId(), customer.getName(), customer.getAddress());
        }
        System.out.println(String.join("", Collections.nCopies(55, "-")));
    }

    //--------------------------------------------------  
    public void exportToFile() throws Exception {
        customerDAO.saveCustomerListToFile();
    }
    //--------------------------------------------------  

    public List<Customer> searchCustomerByName(String value) throws Exception {
        Predicate<Customer> predicate = p -> p.getName().toLowerCase().
                contains(value.toLowerCase());
        return customerDAO.search(predicate);
    }

    //--------------------------------------------------  
    public List<Customer> searchCustomerByAddress(String value) throws Exception {
        Predicate<Customer> predicate = p -> p.getAddress().toLowerCase().
                contains(value.toLowerCase());
        return customerDAO.search(predicate);
    }
    //--------------------------------------------------  
    //Write more the methods here..........
}
