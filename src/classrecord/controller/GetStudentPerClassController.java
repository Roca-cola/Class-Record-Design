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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * Controller used to get student records of a class
 * @author J-Dar Siegfred Rodriguez
 * @version 0.02
 * Version History
 * 0.01 - J-Dar Siegfred Rodriguez - initial codes
 * 0.02 - J-Dar Siegfred Rodriguez - added additional quizzes, exams, and assignments
 */
public class GetStudentPerClassController extends Controller {

    /* (non-Javadoc)
     * @see org.slim3.controller.Controller#run()
     */
    @Override
    protected Navigation run() throws Exception {
        ClassRecordService service = new ClassRecordService();
        JSONObject json = new JSONObject();
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        try {
            Key classKey = KeyFactory.stringToKey(parameter.getString("classKey"));
            List<ClassRecord> list = service.getRecordsForClass(classKey);
            List<RecordsDTO> listRecords = new ArrayList<RecordsDTO>();
            for (ClassRecord record: list){
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
                listRecords.add(dto);
            }
            JSONArray jArray = new JSONArray(listRecords);
            json.put("records", jArray.toString());           
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
