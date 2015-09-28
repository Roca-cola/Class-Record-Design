<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<jsp:include page="includes/styles.html" />
		<jsp:include page="includes/common.jsp" />
	</head>
	<body ng-app="ClassRecordApp">       
		
		<!-- Page Container -->
        <div id="page-container" class="sidebar-l sidebar-o side-scroll header-navbar-fixed">            
            
            <!-- Header and Sidebar -->
			<jsp:include page="includes/sidebar.jsp" /> 	
			<jsp:include page="includes/header.jsp" />
			<!-- End Header and Sidebar -->  			    

            <!-- Main Container -->
            <main id="main-container">
                <!-- Page Header -->
                <div class="content bg-image overflow-hidden">
                </div>
                <!-- END Page Header -->

                <!-- Main Content -->
                <div class="content bg-white border-b">
                	<div  ng-view></div>                    
                </div>
                <!-- End Main Content -->
            </main>
            <!-- END Main Container -->
                        
            <!-- Footer -->
            <jsp:include page="includes/footer.jsp" />
            <!-- END Footer -->
                        
        </div>
        <!-- END Page Container -->
        
        <!-- Scripts -->
        	<jsp:include page="includes/scripts.html" />
        <!-- End Scripts -->
                       
    </body>
</html>