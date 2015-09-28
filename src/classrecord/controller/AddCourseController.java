package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;

import classrecord.dto.CourseDTO;
import classrecord.service.CourseService;
/**
 * Controller used to add a course
 * @author Albert Dale Palacio
 * @version 0.01
 * Version History 
 * 0.01 - Albert Dale Palacio - initial codes
 */
public class AddCourseController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        JSONObject json = new JSONObject();
        CourseDTO dto = new CourseDTO();
        dto.setName(parameter.getString("name"));
        CourseService service = new CourseService();
        try {
            service.create(dto);
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
