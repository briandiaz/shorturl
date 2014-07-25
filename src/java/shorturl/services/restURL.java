/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import shorturl.classes.Helper;
import shorturl.classes.Parameters;
import shorturl.classes.urlParser;
import shorturl.entities.Role;
import shorturl.entities.Url;
import shorturl.entities.UrlVisits;
import shorturl.entities.User;
import shorturl.persistence.PersistenceJPA;

@Path("restURL")
public class restURL {

    @Context
    private UriInfo context;

    @Context
    private HttpServletRequest req;
    private HttpServletResponse resp;

    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        return "Esto es una prueba del URI";
    }

    @Path("user")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public User crearUsuario(@Context final HttpServletResponse response,
      @Context final HttpServletRequest request) throws Exception{
        User user = new User();
        Role role = (Role) PersistenceJPA.getSingletonInstance().read(Role.class, 2);
        String username = request.getParameter("user_username");
        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");
        String photo = request.getParameter("user_photo");
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        user.setPhoto(photo);
        PersistenceJPA.getSingletonInstance().create(user);
        return user;
    }

    @Path("visits")
    @GET
    @Produces("application/json")
    public List<UrlVisits> getURLvisits(@QueryParam("short") String link) {
        Url url = PersistenceJPA.getSingletonInstance().getUrlByShortURL(link);
        List<UrlVisits> uri = PersistenceJPA.getSingletonInstance().getListaUrlVisitsByUrl(url);
        return uri;
    }
    
    
    @Path("urls")
    @GET
    @Produces("application/json")
    public List<Url> getUrls(@QueryParam("username") String username) {
        User user = PersistenceJPA.getSingletonInstance().getUserByUsername(username);
        List<Url> urls = null;
        if(user != null){
            urls = PersistenceJPA.getSingletonInstance().getListaUrl(user.getUsername());
        }
        return urls;
    }

    @Path("login")
    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    @Consumes("application/x-www-form-urlencoded")
    public User login(@Context final HttpServletResponse response,
      @Context final HttpServletRequest request) throws Exception {
        HttpSession session = req.getSession();
        String user_username = request.getParameter("user_username");
        String user_password = request.getParameter("user_password");
        User user = new User();
        user = PersistenceJPA.getSingletonInstance().getUserBySession(user_username, user_password);
        
        if (user != null) {
            session.setAttribute(Parameters.userSessionProp, user);
        }
        return user;
    }

    @Path("url")
    @GET
    @Produces("application/json")
    public Url createURL(@QueryParam("url") String link, @QueryParam("user") String username) {
        Random randomGenerator = new Random();
        HttpSession session = req.getSession();
        if(link == null){
            return null;
        }
        Url url = new Url(randomGenerator.nextInt(100));
        String encodedURL = urlParser.randomString(10);
        url.setShortUrl(encodedURL);
        url.setFullUrl(link);
        url.setCreatedAt(new Date());
        User user = PersistenceJPA.getSingletonInstance().getUserByUsername(username);
        System.out.println(username);
        if (user != null) {
            url.setUser(user);
            System.out.println("user added to url");
        }
        PersistenceJPA.getSingletonInstance().create(url);
        return url;
    }

}
