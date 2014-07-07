package shorturl.classes;

public class Parameters {
 
    public static final String homePage = "index.jsp";
    public static final String rootPath = "/shorturl/";
    public static final String loginPage = "login.jsp";
    public static final String registerPage = "register.jsp";
    public static final String dashboardPage = "dashboard.jsp";
    
    public static final String createURLServlet = "ServletURL";
    public static final String showURLPage = "showURL.jsp";
    public static final String servletAction = "servlet_action";
    /*  User Class */

    public static final String userIDProp = "user_id";
    public static final String userUsuarioProp = "user_username";
    public static final String userPasswordProp = "user_password";
    public static final String userEmailProp = "user_email";
    public static final String userPhotoProp = "user_photo";
    public static final String userRoleProp = "user_role";

    public static final String userSessionProp = "user_session";

    public static final String urlFullURLProp = "url_full";
    public static final String urlShortURLProp = "url_short";
    public static final String urlUserProp = "url_user";
    public static final String urlIDProp = "url_id";
    
    
    public static final String QR_API = "https://api.qrserver.com/v1/";
    public static final String QR_API_CREATE = QR_API + "create-qr-code/";
    public static final String QR_API_READ = QR_API + "read-qr-code/";


    /* DB Permisions  */
    public static final String dbUrl = "jdbc:h2:tcp://localhost/~/practica4";
    public static final String dbUser = "sa";
    public static final String dbPassword = "";

}
