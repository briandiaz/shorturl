
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shorturl.classes.urlJPA;
import shorturl.classes.urlParser;
import shorturl.context.ContextURL;
import shorturl.entities.Url;
import shorturl.entities.User;

/**
 *
 * @author frangel
 */
public class createURL extends HttpServlet {

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
        CreateUrl(request, response);
    }
    
    protected void CreateUrl(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
        HttpSession session = request.getSession();
        //si el usuario es valido
        if (urlParser.validateUrl(request)) {
            String link = request.getParameter("url");
            String encoded = urlParser.EncodeUrl(link);
            Url uri = new Url(1);
            uri.setUrl(link);
            uri.setShortUrl(encoded);
            // UrlJ.persist(uri); la persistencia no funciona
            ContextURL context = new ContextURL();
            if (context.create(request.getServletContext(), uri)) {
                response.sendRedirect("./showUrl.jsp");
            } else {
                response.sendRedirect("./createURL");
            }
            System.out.println(link);
            
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
