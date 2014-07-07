package shorturl.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shorturl.classes.Helper;
import shorturl.classes.Parameters;
import shorturl.entities.Url;
import shorturl.entities.User;
import shorturl.persistence.PersistenceJPA;

/**
 *
 * @author Eder
 */
public class ServletUser extends HttpServlet {

    PersistenceJPA persistence = PersistenceJPA.getSingletonInstance();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processUser(request, response);
    }

    protected void processUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user;
        if (request.getParameter(Parameters.servletAction).equals("create")) {
            user = Helper.createUsuarioInstance(request);
            if (Helper.validateParamsUser(request)) {
                if (create(user)) {
                    session.setAttribute(Parameters.userSessionProp, user);
                    response.sendRedirect("index.jsp");
                }
            } else {
                response.sendRedirect(Parameters.registerPage);
            }
        } else if (request.getParameter(Parameters.servletAction).equals("update")) {
            
        } else if (request.getParameter(Parameters.servletAction).equals("delete")) {

        } else if (request.getParameter(Parameters.servletAction).equals("login")) {
            user = new User();
            user.setUsername(request.getParameter(Parameters.userUsuarioProp));
            user.setPassword(request.getParameter(Parameters.userPasswordProp));
            if(UserValidated(user)){
                session.setAttribute(Parameters.userSessionProp, user);
                response.sendRedirect(Parameters.dashboardPage);
            }else{
                response.sendRedirect(Parameters.loginPage);
            }
            
        }
    }

    public boolean UserValidated(User user){
        boolean isValidated = false;
        List<User> listaUsuario = persistence.getListaUsuario();
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            System.out.println(listaUsuario.size());
        for(User _user : listaUsuario){
            System.out.println(_user.getUsername());
            System.out.println(_user.getPassword());
            if(_user.getUsername().equals(user.getUsername()) 
                    && _user.getPassword().equals(user.getPassword())){
                isValidated = true;
                break;
            }
        }
        return isValidated;
    }
    
    public boolean create(User user) {
        boolean isCreated = false;
        EntityManager entityManager = persistence.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            return isCreated;
        }
    }

    protected boolean update(User user) {
        return persistence.updateUser(user);
    }

    protected boolean delete(User user) {
        User deleteUser = (User) persistence.read(Url.class, user.getId());
        return persistence.delete(deleteUser);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
