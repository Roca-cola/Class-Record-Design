package classrecord.controller;

import org.slim3.controller.router.RouterImpl;


/**
 * Routing Controller used to route 
 * @author Bryan Agustine Cabansay & Miqueas Cagot
 * @version 0.02
 * Version History 
 * 0.01 - Bryan Agustine Cabansay - initial codes
 * 0.02 - Miqueas Cagot - added url and actions
 */
public class AppRouter extends RouterImpl {

    public AppRouter() {
        
        //actions : register, unregister, find, get
        addRouting("/api/students/{action}", "/student?action={action}");
        
       
        //actions : register, unregister, update, get
        addRouting("/api/classes/{action}", "/class?action={action}");
        
        //
        addRouting("/api/records/{action}", "/record?action={action}");
        
        //
        addRouting("/api/courses/{action}", "/course?action={action}");
    }

}
