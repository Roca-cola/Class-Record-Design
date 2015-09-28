package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import classrecord.dto.RecordsDTO;
import classrecord.dto.StudentDTO;
import classrecord.model.ClassRecord;
import classrecord.service.ClassRecordService;
import classrecord.service.RecordService;
import classrecord.service.StudentService;

/**
 * Controller used to delete student record
 * @author Miqueas Cagot
 * @version 0.01
 * Version History 
 * 0.01 - Miqueas Cagot - initial codes
 */
public class DeleteStudentController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        JSONObject json = new JSONObject();
        StudentDTO dto = new StudentDTO();
        ClassRecordService service = new ClassRecordService();
        try {
            Key studentKey = KeyFactory.stringToKey(parameter.getString("key"));
            ClassRecord record = service.getClassRecordByStudent(studentKey);
            RecordService recordService = new RecordService();
            RecordsDTO recordDTO = new RecordsDTO();
            if (record != null){
                recordDTO.setKey(KeyFactory.keyToString(record.getKey()));
                recordService.delete(recordDTO);
            }
            dto.setKey(parameter.getString("key"));
            StudentService.get().unregisterStudent(dto);
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
