import java.io.BufferedReader;

/**
 * Parsing information, taken from a buffered reader.
 * 
 * @author Samuel A. Rebelsky
 */
public class ReaderPI implements ParseInfo {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The underlying reader
     */
    BufferedReader reader;

    /**
     * A buffer of the next character read.
     */
    int buf;

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
     * Set up parsing information, with input taken from a reader.
     */
    public ReaderPI(BufferedReader reader) {
	this.reader = reader;
	updateBuffer();
	this.line = 1;
	this.col = 1;
    } // ReaderPI(String)

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    public boolean atEnd() {
	return this.buf == -1;
    } // atEnd()

    public String info() {
	return this.line + "," + this.col;
    } // info()

    public int peek() {
	return this.buf;
    } // peek()

    // +----------+--------------------------------------------------------
    // | Mutators |
    // +----------+

    public void close() {
	try {
	    reader.close();
	} catch (Exception e) {
	    // If we can't close it, don't do anything.
	} // try/catch
    } // close()

    public int next() {
	if (this.atEnd()) {
	    return -1;
	} else {
	    int ch = this.buf;
	    if (ch == '\n') {
		++this.line;
		this.col = 1;
	    } else {
		++this.col;
	    } // if it's not a new line
	    updateBuffer();
	    return ch;
	} // if !atEnd
    } // next()

    public void skipWhitespace() {
	while (!this.atEnd() && Character.isWhitespace((char) this.peek())) {
	    this.next();
	} // while
    } // skipWhitespace

    // +---------+---------------------------------------------------------
    // | Helpers |
    // +---------+

    /**
     * Update the buffer with the next input character.
     */
    void updateBuffer() {
	try {
	    this.buf = reader.read();
	} catch (Exception e) {
	    this.buf = -1;
	} // try/catch
    } // updateBuffer()

} // class ReaderPI
