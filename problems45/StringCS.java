/**
 * A sequence of characters implemented as a string.
 *
 * @author Samuel A. Rebelsky 
 */
public class StringCS implements CharSequence {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The underlying string.
     */
    String str;

    /**
     * The length of that string.
     */
    int len;

    /**
     * The position in the string.
     */
    int i;  

    /**
     * The line number.
     */
    int line;

    /**
     * The column within that line.
     */
    int col;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /** 
     * Create a new sequence from str.
     */
    public StringCS(String str) {
        this.str = str;
        this.len = str.length();
        this.i = 0;
        this.line = 1;
        this.col = 1;
    } // StringCS(String)

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    public boolean atEnd() {
        return this.i >= this.len;
    } // atEnd()

    public String info() {
        return this.line + "," + this.col;
    } // info()

    public int peek() {
        if (this.atEnd()) {
            return -1;
        } else {
            return this.str.charAt(this.i);
        } // not at end
    } // peek()

    // +----------+--------------------------------------------------------
    // | Mutators |
    // +----------+

    public void close() {
        // NOOP.
    } // close()

    public int next() {
        if (this.atEnd()) {
            return -1;
        } else {
            int ch = this.str.charAt(this.i++);
            if (ch == '\n') {
                ++this.line;
                this.col = 1;
            } else {
                ++this.col;
            } // if it's not a new line
            return ch;
        } // if !atEnd
    } // next()

    public void skipWhitespace() {
        while (!this.atEnd() && Character.isWhitespace((char) this.peek())) {
            this.next();
        } // while
    } // skipWhitespace
} // class StringCS
