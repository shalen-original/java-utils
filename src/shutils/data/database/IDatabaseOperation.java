package shutils.data.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Describes a generic database operation
 */
public interface IDatabaseOperation {   
    public void accept(Connection p1) throws SQLException;
}