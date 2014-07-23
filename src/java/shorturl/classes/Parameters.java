package shorturl.classes;

public class Parameters {
    /*      Pages and Servlets  */
    public static final String homePage = "index.jsp";
    public static final String rootPath = "/shorturl/";
    public static final String loginPage = "login.jsp";
    public static final String registerPage = "register.jsp";
    public static final String dashboardPage = "dashboard.jsp";
    public static final String manageUrlsPage = "postedurls.jsp";
    public static final String manageUsersPage = "users.jsp";
    public static final String myURLPage = "myURL.jsp";
    public static final String notUserURLSPage = "urls.jsp";
    public static final String createURLServlet = "ServletURL";
    public static final String showURLPage = "showUrl.jsp";
    public static final String servletAction = "servlet_action";
    public static final String root = "http://localhost:8080/shorturl/";
    
    /*      User Class */
    public static final String userIDProp = "user_id";
    public static final String userUsuarioProp = "user_username";
    public static final String userPasswordProp = "user_password";
    public static final String userEmailProp = "user_email";
    public static final String userPhotoProp = "user_photo";
    public static final String userRoleProp = "user_role";


    /*      Url     */
    public static final String urlFullURLProp = "url_full";
    public static final String urlShortURLProp = "url_short";
    public static final String urlUserProp = "url_user";
    public static final String urlIDProp = "url_id";
    
    /*      QR Api  */
    public static final String QR_API = "https://api.qrserver.com/v1/";
    public static final String QR_API_CREATE = QR_API + "create-qr-code/";
    public static final String QR_API_READ = QR_API + "read-qr-code/";


    /*      DB Permisions  */
    public static final String dbUrl = "jdbc:h2:tcp://localhost/~/practica4";
    public static final String dbUser = "sa";
    public static final String dbPassword = "";

    /*      Sessions */
    public static final String sessionDataURL = "dataUrl";
    public static final String userSessionProp = "user_session";
    
    /*      Browsers */
    public static final String browserInternetExplorer = "InternetExplorer";
    public static final String browserFirefox = "Firefox";
    public static final String browserChrome = "Chrome";
    public static final String browserSafari = "Safari";
    public static final String browserOpera = "Opera";
    public static final String browserNetScape = "NetScape";
    public static final String browserUnknown = "Unknown";
    
    /**     Operative System    */
    public static final String osUnknown = "Unknown";
    public static final String osWindows = "Windows";
    public static final String osMac = "Mac";
    public static final String osAndroid = "Android";
    public static final String osiPhone = "iPhone";
    public static final String osUnix = "Unix";
    
    /*     ScreenShot API     */
    public static final String ScreenShotApiURL = "https://api.browshot.com/api/v1/simple";
    public static final String ScreenShotApiKey = "R4uJaCOXSmCQYsm1DhimRQaNVv";
    public static final String ScreenShotApiFormat = "png";
    public static final int ScreenShotApiInstance = 12;
    public static final int ScreenShotApiWidth = 640;
    public static final int ScreenShotApiHeight = 480;
    public static final int ScreenShotApiShot = 1;
    public static final int ScreenShotApiQuality = 90;
    public static final int ScreenShotApiT = 8;
    
    /*      IP JSON     */
    public static final String IPjsonURL = "http://ip-api.com/json/";
    
}
