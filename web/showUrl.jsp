
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="shorturl.APIs.ScreenShotApi"%>
<%@page import="shorturl.APIs.QRApi"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shorturl.entities.UrlVisits"%>
<%@page import="shorturl.persistence.PersistenceJPA"%>
<%@page import="java.util.List"%>
<%@page import="shorturl.entities.Url"%>
<%@page import="shorturl.entities.Url"%>
<%@page import="shorturl.entities.User"%>
<%@page import="shorturl.classes.Helper"%>
<%@page import="shorturl.classes.Parameters"%>
<%
    User current_user = Helper.getCurrentUser(request);
    int urlID = Integer.parseInt(request.getParameter("id"));
    
    List<Url> urls = PersistenceJPA.getSingletonInstance().getListaUrl();
    Url url = null;
    for(Url _url : urls){
        if(_url.getId().equals(urlID)){
            url = _url;
            break;
        }
    }
    List<UrlVisits> urlVisits = PersistenceJPA.getSingletonInstance().getListaUrlVisits();
    List<UrlVisits> myUrlVisits = new ArrayList<UrlVisits>();
    for(UrlVisits urlV : urlVisits){
        if(urlV.getUrl().getId().equals(urlID)){
            myUrlVisits.add(urlV);
        }
    }
    int[] browserData = Helper.getBrowserChartData(myUrlVisits);
    int[] osData = Helper.getOsChartData(myUrlVisits);
    List<String> dateVisits = Helper.getVisitsDateTimeDetail(myUrlVisits);
    Set set = Helper.getVisitsDateTimeChartData(dateVisits).entrySet();
    Iterator iterator = set.iterator();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Short.me | <%= url.getShortUrl() %></title>
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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- jQuery UI 1.10.3 -->
        <script src="assets/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>  
        <script src="http://cdn.oesmith.co.uk/morris-0.4.1.min.js"></script>

        
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
                        <li><a href="#" class="btn btn-link btn-sm" data-toggle="modal" data-target="#newURL"><i class="glyphicon glyphicon-plus"></i> <span>New URL</span></a></li>
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
                        <li>
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
                        <a href="<%= Parameters.root %>?l=<%= url.getShortUrl() %>">short.me/<%= url.getShortUrl() %></a>
                        <small><%= url.getFullUrl() %></small>
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
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-green">
                                <div class="inner">
                                    <h3>
                                        <% if(myUrlVisits.size()>0) {%>
                                        <%= myUrlVisits.size() %>
                                        <%} else{ %>
                                        0
                                        <% }%>
                                    </h3>
                                    <p>
                                        Visits
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-stats-bars"></i>
                                </div>
                                <a href="#" class="small-box-footer">
                                    More info <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                                       <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-orange">
                                <div class="inner">
                                    <h3>
                                        <%
                                        int cant = 0;
                                        for(int i = 0; i < browserData.length; i++){
                                            if(browserData[i]>0){
                                                cant++;
                                            }
                                        }
                                        %>
                                        <%= cant %>
                                    </h3>
                                    <p>
                                        Browsers
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-android-earth"></i>
                                </div>
                                <a href="#" class="small-box-footer">
                                    More info <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                                       <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-red">
                                <div class="inner">
                                    <h3>
                                        <%
                                        cant = 0;
                                        for(int i = 0; i < osData.length; i++){
                                            if(osData[i]>0){
                                                cant++;
                                            }
                                        }
                                        %>
                                        <%= cant %>
                                    </h3>
                                    <p>
                                        Operative Systems
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-android-system-windows"></i>
                                </div>
                                <a href="#" class="small-box-footer">
                                    More info <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                    </div><!-- /.row -->

                    <!-- top row -->
                    <div class="row">
                        <div class="col-xs-12 connectedSortable">
                            
                        </div><!-- /.col -->
                    </div>
                    <!-- /.row -->
                    
                    <div class="row">
                        <div class="col-md-6">
                            <!-- Bar chart -->
                            <div class="box box-primary">
                                <div class="box-header">
                                    <i class="fa fa-bar-chart-o"></i>
                                    <h3 class="box-title">Browsers</h3>
                                </div>
                                <div class="box-body">
                                    <div id="browser-chart"></div>
                                </div><!-- /.box-body-->
                            </div><!-- /.box -->
                        </div>
                        <div class="col-md-6">
                            <!-- Bar chart -->
                            <div class="box box-primary">
                                <div class="box-header">
                                    <i class="fa fa-bar-chart-o"></i>
                                    <h3 class="box-title">Operative Systems</h3>
                                </div>
                                <div class="box-body">
                                    <div id="os-chart"></div>
                                </div><!-- /.box-body-->
                            </div><!-- /.box -->
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <!-- Bar chart -->
                            <div class="box box-primary">
                                <div class="box-header">
                                    <i class="fa fa-bar-chart-o"></i>
                                    <h3 class="box-title">Date</h3>
                                </div>
                                <div class="box-body">
                                    <div id="date-chart"></div>
                                </div><!-- /.box-body-->
                            </div><!-- /.box -->
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <h3>QR Code</h3>
                            <img src="<%= (new QRApi("http://localhost:8080/shorturl/?l="+url.getShortUrl(),"350x350","UTF-8")).getQR() %>" class="thumbnail"/>
                        </div>
                        <div class="col-md-6">
                            <h3>ScreenShot</h3>
                            <img src="<%= (new ScreenShotApi(url.getFullUrl())).getScreenShot() %>" class="thumbnail"/>
                        
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">All Visits</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                    <table class="table table-bordered">
                                        <tbody><tr>
                                            <th style="width: 10px">ID</th>
                                            <th>Browser</th>
                                            <th>ClientDomain</th>
                                            <th>IP</th>
                                            <th>OS</th>
                                            <th>Date</th>
                                        </tr>
                                        <% for(UrlVisits urlVis : myUrlVisits) { %>
                                        <tr>
                                            <td><%= urlVis.getId() %></td>
                                            <td><%= urlVis.getBrowser() %></td>
                                            <td><%= urlVis.getClientDomain() %></td>
                                            <td><%= urlVis.getIp()%></td>
                                            <td><%= urlVis.getOperativeSystem()%></td>
                                            <td><%= urlVis.getCreatedAt()%></td>
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
                                                <input class="form-control" placeholder="url_full" name="url_full" id="url_full" type="url" autofocus required pattern="https?://.+" >
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
      <script type="text/javascript">
$(function() {
Morris.Bar({
  element: 'browser-chart',
  data: [
      <% String[] browsersName = {"InternetExplorer", "Firefox", "Chrome", "Safari", "Opera", "NetScape", "Unknown"}; %>
      <% for(int i = 0; i < browserData.length; i++){ 
      %>
          { y : '<%= browsersName[i] %>', a : <%= browserData[i] %> },
      <% } %>
  ],
  xkey: 'y',
  ykeys: ['a'],
  labels: ['Visits']
});

Morris.Donut({
  element: 'os-chart',
  data: [
      <% String[] osName = {"Unknown", "Windows", "Mac", "Android", "iPhone", "Unix"}; %>
      <% for(int i = 0; i < osData.length; i++){ 
          if(osData[i] > 0){
      %>
          { label : '<%= osName[i] %>', value : <%= osData[i] %> },
      <% } 
      } %>
  ]
});
Morris.Line({
  element: 'date-chart',
  data: [
      <% while(iterator.hasNext()) {
         Map.Entry dataVisit = (Map.Entry)iterator.next(); %>
        { y: '<%= dataVisit.getKey() %>', a: <%= dataVisit.getValue() %>},
    <% }%>
  ],
  xkey: 'y',
  ykeys: ['a'],
  labels: ['Date']
});    
});
            </script>

        <!-- jQuery 2.0.2 -->
        <!-- Morris.js charts -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
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
        
  
    </body>
</html>