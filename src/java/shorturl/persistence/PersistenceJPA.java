/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.persistence;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import shorturl.entities.Role;
import shorturl.entities.Url;
import shorturl.entities.UrlVisits;
import shorturl.entities.User;
import shorturl.interfaces.IORMCRUD;

public class PersistenceJPA {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shorturlPU");

    private static PersistenceJPA persistenceJPA;

    private PersistenceJPA() {
    }

    public static PersistenceJPA getSingletonInstance() {
        if (persistenceJPA == null) {
            persistenceJPA = new PersistenceJPA();
        }
        return persistenceJPA;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public boolean create(Object object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean isCreated = false;
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            return isCreated;
        }
    }

    public boolean delete(Object object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean isDeleted = false;
        try {

            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.remove(object);
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

    public Object read(Class clase, Object primaryKey) {
        System.out.println("Buscando " + clase.getName() + " - PK: " + primaryKey.toString());
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Object find = entityManager.find(clase, primaryKey);
        entityManager.close();
        return find;
    }

    public boolean updateUser(User usr) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean isUpdated = false;
        try {

            entityManager.getTransaction().begin();
            User find = entityManager.find(User.class, usr.getId());
            find.setEmail(usr.getEmail());
            find.setUsername(usr.getUsername());
            find.setPassword(usr.getPassword());
            find.setPhoto(usr.getPhoto());
            find.setRole(usr.getRole());
            find.setUrlList(usr.getUrlList());
            entityManager.getTransaction().commit();
            isUpdated = true;

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            return isUpdated;
        }
    }

    public boolean updateRole(Role rol) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean isUpdated = false;
        try {

            entityManager.getTransaction().begin();
            Role find = entityManager.find(Role.class, rol.getId());
            find.setName(rol.getName());
            find.setUserList(rol.getUserList());
            find.setValue(rol.getValue());
            entityManager.getTransaction().commit();
            isUpdated = true;

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            return isUpdated;
        }
    }

    public boolean updateUrl(Url url) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean isUpdated = false;
        try {

            entityManager.getTransaction().begin();
            Url find = entityManager.find(Url.class, url.getId());
            find.setShortUrl(url.getShortUrl());
            find.setFullUrl(url.getFullUrl());
            find.setUser(url.getUser());
            find.setCreatedAt(url.getCreatedAt());
            find.setUpdatedAt(url.getUpdatedAt());
            entityManager.getTransaction().commit();
            isUpdated = true;

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            return isUpdated;
        }
    }

    public boolean UpdateUrlVisits(UrlVisits vis) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean isUpdated = false;
        try {

            entityManager.getTransaction().begin();
            UrlVisits find = entityManager.find(UrlVisits.class, vis.getId());
            find.setBrowser(vis.getBrowser());
            find.setClientDomain(vis.getClientDomain());
            find.setIp(vis.getIp());
            find.setOperativeSystem(vis.getOperativeSystem());
            find.setUrl(vis.getUrl());
            entityManager.getTransaction().commit();
            isUpdated = true;

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            return isUpdated;
        }
    }

    public User getUserByUsername(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from User e where e.username = :username");
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        entityManager.close();
        return (users.size() > 0) ? users.get(0) : null;
    }

    public User getUserBySession(String username, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from User e where e.username = :username and e.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        entityManager.close();
        return (users.size() > 0) ? users.get(0) : null;
    }

    public List<Url> getListaUrl() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createQuery("select e from Url e").getResultList();
        entityManager.close();
        return resultList;
    }
    
    public List<Url> getListaUrl(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from Url e where e.user.username = :username");
        query.setParameter("username", username);
        List<Url> urls = query.getResultList();
        entityManager.close();
        return urls;
    }
    
    public Url getUrlByID(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from Url e where e.id = :id");
        query.setParameter("id", id);
        List<Url> urls = query.getResultList();
        entityManager.close();
        return (urls.size() > 0) ? urls.get(0) : null;
    }
    
    public Url getUrlByShortURL(String shortUrl) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from Url e where e.shortUrl = :shortUrl");
        query.setParameter("shortUrl", shortUrl);
        List<Url> urls = query.getResultList();
        entityManager.close();
        return (urls.size() > 0) ? urls.get(0) : null;
    }
    
    public List<User> getListaUsuario() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createQuery("select e from User e").getResultList();
        entityManager.close();
        return resultList;
    }

    public List<UrlVisits> getListaUrlVisits() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createQuery("select e from UrlVisits e").getResultList();
        entityManager.close();
        return resultList;
    }
    
    public List<UrlVisits> getListaUrlVisitsByUrl(Url url) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from UrlVisits e where e.url.id = :id");
        query.setParameter("id", url.getId());
        List<UrlVisits> urlVisits = query.getResultList();
        entityManager.close();
        return urlVisits;
    }

    public List<UrlVisits> getListaUrlVisits(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createQuery("select e from UrlVisits e WHERE e.url.id = " + id).getResultList();
        entityManager.close();
        return resultList;
    }

    public List<Role> getListaRole() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createQuery("select e from Role e").getResultList();
        entityManager.close();
        return resultList;
    }

}
