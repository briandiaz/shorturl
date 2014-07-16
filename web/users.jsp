
<%@page import="shorturl.classes.Parameters"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shorturl.persistence.PersistenceJPA"%>
<%@page import="java.util.List"%>
<%@page import="shorturl.entities.Url"%>
<%@page import="shorturl.entities.User"%>
<%@page import="shorturl.classes.Helper"%>
<%
    User current_user = Helper.getCurrentUser(request);
    List<User> users = PersistenceJPA.getSingletonInstance().getListaUsuario();
    if(!current_user.getUsername().equals("admin")){
        response.sendRedirect(Parameters.homePage);
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Short.me | Users</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="assets/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="assets/css/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="assets/css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- fullCalendar -->
        <link href="assets/css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
        <!-- Daterange picker -->
        <link href="assets/css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link href="assets/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="assets/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="index.html" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Short.Me
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        
                        <li><a href="#" class="btn btn-link btn-sm" data-toggle="modal" data-target="#newURL">New URL</a></li>
                        <% if(current_user != null) { %>
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span><%= current_user.getUsername() %> <i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-light-blue">
                                    <img src="<%= current_user.getPhoto() %>" class="img-circle" alt="User Image" />
                                    <p>
                                        <%= current_user.getEmail() %>
                                    </p>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-right">
                                        <a href="DestroySession" class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <% } %>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <aside class="left-side sidebar-offcanvas">
                <section class="sidebar">
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="<%= current_user.getPhoto() %>" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Hello, <%= current_user.getUsername() %></p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <ul class="sidebar-menu">
                        <li>
                            <a href="#" data-toggle="modal" data-target="#newURL">
                                <i class="glyphicon glyphicon-plus"></i> <span>New URL</span>
                            </a>
                        </li>
                        <li>
                            <a href="myURL.jsp">
                                <i class="glyphicon glyphicon-link"></i> <span>URLs</span> 
                            </a>
                        </li>
                        <% if(current_user.getUsername().equals("admin")){%>
                        <li class="active">
                            <a href="users.jsp">
                                <i class="glyphicon glyphicon-user"></i> <span>Users</span> 
                            </a>
                        </li>
                        <li>
                            <a href="postedurls.jsp">
                                <i class="glyphicon glyphicon-flag"></i> <span>Manage URLs</span> 
                            </a>
                        </li>
                        <%}%>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Dashboard
                        <small>My Urls</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Dashboard</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">

                    <!-- Small boxes (Stat box) -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">All Urls</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                    <table class="table table-bordered">
                                        <tbody><tr>
                                            <th style="width: 10px">ID</th>
                                            <th>UserName</th>
                                            <th>Email</th>
                                            <th>Role</th>
                                        </tr>
                                        <% for(User user : users) { %>
                                        <tr>
                                            <td><%= user.getId() %></td>
                                            <td><%= user.getUsername() %></td>
                                            <td><a href="mailto:<%= user.getEmail() %>"><%= user.getEmail() %></a></td>
                                            <td><%= user.getRole().getName() %></td>
                                        </tr>
                                        <% } %>
                                    </tbody></table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>

                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

        <!-- add new calendar event modal -->
        <div class="modal fade" id="newURL" tabindex="-1" role="dialog" aria-labelledby="newURL" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Create New URL</h4>
              </div>
              <div class="modal-body">
                  
                            <form accept-charset="UTF-8" role="form" method="POST" action="./ServletURL">
                                    <fieldset>
                                         <div class="form-group text-center">
                                             Enter your url,a  long URL is a website address such as:</br><span class="text-primary ">https://www.pucmmsti.edu.do/websise/estudiante/</span> 
                                        </div>
                                        <input class="form-control" name="servlet_action" id="servlet_action" type="hidden" value="create" autofocus >
                                        <input class="form-control" name="url_user" id="url_user" type="hidden" value="" autofocus >
                                        <div class="form-group">
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-link"></i>
                                                </div>
                                                <input class="form-control" placeholder="url_full" name="url_full" id="url_full" autofocus  type="url" autofocus required pattern="https?://.+">
                                            </div>
                                        </div>
                                        
                                        <input class="btn btn-lg btn-success btn-block" type="submit" value="Short URL">
                                    </fieldset>
                                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
              </div>
            </div>
          </div>
        </div>

        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <!-- jQuery UI 1.10.3 -->
        <script src="assets/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Morris.js charts -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="assets/js/plugins/morris/morris.min.js" type="text/javascript"></script>
        <!-- Sparkline -->
        <script src="assets/js/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
        <!-- jvectormap -->
        <script src="assets/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
        <script src="assets/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
        <!-- fullCalendar -->
        <script src="assets/js/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
        <!-- jQuery Knob Chart -->
        <script src="assets/js/plugins/jqueryKnob/jquery.knob.js" type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="assets/js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="assets/js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="assets/js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>

        <!-- AdminLTE App -->
        <script src="assets/js/AdminLTE/app.js" type="text/javascript"></script>
        
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="assets/js/AdminLTE/dashboard.js" type="text/javascript"></script>     
        
        <!-- AdminLTE for demo purposes -->
        <script src="assets/js/AdminLTE/demo.js" type="text/javascript"></script>

    </body>
</html>