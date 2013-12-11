import java.io.PrintWriter;

/**
 * A quick experiment with StringCS.
 */
public class StringCSExpt {
    public static void main(String[] args) {
        PrintWriter pen = new PrintWriter(System.out, true);
        StringCS seq = new StringCS("Hello\nWorld\nHow are you today?");
        int i = 0;
        while (!seq.atEnd()) {
            pen.print((char) seq.next());
            if ((i++ % 5) == 0) {
                pen.print(" (" + seq.info() + ") ");
            } // if
        } // while
        seq.close();
        pen.close();
    } // main(String[])
} // class StringCSExpt
