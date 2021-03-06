
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Random;
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
import shorturl.classes.urlCRUD;
import shorturl.classes.urlParser;
import shorturl.entities.Url;
import shorturl.entities.User;
import shorturl.persistence.PersistenceJPA;

/**
 *
 * @author frangel
 */
public class ServletURL extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //urlJPA UrlJ = urlJPA.getInstancia();
    PersistenceJPA persistence = PersistenceJPA.getSingletonInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        proccessUrl(request, response);
    }

    protected void proccessUrl(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
        HttpSession session = request.getSession();
        Random randomGenerator = new Random();
        Url url = new Url(randomGenerator.nextInt(100));
        int userID;

        String link = request.getParameter(Parameters.urlFullURLProp);
        String encodedURL = urlParser.randomString(10);
        url.setFullUrl(link);
        url.setShortUrl(encodedURL);
        User user = new User();
        EntityManager entityManager = persistence.createEntityManager();
        boolean isCurrentUser = Helper.isUserLoggedIn(request);
        if (isCurrentUser) {
            url.setUser(Helper.getCurrentUser(request));
        }
        if (request.getParameter(Parameters.servletAction).equals("create")) {
            url.setCreatedAt(new Date());
            try {

                entityManager.getTransaction().begin();
                entityManager.persist(url);
                entityManager.getTransaction().commit();
                if(isCurrentUser){
                    response.sendRedirect("showUrl.jsp?id="+url.getId());
                }
                else{
                    Helper.addURLFromNotRegisteredUser(session, url);
                    response.sendRedirect("urls.jsp");
                    
                }

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
                // return isCreated;
            }
        } else if (request.getParameter(Parameters.servletAction).equals("update")) {
            entityManager.getTransaction().begin();
            Url updatedUrl = entityManager.find(Url.class, request.getParameter(Parameters.urlIDProp));
            updatedUrl.setId(Integer.parseInt(request.getParameter(Parameters.urlIDProp)));
            updatedUrl.setShortUrl(request.getParameter(Parameters.urlShortURLProp));
            updatedUrl.setFullUrl(request.getParameter(Parameters.urlFullURLProp));
            //  updatedUrl.setUser((User) persistence.read(User.class  Integer.parseInt(Parameters.urlUserProp)));
            updatedUrl.setUpdatedAt(new Date());
            entityManager.getTransaction().commit();
            //  persistence.updateUrl(updatedUrl);
            response.sendRedirect("showUrl.jsp");
        } else if (request.getParameter(Parameters.servletAction).equals("delete")) {
            try {

                entityManager.getTransaction().begin();
                Url urix = entityManager.find(Url.class, Integer.parseInt(request.getParameter(Parameters.urlIDProp)));
                entityManager.remove(urix);
                entityManager.getTransaction().commit();
                response.sendRedirect("showUrl.jsp");

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
                // return isCreated;
            }

        }
    }

    protected boolean create(Url url) {
        url.setCreatedAt(new Date());
        boolean isCreated = false;
        EntityManager entityManager = persistence.createEntityManager();
        try {
            
            entityManager.getTransaction().begin();
            entityManager.persist(url);
            entityManager.getTransaction().commit();
            
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            return isCreated;
        }
    }

    protected Url read(Object object) {
        return (Url)persistence.read(Url.class, object);
    }
    
    protected boolean update(Url url) {
        return persistence.updateUrl(url);
    }

    protected boolean delete(Url url) {
        Url deleteUrl = (Url) persistence.read(Url.class, url.getId());
        return persistence.delete(deleteUrl);
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
