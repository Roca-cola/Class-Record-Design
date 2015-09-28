package classrecord.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;
import com.google.appengine.api.datastore.Key;

import classrecord.service.ClassRecordService;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class GetStudentKeysFromClassController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        ClassRecordService service = new ClassRecordService();
        JSONObject json = new JSONObject();
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        try {
            Key classKey = KeyFactory.stringToKey(parameter.getString("classKey"));
            List<Key> studentKeys = service.getStudentKeysForClass(classKey);
            List<String> keyList = new ArrayList<String>();
            for (Key key: studentKeys){
                keyList.add(KeyFactory.keyToString(key));
            }
            JSONArray jArray = new JSONArray(keyList);
            json.put("keys", jArray.toString());           
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
