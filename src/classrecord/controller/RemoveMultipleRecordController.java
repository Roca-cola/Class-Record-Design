package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

import classrecord.dto.RecordsDTO;
import classrecord.service.RecordService;

/**
 * Controller used to remove multiple records
 * @author Miqueas Cagot
 * @version 0.01
 * Version History
 * 0.01 - Miqueas Cagot - initial codes
 */

public class RemoveMultipleRecordController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        JSONObject json = new JSONObject();
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        RecordService service = new RecordService();
        
        try {
           JSONArray array = new JSONArray(parameter.get("records").toString());
           for (int i = 0; i < array.length(); i++){
               RecordsDTO dto = new RecordsDTO();
               dto.setKey(array.getString(i));
               service.delete(dto);
               json.put("error", "");
           }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("error", e.getMessage());
        }        
        response.setContentType("application/json");
        response.getWriter().write(json.toString());
        return null;
    }

}
