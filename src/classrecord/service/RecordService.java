package classrecord.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import classrecord.dao.ClassDao;
import classrecord.dao.ClassRecordDao;
import classrecord.dao.StudentDao;
import classrecord.dto.RecordsDTO;
import classrecord.dto.StudentDTO;
import classrecord.model.Class;
import classrecord.model.ClassRecord;

/**
 * Service used to handle class record transactions
 * @author Bryan Agustine Cabansay
 * @version 0.02
 * Version History
 * 0.01 - Bryan Agustine Cabansay - initial codes
 * 0.02 - Bryan Agustine Cabansay - added additional quizzes, exams and assignments
 */

public class RecordService {
    
    /**
     * Method used to get class record of a class
     * @param dto - record DTO
     * @return List<RecordsDTO>
     */
    public List<RecordsDTO> get(RecordsDTO dto) {
        Key classKey = KeyFactory.stringToKey(dto.getClassKey());
        
        ClassDao classDao = new ClassDao();
        
        if (!classDao.exists(classKey)) {
            throw new InvalidParameterException("No such class.");
        }
        
        Class model = classDao.get(classKey);
        
        List<ClassRecord> records = model.getClassRecordListReference().getModelList();
        List<RecordsDTO> recordDTOs = new ArrayList<RecordsDTO>(records.size());
        RecordsDTO recordsDTO = null;
        StudentDTO studentDTO = null;
        
        for (ClassRecord record : records) {
            recordsDTO = new RecordsDTO();
            recordsDTO.setKey(KeyFactory.keyToString(record.getKey()));
            recordsDTO.setClassKey(KeyFactory.keyToString(record.getClassReference().getKey()));
            
            recordsDTO.setQuizOne(record.getQuizOne());
            recordsDTO.setQuizTwo(record.getQuizTwo());
            recordsDTO.setQuizThree(record.getQuizThree());
            
            recordsDTO.setAssignmentOne(record.getAssignmentOne());
            recordsDTO.setAssignmentTwo(record.getAssignmentTwo());
            
            recordsDTO.setExamOne(record.getExamOne());
            recordsDTO.setExamTwo(record.getExamTwo());
            
            
            studentDTO = new StudentDTO();
            studentDTO.setFirstName(record.getStudentReference().getModel().getFirstName());
            studentDTO.setLastName(record.getStudentReference().getModel().getLastName());
            recordsDTO.setStudent(studentDTO);
            
            recordDTOs.add(recordsDTO);
        }
        
        return recordDTOs;
    }
    
    /**
     * Method used to create a new record
     * @param dto - record DTO
     */
    public void create(RecordsDTO dto) {
        Key classKey = KeyFactory.stringToKey(dto.getClassKey());
        ClassDao classDAO = new ClassDao();
        
        if (!classDAO.exists(classKey)) {
            throw new InvalidParameterException("No such class");
        }
        
        Key studentKey = KeyFactory.stringToKey(dto.getStudent().getKey());
        StudentDao studentDAO = new StudentDao();
        
        if (!studentDAO.exists(studentKey)) {
            throw new InvalidParameterException("No such student");
        }
        
        ClassRecord record = new ClassRecord();
        record.getClassReference().setKey(classKey);
        record.getStudentReference().setKey(studentKey);
        
        Class classModel = classDAO.get(classKey);
        classModel.getClassRecordListReference().getModelList().add(record);
        ClassRecordDao recDAO = new ClassRecordDao();
        recDAO.put(record);
        classDAO.persist(classModel);
    }
    
    /**
     * Method used to delete a record
     * @param dto - record DTO
     */
    public void delete(RecordsDTO dto) {
        Key key = KeyFactory.stringToKey(dto.getKey());
        ClassRecordDao DAO = new ClassRecordDao();
        
        if (!DAO.exists(key)) {
            throw new InvalidParameterException("No such record");
        }
        
        DAO.delete(key);
    }
    
    /**
     * Method used to update a class record
     * @param dto - record DTO
     */
    public void update(RecordsDTO dto) {
        Key key = KeyFactory.stringToKey(dto.getKey());
        ClassRecordDao DAO = new ClassRecordDao();
        
        if (!DAO.exists(key)) {
            throw new InvalidParameterException("No such record");
        }
        
        ClassRecord model = DAO.get(key);
        
        model.setQuizOne(dto.getQuizOne());
        model.setQuizTwo(dto.getQuizTwo());
        model.setQuizThree(dto.getQuizThree());
        
        model.setAssignmentOne(dto.getAssignmentOne());
        model.setAssignmentTwo(dto.getAssignmentTwo());
        
        model.setExamOne(dto.getExamOne());
        model.setExamTwo(dto.getExamTwo());
        
        DAO.put(model);
    }
}
