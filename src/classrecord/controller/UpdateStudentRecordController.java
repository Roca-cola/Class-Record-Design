package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import classrecord.dto.RecordsDTO;
import classrecord.service.RecordService;

/**
 * Controller used to update a student record
 * @author
 * @version
 */
public class UpdateStudentRecordController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        JSONObject json = new JSONObject();
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        Key key = KeyFactory.stringToKey(parameter.getString("key"));
        RecordsDTO dto = new RecordsDTO();
        dto.setKey(parameter.getString("key"));        
        try {
            dto.setQuizOne(parameter.getDouble("quizOne"));
            dto.setQuizTwo(parameter.getDouble("quizTwo"));
            dto.setQuizThree(parameter.getDouble("quizThree"));        
            dto.setAssignmentOne(parameter.getDouble("assignmentOne"));
            dto.setAssignmentTwo(parameter.getDouble("assignmentTwo"));        
            dto.setExamOne(parameter.getDouble("examOne"));
            dto.setExamTwo(parameter.getDouble("examTwo"));
            RecordService service = new RecordService();
            service.update(dto);
            json.put("error", "");
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("error", e.getMessage());
        }
        response.setContentType("application/json");
        response.getWriter().write(json.toString());   
        return null;
    }

}
