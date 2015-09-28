package classrecord.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import classrecord.dao.ClassDao;
import classrecord.dto.ClassDTO;
import classrecord.meta.ClassMeta;
import classrecord.model.Class;

/**
 * Service used to handle class transactions
 * @author
 * @version
 */
public class ClassService {
    
    /**
     * Method used to create a class
     * @param dto - class DTO
     * @return ClassDTO
     */
    public ClassDTO createClass(ClassDTO dto) {
        ClassDao DAO = new ClassDao();
        
        int matches = DAO.query().filter(ClassMeta.get().name.equal(dto.getName())).count();
        
        if (matches != 0) {
            throw new InvalidParameterException("Class with the same name exists");
        }
        
        Class model = new Class();
        model.setName(dto.getName());
        DAO.persist(model);
        
        dto.setKey(KeyFactory.keyToString(model.getKey()));
        return dto;
    }
    
    /**
     * Method used to delete a class
     * @param dto - class DTO
     */
    public void deleteClass(ClassDTO dto) {
        Key key = KeyFactory.stringToKey(dto.getKey());
        
        ClassDao DAO = new ClassDao();
        
        if (!DAO.exists(key)) {
            throw new InvalidParameterException("No such class.");
        }
        
        //should we delete orphaned records? or keep it as history?
        DAO.delete(key);
    }
   
    /**
     * Method used to get all classes
     * @return List<ClassDTO>
     */
    public List<ClassDTO> list() {
        ClassDao DAO = new ClassDao();
        List<Class> classModels = DAO.query().asList();
        List<ClassDTO> classDTOs = new ArrayList<ClassDTO>(classModels.size());
        
        for (Class classModel : classModels) {
            classDTOs.add(new ClassDTO(KeyFactory.keyToString(classModel.getKey()), classModel.getName()));
        }
        
        return classDTOs;
    }
    
    /**
     *Method used to update a class
     * @param classObject - class to update
     */
    public void updateClass (Class classObject) {
        ClassDao DAO = new ClassDao();
        DAO.put(classObject);
    }
}
