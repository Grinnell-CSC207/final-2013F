import java.io.PrintWriter;

/**
 * Constants in JSON.
 */
public class JSONConstant implements JSONValue {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The name of the constant.
     */
    String name;

    // +-----------+-------------------------------------------------------
    // | Constants |
    // +-----------+

    /**
     * The value 'true'.
     */
    public static final JSONConstant TRUE = new JSONConstant("true");

    /**
     * The value 'false'.
     */
    public static final JSONConstant FALSE = new JSONConstant("false");

    /**
     * The value 'null'.
     */
    public static final JSONConstant NULL = new JSONConstant("null");

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Construct the constant with a particular name. Not intended for use
     * outside of this class.
     */
    private JSONConstant(String name) {
	this.name = name;
    } // JSONConstant

    // +--------+----------------------------------------------------------
    // | Output |
    // +--------+

    public void dump(PrintWriter pen, String indent) {
	// STUB
    } // dump(PrintWriter pen)

    // +-------------------------+-----------------------------------------
    // | Standard Object Methods |
    // +-------------------------+

    public int hashCode() {
	return this.name.hashCode();
    } // hashCode

    public String toString() {
	return "CONSTANT(" + this.name + ")";
    } // toString()
} // class JSONConstant

