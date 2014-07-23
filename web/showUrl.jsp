
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

    Url url = PersistenceJPA.getSingletonInstance().getUrlByID(urlID);
            
    List<UrlVisits> myUrlVisits = PersistenceJPA.getSingletonInstance().getListaUrlVisitsByUrl(url);
    
    int[] browserData = Helper.getBrowserChartData(myUrlVisits);
    int[] osData = Helper.getOsChartData(myUrlVisits);
    List<String> dateVisits = Helper.getVisitsDateTimeDetail(myUrlVisits);
    List<String> countryCodesList = Helper.getCountryCodeDetail(myUrlVisits);
    List<String> countryList = Helper.getCountryDetail(myUrlVisits);
    
    
    
    Set setDate = Helper.convertToChartData(dateVisits).entrySet();
    Iterator iteratorDates = setDate.iterator();
    
    Set setCountryCodes = Helper.convertToChartData(countryCodesList).entrySet();
    Iterator iteratorCountryCodes = setCountryCodes.iterator();
    
    Set setCountries = Helper.convertToChartData(countryList).entrySet();
    Iterator iteratorCountries = setCountries.iterator();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Short.me | <%= url.getShortUrl()%></title>
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
                        <% if (Helper.isAdminUser(current_user)) {%>
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
                        <a href="<%= Parameters.root%>?l=<%= url.getShortUrl()%>">short.me/<%= url.getShortUrl()%></a>
                        <small><%= url.getFullUrl()%></small>
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
                                        <% if (myUrlVisits.size() > 0) {%>
                                        <%= myUrlVisits.size()%>
                                        <%} else { %>
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
                                            for (int i = 0; i < browserData.length; i++) {
                                                if (browserData[i] > 0) {
                                                    cant++;
                                                }
                                            }
                                        %>
                                        <%= cant%>
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
                                            for (int i = 0; i < osData.length; i++) {
                                                if (osData[i] > 0) {
                                                    cant++;
                                                }
                                            }
                                        %>
                                        <%= cant%>
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
                        
                        <div class="col-lg-6 connectedSortable">
                            <!-- Map box -->
                            <div class="box box-primary">
                                <div class="box-header">
                                    <!-- tools box -->
                                    <div class="pull-right box-tools">                                        
                                        <button class="btn btn-primary btn-sm daterange pull-right" data-toggle="tooltip" title="Date range"><i class="fa fa-calendar"></i></button>
                                        <button class="btn btn-primary btn-sm pull-right" data-widget='collapse' data-toggle="tooltip" title="Collapse" style="margin-right: 5px;"><i class="fa fa-minus"></i></button>
                                    </div><!-- /. tools -->

                                    <i class="fa fa-map-marker"></i>
                                    <h3 class="box-title">
                                        Visitors
                                    </h3>
                                </div>
                                <div class="box-body no-padding">
                                    <div id="world-map" style="height: 300px;"></div>
                                    <div class="table-responsive">
                                        <!-- .table - Uses sparkline charts-->
                                        <table class="table table-striped">
                                            <tr>
                                                <th>Country</th>
                                                <th>Visitors</th>
                                            </tr>
                                            <% while (iteratorCountries.hasNext()) {
                                                Map.Entry dataVisit = (Map.Entry) iteratorCountries.next();%>
                                            <tr>
                                                <td><%= dataVisit.getKey()%></td>
                                                <td><%= dataVisit.getValue()%></td>
                                            </tr>
                                            <% }%>
                                        </table><!-- /.table -->
                                    </div>
                                </div><!-- /.box-body-->
                                <div class="box-footer">
                                    <button class="btn btn-info"><i class="fa fa-download"></i> Generate PDF</button>
                                    <button class="btn btn-warning"><i class="fa fa-bug"></i> Report Bug</button>
                                </div>
                            </div>
                            <!-- /.box -->
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
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
                        
                        <div class="col-md-6">
                            <!-- Bar chart -->
                            <div class="box box-primary">
                                <div class="box-header">
                                    <i class="fa fa-bar-chart-o"></i>
                                    <h3 class="box-title">Platforms</h3>
                                </div>
                                <div class="box-body">
                                    <div id="os-chart"></div>
                                </div><!-- /.box-body-->
                            </div><!-- /.box -->
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <h3>QR Code</h3>
                            <img src="<%= (new QRApi("http://localhost:8080/shorturl/?l=" + url.getShortUrl(), "350x350", "UTF-8")).getQR()%>" class="thumbnail"/>
                        </div>
                        <div class="col-md-6">
                            <h3>ScreenShot</h3>
                            <img src="<%= (new ScreenShotApi(url.getFullUrl())).getScreenShot()%>" class="thumbnail"/>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">All Visits</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                    <table class="table table-bordered" id="table-sortable">
                                        <thead>
                                            <tr>
                                                <th style="width: 10px">ID</th>
                                                <th>Browser</th>
                                                <th>ClientDomain</th>
                                                <th>IP</th>
                                                <th>Platforms (OS)</th>
                                                <th>Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (UrlVisits urlVis : myUrlVisits) {%>
                                            <tr>
                                                <td><%= urlVis.getId()%></td>
                                                <td><%= urlVis.getBrowser()%></td>
                                                <td><%= urlVis.getClientDomain()%></td>
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
        
        <%@ include file="template/modals/newurl.jsp"%>

        <!-- Morris.js charts -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <!-- Sparkline -->
        <script src="assets/js/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
        <!-- jvectormap -->
        <script src="assets/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
        <script src="assets/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
        <script type="text/javascript">
                    $(function() {
                    Morris.Bar({
                    element: 'browser-chart',
                            data: [
            <% String[] browsersName = {"InternetExplorer", "Firefox", "Chrome", "Safari", "Opera", "NetScape", "Unknown"}; %>
            <% for (int i = 0; i < browserData.length; i++) {
            %>
                            { y : '<%= browsersName[i]%>', a : <%= browserData[i]%> },
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
            <% for (int i = 0; i < osData.length; i++) {
                if (osData[i] > 0) {
            %>
                                    { label : '<%= osName[i]%>', value : <%= osData[i]%> },
            <% }
            } %>
                                    ]
                                    });
                            Morris.Line({
                            element: 'date-chart',
                                    data: [
            <% while (iteratorDates.hasNext()) {
                Map.Entry dataVisit = (Map.Entry) iteratorDates.next();%>
                                    { y: '<%= dataVisit.getKey()%>', a: <%= dataVisit.getValue()%>},
            <% }%>
                                    ],
                                    xkey: 'y',
                                    ykeys: ['a'],
                                    labels: ['Visits']
                                    });
                            


    //jvectormap data
    var visitorsData = {
        <% while (iteratorCountryCodes.hasNext()) {
            Map.Entry dataVisit = (Map.Entry) iteratorCountryCodes.next();%>
            "<%= dataVisit.getKey()%>": <%= dataVisit.getValue()%>,
        <% }%>
    };
    //World map by jvectormap
    $('#world-map').vectorMap({
        map: 'world_mill_en',
        backgroundColor: "#fff",
        regionStyle: {
            initial: {
                fill: '#e4e4e4',
                "fill-opacity": 1,
                stroke: 'none',
                "stroke-width": 0,
                "stroke-opacity": 1
            }
        },
        series: {
            regions: [{
                    values: visitorsData,
                    scale: ["#3c8dbc", "#2D79A6"], //['#3E5E6B', '#A6BAC2'],
                    normalizeFunction: 'polynomial'
                }]
        },
        onRegionLabelShow: function(e, el, code) {
            if (typeof visitorsData[code] != "undefined")
                el.html(el.html() + ': ' + visitorsData[code] + ' new visitors');
        }
    });
});     
                            
                            





            </script>
        <script src="assets/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="assets/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>

        <script src="assets/js/shorturl.js" type="text/javascript"></script>

    </body>
</html>