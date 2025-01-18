package BussinessObject;

import Presentation.Menu;
import Utilities.DataInput;
import Core.Entities.Student;
import Core.Entities.Mountain;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import Core.Interfaces.IStudentDAO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SwordLake
 */
public class StudentManagement {

    IStudentDAO studentDAO;
    Map<String, Mountain> mountainStatistics;
    //--------------------------------------------------  
    public StudentManagement(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
        this.mountainStatistics = new HashMap<>();
    }

    //--------------------------------------------------  
    //--------------------------------------------------  
    public void processMenuForStudent() {
        boolean stop = true;
        try {
            do {
                Menu.print("******Student Management******|1.Add Student|2.Update Student|3.Display Registered List"
                        + "|4.Delete Registration Information|5.Search Participants by Name|6.Filter Data by Campus|7.Statistics of Registration|8.Exit the Program|Select :");
                int choice = Menu.getUserChoice();
                switch (choice) {
                    case 1:
                        addNewStudent();
                        break;
                    case 2:
                        updateStudent();
                        break;
                    case 3:
                        System.out.println(">>Student List:");
                        printList(studentDAO.getStudents());
                        break;

                    case 4:
//hiển thị đối tượng chuẩn bị bị xóa                        
//cần ask còmirm từ người dùng
                        deleteStudent();
                        break;
                    case 5:
                        printSearchStudentsByName();
                        break;
                    case 6:
                        // chưa xét đièu kiện để thỏa mãn filter by campus
                        printSearchStudentsByCampus();
                        break;
                    case 7:
                        statisticalize();
                        break;
                    case 8:
                        stop = false;
                    default:
                        System.out.println(">>Choice invalid");
                        break;
                }
            } while (stop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //--------------------------------------------------    

    public Student inputStudent() throws Exception {
        String id = DataInput.getString("Enter student id:");
        String name = DataInput.getString("Enter student name:");
        String phone=DataInput.getString("Enter student phone");
        String email = DataInput.getString("Enter student email:");
        String mountainCode=DataInput.getString("Enter Mountain code:");
        
        // return new Student(id, name, email);
        return new Student( id, name, phone, email, mountainCode);
    }

    //--------------------------------------------------    
    public void setNewStudentInfo(Student student) throws Exception {
        String name = DataInput.getString("Enter new name:");
        if (!name.isEmpty()) {
            student.setName(name);
        }
        String phone = DataInput.getString("Enter new phone:");
        if (!phone.isEmpty()) {
            student.setPhone(phone);
        }
        String email = DataInput.getString("Enter new email:");
        if (!email.isEmpty()) {
            student.setEmail(email);
        }
        String mCode = DataInput.getString("Enter new mountain code:");
        if (!mCode.isEmpty()) {
            student.setMountainCode(mCode);
        }
    }

    //--------------------------------------------------  
    public void addNewStudent() {
        try {
            Student student = inputStudent();
            if (studentDAO.getStudentById(student.getId()) != null) {
                System.out.println(">>The student already exists.");
                return;
            }
            studentDAO.addStudent(student);
            System.out.println(">>The student has updated successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void updateStudent() {
        try {
            String id = DataInput.getString("Enter student id:");
            Student student = studentDAO.getStudentById(id);
            if (student == null) {
                System.out.println(">>The student not found.");
                return;
            }
            setNewStudentInfo(student);
            studentDAO.updateStudent(student);
            System.out.println(">>The student has updated successfully.");

        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void deleteStudent() {
        try {
            String id = DataInput.getString("Enter student id:");
            Student student = studentDAO.getStudentById(id);

            if (student == null) {
                System.out.println("This student has not registered yet.");
                return;
            }

            System.out.println("Are you sure you want to delete this student?");

            System.out.println(String.join("", Collections.nCopies(55, "-")));
            System.out.print("Student ID: ");
            System.out.println(student.getId());
            System.out.print("Name: ");
            System.out.println(student.getName());
            System.out.print("Phone: ");
            System.out.println(student.getPhone());
            System.out.print("Mountain: ");
            System.out.println(student.getMountainCode());
            System.out.print("Fee: ");
            System.out.println(student.getTuitionFee());
            
            System.out.println(String.join("", Collections.nCopies(55, "-")));
            System.out.print("Are you sure you want to delete this registration? (Y/N):");

            String choice = DataInput.getString().trim().toUpperCase();

            if (choice.equals("Y")) {
                studentDAO.removeStudent(student);
                System.out.println("The registration has been successfully deleted.");
            } else if (choice.equals("N")) {
                System.out.println("Returning to the main menu without changes.");
            } else {
                System.out.println("Invalid choice. Returning to the main menu.");
            }
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }


    public List<Student> searchStudentByName(String value) throws Exception {
        Predicate<Student> predicate = p -> p.getName().toLowerCase().
                contains(value.toLowerCase());
        return studentDAO.search(predicate);
    }
     public List<Student> searchStudentByCampus(String value) throws Exception {
        Predicate<Student> predicate = p -> p.getId().toLowerCase().
                contains(value.toLowerCase());
        return studentDAO.search(predicate);
    }
    //--------------------------------------------------  
    public void printSearchStudentsByName() throws Exception {
        String value;
        value = DataInput.getString("Enter student name:");
        List<Student> students = searchStudentByName(value);
        if (!students.isEmpty()) {
            printList(students);
        } else {
            System.out.print("No one matches the search criteria!");
        }
    }

    public void printSearchStudentsByCampus() throws Exception {
        String value;
        value = DataInput.getString("Enter student Campus code:");
        List<Student>students = searchStudentByCampus(value);
        if (!students.isEmpty()) {
            printList(students);
        } else {
            System.out.print("No students have registered under this campus.");
        }

    }

    //--------------------------------------------------  
    public void printList(List<Student> students) throws Exception {
        //studentDAO.getStudentList().forEach(obj -> System.out.println(obj));
        //or
        System.out.format("%-10s | %-20s | %-10s | %-20s| %s %n", "Student Id", "Student Name", "Phone","Mountain Peak Code","Tuition Fee");
        System.out.println(String.join("", Collections.nCopies(55, "-")));
        for (Student student : students) {
            System.out.format("%-10s | %-20s | %-10s | %-20s| %s%n",
                    student.getId(), student.getName(), student.getPhone(),student.getMountainCode(),student.getTuitionFee());
        }
        System.out.println(String.join("", Collections.nCopies(55, "-")));
    }

    //--------------------------------------------------  
    public void exportToFile() throws Exception {
        studentDAO.saveStudentListToFile();
    }
    public void statisticalize() {
    try {
        // Fetch all students from DAO
        List<Student> students = studentDAO.getStudents();

        // Create a map to store mountain codes and their statistics
        Map<String, Mountain> mountainStats = new HashMap<>();

        for (Student student : students) {
            String mountainCode = student.getMountainCode();
            int tuitionFee = Integer.parseInt(student.getTuitionFee());

            // Update statistics for each mountain code
            //cần xem lại
            mountainStats.putIfAbsent(mountainCode, new Mountain(mountainCode, 0, 0));
            Mountain mountain = mountainStats.get(mountainCode);
            mountain.setNumOfRegistration(mountain.getNumOfRegistration() + 1);
            mountain.setTotalCost(mountain.getTotalCost() + tuitionFee);
        }

        // Print statistics
        System.out.format("%-20s | %-15s | %-15s%n", "Mountain Code", "Registrations", "Total Cost");
        System.out.println(String.join("", Collections.nCopies(55, "-")));
        for (Mountain mountain : mountainStats.values()) {
            System.out.format("%-20s | %-15d | %-15d%n", 
                              mountain.getMountainCode(), 
                              mountain.getNumOfRegistration(), 
                              mountain.getTotalCost());
        }
        System.out.println(String.join("", Collections.nCopies(55, "-")));

    } catch (Exception e) {
        System.out.println("Error while processing statistics: " + e.getMessage());
    }
}

    //--------------------------------------------------  

    //--------------------------------------------------  
   
    //--------------------------------------------------  
    //To do here..........
}
