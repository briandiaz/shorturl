
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shorturl.classes.Parameters;
import shorturl.classes.urlCRUD;
import shorturl.classes.urlJPA;
import shorturl.classes.urlParser;
import shorturl.entities.Url;
import shorturl.entities.User;

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
    urlJPA UrlJ = urlJPA.getInstancia();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        proccessUrl(request, response);
    }
    
    protected void proccessUrl(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
        HttpSession session = request.getSession();
        urlCRUD CRUD = new urlCRUD();
        //si el usuario es valido
        if (urlParser.validateUrl(request)) {
            if(request.getParameter(Parameters.servletAction).equals("create")){
                if(CRUD.create(request)){
                    response.sendRedirect(Parameters.showURLPage);
                } else {
                    response.sendRedirect(Parameters.createURLServlet);
                }
            }
            if(request.getParameter(Parameters.servletAction).equals("update")){
                if(CRUD.update(request)){
                    response.sendRedirect(Parameters.showURLPage);
                } else {
                    response.sendRedirect(Parameters.createURLServlet);
                }
            }
            if(request.getParameter(Parameters.servletAction).equals("delete")){
                if(CRUD.delete(request)){
                    response.sendRedirect(Parameters.homePage);
                } else {
                    response.sendRedirect(Parameters.createURLServlet);
                }
            }
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
