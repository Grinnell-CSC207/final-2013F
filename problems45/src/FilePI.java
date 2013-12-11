import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Parsing information, taken from a file.
 * 
 * @author Samuel A. Rebelsky
 */
public class FilePI extends ReaderPI {
    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new object from a file.
     */
    public FilePI(String fname) throws Exception {
        super(new BufferedReader(new FileReader(fname)));
    } // FilePI(String)
} // class FilePI
