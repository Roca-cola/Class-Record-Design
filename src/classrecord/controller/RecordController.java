package classrecord.controller;

import java.io.IOException;
import java.security.InvalidParameterException;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;
import org.slim3.repackaged.org.json.JSONException;
import org.slim3.repackaged.org.json.JSONObject;

import classrecord.constants.Constants;
import classrecord.dto.RecordsDTO;
import classrecord.dto.StudentDTO;
import classrecord.service.RecordService;

public class RecordController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String action = param("action");
        JSONObject parameter = new JSONObject(request.getReader().readLine());
        
        if (action.equals(Constants.SYS_RECORDS_ACTION_GET)) {
            get(parameter);
        } else if (action.equals(Constants.SYS_RECORDS_ACTION_UPDATE)) {
            update(parameter);
        } else if (action.equals(Constants.SYS_RECORDS_ACTION_CREATE)) {
            create(parameter);
        } else if (action.equals(Constants.SYS_RECORDS_ACTION_DELETE)) {
            delete(parameter);
        } else {
            throw new InvalidParameterException("Invalid request.");
        }        
        return null;
    }

    private void get(JSONObject parameter) throws JSONException, IOException {
        RecordsDTO dto = new RecordsDTO();
        dto.setClassKey(parameter.getString("classKey"));
        RecordService service = new RecordService();
        JSONArray jArray = new JSONArray(service.get(dto));
        response.getWriter().write(jArray.toString());
    }

    private void update(JSONObject parameter) throws JSONException {
        RecordsDTO dto = new RecordsDTO();
        dto.setKey(parameter.getString("key"));
        
        dto.setQuizOne(parameter.getDouble("quizOne"));
        dto.setQuizTwo(parameter.getDouble("quizTwo"));
        dto.setQuizThree(parameter.getDouble("quizThree"));
        
        dto.setAssignmentOne(parameter.getDouble("assignmentOne"));
        dto.setAssignmentTwo(parameter.getDouble("assignmentTwo"));
        
        dto.setExamOne(parameter.getDouble("examOne"));
        dto.setExamTwo(parameter.getDouble("examTwo"));
        
        RecordService service = new RecordService();
        service.update(dto);
    }

    private void create(JSONObject parameter) throws JSONException {
        RecordsDTO dto = new RecordsDTO();
        dto.setClassKey(parameter.getString("classKey"));
        StudentDTO sDTO = new StudentDTO();
        sDTO.setKey(parameter.getJSONObject("student").getString("key"));
        dto.setStudent(sDTO);
        RecordService service = new RecordService();
        service.create(dto);
    }

    private void delete(JSONObject parameter) throws JSONException {
        RecordsDTO dto = new RecordsDTO();
        dto.setKey(parameter.getString("key"));
        RecordService service = new RecordService();
        service.delete(dto);
    }

}
