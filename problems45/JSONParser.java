/**
 * A simple mechanism for parsing JSON text.  Does not support numbers
 * other than integers.  May not support unicode.
 */
 public class JSONParser {

    // +----------------+--------------------------------------------------
    // | Public Methods |
    // +----------------+

    public static JSONValue parse(String str) throws Exception {
        return parse(new StringCS(str));
    } // JSONValue

    // +---------+---------------------------------------------------------
    // | Helpers |
    // +---------+

    /**
     * Give up on parsing.
     */
    static void giveup(CharSequence seq, String reason) throws Exception {
        throw new Exception(seq.info() + ": " + reason);
    } // giveup(CharSequence, String)

    /**
     * Parse the text remaining in a CharSequence.
     *
     * @throws Exception
     *   If it cannot successfully parse.
     */
    static JSONValue parse(CharSequence seq) throws Exception {
        seq.skipWhitespace();
        
        // Sanity check
        if (seq.atEnd()) {
            giveup(seq, "No JSON objects remain");
        } // if no characters remain

        // Decide what to do based on the next character
        switch (seq.peek()) {
            // Objects
            case '{':
                return parseObject(seq);
            case '[':
                return parseArray(seq);
            case '"':
                return parseString(seq);
            case 'f':
                return parseConstant(seq, "false", JSONConstant.FALSE);
            case 'n':
                return parseConstant(seq, "null", JSONConstant.NULL);
            case 't':
                return parseConstant(seq, "true", JSONConstant.TRUE);
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return parseNumber(seq);
            default:
                giveup(seq, "Invalid character: " + seq.peek());
                return null;
        } // switch
     } // charSequence

     /**
      * Parse an array.
      */
     public static JSONValue parseArray(CharSequence seq) throws Exception {
         // STUB (yeah, not a very small stub, but a stub nonetheless)

         // Sanity check
         if (seq.peek() != '[') {
             giveup(seq, "Failure to begin an array with open brace");
         } // if (seq.peek() != '[')
         JSONArray arr = new JSONArray();
         String start = seq.info();
         seq.next();
         int level = 1;
         while ((!seq.atEnd()) && (level != 0)) {
             switch (seq.next()) {
                  case '[':
                      ++level;
                      break;
                  case ']':
                      --level;
                      break;
                  default:
                      break;
             } // switch
         } // while

         // Did we finish the array?
         if (level != 0) {
              giveup(seq, "Array that begins at " + start + " not finished.");
         } // Unfinished array

         return arr;
     } // parseArray

    /**
     * See if the next set of characters in the sequence are a
     * known constant.  If not, throw an exception.
     */
    public static JSONConstant parseConstant(CharSequence seq, String name,
            JSONConstant constant) throws Exception {
        char ch;
        for (int i = 0; i < name.length(); i++) {
            if ((ch = (char) seq.next()) != name.charAt(i)) {
                giveup(seq, "Invalid character: " + ch);
            } // if character does not match
        } // for

        // We've gotten this far, it must be a match
        return constant;
    } // parseConstant(CharSequence, String, JSONConstant)

    /**
     * Parse a number.  
     */
    public static JSONNumber parseNumber(CharSequence seq) throws Exception {
        StringBuffer buf = new StringBuffer();
        char ch = (char) seq.peek();;

        // Assume that this is called with the appropriate next character
        buf.append((char) seq.next());

        // Special case: Negative sign.
        if ((ch == '-') && (!Character.isDigit(seq.peek()))) {
            giveup(seq, "Negative sign must be followed by a digit");
        } // special case  of negative sign with no digits.

        // Grab the initial set of digits.
        while (Character.isDigit(seq.peek())) {
            buf.append((char) seq.next());
        } // while

        // Is there a fractional portion?
        if (seq.peek() == '.') {
            buf.append((char) seq.next());
            if (!Character.isDigit(seq.peek())) {
                giveup(seq, "Decimal point must be followed by digit");
            } // if no followup digit
            while (Character.isDigit(seq.peek())) {
                buf.append((char) seq.next());
            } // while
        } // if (seq.peek == '.')

        // Is there an exponent portion?
        if ((seq.peek() == 'e') || (seq.peek() == 'E')) {
            buf.append((char) seq.next());
            if ((seq.peek() == '-') || (seq.peek() == '+')) {
                buf.append((char) seq.next());
            } // if +/-
            if (!Character.isDigit(seq.peek())) {
                giveup(seq, "e must be followed by a digit");
            } // if (!isDigit)
            while (Character.isDigit(seq.peek())) {
                buf.append((char) seq.next());
            } // while
        } // if exponent

        return new JSONNumber(buf.toString());
    } // parseNumber

    /**
     * Parse an object.
     */
    public static JSONObject parseObject(CharSequence seq) throws Exception {
        if (seq.peek() != '{') {
            giveup(seq, "Failure to begin an object with a {");
        } // if (seq.peek() != '[')
        JSONObject obj = new JSONObject();
        String start = seq.info();
        seq.next();
        seq.skipWhitespace();
        while ((!seq.atEnd()) && (seq.peek() != '}')) {
            JSONString key = parseString(seq);
            seq.skipWhitespace();
            if (seq.peek() != ':') {
                giveup(seq, "Missing : after key " + key.value() + 
                        " in object at " + start);
            } // if (if no colon)
            seq.next();
            seq.skipWhitespace();
            JSONValue value = parse(seq);
            obj.set(key.value(), value);
            seq.skipWhitespace();
            if (seq.peek() == ',') {
                seq.next();
                seq.skipWhitespace();
            } else if (seq.peek() == '}') {
                // Do nothing
            } else {
                giveup(seq, "Invalid character '" + seq.peek() + "' object");
            } // if invalid next character
        } // while
        if (seq.peek() != '}') {
            giveup(seq, "Failed to end object beginning at " + start);
        } // if no right brace
        seq.next();

        // And we're done
        return obj;
    } // parseObject

    /**
     * Parse a string.
     */
    public static JSONString parseString(CharSequence seq) throws Exception {
        // Sanity check
        if (seq.peek() != '"') {
            giveup(seq, "Failure to begin a string with a \": ");
        } // if (seq.peek() != '[')
       
        String start = seq.info();
        seq.next();
        StringBuffer buf = new StringBuffer();
        char ch = 0;

        while ((!seq.atEnd()) && ((ch = (char) seq.next()) != '"')) {
            if (ch != '\\') {
                buf.append(ch);
            } else { // Backslash case!
                // Sanity check
                if (seq.atEnd()) {
                    giveup(seq, "String that started at " + start + 
                            " ended with backslash-eof");
                } // if (seq.atEnd()

                // What appears after the backslash
                switch (ch = (char) seq.next()) {
                    case '"':
                    case '\\': 
                    case '/':
                        buf.append(ch);
                        break;
                    case 'b':
                        buf.append('\b');
                        break;
                    case 'f':
                        buf.append('\f');
                        break;
                    case 'n':
                        buf.append('\n');
                        break;
                    case 'r':
                        buf.append('\r');
                        break;
                    case 't':
                        buf.append('\t');
                        break;
                    case 'u':
                        StringBuffer number = new StringBuffer();
                        for (int i = 0; i < 4; i++) {
                            if (seq.atEnd()) {
                                giveup(seq, "Premature end in " +
                                      "\\u" + number);
                            } // if atend
                            number.append(seq.next());
                        } // for
                        buf.append((char) Integer.parseInt(number.toString(), 16));
                        break;
                    default:
                        giveup(seq, "Invalid escape sequence " + "\\" + ch);
                        break;
                } // switch
            } // if it's a backslash
        } // while
        if (ch != '"') {
            giveup(seq, "No end quote for string that begins at " + start);
        } // if no end quote
        return new JSONString(buf.toString());
    } // parseString
} // class JSONParser

