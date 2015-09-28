package classrecord.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.ModelQuery;

import classrecord.model.Course;

/**
 * Dao used to access datastore for course transactions
 * @author
 * @version
 */

public class CourseDao extends DaoBase<Course>{
    public ModelQuery<Course> query() {
        return super.query();
    }
}
