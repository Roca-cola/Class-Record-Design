package classrecord.controller;

import java.io.IOException;
import java.security.InvalidParameterException;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;
import org.slim3.repackaged.org.json.JSONException;
import org.slim3.repackaged.org.json.JSONObject;

import classrecord.constants.Constants;
import classrecord.dto.CourseDTO;
import classrecord.service.CourseService;
/**
 * Controller that handles the courses
 * @author Albert Dale Palacio
 * @version 0.01
 * Version History 
 * 0.01 - Albert Dale Palacio - initial codes
 */
public class CourseController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String action = param("action");
        JSONObject parameter = new JSONObject(request.getReader().readLine());
        
        if (action.equals(Constants.SYS_COURSE_ACTION_LIST)) {
            list(parameter);
        } else if (action.equals(Constants.SYS_COURSE_ACTION_CREATE)) {
            create(parameter);
        } else if (action.equals(Constants.SYS_COURSE_ACTION_DELETE)) {
            delete(parameter);
        } else {
            throw new InvalidParameterException("No such operation.");
        }
        return null;
    }

    private void delete(JSONObject parameter) throws JSONException {
        CourseDTO dto = new CourseDTO();
        dto.setKey(parameter.getString("key"));
        CourseService service = new CourseService();
        service.delete(dto);
    }

    private void create(JSONObject parameter) throws JSONException {
        CourseDTO dto = new CourseDTO();
        dto.setName(parameter.getString("name"));
        CourseService service = new CourseService();
        service.create(dto);
    }

    private void list(JSONObject parameter) throws IOException {
        CourseService service = new CourseService();
        JSONArray jArray = new JSONArray(service.list());
        System.out.println(jArray.toString());
        response.getWriter().write(jArray.toString());
    }
}
