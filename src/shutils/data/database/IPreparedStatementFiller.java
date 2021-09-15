package shutils.data.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Describes a generic method that fills the parameters of a prepared statement
 */
public interface IPreparedStatementFiller {
    public void accept(PreparedStatement s) throws SQLException;
}