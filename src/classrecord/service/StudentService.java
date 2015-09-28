package classrecord.service;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slim3.datastore.FilterCriterion;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import classrecord.dao.StudentDao;
import classrecord.dto.StudentDTO;
import classrecord.meta.StudentMeta;
import classrecord.model.Student;

/**
 * Service used to handle student transactions
 * @author Bryan Agustine Cabansay
 * @version 0.02
 * Version History
 * 0.01 - Bryan Agustine Cabansay - initial codes
 * 0.02 - Bryan Agustine Cabansay - added search student method
 */

public class StudentService {
    /**
     * Student DAO
     */
    private final StudentDao DAO;
    
    /**
     * Student service
     */
    private static final StudentService service = new StudentService();
    
    /**
     * Constructor that initializes the studentDAO
     */
    public StudentService() {
        DAO = new StudentDao();
    }
    
    /**
     * Method to get an instance of StudentService
     * @return StudentService
     */
    public static StudentService get() {
        return service;
    }
    
    /**
     * Method to get the data of a particular student
     * @param dto - studentDTO with a key to search and to set the other fields.
     * @return StudentDTO
     */
    public StudentDTO getStudent(StudentDTO dto) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (!DAO.exists(dto.getKey())) {
            throw new InvalidParameterException("Student does not exist.");
        }
        
        Student student = DAO.get(dto.getKey());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setCourse(student.getCourse());
        dto.setBirthDate(formatter.format(student.getBirthDate()));
        
        return dto;
    }
    
    /**
     * Method used to register a student
     * @param dto - StudentDTO of a new student
     * @return StudentDTO
     * @throws ParseException
     */
    public StudentDTO registerStudent(StudentDTO dto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Student student = new Student(dto.getFirstName(), dto.getLastName(), dto.getCourse(), formatter.parse(dto.getBirthDate()));
        
        if (DAO.studentExists(student)) {
            throw new InvalidParameterException("Student already exists.");
        }
        
        Key key = DAO.put(student);
        dto.setKey(key.toString());
        
        return dto;
    }
    
    /**
     * Method used to unregister a student
     * @param dto - StudentDTO to delete
     */
    public void unregisterStudent(StudentDTO dto) {
        if (!DAO.exists(dto.getKey())) {
            throw new InvalidParameterException("Student does not exist");
        }
        
        DAO.delete(dto.getKey());
    }
    
       /**
        * Method used to update a student record
        * @param dto - StudentDTO of the student to update
        * @throws ParseException
        */
    public void updateStudent(StudentDTO dto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Student student = new Student(dto.getFirstName(), dto.getLastName(), dto.getCourse(), formatter.parse(dto.getBirthDate()));
        if (DAO.studentExists(student)) {
            throw new InvalidParameterException("Student already exists.");
        }               
        if (!DAO.exists(dto.getKey())) {
            throw new InvalidParameterException("Student does not exist.");
        }               
        DAO.updateStudent(dto.getKey(), student);
    }
    
    /**
     * Method used to search a student
     * @param dto - StudentDTO of a student to search
     * @return StudentDTO[]
     */
    public StudentDTO[] findStudentByName(StudentDTO dto) {
        
        //List<Student> firstNameSearchResult = DAO.findStudentByFirstName(dto.getFirstName());
        //List<Student> lastNameSearchResult = DAO.findStudentByLastName(dto.getLastName());
        StudentMeta meta = StudentMeta.get();
        FilterCriterion filter = meta.or(meta.firstName.equal(dto.getFirstName()), meta.lastName.equal(dto.getLastName()));
        List<Student> result = DAO.query().filter(filter).asList();
        ArrayList<StudentDTO> results;
        results = new ArrayList<StudentDTO>(result.size());//(firstNameSearchResult.size() + lastNameSearchResult.size());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        for (Student student : result) {
            results.add(new StudentDTO(KeyFactory.keyToString(student.getKey()), student.getFirstName(),
                                       student.getLastName(), student.getCourse(), 
                                       formatter.format(student.getBirthDate())));
            
        }        
        return results.toArray(new StudentDTO[results.size()]);
    }
    
    /**
     * Method used to get all students
     * @return StudentDTO[]
     */
    public StudentDTO[] getAllStudents() {
        List<Student> result = DAO.query().asList();
        ArrayList<StudentDTO> results;
        results = new ArrayList<StudentDTO>(result.size());//(firstNameSearchResult.size() + lastNameSearchResult.size());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        for (Student student : result) {
            results.add(new StudentDTO(KeyFactory.keyToString(student.getKey()), student.getFirstName(),
                                       student.getLastName(), student.getCourse(), 
                                       formatter.format(student.getBirthDate())));
            
        }        
        return results.toArray(new StudentDTO[results.size()]);        
    }
    
    /**
     * Method used to get the student DAO
     * @return StudentDao
     */
    public StudentDao getDAO() {
        return DAO;
    }
    
    
}
