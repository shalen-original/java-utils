package shutils.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains various database utilities.
 */
public class DBUtils {
    
    private static Connection conn;
    
    /**
     * Allows to perform a generic operation on the database.
     * This methods creates a connection for the requested operation and closes it
     * after such operation completed. This method also catches any SQLException, in
     * order to avoid filling the code with try/catches (not good practice, I know. To
     * refactor in more serious applications)
     * @param operation The operation to execute
     */
    public static void performOperation(IDatabaseOperation operation)
    {
        try
        {
            if (conn == null || conn.isClosed())
            {
                conn = DriverManager.getConnection("URL", "USERNAME", "PASSWORD");
            }

            operation.accept(conn);
            
        }catch (SQLException ex){
            try{
                conn.rollback();
            } catch (Exception ex2) {}
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Performs a generic UID (Update, Insert or Delete) operation on the database.
     * @param sql The string containing the sql of the query
     * @param filler The functions that sets all the parameters of the prepared statement
     */
    public static void performUID(String sql, IPreparedStatementFiller filler)
    {
        performOperation(conn -> {
            PreparedStatement s = conn.prepareStatement(sql);
            filler.accept(s);
            s.executeUpdate();    
        });
    }
    
    /**
     * Performs a select operation on a prepared statement and parses the result, returning a List.
     * @param sql The string containing the sql of the query
     * @param filler The functions that sets all the parameters of the prepared statement
     * @param parser The function that parses each result of the query and returns a new object of type T.
     * @param <T> The type of data contained by the list.
     * @return A list containing all the records selected by the query.
     */
    public static <T> List<T> performSelect(String sql, IPreparedStatementFiller filler,
                                            ISQLResultParser<T> parser)
    {
        
        List<T> ans = new ArrayList<>();
        
        DBUtils.performOperation(conn -> {
            PreparedStatement s = conn.prepareStatement(sql);
            filler.accept(s);
            ResultSet r = s.executeQuery();
            
            while (r.next())
            {
                ans.add(parser.accept(r));
            }
        });
        
        return ans;
    }
    
    /**
     * Performs a select operation on a non prepared sql statement, returning a List.
     * @param sql The string containing the sql of the query
     * @param parser The function that parses each result of the query and returns a new object of type T.
     * @param <T> The type of data contained by the list.
     * @return A list containing all the records selected by the query.
     */
    public static <T> List<T> performSelect(String sql, ISQLResultParser<T> parser)
    {
        List<T> ans = new ArrayList<>();
        
        DBUtils.performOperation(conn -> {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            
            while (r.next())
            {
                ans.add(parser.accept(r));
            }
        });
        
        return ans;
    }
    
    /**
     * Begins a transaction on the current connection. 
     * Absolutely NOT thread safe. (not that anything else really is)
     */
    public static void beginTransaction()
    {
        try
        {
            conn.setAutoCommit(false);
        }catch (SQLException ex){
            //TODO
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Ends a transaction on the current connection.
     */
    public static void endTranstaction()
    {
        try
        {
            conn.commit();
            conn.setAutoCommit(true);
        }catch (SQLException ex){
            //TODO
            throw new RuntimeException(ex.getMessage());
        }
    }

}