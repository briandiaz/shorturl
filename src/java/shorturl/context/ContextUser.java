/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.context;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.servlet.ServletContext;
import practica.jsp.interfaces.ICRUD;
import shorturl.classes.urlJPA;
import shorturl.entities.User;

public class ContextUser implements ICRUD {

    urlJPA Uri = urlJPA.getInstancia();

    public ContextUser() {
    }

    @Override
    public boolean create(ServletContext application, Object object) {
        User usuario = (User) object;
        List<User> usuarios = Uri.getListaUsuario();
        boolean isCreated = false;
        if (isUserAvailable(application, usuario)) {
            usuarios.add(usuario);
            application.setAttribute("contextousuario", usuarios);
            isCreated = true;

        }
        System.out.print(isCreated);
        return isCreated;
    }

    @Override
    public boolean read(ServletContext application, Object object) {
        return false;
    }

    @Override
    public boolean update(ServletContext application, Object object) {
        return false;
    }

    @Override
    public boolean delete(ServletContext application, Object object) {
        List<User> usuarios = Uri.getListaUsuario();
        boolean isDeleted = false;
        User usuario = (User) object;
        Uri.delete(usuario);
        usuarios.remove(usuario);
        application.setAttribute("contextousuario", usuarios);
        isDeleted = true;

        return isDeleted;
    }

    public boolean isUserAvailable(ServletContext application, User usr) {
        List<User> usuarios = Uri.getListaUsuario();
        boolean isAvailable = true;

        for (User usuario : usuarios) {
            System.out.print("false");
            if (usuario.getId() == usr.getId()) {
                isAvailable = false;//devuelve true si el producto existe y es mayor que 0                
                break;
            }
        }
        System.out.print("es: " + isAvailable);
        return isAvailable;
    }

    public List<User> getAllUsers(ServletContext application) {
        List<User> usuarios = (ArrayList<User>) application.getAttribute("contextusuario");
        if (usuarios == null) {
            usuarios = new ArrayList<User>();
        }
        return usuarios;
    }
}
