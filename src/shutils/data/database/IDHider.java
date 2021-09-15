package shutils.data.database;

/**
 * Allows to hide an id field with a display name, while still being able to access the hidden field.
 * @param <T> The type of the field to hide.
 */
public class IDHider <T> {

    private T hidden;
    private String displayName;

    public IDHider(T hiddenField, String displayName) {
        this.hidden = hiddenField;
        this.displayName = displayName;
    }

    public T getHidden() {
        return hidden;
    }

    public void setHidden(T hidden) {
        this.hidden = hidden;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
    
    
    
}