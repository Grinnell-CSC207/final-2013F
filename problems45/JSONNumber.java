import java.io.PrintWriter;

/**
 * A number in JSON.
 */
public class JSONNumber implements JSONValue {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The underlying value.
     */
    Double value;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new number given a string represntation.
     */
    public JSONNumber(String str) {
        try {
            this.value = new Double(str);
        } catch (Exception e) {
            this.value = Double.NaN;
        } // try/catch
    } // JSONNumber(String)

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    /**
     * Get the numeric value.
     */
    public double doubleValue() {
        return (double) this.value;
    } // doubleValue()

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
        return this.value.hashCode();
    } // hashCode()

    public String toString() {
        return "NUMBER(" + this.value + ")";
    } // toString()

} // JSONNumber
