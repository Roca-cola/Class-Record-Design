package classrecord.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;

import classrecord.dto.RecordsDTO;
import classrecord.dto.StudentDTO;
import classrecord.model.ClassRecord;
import classrecord.service.ClassRecordService;
import classrecord.service.RecordService;
import classrecord.service.StudentService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * Controller used to get student record
 * @author
 * @version
 */
public class GetStudentRecordController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        ClassRecordService service = new ClassRecordService();
        JSONObject json = new JSONObject();
        RecordsDTO student = new RecordsDTO();
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        
        try {
            Key recordKey = KeyFactory.stringToKey(parameter.getString("key"));
            ClassRecord record = service.getRecord(recordKey);
            RecordsDTO dto = new RecordsDTO();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(record.getStudentReference().getModel().getFirstName());
            studentDTO.setLastName(record.getStudentReference().getModel().getLastName());
            studentDTO.setKey(KeyFactory.keyToString(record.getStudentReference().getModel().getKey()));
            dto.setStudent(studentDTO);
            dto.setAssignmentOne(record.getAssignmentOne());
            dto.setAssignmentTwo(record.getAssignmentTwo());
            dto.setKey(KeyFactory.keyToString(record.getKey()));
            dto.setExamOne(record.getExamOne());
            dto.setExamTwo(record.getExamTwo());
            dto.setQuizOne(record.getQuizOne());
            dto.setQuizTwo(record.getQuizTwo());
            dto.setQuizThree(record.getQuizThree());
            dto.calculateGrade();
            List<RecordsDTO> list = new ArrayList<RecordsDTO>();
            list.add(dto);
            JSONArray jArray = new JSONArray(list);
            json.put("record", jArray);           
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
