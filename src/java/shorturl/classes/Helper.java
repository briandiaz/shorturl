package shorturl.classes;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import shorturl.entities.Role;
import shorturl.entities.User;

public class Helper {

    private static int _id;
    private static String _email;
    private static String _username;
    private static String _password;
    private static String _photo;
    private static int _role;

    public static boolean validateParamsUser(HttpServletRequest request) {
        _id = Integer.parseInt(request.getParameter(Parameters.userIDProp));
        _email = request.getParameter(Parameters.userEmailProp);
        _username = request.getParameter(Parameters.userUsuarioProp);
        _password = request.getParameter(Parameters.userPasswordProp);
        _photo = request.getParameter(Parameters.userPhotoProp);
        _role = Integer.parseInt(request.getParameter(Parameters.userRoleProp));
        return (_email != null && _username != null && _password != null && _photo != null
                && !_email.isEmpty() && !_username.isEmpty() && !_photo.isEmpty()
                && !_password.isEmpty());
    }

    public static boolean validateUsuario(HttpServletRequest request) {
        _username = request.getParameter(Parameters.userUsuarioProp);
        return (!_username.isEmpty() && _username != null);
    }

    public static boolean isUserValidToLogin(HttpServletRequest request) {
        _username = request.getParameter(Parameters.userIDProp);
        _password = (String) request.getParameter(Parameters.userPasswordProp);
        return (!_username.isEmpty() && !_password.isEmpty() && _username != null && _password != null);
    }

    public static User createUsuarioInstance(HttpServletRequest request) {
        _id = Integer.parseInt(request.getParameter(Parameters.userIDProp));
        _email = request.getParameter(Parameters.userEmailProp);
        _username = request.getParameter(Parameters.userUsuarioProp);
        _password = request.getParameter(Parameters.userPasswordProp);
        _photo = request.getParameter(Parameters.userPhotoProp);
        _role = Integer.parseInt(request.getParameter(Parameters.userRoleProp));
        Role rolusr = new Role(2);// 1 admin - 2 usuario - 3 anonimo
        Role role =(Role) urlJPA.getInstancia().findObjeto(Role.class, rolusr.getId());
        //role.setName();
        // falta traer el role de la base de datos
        User usr = new User();
        usr.setId(_id);
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
