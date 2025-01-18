package Presentation;

import DataObject.StudentDAO;
import Core.Interfaces.IStudentDAO;

public class Program {

    public static void main(String[] args) {
        try {
            // Directly create the StudentDAO instance
            IStudentDAO studentService = new StudentDAO();
            // Call the Student Management menu
            Menu.manageStudent(studentService);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
