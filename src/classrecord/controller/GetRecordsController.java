package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;

import classrecord.dto.RecordsDTO;
import classrecord.service.RecordService;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class GetRecordsController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        RecordService service = new RecordService();
        JSONObject json = new JSONObject();
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        try {
            RecordsDTO dto = new RecordsDTO();
            dto.setClassKey(parameter.getString("classKey"));
            JSONArray jArray = new JSONArray(service.get(dto));
            json.put("records", jArray.toString());           
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
