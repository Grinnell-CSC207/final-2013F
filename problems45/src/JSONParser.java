/**
 * A simple mechanism for parsing JSON text. Does not support numbers other than
 * integers. May not support unicode.
 */
public class JSONParser {

    // +----------------+--------------------------------------------------
    // | Public Methods |
    // +----------------+

    public static JSONValue parse(String str) throws Exception {
	return parse(new StringPI(str));
    } // JSONValue

    // +---------+---------------------------------------------------------
    // | Helpers |
    // +---------+

    /**
     * Give up on parsing.
     */
    static void giveup(ParseInfo info, String reason) throws Exception {
	throw new Exception(info.info() + ": " + reason);
    } // giveup(ParseInfo, String)

    /**
     * Parse the text remaining in a ParseInfo.
     * 
     * @throws Exception
     *             If it cannot successfully parse.
     */
    static JSONValue parse(ParseInfo info) throws Exception {
	info.skipWhitespace();

	// Sanity check
	if (info.atEnd()) {
	    giveup(info, "No JSON objects remain");
	} // if no characters remain

	// Decide what to do based on the next character
	switch (info.peek()) {
	// Objects
	case '{':
	    return parseObject(info);
	case '[':
	    return parseArray(info);
	case '"':
	    return parseString(info);
	case 'f':
	    return parseConstant(info, "false", JSONConstant.FALSE);
	case 'n':
	    return parseConstant(info, "null", JSONConstant.NULL);
	case 't':
	    return parseConstant(info, "true", JSONConstant.TRUE);
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
	    return parseNumber(info);
	default:
	    giveup(info, "Invalid character: " + info.peek());
	    return null;
	} // switch
    } // charSequence

    /**
     * Parse an array.
     */
    public static JSONValue parseArray(ParseInfo info) throws Exception {
	// STUB (yeah, not a very small stub, but a stub nonetheless)

	// Sanity check
	if (info.peek() != '[') {
	    giveup(info, "Failure to begin an array with open brace");
	} // if (info.peek() != '[')
	JSONArray arr = new JSONArray();
	String start = info.info();
	info.next();
	int level = 1;
	while ((!info.atEnd()) && (level != 0)) {
	    switch (info.next()) {
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
	    giveup(info, "Array that begins at " + start + " not finished.");
	} // Unfinished array

	return arr;
    } // parseArray

    /**
     * See if the next set of characters in the infouence are a known constant.
     * If not, throw an exception.
     */
    public static JSONConstant parseConstant(ParseInfo info, String name,
	    JSONConstant constant) throws Exception {
	char ch;
	for (int i = 0; i < name.length(); i++) {
	    if ((ch = (char) info.next()) != name.charAt(i)) {
		giveup(info, "Invalid character: " + ch);
	    } // if character does not match
	} // for

	// We've gotten this far, it must be a match
	return constant;
    } // parseConstant(ParseInfo, String, JSONConstant)

    /**
     * Parse a number.
     */
    public static JSONNumber parseNumber(ParseInfo info) throws Exception {
	StringBuffer buf = new StringBuffer();
	char ch = (char) info.peek();
	;

	// Assume that this is called with the appropriate next character
	buf.append((char) info.next());

	// Special case: Negative sign.
	if ((ch == '-') && (!Character.isDigit(info.peek()))) {
	    giveup(info, "Negative sign must be followed by a digit");
	} // special case of negative sign with no digits.

	// Grab the initial set of digits.
	while (Character.isDigit(info.peek())) {
	    buf.append((char) info.next());
	} // while

	// Is there a fractional portion?
	if (info.peek() == '.') {
	    buf.append((char) info.next());
	    if (!Character.isDigit(info.peek())) {
		giveup(info, "Decimal point must be followed by digit");
	    } // if no followup digit
	    while (Character.isDigit(info.peek())) {
		buf.append((char) info.next());
	    } // while
	} // if (info.peek == '.')

	// Is there an exponent portion?
	if ((info.peek() == 'e') || (info.peek() == 'E')) {
	    buf.append((char) info.next());
	    if ((info.peek() == '-') || (info.peek() == '+')) {
		buf.append((char) info.next());
	    } // if +/-
	    if (!Character.isDigit(info.peek())) {
		giveup(info, "e must be followed by a digit");
	    } // if (!isDigit)
	    while (Character.isDigit(info.peek())) {
		buf.append((char) info.next());
	    } // while
	} // if exponent

	return new JSONNumber(buf.toString());
    } // parseNumber

    /**
     * Parse an object.
     */
    public static JSONObject parseObject(ParseInfo info) throws Exception {
	if (info.peek() != '{') {
	    giveup(info, "Failure to begin an object with a {");
	} // if (info.peek() != '[')
	JSONObject obj = new JSONObject();
	String start = info.info();
	info.next();
	info.skipWhitespace();
	while ((!info.atEnd()) && (info.peek() != '}')) {
	    JSONString key = parseString(info);
	    info.skipWhitespace();
	    if (info.peek() != ':') {
		giveup(info, "Missing : after key " + key.value()
			+ " in object at " + start);
	    } // if (if no colon)
	    info.next();
	    info.skipWhitespace();
	    JSONValue value = parse(info);
	    obj.set(key.value(), value);
	    info.skipWhitespace();
	    if (info.peek() == ',') {
		info.next();
		info.skipWhitespace();
	    } else if (info.peek() == '}') {
		// Do nothing
	    } else {
		giveup(info, "Invalid character '" + info.peek() + "' object");
	    } // if invalid next character
	} // while
	if (info.peek() != '}') {
	    giveup(info, "Failed to end object beginning at " + start);
	} // if no right brace
	info.next();

	// And we're done
	return obj;
    } // parseObject

    /**
     * Parse a string.
     */
    public static JSONString parseString(ParseInfo info) throws Exception {
	// Sanity check
	if (info.peek() != '"') {
	    giveup(info, "Failure to begin a string with a \": ");
	} // if (info.peek() != '[')

	String start = info.info();
	info.next();
	StringBuffer buf = new StringBuffer();
	char ch = 0;

	while ((!info.atEnd()) && ((ch = (char) info.next()) != '"')) {
	    if (ch != '\\') {
		buf.append(ch);
	    } else { // Backslash case!
		     // Sanity check
		if (info.atEnd()) {
		    giveup(info, "String that started at " + start
			    + " ended with backslash-eof");
		} // if (info.atEnd()

		// What appears after the backslash
		switch (ch = (char) info.next()) {
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
			if (info.atEnd()) {
			    giveup(info, "Premature end in " + "\\u" + number);
			} // if atend
			number.append(info.next());
		    } // for
		    buf.append((char) Integer.parseInt(number.toString(), 16));
		    break;
		default:
		    giveup(info, "Invalid escape infouence " + "\\" + ch);
		    break;
		} // switch
	    } // if it's a backslash
	} // while
	if (ch != '"') {
	    giveup(info, "No end quote for string that begins at " + start);
	} // if no end quote
	return new JSONString(buf.toString());
    } // parseString
} // class JSONParser

