package DataObject;

import Core.Interfaces.IStudentDAO;
import Core.Entities.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
//Thêm function xử lí tution fee
public class StudentDAO implements IStudentDAO {

    private List<Student> studentList = new ArrayList<>();
    private final FileManager fileManager;

    public StudentDAO() throws Exception {
        this.fileManager = new FileManager("Students.txt");
        loadDataFromFile();
    }

    //--------------------------------------------------   
    public void loadDataFromFile() throws Exception {
        // ? phone ko the khai b,mCode giong nhung parameter khac
        String id, phone, name, email,mountainCode,tuitionFee;
         
        List<String> empData = fileManager.readDataFromFile();
        studentList.clear();
        for (String e : empData) {
            List<String> emp = Arrays.asList(e.split(","));
            id = emp.get(0).trim();
            name = emp.get(1).trim();
            phone = emp.get(2).trim();
            email = emp.get(3).trim();
            mountainCode=emp.get(4).trim();
            tuitionFee=emp.get(5).trim();
            Student newEmp = new Student(id, name, phone, email, mountainCode);
            studentList.add(newEmp);
            if (studentList.isEmpty()) {
                throw new Exception("Student list is empty.");
            }
        }
    }

    //--------------------------------------------------
    @Override
    public List<Student> getStudents() throws Exception {
        Collections.sort(studentList, (e1, e2) -> e1.getId().compareTo(e2.getId()));
        return studentList;
    }

    //--------------------------------------------------
    @Override
    public void addStudent(Student student) throws Exception {
        studentList.add(student);
        saveStudentListToFile();
    }
    //--------------------------------------------------

    @Override
    public void saveStudentListToFile() throws Exception {
        List<String> stringObjects = studentList.stream().map(String::valueOf).collect(Collectors.toList());
        String data = String.join("\n", stringObjects);
        fileManager.saveDataToFile(data);
    }

    //--------------------------------------------------
    @Override
    public void updateStudent(Student student) throws Exception {
        Student studentUpdate = getStudentById(student.getId());
        if (studentUpdate != null) {
            studentUpdate.setName(student.getName());
            studentUpdate.setPhone(student.getPhone());
            studentUpdate.setEmail(student.getEmail());
            studentUpdate.setMountainCode(student.getMountainCode());
            saveStudentListToFile();
        }
    }

    //--------------------------------------------------
    @Override
    public void removeStudent(Student student) throws Exception {
        Student emp = getStudentById(student.getId());
        if (emp != null) {
            studentList.remove(emp);
            saveStudentListToFile();
        }
    }

    @Override
    public Student getStudentById(String id) throws Exception {
        if (studentList.isEmpty()) {
            getStudents();
        }
        Student student = studentList.stream()
                .filter(e -> e.getId()
                .equalsIgnoreCase(id)).findAny().orElse(null);
        return student;
    }
    public Student getStudentByName(String name) throws Exception {
        if (studentList.isEmpty()) {
            getStudents();
        }
        Student student = studentList.stream()
                .filter(e -> e.getName()
                .equalsIgnoreCase(name)).findAny().orElse(null);
        return student;
    }
    @Override
    public List<Student> search(Predicate<Student> predicate) throws Exception {
        return studentList.stream().
                filter(student -> predicate.test(student)).collect(Collectors.toList());
    }
    //--------------------------------------------------------------------
    //More the methods here..........
}
