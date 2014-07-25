/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.core.UriInfo;
import shorturl.classes.Helper;
import shorturl.classes.Parameters;
import shorturl.classes.urlParser;
import shorturl.entities.Role;
import shorturl.entities.Url;
import shorturl.entities.UrlVisits;
import shorturl.entities.User;
import shorturl.persistence.PersistenceJPA;

/**
 *
 * @author frangel
 */
@Path("restURL")
public class restURL {

    @Context
    private UriInfo context;

    @Context
    private HttpServletRequest req;
    private HttpServletResponse resp;
    // private String lista="";

    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        return "Esto es una prueba del URI";
    }

    @Path("createuser")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public boolean crearUsuario(@FormParam(Parameters.userUsuarioProp) String username, @FormParam(Parameters.userPasswordProp) String password, @FormParam(Parameters.userEmailProp) String email) {
        boolean isCreated = false;
        User user = new User();
        Role role = (Role) PersistenceJPA.getSingletonInstance().read(Role.class, 2);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        EntityManager entityManager = PersistenceJPA.getSingletonInstance().createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            isCreated = true;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            return isCreated;
        }
    }

    //Esta funcion hay que hacerle SPLIT(",") para obtener los valores aparte 
    // del lado de cliente ya que no se puede recibir un ARRAY
    @Path("visits")
    @GET
    //@Consumes("application/json")
    @Produces("application/json")
    public List<UrlVisits> getURLvisits(@QueryParam("short") @DefaultValue("") String link) {
        //List<String> lista = new ArrayList<String>();
         // int conta = 0;
        Url url = PersistenceJPA.getSingletonInstance().getUrlByShortURL(link);
        List<UrlVisits> uri = PersistenceJPA.getSingletonInstance().getListaUrlVisitsByUrl(url);
        /*
         for (UrlVisits actual : uri) {

         lista.add(actual.getId().toString() + "," + actual.getBrowser() + "," + actual.getClientDomain() + ","
         + actual.getCountry() + "," + actual.getCountryCode()
         + actual.getCreatedAt().toGMTString() + "," + actual.getIp() + "," + actual.getOperativeSystem() + "," + url.getFullUrl() + "," + url.getShortUrl());

         }*/
        // conta++;
        //.out.print(lista.get(0));
        return uri;
    }

    @Path("login")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String login(@FormParam("user_username") String username, @FormParam("user_password") String password) {
        String islogin = "false";
        HttpSession session = req.getSession();
        User user = new User();
        user = PersistenceJPA.getSingletonInstance().getUserBySession(username, password);
        if (user != null) {
            session.setAttribute(Parameters.userSessionProp, user);
            islogin = "true";

        } else {
            islogin = "false";
        }
        return islogin;
    }

    @Path("url")
    @GET
    //@Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    //, @PathParam("username") String username
    public Url createURL(@QueryParam("url") @DefaultValue("") String link) {
        Random randomGenerator = new Random();
        HttpSession session = req.getSession();
        Url url = new Url(randomGenerator.nextInt(100));
        String encodedURL = urlParser.randomString(10);
        url.setShortUrl(encodedURL);
        url.setFullUrl(link);
        url.setCreatedAt(new Date());
        boolean isCurrentUser = Helper.isUserLoggedIn(req);
        if (isCurrentUser) {
            url.setUser(Helper.getCurrentUser(req));
        }

        // guardando en la db los datos 
        EntityManager entityManager = PersistenceJPA.getSingletonInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(url);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            // return isCreated;
        }
        //User user = new User();

        System.out.print(url);

        //retornar el codigo del URL 
        return url;
    }

}
