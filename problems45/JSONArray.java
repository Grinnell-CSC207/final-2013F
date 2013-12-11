import java.io.PrintWriter;

import java.util.ArrayList;

/**
 * A simple array class for JSON.
 *
 * @author Samuel A. Rebelsky.
 */
public class JSONArray implements JSONValue {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The underlying array.
     */
    ArrayList<JSONValue> values;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Build a new, empty, array.
     */
    public JSONArray() {
        this.values = new ArrayList<JSONValue>();
    } // JSONArray

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    /**
     * Get the ith element.
     * 
     * @pre
     *    0 <= i < size()
     */
    JSONValue get(int i) throws IndexOutOfBoundsException {
        return this.values.get(i);
    } // get(int)

    /**
     * Get the number of elements in the array.
     */
    int size() {
        return this.values.size();
    } // size()

    // +----------+--------------------------------------------------------
    // | Mutators |
    // +----------+

    // +--------+----------------------------------------------------------
    // | Output |
    // +--------+

    public void dump(PrintWriter pen, String indent) {
        // STUB
    } // dump(PrintWriter pen)

    // +------------------+------------------------------------------------
    // | Standard Methods |
    // +------------------+

    public String toString() {
        return "ARRAY(" + this.values.toString() + ")";
    } // toString()

    public int hashCode() {
        return this.values.hashCode();
    } // hashCode()

} // class JSONArray
