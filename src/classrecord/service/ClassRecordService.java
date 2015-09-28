package classrecord.service;

import java.util.List;

import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;
import classrecord.dao.ClassDao;
import classrecord.dao.ClassRecordDao;
import classrecord.model.ClassRecord;
import classrecord.model.Class;
import classrecord.model.Student;

/**
 * Service used to handle class record transactions
 * @author:Julie Jane Alegre
 * @version 1.0
 */
public class ClassRecordService {
    /**
     * Creates an instance of Class record service
     */
    private static final ClassRecordService instance = new ClassRecordService();
    
    /**
     * Class DAO
     */
    private final ClassDao classDAO;
    
    /**
     * Class record DAO
     */
    private final ClassRecordDao classRecordDAO;
    
    /**
     * Method to get an instance of ClassRecordService
     * @return ClassRecordService
     */
    public static ClassRecordService get() {
        return instance;
    }
    
    /**
     * Constructor which initializes the classDao and classRecordDao to use
     * Contains methods for transaction to the datastore
     */
    public ClassRecordService() {
        classDAO = new ClassDao();
        classRecordDAO = new ClassRecordDao();
    }
    
    /**
     * Method used to delete a class
     * @param key - the key of the class to be deleted
     */
    public void deleteClass(Key key){
        classDAO.delete(key);
    }
    
    /**
     * Method used to delete a student
     * @param key - the key of the student to be deleted
     */
    public void deleteStudent(Key key) {
        classRecordDAO.delete(key);
    }
    
    /**
     * Method used to update a class
     * @param classObject - the classObject to be updated
     */
    public void updateClass(Class classObject){        
        classDAO.put(classObject);
    }
    
    /**
     * Method used to get a class record
     * @param key - the key of the class where the records belong
     * @return ClassRecord - the records of a specific class
     */
    public ClassRecord getStudentRecordByClass(Key key){       
        return classRecordDAO.get(key);
    }
    
    /**
     * Method used to update a class record
     * @param record - the record to be updated
     */
    public void updateClassRecord(ClassRecord record){
        classRecordDAO.put(record);
    }    
    
    /**
     * Method used to check if a student has a duplicate in a class
     * @param classKey - the key of the class where the student belongs
     * @param studentKey - the key of the student
     * @return boolean - true, if it has duplicate, otherwise, false
     */
    public boolean hasDuplicate (Key classKey, Key studentKey) {
        return classRecordDAO.hasDuplicate(classKey, studentKey);
    }
    
    /**
     * Method to get all the student keys for a class
     * @param classID - id of the class
     * @return List<Key>
     */
    public List<Key> getStudentKeysForClass(Key classID) {
        return classRecordDAO.getStudentKeysForClass(classID);
    }
    
    /**
     * Method to get all the students in a class
     * @param classID - id of the class
     * @return List<Student>
     */
    public List<Student> getStudentPerClass (Key classID){
        return classRecordDAO.getStudentsPerClass(classID);
    }
    
    /**
     * Method used to get the class records of a class
     * @param classID - id of the class
     * @return List<ClassRecord>
     */
    public List<ClassRecord> getRecordsForClass (Key classID){
        return classRecordDAO.getRecordsForClass(classID);
    }
    
    /**
     * Method used to get a single record in the class record
     * @param recordKey - key of the record to get
     * @return ClassRecord
     */
    public ClassRecord getRecord(Key recordKey) {
        return classRecordDAO.get(recordKey);
    }
    
    /**
     * Method to get class record by student
     * @param studentKey - key of the student to get
     * @return ClassRecord
     */
    public ClassRecord getClassRecordByStudent (Key studentKey) {
        return classRecordDAO.getClassRecordByStudent(studentKey);
    }
    
}
