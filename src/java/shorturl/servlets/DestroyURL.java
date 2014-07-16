
package shorturl.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shorturl.classes.Helper;
import shorturl.entities.Url;
import shorturl.entities.UrlVisits;
import shorturl.entities.User;
import shorturl.persistence.PersistenceJPA;

/**
 *
 * @author Eder
 */
public class DestroyURL extends HttpServlet {

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
        processURL(request,response);
    }

    protected void processURL(HttpServletRequest request, HttpServletResponse response) 
            throws IOException{
        PrintWriter out = response.getWriter();
        if(Helper.isAdminUser(request)){
            int urlID = Integer.parseInt(request.getParameter("id"));
            List<UrlVisits> urlVisits = PersistenceJPA.getSingletonInstance().getListaUrlVisits(urlID);
            if(destroy(urlID,urlVisits)){
                out.println("<div class=\"alert alert-success alert-dismissible\" role=\"alert\">\n" +
"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n" +
"  <strong>URL Deleted!</strong> This url was deleted successfully!.\n" +
"</div>");
            }
            else{
            out.println("<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">\n" +
"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n" +
"  <strong>Error!</strong> An error occured while trying to delete this url!.\n" +
"</div>");
            }
        }
        else{
            out.println("<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">\n" +
"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n" +
"  <strong>Error!</strong> You are not admin user!.\n" +
"</div>");
        }
    }
    
    protected boolean destroy(int urlID, List<UrlVisits> urlVisits) {
        boolean isDeleted = false;
        EntityManager entityManager = persistence.createEntityManager();
            
        try {

            entityManager.getTransaction().begin();
            for(UrlVisits visit : urlVisits){
                UrlVisits urlVisit = entityManager.getReference(UrlVisits.class, visit.getId());
                entityManager.merge(urlVisit);
                entityManager.remove(urlVisit);
            }
            Url url = entityManager.getReference(Url.class, urlID);
            entityManager.merge(url);
            entityManager.remove(url);
            entityManager.getTransaction().commit();
            isDeleted = true;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            return isDeleted;
        }
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
