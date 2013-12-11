import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A quick experiment with binary search trees.
 *
 * @author Samuel A. Rebelsky
 * @author Anna
 * @author Tate
 * @author Your Name Here
 */
public class BSTExpt {
    // +-----------+-------------------------------------------------------
    // | Constants |
    // +-----------+

    /**
     * Our lovely string comparator.
     */
    public static final Comparator<String> COMPARE_STRINGS =
             new Comparator<String>() {
                 public int compare(String left, String right) {
                     return left.compareTo(right);
                 } // compare(String, String)
             }; // new Comparator

    // +------+------------------------------------------------------------
    // | Main |
    // +------+

    public static void main(String[] args) throws Exception {
        BST<String,String> tree = new BST<String,String>(COMPARE_STRINGS);
        PrintWriter pen = new PrintWriter(System.out, true);

        // Print the empty tree
        dump(pen, tree, "Empty tree");

        // Annotate the empty tree
        tree.annotate("first annotation");
        dump(pen, tree, "Annotated empty tree");

        // Build and print a simple tree
        buildTree(tree);
        dump(pen, tree, "Sample tree");

        // Annotate the tree.
        tree.annotate("note");
        dump(pen, tree, "Annotated sample tree");

        // Extend the tree with a few more values
        extendTree(tree);
        dump(pen, tree, "Extended tree");

        // Re-annotate the tree
        tree.annotate("new annotation");
        dump(pen, tree, "Re-annotated");
    } // main(String[])

    // +---------+---------------------------------------------------------
    // | Helpers |
    // +---------+

    /**
     * Build a tree and then show it off.
     */
    public static void buildTree(BST<String,String> tree) throws Exception {
        tree.set("g", "gorilla");
        tree.set("d", "dingo");
        tree.set("a", "aardvark");
        tree.set("b", "baboon");
        tree.set("y", "yak");
        tree.set("h", "hippo");
        tree.set("k", "koala");
    } // buildTree()

    /**
     * Dump a tree with a message.
     */
    public static void dump(PrintWriter pen, BST tree, String message) {
         pen.println(message);
         tree.dump(pen);
         pen.println();
    } // dump

    /**
     * Extend a tree.
     */
    public static void extendTree(BST<String,String> tree) throws Exception {
        tree.set("c", "chinchilla");
        tree.set("g", "gnu");
        tree.set("z", "zorilla");
    } // extendTree(BST<String,STring>)

} // BSTExpt
