package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

import classrecord.service.CourseService;

/**
 * Controller used to get all the courses
 * @author
 * @version
 */

public class CourseListController extends Controller {
    
    @Override
    protected Navigation run() throws Exception {
        CourseService service = new CourseService();
        JSONObject json = new JSONObject();
        try {
            JSONArray jArray = new JSONArray(service.list());
            json.put("courses", jArray);           
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
