package Core.Interfaces;

import Core.Entities.Student;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author SwordLake
 */
public interface IStudentDAO{   
    List<Student>  getStudents() throws Exception ;  
    Student  getStudentById(String id) throws Exception ; 
    void addStudent(Student student) throws Exception ;   
    void updateStudent(Student student) throws Exception ;   
    void removeStudent(Student student) throws Exception ;   
    void saveStudentListToFile() throws Exception ;  
    List<Student> search(Predicate<Student> predicate) throws Exception ;
    //--------------------------------------------------  
    //More the method here..........
}
