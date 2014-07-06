/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.context;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.servlet.ServletContext;
import practica.jsp.interfaces.ICRUD;
import shorturl.classes.Parameters;
import shorturl.classes.urlJPA;
import shorturl.entities.Url;


public class ContextURL implements ICRUD {

    //urlJPA Uri = urlJPA.getInstancia();
    public ContextURL() {
    }

    @Override
    public boolean create(ServletContext application, Object object) {
        Url url = (Url) object;
        List<Url> urls = getAllUrls(application);//Uri.getListaUrl();
        boolean isCreated = false;
        if (isUserAvailable(application, url)) {
            urls.add(url);
            application.setAttribute(Parameters.contextUrl, urls);
            isCreated = true;

        }
        System.out.print(isCreated);
        return isCreated;
    }

    @Override
    public boolean read(ServletContext application, Object object) {
        return false;
    }

    @Override
    public boolean update(ServletContext application, Object object) {
        return false;
    }

    @Override
    public boolean delete(ServletContext application, Object object) {
        List<Url> urls = getAllUrls(application);
        boolean isDeleted = false;
        Url url = (Url) object;
        //Uri.delete(url);
        urls.remove(url);
        application.setAttribute(Parameters.contextUrl, urls);
        isDeleted = true;

        return isDeleted;
    }

    public boolean isUserAvailable(ServletContext application, Url link) {
        List<Url> urls = getAllUrls(application);
        boolean isAvailable = true;
        for (Url url : urls) {
            System.out.print("false");
         //  if (url.getId() == link.getId()) {
              //  isAvailable = false;//devuelve true si el producto existe y es mayor que 0                
             //   break;
          //  }
        }
        System.out.print("es: " + isAvailable);
        return isAvailable;
    }

    public List<Url> getAllUrls(ServletContext application) {
        List<Url> urls = (ArrayList<Url>) application.getAttribute(Parameters.contextUrl);
        if (urls == null) {
            urls = new ArrayList<Url>();
        }
        return urls;
    }
}
