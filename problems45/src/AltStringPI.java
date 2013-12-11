import java.io.BufferedReader;
import java.io.StringReader;

/**
 * Parsing information, taken from a string.  (A simpler alternative to 
 * StringPI.java)
 * 
 * @author Samuel A. Rebelsky
 */
public class AltStringPI extends ReaderPI {
    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new ParseInfo object that takes its input from a string.
     */
    public AltStringPI(String fname) throws Exception {
        super(new BufferedReader(new StringReader(fname)));
    } // AltStringPI(String)
} // class AltStringPI
