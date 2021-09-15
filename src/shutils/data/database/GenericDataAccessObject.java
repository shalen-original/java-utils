package shutils.data.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Represents a generic Data Access Object (DAO) for a uniquely identifiable object.
 * Provides a list of methods that each DAO should have and implements some shortcuts
 * for the simpler methods.
 * @param <T> The type of the uniquely identifiable object that this DAO is wrapping.
 */
public abstract class GenericDataAccessObject<T extends IUniquelyIdentifiable>
{
    private String associatedTableName;
    private String associatedTablePrimaryKeyName;
    private String findByIdQuery;
    private String deleteByIdQuery;
    
    private static Random rand = new Random((new Date()).getTime());
    
    protected ISQLResultParser<T> defaultParser = r -> {return parseSQLResult(r);};
    
    /**
     * Creates a new generic DAO.
     * @param tableName The name of the table associated to this DAO. Attention. This value
     * is user directly in a query, without escape. Possible SQL injection risk. Use only trusted
     * values as parameter of this constructor.
     * @param pKey The name of the primary key of the table associated to this DAO. Attention. This value
     * is user directly in a query, without escape. Possible SQL injection risk. Use only trusted
     * values as parameter of this constructor.
     */
    public GenericDataAccessObject(String tableName, String pKey)
    {
        this.associatedTableName = tableName;
        this.associatedTablePrimaryKeyName = pKey;
        
        this.findByIdQuery = "SELECT * FROM " + tableName + " WHERE " + pKey + " = ?";
        this.deleteByIdQuery = "DELETE FROM " + tableName + " WHERE " + pKey + " = ?";
    }
    
    /**
     * Returns the list containing all the memorized T objects.
     * @return The list containing all memorized T objects
     */
    public List<T> getAll()
    {
        return DBUtils.performSelect("SELECT * FROM " + associatedTableName, defaultParser);
    }
    
    /**
     * Updates a single object from the memory to the persistent storage.
     * @param objToWrite The object to be written on the persistent storage.
     */
    public abstract void update(T objToWrite);
    
    /**
     * Deletes a single object from the persistent storage.
     * @param objToDelete The object to be deleted from the persistent storage.
     */
    public void delete(T objToDelete)
    {
        deleteByID(objToDelete.getId());
    }
    
    /**
     * Inserts a new object of type T in the persistent storage
     * @param objToInsert The new object to be inserted
     */
    public abstract void insert(T objToInsert);
    
    /**
     * Finds a single object in the persistent storage, searching by ID.
     * @param ID The ID of the object to be found in the persistent storage.
     * @return The object with id <i>ID</i> in the persistent storage. If no object
     * with id <i>ID</i> is found, the method returns nothing.
     */
    public T findByID(int ID)
    {
        ArrayList<T> ans = new ArrayList<>();
        
        DBUtils.performOperation(conn -> {
            PreparedStatement s = conn.prepareStatement(this.findByIdQuery);
            s.setInt(1, ID);
            ResultSet r = s.executeQuery();
            
            while (r.next())
            {
                ans.add(parseSQLResult(r));
            }
        });
        
        return ans.size() >= 1 ? ans.get(0) : null;
    }
    
    /**
     * Deletes a single object in the persistent storage, given its ID.
     * @param ID The ID of the object to be deleted.
     */
    public void deleteByID(int ID)
    {
        DBUtils.performUID(this.deleteByIdQuery, s -> {
            s.setInt(1, ID);
        });
    }
    
    /**
     * Generates a new random valid ID for the data type T
     * @return A valid, non serial ID for a new object of type T
     */
    public int getNextValidId()
    {
        int ans = rand.nextInt(Integer.MAX_VALUE);
        
        while (findByID(ans) != null)
            ans = rand.nextInt(Integer.MAX_VALUE);

        return ans;
        
    }
    
    /**
     * Analyzes a single row of a <i>ResultSet</i> and converts it to a object
     * of type T. This method does not call <i>next()</i> on the ResultSet object.
     * @param r The ResultSet placed in the right position.
     * @return An object of type T with the fields correctly initialized from the current
     * row of the ResultSet.
     * @throws SQLException If a database error occours.
     */
    public abstract T parseSQLResult(ResultSet r) throws SQLException;
    
}