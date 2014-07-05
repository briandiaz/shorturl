/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.classes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import shorturl.entities.Role;
import shorturl.entities.Url;
import shorturl.entities.UrlVisits;
import shorturl.entities.User;

/**
 *
 * @author frangel
 */
public class urlJPA {
   
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("shorturlPU");
    
    private static urlJPA urlJPA;

    private urlJPA() {
    }

    public static urlJPA getInstancia() {
        if (urlJPA == null) {
            urlJPA = new urlJPA();
        }
        return urlJPA;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param object
     */
    public void delete(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Object findObjeto(Class clase, Object primaryKey) {
        System.out.println("Buscando " + clase.getName() + " - PK: " + primaryKey.toString());
        EntityManager em = emf.createEntityManager();
        Object find = em.find(clase, primaryKey);
        em.close();
        return find;
    }

    public void deleteObjeto(Class clase, Object primaryKey) {
        System.out.println("Eliminado " + clase.getName() + " - PK: " + primaryKey.toString());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Object find = em.find(clase, primaryKey);
        em.remove(find);
        em.getTransaction().commit();
        em.close();

    }

    public void updateUser(User usr) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User find = em.find(User.class, usr.getId());
        find.setEmail(usr.getEmail());
        find.setUsername(usr.getUsername());
        find.setPassword(usr.getPassword());
        find.setPhoto(usr.getPhoto());
        find.setRole(usr.getRole());
        find.setUrlList(usr.getUrlList());
        em.getTransaction().commit();
        em.close();
    }

    public void updateRole(Role rol) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role find = em.find(Role.class, rol.getId());
        find.setName(rol.getName());
        find.setUserList(rol.getUserList());
        find.setValue(rol.getValue());
        em.getTransaction().commit();
        em.close();
    }

    public void updateUrl(Url uri) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Url find = em.find(Url.class, uri.getId());
        find.setShortUrl(uri.getShortUrl());
        find.setUrl(uri.getUrl());
        find.setUrlVisitsList(uri.getUrlVisitsList());
        find.setUserList(uri.getUserList());
        find.setCreatedAt(uri.getCreatedAt());
        find.setUpdatedAt(uri.getUpdatedAt());
        em.getTransaction().commit();
        em.close();
    }

    public void UpdateUrlVisits(UrlVisits vis) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UrlVisits find = em.find(UrlVisits.class, vis.getId());
        find.setClientDomain(vis.getClientDomain());
        find.setIp(vis.getIp());
        find.setOperativeSystem(vis.getOperativeSystem());
        find.setVisits(vis.getVisits());
        find.setUrl(vis.getUrl());
        em.getTransaction().commit();
        em.close();
    }

   
    public List<Url> getListaUrl() {
        EntityManager em = emf.createEntityManager();
        List resultList = em.createQuery("select e from Url e").getResultList();
        em.close();
        return resultList;
    }

   
    public List<User> getListaUsuario() {
        EntityManager em = emf.createEntityManager();
        List resultList = em.createQuery("select e from User e").getResultList();
        em.close();
        return resultList;
    }

   
    public List<UrlVisits> getListaUrlVisits() {
        EntityManager em = emf.createEntityManager();
        List resultList = em.createQuery("select e from URL_Visits e").getResultList();
        em.close();
        return resultList;
    }
    
     public List<Role> getListaRole() {
        EntityManager em = emf.createEntityManager();
        List resultList = em.createQuery("select e from Role e").getResultList();
        em.close();
        return resultList;
    }
}
