<!DOCTYPE html>
<html class="bg-black">
    <head>
        <meta charset="UTF-8">
        <title>Short.me - SignUp</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="assets/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="bg-black">

        <div class="form-box" id="login-box">
            <div class="header">Register in Short.me</div>
            <form action="./ServletUser" method="post">
                <div class="body bg-gray">
                    <input type="hidden" name="servlet_action" id="servlet_action" value="create"/>
                    <input type="hidden" name="user_role" id="user_role" value="2"/>
                    <input type="hidden" name="user_photo" id="user_photo" value="https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfp1/t1.0-1/10308232_10202539407424858_6494413392333976801_n.jpg"/>
                    <div class="form-group">
                        <input type="email" name="user_email" id="user_email" class="form-control" placeholder="Email" required/>
                    </div>
                    <div class="form-group">
                        <input type="text" name="user_username" id="user_username" class="form-control" placeholder="Username" required/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="user_password" id="user_password" class="form-control" placeholder="Password" required/>
                    </div>
                </div>
                <div class="footer">                    

                    <button type="submit" class="btn bg-olive btn-block">Sign me up</button>

                    <a href="login.jsp" class="text-center">I already have a membership</a>
                </div>
            </form>

        </div>


        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

    </body>
</html>