package classrecord.dao;

import java.util.ArrayList;
import java.util.List;

import org.slim3.datastore.DaoBase;

import com.google.appengine.api.datastore.Key;

import classrecord.meta.ClassRecordMeta;
import classrecord.model.ClassRecord;
import classrecord.model.Student;

/**
 * Dao used to handle ClassRecord transactions
 * @author Julie Jane Alegre 
 * @version 1.0
 */

public class ClassRecordDao extends DaoBase<ClassRecord>{
    
    /**
     * Method used to get all the records for a specific class
     * @param classID - the class to get
     * @return List<ClassRecord> - List of records of a class
     */

    public List<ClassRecord> getRecordsForClass(Key classID) {
        List<ClassRecord> list =  super.query().filter(ClassRecordMeta.get().classReference.equal(classID)).asList();
        return list;
    }
    
    
    /**
     * Method used to get a specific record in the class record
     * @param recordKey - key of the record to get
     * @return ClassRecord
     */
    public ClassRecord getRecord(Key recordKey) {
        ClassRecordMeta meta = ClassRecordMeta.get();
        return super.query().filter(meta.key.equal(recordKey)).asSingle();
    }
    
    /**
     * Method used to get all student keys in a class
     * @param classID - id of the class
     * @return List<Key>
     */
    public List<Key> getStudentKeysForClass(Key classID) {
        List<ClassRecord> records = super.query().filter(ClassRecordMeta.get().classReference.equal(classID)).asList();
        List<Key> keys = new ArrayList<Key>();
        for (ClassRecord record : records) {
            keys.add(record.getStudentReference().getKey());
        }
        return keys;
    }
    
    /**
     * Method used to get the class record of a student
     * @param studentKey - key of the student to get
     * @return ClassRecord
     */
    public ClassRecord getClassRecordByStudent (Key studentKey) {
        ClassRecordMeta meta = ClassRecordMeta.get();
        return super.query().filter(meta.studentReference.equal(studentKey)).asSingle();
    }
    
    /**
     * Method used to get the list of student in a class
     * @param classID - id of the class to get
     * @return List<Student>
     */
    public List<Student> getStudentsPerClass (Key classID) {
        List<ClassRecord> records = super.query().filter(ClassRecordMeta.get().classReference.equal(classID)).asList();
        List<Student> students = new ArrayList<Student>();
        for (ClassRecord record : records) {
            students.add(record.getStudentReference().getModel());
        }
        return students;
    }
    
    
    /**
     * Method used to update a record
     * @param record - the record you want to update
     * @return boolean true
     */
    
    public boolean updateRecord(ClassRecord record) {
        super.put(record);
        return true;
    }
    
    /**
     * Method used to check if the a student in a class has a duplicate
     * @param classID - the class where you want to add a new record 
     * @param studentID - the id of the student to be added to the class
     * @return boolean - true, if it has duplicate, otherwise, false 
     */
    
    public boolean hasDuplicate (Key classID, Key studentID) {
        ClassRecordMeta meta = ClassRecordMeta.get();
        Object obj = super.query().filter(meta.and(meta.classReference.equal(classID), 
                meta.studentReference.equal(studentID))).asSingle();
        return obj != null;        
    }
}
