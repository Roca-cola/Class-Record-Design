package classrecord.controller;

import java.io.IOException;
import java.security.InvalidParameterException;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;
import org.slim3.repackaged.org.json.JSONException;
import org.slim3.repackaged.org.json.JSONObject;

import classrecord.constants.Constants;
import classrecord.dto.ClassDTO;
import classrecord.service.ClassService;

/**
 * Controller that handles the classes
 * @author Sheila Mae Batistil
 * @version 0.01
 * Version History 
 * 0.01 - Sheila Mae Batistil - initial codes
 */
public class ClassController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String action = param("action");
        JSONObject parameter = new JSONObject(request.getReader().readLine());
        
        if (action.equals(Constants.SYS_CLASS_ACTION_LIST)) {
            list(parameter);
        } else if (action.equals(Constants.SYS_CLASS_ACTION_CREATE)) {
            create(parameter);
        } else if (action.equals(Constants.SYS_CLASS_ACTION_DELETE)) {
            delete(parameter);
        } else {
            throw new InvalidParameterException("No such operation.");
        }
        
        return null;
    }

    private void list(JSONObject parameter) throws IOException {
        ClassService service = new ClassService();
        JSONArray jArray = new JSONArray(service.list());
        response.getWriter().write(jArray.toString());
    }

    private void create(JSONObject parameter) throws JSONException {
        ClassDTO dto = new ClassDTO();
        dto.setName(parameter.getString("name"));
        ClassService service = new ClassService();
        service.createClass(dto);
    }

    private void delete(JSONObject parameter) throws JSONException {
        ClassDTO dto = new ClassDTO();
        dto.setKey(parameter.getString("key"));
        ClassService service = new ClassService();
        service.deleteClass(dto);
    }

}
