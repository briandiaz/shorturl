package shorturl.classes;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import shorturl.entities.Url;
import shorturl.entities.User;
import shorturl.interfaces.IORMCRUD;
import shorturl.persistence.PersistenceJPA;

public class urlCRUD implements IORMCRUD {

    PersistenceJPA persistence = PersistenceJPA.getSingletonInstance();

    @Override
    public boolean create(Object object) {
        HttpServletRequest request = (HttpServletRequest) object;
        Url url = new Url();
        int userID;
        url.setId(10);
        User user = new User();
        if (request.getParameter(Parameters.urlIDProp) != null) {
            userID = Integer.parseInt(request.getParameter(Parameters.urlIDProp));
            user = (User) persistence.read(User.class, userID);
            url.setUser(user);
        }
        String link = request.getParameter(Parameters.urlFullURLProp);
        String encodedURL = urlParser.randomString(10);
        url.setFullUrl(Parameters.urlFullURLProp);
        url.setShortUrl(encodedURL);
        //.setUpdatedAt(null);
        url.setCreatedAt(new Date());
        EntityManager entityManager = persistence.createEntityManager();

        boolean isCreated = false;
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

    @Override
    public Object read(Object object) {
        return persistence.read(Url.class, object);
    }

    @Override
    public boolean update(Object object) {
        HttpServletRequest request = (HttpServletRequest) object;
        Url updatedUrl = new Url();
        updatedUrl.setId(Integer.parseInt(request.getParameter(Parameters.urlIDProp)));
        updatedUrl.setShortUrl(request.getParameter(Parameters.urlShortURLProp));
        updatedUrl.setFullUrl(request.getParameter(Parameters.urlFullURLProp));
        updatedUrl.setUser((User) persistence.read(User.class, Integer.parseInt(Parameters.urlUserProp)));
        updatedUrl.setUpdatedAt(new Date());
        return persistence.updateUrl(updatedUrl);
    }

    @Override
    public boolean delete(Object object) {
        HttpServletRequest request = (HttpServletRequest) object;
        Url url = (Url) persistence.read(Url.class,
                Integer.parseInt(request.getParameter(Parameters.urlIDProp)));
        return persistence.delete(url);
    }

}
