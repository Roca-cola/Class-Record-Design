package classrecord.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import classrecord.dto.ClassDTO;
import classrecord.dto.RecordsDTO;
import classrecord.model.ClassRecord;
import classrecord.service.ClassRecordService;
import classrecord.service.ClassService;
import classrecord.service.RecordService;

/**
 * Controller used to delete a class
 * @author
 * @version
 */
public class DeleteClassController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        JSONObject json = new JSONObject();
        try {            
            ClassRecordService service = new ClassRecordService();
            Key classKey = KeyFactory.stringToKey(parameter.getString("key"));
            List<ClassRecord> records = service.getRecordsForClass(classKey);
            RecordService recordService = new RecordService();
            for (ClassRecord record: records){
                RecordsDTO recordDTO = new RecordsDTO();
                recordDTO.setKey(KeyFactory.keyToString(record.getKey()));
                recordService.delete(recordDTO);
            }
            ClassDTO dto = new ClassDTO();
            dto.setKey(parameter.getString("key"));
            ClassService classService = new ClassService();
            classService.deleteClass(dto);                       
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
