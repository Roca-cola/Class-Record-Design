package classrecord.controller;

import java.io.IOException;
import java.text.ParseException;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;
import org.slim3.repackaged.org.json.JSONException;
import org.slim3.repackaged.org.json.JSONObject;

import classrecord.constants.Constants;
import classrecord.dto.StudentDTO;
import classrecord.service.StudentService;

/**
 * Controller used to access student transactions
 * @author J-Dar Siegfred Rodriguez
 * @version 0.01
 * Version History
 * 0.01 - J-Dar Siegfred Rodriguez - initial codes
 */

public class StudentController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String action = request.getParameter("action");
        JSONObject parameter = new JSONObject(request.getReader().readLine());
        
        if (action.equals(Constants.SYS_STUDENT_ACTION_FIND)) {
            this.find(parameter);
        } else if (action.equals(Constants.SYS_STUDENT_ACTION_REGISTER)) {
            this.register(parameter);
        } else if (action.equals(Constants.SYS_STUDENT_ACTION_UNREGISTER)) {
            this.unregister(parameter);
        } else if (action.equals(Constants.SYS_STUDENT_ACTION_UPDATE)) {
            this.update(parameter);
        } else if (action.equals(Constants.SYS_STUDENT_ACTION_GET)) {
            this.get(parameter);
        } else { //somebody tried to breach.
            response.sendError(0, "Action \"" + action + "\" dose not exist.");
        }
        return null;
    }

    private void update(JSONObject parameter) throws JSONException, ParseException {
        StudentDTO dto = new StudentDTO();
        dto.setKey(parameter.getString("key"));
        dto.setFirstName(parameter.getString("firstName"));
        dto.setLastName(parameter.getString("lastName"));
        dto.setCourse(parameter.getString("course"));
        dto.setBirthDate(parameter.getString("birthDate"));
        StudentService.get().updateStudent(dto);
    }

    private void get(JSONObject parameter) {
        // TODO Tentative?
        
    }

    private void unregister(JSONObject parameter) throws JSONException {
        StudentDTO dto = new StudentDTO();
        dto.setKey(parameter.getString("key"));
        StudentService.get().unregisterStudent(dto);
    }

    private void register(JSONObject parameter) throws JSONException, ParseException {
        StudentService service = StudentService.get();
        StudentDTO dto = new StudentDTO();
        dto.setFirstName(parameter.getString("firstName"));
        dto.setLastName(parameter.getString("lastName"));
        dto.setCourse(parameter.getString("course"));
        
        dto.setBirthDate(parameter.getString("birthDate"));
        service.registerStudent(dto);
    }

    private void find(JSONObject parameter) throws JSONException, IOException {
        StudentService service = StudentService.get();
        StudentDTO dto = new StudentDTO();
        dto.setFirstName(parameter.getString("firstName"));
        dto.setLastName(parameter.getString("lastName"));
        StudentDTO[] results = service.findStudentByName(dto);
        JSONArray resp = new JSONArray(results);
        response.getWriter().write(resp.toString());
    }
}
