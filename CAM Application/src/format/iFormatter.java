package format;

/**
 * Formats an object into a more readable String for printing purposes.
 */
public interface iFormatter<T> {
    /**
     * Formats an object into a String containing the full list of information of that object.
     * @param t The object.
     * @return the String containing information of the object. 
     */
    public String formatFull(T t);
}
