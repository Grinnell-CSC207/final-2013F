/**
 * A sequence of characters.
 */
public interface CharSequence {
    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    /**
     * Determine if we're at the end of the sequence (that is, no
     * characters remain).
     */
    public boolean atEnd();

    /**
     * Get a human-readable string with helpful information about where 
     * in the sequence we are.
     */
    public String info();

    /**
     * Look at the next character in the sequence, but do not advance
     * the sequence.  Returns -1 if no characters remain in the sequence.
     */
    public int peek();

    // +----------+--------------------------------------------------------
    // | Mutators |
    // +----------+

    /**
     * Close any resources associated with the sequence.
     */
    public void close();

    /**
     * Get the next character in the sequence, advancing beyond that
     * character.  Returns -1 if no characters remain in the sequence.
     */
    public int next();

    /**
     * Skip over any whitespace.
     */
    public void skipWhitespace();
} // interface CharSequence
