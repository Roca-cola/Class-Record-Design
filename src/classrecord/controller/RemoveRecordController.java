package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;

import classrecord.dto.RecordsDTO;
import classrecord.dto.StudentDTO;
import classrecord.service.RecordService;
import classrecord.service.StudentService;

/**
 * Controller used to remove a class record
 * @author Bryan Agustine Cabansay
 * @version 0.01
 * Version History
 * 0.01 - Bryan Agustine Cabansay - initial codes
 */

public class RemoveRecordController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        JSONObject json = new JSONObject();
        RecordsDTO dto = new RecordsDTO();                
        try {
            dto.setKey(parameter.getString("key"));
            RecordService service = new RecordService();
            service.delete(dto);
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
