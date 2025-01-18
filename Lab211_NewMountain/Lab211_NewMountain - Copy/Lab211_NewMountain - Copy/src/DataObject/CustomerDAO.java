package DataObject;

import Core.Interfaces.ICustomerDAO;
import Core.Entities.Customer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class CustomerDAO implements ICustomerDAO {
    private List<Customer> customerList = new ArrayList<>();
    private final  FileManager fileManager;
    public CustomerDAO() throws Exception{
         this.fileManager = new FileManager("Customers.txt");
         loadDataFromFile();
    }
    //--------------------------------------------------   
    public  void loadDataFromFile() throws Exception {        
        String id, name, address;
        List<String> cusData = fileManager.readDataFromFile();
        for (String c : cusData) {
            List<String> emp = Arrays.asList(c.split(","));
            id = emp.get(0).trim();
            name = emp.get(1).trim();
            address = emp.get(2).trim();
            Customer newCustomer = new Customer(id, name, address);
            customerList.add(newCustomer);
        }
    }
    //--------------------------------------------------
    @Override
    public  List<Customer> getCustomers() throws Exception {      
        Collections.sort(customerList,(e1,e2)->e1.getId().compareTo(e2.getId()));
        return customerList;        
    }
    //--------------------------------------------------
    @Override
    public Customer getCustomerById(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //--------------------------------------------------
    @Override
    public void addCustomer(Customer customer) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //--------------------------------------------------
    @Override
    public void updateCustomer(Customer customer) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //--------------------------------------------------
    @Override
    public void removeCustomer(Customer customer) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //--------------------------------------------------
    @Override
    public void saveCustomerListToFile() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //--------------------------------------------------
    @Override
    public List<Customer> search(Predicate<Customer> predicate) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //--------------------------------------------------
    //Write more the methods here
}
