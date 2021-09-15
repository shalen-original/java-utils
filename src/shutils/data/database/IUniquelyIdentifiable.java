package shutils.data.database;

/**
 * Describes a uniquely identifiable object, that is, an object which exposes
 * a unique id. This unique id should also be immutable.
 */
public interface IUniquelyIdentifiable {

    public int getId();
    
}