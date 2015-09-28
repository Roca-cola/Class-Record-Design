<!-- Sidebar -->
 <nav id="sidebar">
     <!-- Sidebar Scroll Container -->
     <div id="sidebar-scroll">
         <!-- Sidebar Content -->
         <!-- Adding .sidebar-mini-hide to an element will hide it when the sidebar is in mini mode -->
         <div class="sidebar-content">
             <!-- Side Header -->
             <div class="side-header side-content bg-white-op">
                 <!-- Layout API, functionality initialized in App() -> uiLayoutApi() -->
                 <button class="btn btn-link text-gray pull-right hidden-md hidden-lg" type="button" data-toggle="layout" data-action="sidebar_close">
                     <i class="fa fa-times"></i>
                 </button>                            
                 <a class="h5 text-white" href="/">
                    <span class="h4 font-w600 sidebar-mini-hide">Class Record</span>
                 </a>
             </div>
             <!-- END Side Header -->

             <!-- Side Content -->
            <div class="side-content">
                 <ul class="nav-main">
                     <li class="nav-main-heading"><span class="sidebar-mini-hide">Student</span></li>
                     <li id="student-nav">
                         <a class="nav-submenu" data-toggle="nav-submenu"><i class="fa fa-user-plus"></i><span class="sidebar-mini-hide">Student Management</span></a>
                         <ul>
                             <li>
                                 <a id="add-student-page" href="#/add_student">Add Student</a>
                             </li>                             
                             <li>
                                 <a id="edit-student-page" href="#/manage_students">View Students</a>
                             </li>                                        
                         </ul>
                     </li>
                     <li class="nav-main-heading"><span class="sidebar-mini-hide">Class Record</span></li>
                     <li id="record-nav">
                         <a class="nav-submenu" data-toggle="nav-submenu" ><i class="fa fa-table"></i><span class="sidebar-mini-hide">Records Management</span></a>
                         <ul>   
                         	<!-- 
                           	<li>
                               <a id="assign-student-link" href="#/student_assignments">Assign Students</a>
                            </li>
                             -->   
                             <li>
                                 <a id="class-record-page" href="#/class_record_view">View Class Record</a>
                             </li>
                         </ul>
                     </li>                                                                
                 </ul>
             </div>
             <!-- END Side Content -->
         </div>
         <!-- Sidebar Content -->
     </div>
     <!-- END Sidebar Scroll Container -->
 </nav>
 <!-- END Sidebar -->