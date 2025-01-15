package BussinessObject;

import Presentation.Menu;
import Utilities.DataInput;
import Core.Entities.Student;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import Core.Interfaces.IStudentDAO;

/**
 *
 * @author SwordLake
 */
public class StudentManagement {

    IStudentDAO studentDAO;

    //--------------------------------------------------  
    public StudentManagement(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    //--------------------------------------------------  
    //--------------------------------------------------  
    public void processMenuForStudent() {
        boolean stop = true;
        try {
            do {
                Menu.print("******Student Management******|1.Add Student|2.Update Student|3.Remove Student"
                        + "|4.Search Students|5.Print Student List|6.Export to file|7.Back to main menu|Select :");
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
                        exportToFile();
                        System.out.println(">>The student list has successfully exported.");
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
                System.out.println(">>The student not found.");
                return;
            }
            studentDAO.removeStudent(student);
            System.out.println(">>The student has deleted successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
//    public void findStudentById() {
//        Student student;
//        try {
//            String id = DataInput.getString("Enter student id:");
//            student = studentDAO.getStudentById(id);
//            if (student != null) {
//                System.out.println(student);
//            } else {
//                System.out.format("The student id : %s not found.%n", id);
//            }
//        } catch (Exception e) {
//            System.out.println(">>Error:" + e.getMessage());
//        }
//    }

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
            System.out.format("The students with name:%s not found.%n", value);
        }
    }

    public void printSearchStudentsByCampus() throws Exception {
        String value;
        value = DataInput.getString("Enter student Campus code:");
        List<Student>students = searchStudentByCampus(value);
        if (!students.isEmpty()) {
            printList(students);
        } else {
            System.out.format("The students with email:%s not found.%n", value);
        }

    }

//    public void searchStudents() throws Exception {
//        int choice;
//        boolean stop = true;
//        String value;
//        try {
//            do {
//                Menu.print("1.Search by Name|2.Search by Campus|3.Back|Select:");
//                choice = DataInput.getIntegerNumber();
//                switch (choice) {
//                    case 1:
//                        value = DataInput.getString("Enter student name:");
//                        List<Student> students = searchStudentByName(value);
//                        if (!students.isEmpty()) {
//                            printList(students);
//                        } else {
//                            System.out.format("The students with name:%s not found.%n", value);
//                        }
//                        break;
//                    case 2:
//                        value = DataInput.getString("Enter student ID:");
//                        students = searchStudentByCampus(value);
//                        if (!students.isEmpty()) {
//                            printList(students);
//                        } else {
//                            System.out.format("The students with email:%s not found.%n", value);
//                        }
//                        break;
//
//                    case 3:
//                        stop = false;
//                        break;
//                    default:
//                        System.out.println(">>Choice invalid");
//                        break;
//                }
//            } while (stop);
//        } catch (Exception e) {
//            System.out.println(">>Error:" + e.getMessage());
//        }
//    }

    //--------------------------------------------------  
    public void printList(List<Student> students) throws Exception {
        //studentDAO.getStudentList().forEach(obj -> System.out.println(obj));
        //or
        System.out.format("%-10s | %-20s | %-10s | %-20s| %s %n", "Student Id", "Student Name", "Phone","Mountain Peak Code","Tuition Fee");
        System.out.println(String.join("", Collections.nCopies(55, "-")));
        for (Student student : students) {
            System.out.format("%-10s | %-20s | %-10s | %-20s| %d%n",
                    student.getId(), student.getName(), student.getPhone(),student.getMountainCode(),student.getTuitionFee());
        }
        System.out.println(String.join("", Collections.nCopies(55, "-")));
    }

    //--------------------------------------------------  
    public void exportToFile() throws Exception {
        studentDAO.saveStudentListToFile();
    }
    //--------------------------------------------------  

    //--------------------------------------------------  
   
    //--------------------------------------------------  
    //To do here..........
}
