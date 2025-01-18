package Presentation;

import Utilities.DataInput;
import DataObject.CustomerDAO;
import DataObject.StudentDAO;
import Core.Interfaces.ICustomerDAO;
import Core.Interfaces.IStudentDAO;

public class Program {

    public static void main(String[] args) {
        int choice;
        //System.out.println(String.join("", Collections.nCopies(10, "**********")));
        try {

            do {
                System.out.println("***************Main Menu***************");
                Menu.print("1.Student Management|2.Customer Management|3.Exit|Select:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                        IStudentDAO employeeService = new StudentDAO();
                        Menu.manageStudent(employeeService);
                        break;
                    case 2:
                        ICustomerDAO customerService = new CustomerDAO();
                        Menu.manageCustomer(customerService);
                        break;
                    default:
                        System.out.println("Good bye !");
                        System.exit(0);
                        break;
                }
            } while (true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
