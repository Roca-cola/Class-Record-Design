package classrecord.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import classrecord.dao.CourseDao;
import classrecord.dto.CourseDTO;
import classrecord.meta.CourseMeta;
import classrecord.model.Course;

/**
 * Service used to handle course transactions
 * @author Sheila Mae Batistil
 * @version 0.01
 * Version History
 * 0.01 - Sheila Mae Batistil - initial codes
 */

public class CourseService {
    
    /**
     * Method used to create a course
     * @param dto - the course dto
     */
    public void create(CourseDTO dto) {
        CourseDao DAO = new CourseDao();
        int match = DAO.query().filter(CourseMeta.get().name.equal(dto.getName())).count();
        
        if (match != 0) {
            throw new InvalidParameterException("Class already exist.");
        }
        
        Course model = new Course();
        model.setName(dto.getName());
        DAO.put(model);
    }
    
    /**
     * Method used to delete a course
     * @param dto - the course dto
     */ 
    public void delete(CourseDTO dto) {
        Key key = KeyFactory.stringToKey(dto.getKey());
        
        CourseDao DAO = new CourseDao();
        if (!DAO.exists(key)) {
            throw new InvalidParameterException("No such course");
        }
        
        DAO.delete(key);
    }
    
    /**
     * Method used to retrieve all courses
     * @return List<CourseDTO> - list of courses
     */
    public List<CourseDTO> list() {
        CourseDao DAO = new CourseDao();
        List<Course> models = DAO.query().asList();
        List<CourseDTO> dtos = new ArrayList<CourseDTO>(models.size());        
        CourseDTO dto = null;
        for (Course course : models) {
            dto = new CourseDTO();
            dto.setKey(KeyFactory.keyToString(course.getKey()));
            dto.setName(course.getName());
            dtos.add(dto);
        }
        return dtos;
    }
}
