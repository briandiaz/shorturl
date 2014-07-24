package shorturl.services;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import shorturl.classes.urlParser;
import shorturl.entities.Role;
import shorturl.entities.Url;
import shorturl.entities.UrlVisits;
import shorturl.entities.User;
import shorturl.persistence.PersistenceJPA;

@WebService(serviceName = "createUrlWS")
public class createUrlWS {


    /*
     Que operaciones permitira este servicio
    
     *-Crear usuario
     -Crear un URL
     *-Autentificar un usuario
     
     -Enviar datos de las visitas al URL
    
     */
    //Esta funcion recibe el url y envia de vuelta una lista con todas las visitas de las url en un String 
    @WebMethod(operationName = "getURLvisits")
    public String[] getURLvisits(@WebParam(name = "url") String link) {

        String[] visitas = null;
        int conta = 0;
        Url url = PersistenceJPA.getSingletonInstance().getUrlByShortURL(link);
        List<UrlVisits> uri = PersistenceJPA.getSingletonInstance().getListaUrlVisitsByUrl(url);
        for (UrlVisits actual : uri) {
            //   0    1         2            3       4         5      6      7              8         9 
            // "id,browser,clientdomain,country,countrycode,createdAt,Ip,OpetativeSystem,FullUrl, ShortUrl"
            //arreglo de 9 elementos
            visitas[conta] = actual.getId() + "," + actual.getBrowser() + "," + actual.getClientDomain() + "," + actual.getCountry() + "," + actual.getCountryCode()
                    + actual.getCreatedAt() + "," + actual.getIp() + "," + actual.getOperativeSystem() + "," + url.getFullUrl() + "," + url.getShortUrl();
        }
        return visitas;
    }

    @WebMethod(operationName = "validateLogin")
    public boolean validateLogin(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        boolean isValidated = false;
        User user = PersistenceJPA.getSingletonInstance().getUserByUsername(username);
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            isValidated = true;
        } else {
            isValidated = false;
        }
        return isValidated;
    }

    //metodo para crear el usuario recive por parametros el usuario contrasena y email y devuelve un TRUE si el usuario puede ser creado de lo contrario es que ya existe 
    @WebMethod(operationName = "crearUsuario")
    public boolean crearUsuario(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "email") String email) {
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

    @WebMethod(operationName = "createURL")
    public String crearURL(@WebParam(name = "url") String link, @WebParam(name = "username") String username) {
        Random randomGenerator = new Random();
        Url url = new Url(randomGenerator.nextInt(100));
        String encodedURL = urlParser.randomString(10);
        url.setFullUrl(link);
        url.setShortUrl(encodedURL);
        url.setCreatedAt(new Date());
        User user;
        //si llega un nombre de usuario como parametro buscarlo y agregarlo al link
        if (username != null) {
            user = PersistenceJPA.getSingletonInstance().getUserByUsername(username);
            url.setUser(user);
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
        return encodedURL;
    }

}
