package classrecord.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.ModelQuery;

import classrecord.model.Class;

/**
 * Dao used to access datastore for class transactions
 * @author 
 * @version
 */
public class ClassDao extends DaoBase<Class>{
    public Class persist(Class model) {
        model.setKey(super.put(model));
        return model;
    }
    
    public ModelQuery<Class> query() {
        return super.query();
    }
}
