package shutils.data.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Analyzes a single row of a <i>ResultSet</i> and converts it to a object
 * of type T. This method does not call <i>next()</i> on the ResultSet object.
 * @param <T> The type of the result
 */
public interface ISQLResultParser<T> {
    public T accept (ResultSet r) throws SQLException;
}