<%@page import="shorturl.APIs.ScreenShotApi"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shorturl.APIs.QRApi"%>
<%@page import="shorturl.persistence.PersistenceJPA"%>
<%@page import="java.util.List"%>
<%@page import="shorturl.entities.Url"%>
<%@page import="shorturl.entities.User"%>
<%@page import="shorturl.classes.Helper"%>
<%
    User current_user = Helper.getCurrentUser(request);
    List<Url> allUrls = Helper.getAllURL();


%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Short.me | Manage URLs</title>
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
        <link href="assets/css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

        <link href="assets/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="skin-black">
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
                        <li><a href="#" class="btn btn-link btn-sm" data-toggle="modal" data-target="#newURL"><i class="glyphicon glyphicon-plus"></i> <span>New URL</span></a></li>
                            <% if (current_user != null) {%>
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span><%= current_user.getUsername()%> <i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-light-blue">
                                    <img src="<%= current_user.getPhoto()%>" class="img-circle" alt="User Image" />
                                    <p>
                                        <%= current_user.getEmail()%>
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
                        <% }%>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <aside class="left-side sidebar-offcanvas">
                <section class="sidebar">
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="<%= current_user.getPhoto()%>" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Hello, <%= current_user.getUsername()%></p>

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
                        <% if (current_user.getRole().getValue().equals(1)) {%>
                        <li>
                            <a href="users.jsp">
                                <i class="glyphicon glyphicon-user"></i> <span>Users</span> 
                            </a>
                        </li>
                        <li class="active">
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

                    <div class="row">
                        <div class="col-md-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">All Urls</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                    <table class="table table-bordered" id="table-sortable">
                                        <thead>
                                            <tr>
                                                <th style="width: 30px">ID</th>
                                                <th>Created AT</th>
                                                <th>Full URL</th>
                                                <th>Short url</th>
                                                <th>User ID</th>
                                                <th>ScreenShot</th>
                                                <th>Action</th>
                                            </tr>                                            
                                        </thead>
                                        <tbody>
                                            <% for (Url url : allUrls) {%>
                                            <tr>
                                                <td><a href="http://localhost:8080/shorturl/showUrl.jsp?id=<%= url.getId()%>"><%= url.getId()%></a></td>
                                                <td><%= url.getCreatedAt()%></td>
                                                <td><a href="<%= url.getFullUrl()%>"><%= url.getFullUrl()%></a></td>
                                                <td><a href="http://localhost:8080/shorturl/?l=<%= url.getShortUrl()%>"><%= url.getShortUrl()%></a></td>
                                                <td><% if (url.getUser() != null) {%><%= url.getUser().getUsername()%><% }%></td>
                                                <td><img src="<%= (new ScreenShotApi(url.getFullUrl())).getScreenShot()%>" width="50" height="50"/></td>
                                                <td><a href="#" class="btn btn-sm btn-danger deleteUrl" data-url="<%= url.getId()%>">Delete</a></td>
                                            </tr>
                                            <% }%>
                                        </tbody></table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>

                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        
        <%@ include file="template/modals/newurl.jsp"%>
        
        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <!-- jQuery UI 1.10.3 -->
        <script src="assets/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

        <script src="assets/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="assets/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>

        <script src="assets/js/shorturl.js" type="text/javascript"></script>

    </body>
</html>