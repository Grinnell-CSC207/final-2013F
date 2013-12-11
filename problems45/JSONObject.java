import java.io.PrintWriter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * A simple representation of a JSON objects.
 */
public class JSONObject implements JSONValue {

    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The fields of the object.
     */
    Hashtable<String,JSONValue> fields;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create an empty objects.
     */
    public JSONObject() {
        this.fields = new Hashtable<String,JSONValue>();
    } // JSONObject

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    /**
     * Get the value associated with a particular field name.  Returns
     * null if there is no such value.
     */
    public JSONValue get(String field) {
        return fields.get(field);
    } // get(String)

    /**
     * Get an iterable for the keys.  (Why an iterable, rather than
     * an iterator?  Because I have a desire to write code like
     * <pre>
     *   for String key : obj.keys() {
     *       System.out.println(key + ": " + obj.get(key));
     *   } // for
     * </pre>
     */
    public Iterable<String> keys() {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    Enumeration keys = fields.keys();
                    public String next() {
                        return (String) keys.nextElement();
                    } // next()
                    public boolean hasNext() {
                        return keys.hasMoreElements();
                    } // hasNext()
                    public void remove() 
                            throws UnsupportedOperationException {
                        throw new UnsupportedOperationException();
                    } // remove()
                }; // new Iterator<String>
            } // iterator()
        }; // new Iterable<String>
    } // keys

    // +----------+--------------------------------------------------------
    // | Mutators |
    // +----------+

    /**
     * Set a field and its value.
     */
    public void set(String field, JSONValue value) {
        this.fields.put(field, value);
    } // set(String, JSONValue)

    // +--------+----------------------------------------------------------
    // | Output |
    // +--------+

    public void dump(PrintWriter pen, String indent) {
        // STUB
    } // dump(PrintWriter, String)

    // +-------------------------+-----------------------------------------
    // | Standard Object Methods |
    // +-------------------------+

    public int hashCode() {
        return this.fields.hashCode();
    } // hashCode()

    public String toString() {
        return "OBJECT(" + this.fields.toString() + ")";
    } // toString

} // JSONObject

