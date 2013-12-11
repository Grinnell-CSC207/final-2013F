import java.io.PrintWriter;

/**
 * A way to unify the various kinds of JSON values.
 */
public interface JSONValue {
    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    /**
     * Print the value using a specified indent (prefix).
     */
    public void dump(PrintWriter pen, String indent);
} // interface JSONValue
