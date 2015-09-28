package classrecord.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

/**
 * Initial Controller
 * @author Julie Jane Alegre
 * @version 0.01
 * Version History
 * 0.01 - Julie Jane Alegre - initial codes
 */

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("index.jsp");
    }
}
