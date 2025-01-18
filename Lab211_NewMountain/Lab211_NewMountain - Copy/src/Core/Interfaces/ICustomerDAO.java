package Core.Interfaces;

/**
 *
 * @author Sword Lake
 */


import Core.Entities.Customer;
import java.util.List;
import java.util.function.Predicate;

public interface ICustomerDAO{    
    List<Customer> getCustomers() throws Exception ;   
    Customer  getCustomerById(String id) throws Exception ; 
    void addCustomer(Customer customer) throws Exception ;   
    void updateCustomer(Customer customer) throws Exception ;   
    void removeCustomer(Customer customer) throws Exception ;   
    void saveCustomerListToFile() throws Exception ;  
    List<Customer> search(Predicate<Customer> predicate) throws Exception ;
    //More the mehthods here
}