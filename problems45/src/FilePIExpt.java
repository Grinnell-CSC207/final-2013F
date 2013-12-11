import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * A quick and silly experiment with the FilePI class. Dump a file with the
 * position every few characters.
 */
public class FilePIExpt {
    public static void main(String[] args) throws Exception {
	PrintWriter pen = new PrintWriter(System.out, true);
	FilePI info = new FilePI("FilePI.java");

	int i = 0;
	while (!info.atEnd()) {
	    pen.print((char) info.next());
	    if ((i++ % 5) == 0) {
		pen.print(" (" + info.info() + ") ");
	    } // if
	} // while
	info.close();
	pen.close();
    } // main(String[])
} // class FilePIExpt
