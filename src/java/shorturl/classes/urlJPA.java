/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.classes;

import java.util.Date;
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
import shorturl.interfaces.IORMCRUD;


public class urlJPA{
   
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shorturlPU");
    
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
        return entityManagerFactory;
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

    /**
     *
     * @param object
     */
    public boolean delete(Object object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean isDeleted = false;
        try {
            entityManager.getTransaction().begin();
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

    public boolean update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Object findObjeto(Class clase, Object primaryKey) {
        System.out.println("Buscando " + clase.getName() + " - PK: " + primaryKey.toString());
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Object find = entityManager.find(clase, primaryKey);
        entityManager.close();
        return find;
    }

    public void deleteObjeto(Class clase, Object primaryKey) {
        System.out.println("Eliminado " + clase.getName() + " - PK: " + primaryKey.toString());
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Object find = entityManager.find(clase, primaryKey);
        entityManager.remove(find);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

   
    public List<Url> getListaUrl() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createQuery("select e from Url e").getResultList();
        entityManager.close();
        return resultList;
    }

   
    public List<User> getListaUsuario() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createQuery("select e from User e").getResultList();
        entityManager.close();
        return resultList;
    }

   
    public List<UrlVisits> getListaUrlVisits() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createQuery("select e from URL_Visits e").getResultList();
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
