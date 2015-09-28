package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;

import classrecord.service.ClassService;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * Controller used to get all the classes
 * @author
 * @version 0.01
 * Version History 
 * 0.01 - Julie Jane Alegre - initial codes
 */
public class ClassesListController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        ClassService service = new ClassService();
        JSONObject json = new JSONObject();
        try {
            JSONArray jArray = new JSONArray(service.list());
            json.put("classes", jArray);           
            json.put("error", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("error", e.getMessage());
        }        
        response.setContentType("application/json");
        response.getWriter().write(json.toString());   
        return null;
    }

}
