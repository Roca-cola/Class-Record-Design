package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

/**
 * Initial Controller
 * @author 
 * @version
 */

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("index.jsp");
    }
}
