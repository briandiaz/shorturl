package shorturl.classes;

import java.io.PrintWriter;
import java.util.List;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import shorturl.entities.Role;
import shorturl.entities.User;
import shorturl.persistence.PersistenceJPA;
import shorturl.servlets.ServletUser;


public class Helper {

    private static int _id;
    private static String _email;
    private static String _username;
    private static String _password;
    private static String _photo;
    private static int _role;

    public static boolean validateParamsUser(HttpServletRequest request) {
        _email = request.getParameter(Parameters.userEmailProp);
        _username = request.getParameter(Parameters.userUsuarioProp);
        _password = request.getParameter(Parameters.userPasswordProp);
        _photo = request.getParameter(Parameters.userPhotoProp);
        _role = Integer.parseInt(request.getParameter(Parameters.userRoleProp));
        return (_email != null && _username != null && _password != null && _photo != null
                && !_email.isEmpty() && !_username.isEmpty() && !_photo.isEmpty()
                && !_password.isEmpty());
    }

    
    public static void createAdminUser() {
        
        ServletUser servlUser = new ServletUser();
        Role role = (Role)PersistenceJPA.getSingletonInstance().read(Role.class, 1);
        User usr = new User();
        usr.setUsername("admin");
        usr.setPassword("admin");
        usr.setEmail("admin@admin.com");
        usr.setPhoto("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfp1/t1.0-1/p160x160/10308232_10202539407424858_6494413392333976801_n.jpg");
        usr.setRole(role);
        if(!servlUser.UserValidated(usr)){
            servlUser.create(usr);
        }
    }
    
    public static boolean validateUsuario(HttpServletRequest request) {
        _username = request.getParameter(Parameters.userUsuarioProp);
        return (!_username.isEmpty() && _username != null);
    }
    
    public static boolean isUserLoggedIn(HttpServletRequest request) {
        return (currentUser(request)!=null);
    }

    public static User currentUser(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        return (User)session.getAttribute(Parameters.userSessionProp);
    }
    
    public static User getCurrentUser(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        ServletUser servlUser = new ServletUser();
        User user = (User)session.getAttribute(Parameters.userSessionProp);
        return servlUser.read(user);
    }
    
    public static boolean isUserValidToLogin(HttpServletRequest request) {
        _username = request.getParameter(Parameters.userIDProp);
        _password = (String) request.getParameter(Parameters.userPasswordProp);
        return (!_username.isEmpty() && !_password.isEmpty() && _username != null && _password != null);
    }

    public static User createUsuarioInstance(HttpServletRequest request) {
        _email = request.getParameter(Parameters.userEmailProp);
        _username = request.getParameter(Parameters.userUsuarioProp);
        _password = request.getParameter(Parameters.userPasswordProp);
        _photo = request.getParameter(Parameters.userPhotoProp);
        _role = Integer.parseInt(request.getParameter(Parameters.userRoleProp));
        Role role = (Role)PersistenceJPA.getSingletonInstance().read(Role.class, _role);
        User usr = new User();
        usr.setUsername(_username);
        usr.setPassword(_password);
        usr.setEmail(_email);
        usr.setPhoto(_photo);
        usr.setRole(role);
        return usr; 
    }

    public static User createUsuarioLoginInstance(HttpServletRequest request) {
        _username = request.getParameter(Parameters.userIDProp);
        _password = request.getParameter(Parameters.userPasswordProp);
        return new User();
    }

}
