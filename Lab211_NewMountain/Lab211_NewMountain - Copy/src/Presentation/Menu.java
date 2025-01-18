package Presentation;

import Utilities.DataInput;
import java.util.Arrays;
import Core.Interfaces.ICustomerDAO;
import Core.Interfaces.IStudentDAO;
import BussinessObject.CustomerManagement;
import BussinessObject.StudentManagement;
import java.util.List;

/**
 *
 * @author SwordLake
 */
public class Menu {

    public static void print(String str) {
        List<String> menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }

        });
    }

    public static int getUserChoice() {
        int number = 0;
        try {
            number = DataInput.getIntegerNumber();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }

    public static void manageStudent(IStudentDAO service) {
        StudentManagement empMenu = new StudentManagement(service);
        empMenu.processMenuForStudent();
    }

    public static void manageCustomer(ICustomerDAO service) {
        CustomerManagement cusMenu = new CustomerManagement(service);
        cusMenu.processMenuForCustomer();
    }
}
