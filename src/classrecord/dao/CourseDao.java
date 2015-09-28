package classrecord.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.ModelQuery;

import classrecord.model.Course;

/**
 * Dao used to access datastore for course transactions
 * @author Albert Dale Palacio
 * @version 0.01
 * Version History
 * 0.01 - Albert Dale Palacio -  initial codes
 */

public class CourseDao extends DaoBase<Course>{
    public ModelQuery<Course> query() {
        return super.query();
    }
}
