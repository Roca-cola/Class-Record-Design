package classrecord.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;

import classrecord.dto.StudentDTO;
import classrecord.service.StudentService;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * Controller used to get student details
 * @author Sheila Mae Batistil
 * @version 0.01
 * Version History
 * 0.01 - Sheila Mae Batistil - initial codes
 */

public class GetStudentController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        StudentService service = new StudentService();
        JSONObject json = new JSONObject();
        StudentDTO student = new StudentDTO();
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        try {
            List<StudentDTO> list = new ArrayList<StudentDTO>();            
            student.setKey(parameter.getString("key"));         
            list.add(service.getStudent(student));
            JSONArray jArray = new JSONArray(list);
            json.put("student", jArray.toString());
            json.put("error", "");
            System.out.println("Retrieved Student >>>> " + json.toString());
        } catch (Exception e) {
            e.printStackTrace();
            json.put("error", e.getMessage());
        }        
        response.setContentType("application/json");
        response.getWriter().write(json.toString());   
        return null;
    }

}
