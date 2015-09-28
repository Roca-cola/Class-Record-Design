package classrecord.controller;

import org.slim3.controller.router.RouterImpl;

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
