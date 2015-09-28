package classrecord.controller;

import java.util.ArrayList;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;
import org.slim3.repackaged.org.json.JSONObject;

import classrecord.dto.RecordsDTO;
import classrecord.dto.StudentDTO;
import classrecord.service.ClassRecordService;
import classrecord.service.RecordService;
import classrecord.service.StudentService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;



/**
 * Controller used to assign a student to an array
 * @author Albert Dale Palacio
 * @version 0.01
 * Version History
 * 0.01 - Albert Dale Palacio - initial codes
 */

public class StudentassignController extends Controller {

    @Override
    public Navigation run() throws Exception {        
        if (isPost()) {
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> addedNames = new ArrayList<String>();
            JSONObject json = new JSONObject();
            JSONArray array = new JSONArray(request.getAttribute("students").toString());
            Key classKey = KeyFactory.stringToKey(request.getAttribute("class").toString());  
            for (int i = 0; i < array.length(); ++i) {
                Key studentKey = KeyFactory.stringToKey(array.getString(i));
                if (ClassRecordService.get().hasDuplicate(classKey, studentKey)){
                    StudentDTO sDTO = new StudentDTO();
                    sDTO.setKey(array.get(i).toString());
                    String fullName = StudentService.get().getStudent(sDTO).getFirstName() + " " +
                            StudentService.get().getStudent(sDTO).getLastName();
                    names.add(fullName); 
                } else {
                    RecordsDTO dto = new RecordsDTO();
                    dto.setClassKey(KeyFactory.keyToString(classKey));
                    StudentDTO sDTO = new StudentDTO();
                    sDTO.setKey(KeyFactory.keyToString(studentKey));
                    dto.setStudent(sDTO);
                    RecordService service = new RecordService();
                    service.create(dto);                     
                    String fullName = StudentService.get().getStudent(sDTO).getFirstName() + " " +
                            StudentService.get().getStudent(sDTO).getLastName();                    
                    addedNames.add(fullName); 
                }
            }      
            json.put("alreadyAdded", names);
            json.put("newlyAdded", addedNames);
            response.getWriter().write(json.toString());
        }        
        return null;
    }
}
