import java.io.PrintWriter;

/**
 * A simple test of my parser.
 *
 * @author Samuel A. Rebelsky
 */
public class ParserExpt {
    // +------+------------------------------------------------------------
    // | Main |
    // +------+
  
    public static void main(String[] args) {
        PrintWriter pen = new PrintWriter(System.out, true);

        // A few strings, some valid, some invalid
        // expt(pen, "\"hello\"");
        // expt(pen, "\"goodbye");
        // expt(pen, "\"\\n\"");

        // A few arrays
        // expt(pen, "[]");
        // expt(pen, "[1,2,3]");
        // expt(pen, "[\"hello\", \"goodbye\"]");

        // A few constants
        // expt(pen, "true");
        // expt(pen, "false");
        // expt(pen, "null");

        // A few numbers
        // expt(pen, "123");
        // expt(pen, "123.45");
        // expt(pen, "-11");
        // expt(pen, "3e3");
        // expt(pen, "-1.2e-3");

        // A few simple objects
        // expt(pen, "{}");
        // expt(pen, "{ }");
        // expt(pen, "{\"name\":\"sam\"}");
        // expt(pen, "{\"name\" \"sam\"}");

        // A few more complex objects
        expt(pen, "{\"name\":\"sam\",\n \"title\":\"prof\", \"alive\":true}");
        expt(pen, "{\"name\":\"sam\", \"job\":{\"employer\":\"Grinnell\", \"title\":\"Professor\"}, \"alive\":true}");
        expt(pen, "{\"name\":\"sam\", \"grades\":{\"exam1\":80,\"exam2\":85,\"final\":100},\"letter\":\"B\"}");
    } // main(String[])

    // +---------+---------------------------------------------------------
    // | Helpers |
    // +---------+

    public static void expt(PrintWriter pen, String json) {
        pen.println(json + " => ");
        try {
            JSONValue val = JSONParser.parse(json);
            pen.println("  " + val.toString());
            val.dump(pen, "----");
        } catch (Exception e) {
            pen.println("  " + e.toString());
        } // try/catch
        pen.println();
    } // expt(String)
} // ParserExpt
