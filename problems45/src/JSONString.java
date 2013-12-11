import java.io.PrintWriter;

/**
 * A string in JSON.
 */
public class JSONString implements JSONValue {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The underlying string.
     */
    public String str;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Build a new string.
     */
    public JSONString(String str) {
	this.str = str;
    } // JSONString(String)

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    /**
     * Get the underlying string.
     */
    String value() {
	return str;
    } // value()

    // +--------+----------------------------------------------------------
    // | Output |
    // +--------+

    public void dump(PrintWriter pen, String indent) {
	pen.print(indent);
	pen.println(str);
    } // dump(PrintWriter pen)

    // +-------------------------+-----------------------------------------
    // | Standard Object Methods |
    // +-------------------------+

    public String toString() {
	return "STRING(" + str + ")";
    } // toString

    public int hashCode() {
	return str.hashCode();
    } // hashCode
} // JSONString
