
package practica.jsp.interfaces;

import java.util.Collections;
import java.util.List;
import javax.servlet.ServletContext;

public interface ICRUD {
    
    public boolean create(ServletContext application, Object object);
    
    public boolean read(ServletContext application, Object object);
    
    public boolean update(ServletContext application, Object object);
    
    public boolean delete(ServletContext application, Object object);
}
