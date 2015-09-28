package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;

import classrecord.dto.StudentDTO;
import classrecord.service.StudentService;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * Controller used to search a student
 * @author Bryan Agustine Cabansay
 * @version 0.01
 * Version History
 * 0.01 - Bryan Agustine Cabansay - initial codes
 */

public class SearchStudentController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        StudentService service = new StudentService();
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        JSONObject json = new JSONObject();
        try {
            StudentDTO dto = new StudentDTO();
            dto.setFirstName(parameter.getString("firstName"));
            dto.setLastName(parameter.getString("lastName"));
            JSONArray jArray = new JSONArray(service.findStudentByName(dto));
            json.put("students", jArray);           
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
