import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;

/**
 * A quick experiment with linked lists.
 *
 * @author Samuel A. Rebelsky
 * @author Anna
 * @author Tate
 * @author Your Name Here
 */
public class LLExpt {
    // +------+------------------------------------------------------------
    // | Main |
    // +------+

    public static void main(String[] args) throws Exception {
        LinkedList<String> list = new LinkedList<String>();
        PrintWriter pen = new PrintWriter(System.out, true);

        // Print the empty list.
        pen.println("Empty List");
        list.dump(pen);
        pen.println();

        // Build and print a simple list
        buildList(pen, list);

        // Annotate the list
        annotateEvens(pen, list);
        annotateTriplets(pen, list);
        annotateEvens(pen, list);
    } // main(String[])

    // +-------------+-----------------------------------------------------
    // | Experiments |
    // +-------------+

    /**
     * Build a list and then show it off.
     */
    public static void buildList(PrintWriter pen, LinkedList<String> list) 
            throws Exception {
        Cursor c = list.front();
        list.add(c, "a");
        list.advance(c);
        list.add(c, "b");
        list.advance(c);
        list.add(c, "c");
        list.advance(c);
        list.add(c, "d");
        list.advance(c);
        list.add(c, "e");
        pen.println("After adding a, b, c, d, e");
        list.dump(pen);
        pen.println();
    } // buildList()

    /**
     * Anntoate the items with their value mod 3
     */
    public static void annotateTriplets(PrintWriter pen, 
            LinkedList<String> list) throws Exception {
        int i = 0;
        Cursor c = list.front();
        while (!list.atEnd(c)) {
            list.setInfo(c, i++ % 3);
            list.advance(c);
        } // while
        pen.println("After annotating the triplets");
        list.dump(pen);
        pen.println();
    } // annotateTriplets

    /**
     * Annotate the items in even positions.
     */
    public static void annotateEvens(PrintWriter pen, LinkedList<String> list)
            throws Exception {
        int i = 0;
        Cursor c = list.front();
        while (!list.atEnd(c)) {
            if (i++ % 2 == 0) {
                list.setInfo(c, "even");
            } // if
            list.advance(c);
        } // while
        pen.println("After annotating the even elements");
        list.dump(pen);
        pen.println();
    } // annotateEvens

} // LLExpt
