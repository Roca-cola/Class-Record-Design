package classrecord.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import classrecord.dto.StudentDTO;
import classrecord.service.StudentService;
import classrecord.utility.Validator;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * Controller used to add a student
 * @author 
 * @version
 */

public class AddStudentController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        if (isPost()) {
            StudentService service = StudentService.get();
            JSONObject json = new JSONObject();
            StudentDTO dto = new StudentDTO();
            JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());            
            dto.setFirstName(parameter.getString("firstName"));
            dto.setLastName(parameter.getString("lastName"));
            dto.setCourse(parameter.getString("course"));            
            dto.setBirthDate(parameter.getString("birthDate"));
            
            try {
                DateFormat format = new SimpleDateFormat ("yyyy/MM/dd", Locale.ENGLISH);
                Date date = format.parse(dto.getBirthDate());
                if (Validator.isAgeValid(date)){
                    service.registerStudent(dto);
                    json.put("error", "");
                } else {
                    json.put("error", "Age is not valid. Age should be above 18 years.");
                }                
            } catch (Exception e) {
                e.printStackTrace();
                json.put("error", e.getMessage());
            }                                                 
            response.setContentType("application/json");
            response.getWriter().write(json.toString());  
        }  
        return null;
    }

}
