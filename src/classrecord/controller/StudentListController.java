package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;

import classrecord.service.StudentService;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * Controller used to retrieve all students
 * @author
 * @version
 */
public class StudentListController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        StudentService service = new StudentService();
        JSONObject json = new JSONObject();
        try {
            JSONArray jArray = new JSONArray(service.getAllStudents());
            json.put("students", jArray.toString());           
            json.put("error", "");
            System.out.println(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
            json.put("error", e.getMessage());
        }        
        response.setContentType("application/json");
        response.getWriter().write(json.toString());           
        return null;
    }

}
