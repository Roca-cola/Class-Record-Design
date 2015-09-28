package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;

import classrecord.dto.ClassDTO;
import classrecord.service.ClassService;

/**
 * Controller used to add a class
 * @author Bryan Agustine Cabansay
 * @version 0.02
 * Version History 
 * 0.01 - Bryan Agustine Cabansay - initial codes
 * 0.02 - Bryan Agustine Cabansay - final edit
 */
public class AddClassController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        JSONObject parameter = new JSONObject((String)this.request.getReader().readLine());
        JSONObject json = new JSONObject();
        ClassDTO dto = new ClassDTO();
        dto.setName(parameter.getString("name"));
        ClassService service = new ClassService();
        try {
            service.createClass(dto);
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
