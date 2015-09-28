package classrecord.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

import classrecord.dto.StudentDTO;
import classrecord.service.StudentService;
import classrecord.utility.Validator;

/**
 * Controller used to update student record
 * @author Julie Jane Alegre
 * @version 0.01
 * Version History
 * 0.01 - Julie Jane Alegre - initial codes
 */

public class UpdateStudentController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        StudentDTO dto = new StudentDTO();
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        dto.setKey(parameter.getString("key"));
        dto.setFirstName(parameter.getString("firstName"));
        dto.setLastName(parameter.getString("lastName"));
        dto.setCourse(parameter.getString("course"));
        dto.setBirthDate(parameter.getString("birthDate"));
        JSONObject json = new JSONObject();
        try {
            DateFormat format = new SimpleDateFormat ("yyyy/MM/dd", Locale.ENGLISH);
            Date date = format.parse(dto.getBirthDate());
            if (Validator.isAgeValid(date)){
                StudentService.get().updateStudent(dto);
                json.put("error", "");
            } else {
                json.put("error", "Age is not valid. Age should be above 18 years of age.");
            }                
        } catch (Exception e) {
            e.printStackTrace();
            json.put("error", e.getMessage());
        }                                                 
        response.setContentType("application/json");
        response.getWriter().write(json.toString());     
        return null;
    }

}
