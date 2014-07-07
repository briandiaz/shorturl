package shorturl.classes;

public class Parameters {
 
    public static final String homePage = "index.jsp";
    public static final String createURLServlet = "ServletURL";
    public static final String showURLPage = "showURL.jsp";
    public static final String servletAction = "servlet_action";
    /*  User Class */

    public static final String userIDProp = "usuario_id";
    public static final String userUsuarioProp = "usuario_usuario";
    public static final String userPasswordProp = "usuario_contrasena";
    public static final String userEmailProp = "usuario_apellido";
    public static final String userPhotoProp = "usuario_apellido";
    public static final String userRoleProp = "usuario_role";

    public static final String userSessionProp = "usuario_session";

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
