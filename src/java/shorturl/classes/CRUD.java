
package shorturl.classes;

/**
 *
 * @author Eder
 */
public abstract class CRUD {
    /**
     *
     * @param object This object will be saved into the database.
     * @return true if saved, otherwise false.
     *
     */
    public abstract boolean create(Object object);
    
    /**
     *
     * @param myClass
     * @param object
     * @return object.
     *
     */
    public abstract Object read(Object object);
    
    /**
     *
     * @param object This object will be updated in the database.
     * @return true if updated, otherwise false.
     *
     */
    public abstract boolean update(Object object);
    
    /**
     *
     * @param object This object will be deleted from the database.
     * @return true if deleted, otherwise false.
     *
     */
    public abstract boolean delete(Object object);
}
