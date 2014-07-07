
package shorturl.interfaces;

/**
 *
 * @author Eder
 */
public interface IORMCRUD {
    /**
     *
     * @param object This object will be saved into the database.
     * @return true if saved, otherwise false.
     *
     */
    public boolean create(Object object);
    
    /**
     *
     * @param myClass
     * @param object
     * @return object.
     *
     */
    public Object read(Object object);
    
    /**
     *
     * @param object This object will be updated in the database.
     * @return true if updated, otherwise false.
     *
     */
    public boolean update(Object object);
    
    /**
     *
     * @param object This object will be deleted from the database.
     * @return true if deleted, otherwise false.
     *
     */
    public boolean delete(Object object);
}
