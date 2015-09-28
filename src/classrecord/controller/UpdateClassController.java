package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import classrecord.model.Class; 

import classrecord.service.ClassService;

/**
 * Controller used to update a class
 * @author Miqueas Cagot
 * @version 0.01
 * Version History
 * 0.01 - Miqueas Cagot - initial codes
 */

public class UpdateClassController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        Key key = KeyFactory.stringToKey(parameter.getString("key"));
        String name = parameter.getString("name");
        ClassService service = new ClassService();
        JSONObject json = new JSONObject();
        try {
            Class object = new Class();
            object.setKey(key);
            object.setName(name);
            service.updateClass(object);
            json.put("error", "");
        } catch (Exception e) {
            json.put("error", e.getMessage());
        }
        response.setContentType("application/json");
        response.getWriter().write(json.toString());
        return null;
    }

}
