package classrecord.dao;

import java.util.List;
import org.slim3.datastore.DaoBase;
import org.slim3.datastore.FilterCriterion;
import org.slim3.datastore.ModelQuery;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import classrecord.meta.StudentMeta;
import classrecord.model.Student;

/**
 * Dao used to access datastore for Student transactions
 * @author J-Dar Siegfred Rodriguez
 * @version 0.01
 * Version History
 * 0.01 - J-Dar Siegfred Rodriguez -  initial codes
 */

public class StudentDao extends DaoBase<Student>{
    
    public ModelQuery<Student> query() {
        return super.query();
    }
    
    /**
     * Method used to delete a student
     * @param sKey - key of a student to delete
     */
    public void delete(String sKey) {
        Key key = KeyFactory.stringToKey(sKey);
        super.delete(key);
    }
    
    /**
     * Method used to get student record
     * @param sKey - key of a student to get
     * @return Student
     */
    public Student get(String sKey) {
        Key key = KeyFactory.stringToKey(sKey);
        return super.get(key);
    }
    
    public void getKey () {
        
    }
    
    /**
     * Method used to check if a student already exists in the datastore
     * @param student - Student to check
     * @return boolean - true if student already exists, otherwise false
     */
    public boolean studentExists(Student student) {
        StudentMeta meta = StudentMeta.get();
    
                                 //should not trust. watch closely
        FilterCriterion filter = meta.and(meta.firstName.equal(student.getFirstName()),
                                          meta.lastName.equal(student.getLastName()),
                                          meta.birthDate.equal(student.getBirthDate()), 
                                          meta.course.equal(student.getCourse()));
       
        
        Student result = super.query().filter(filter).asSingle();
        
        return result != null;
    }
    
    /**
     * Method used to check an entity exists
     * @param key - key to check
     * @return boolean
     */
    public boolean exists(String key) {
        return super.exists(KeyFactory.stringToKey(key));
    }
    
    /**
     * Method used to find student by first name
     * @param firstName - string to search
     * @return List<Student>
     */
    public List<Student> findStudentByFirstName(String firstName) {
        return super.query().filter(StudentMeta.get().firstName.startsWith(firstName)).asList();
    }
    
    /**
     * Method used to find student by last name
     * @param lastName - string to search
     * @return List<Student>
     */
    public List<Student> findStudentByLastName(String lastName) {
        return super.query().filter(StudentMeta.get().lastName.startsWith(lastName)).asList();
    }
    
    /**
     * Method used to update a student record
     * @param key - key of the student to update
     * @param student - student object to update
     */
    public void updateStudent(String key, Student student) {
        student.setKey(KeyFactory.stringToKey(key));
        super.put(student);
    }
    
    /**
     * Method used to get all the students
     * @return List<Student>
     */
    public List<Student> getAllStudents() {
        List<Student> students  = super.query().asList();
        return students;
    }
}
