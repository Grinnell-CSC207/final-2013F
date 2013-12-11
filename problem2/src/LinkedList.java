import java.io.PrintWriter;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple linked lists.
 * 
 * @author Samuel A. Rebelsky
 * @author Anna
 * @author Tate
 * @author Your Name Here
 */
public class LinkedList<T> implements Iterable<T> {
    // +-------+-----------------------------------------------------------
    // | Notes |
    // +-------+

    /*
     * These are simple, singly-linked lists. Each node has a pointer to the
     * next node. For fun, convenience, or confusion, we a dummy node at the
     * front of the list that also serves as the end of the list.
     */

    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The front/back of the list.
     */
    NodeLL front;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new, empty, list.
     */
    public LinkedList() {
	this.clear();
    } // LinkedList

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    /**
     * Get a cursor right before the first element of the list.
     */
    public Cursor front() {
	return new CursorLL(front);
    } // front()

    /**
     * Get the value immediately following the cursor.
     */
    public T get(Cursor c) throws Exception {
	@SuppressWarnings("unchecked")
	CursorLL cll = (CursorLL) c;
	if (cll.current.next == front) {
	    throw new NoSuchElementException();
	} // if (cll.current.next == front)
	return cll.current.next.value;
    } // get(Cursor)

    /**
     * Get the information on the value immediately following the cursor.
     */
    public Object getInfo(Cursor c) throws Exception {
	@SuppressWarnings("unchecked")
	CursorLL cll = (CursorLL) c;
	if (cll.current.next == front) {
	    throw new NoSuchElementException();
	} // if (cll.current.next == front)
	return cll.current.next.info;
    } // get(Cursor)

    /**
     * Advance the cursor over one element.
     * 
     * @pre The cursor must have been created by front and not be at the end of
     *      the list.
     * @throws Exception
     *             If the cursor is at the end of the list.
     */
    public void advance(Cursor c) throws Exception {
	@SuppressWarnings("unchecked")
	CursorLL cll = (CursorLL) c;
	if (cll.current.next == front) {
	    throw new NoSuchElementException();
	} // if (cll.current.next == front)
	cll.current = cll.current.next;
    } // advance

    /**
     * Determine if the cursor is at the end of the list, with no next value.
     */
    public boolean atEnd(Cursor c) {
	@SuppressWarnings("unchecked")
	CursorLL cll = (CursorLL) c;
	return cll.current.next == front;
    } // atEnd(Cursor c)

    /**
     * Print the list.
     */
    public void dump(PrintWriter pen) {
	// Special case: Empty list
	// if (this.front.next == this.front) {
	if (false) {
	    pen.println("{}");
	} else {
	    // The list is nonempty
	    boolean started = false;
	    Cursor c = this.front();

	    pen.print("{ ");
	    while (!this.atEnd(c)) {
		if (!started) {
		    started = true;
		} else {
		    pen.print(", ");
		} // if/else
		this.dump(pen, c);
		try {
		    this.advance(c);
		} catch (Exception e) {
		    // We should never get an exception. But if we
		    // do, it's time to escape the loop.
		    break;
		} // try/catch
	    } // while
	    pen.println(" }");
	} // nonempty list
    } // dump(PrintWriter)

    /**
     * Print one value in the list.
     */
    public void dump(PrintWriter pen, Cursor c) {
	try {
	    pen.print(this.get(c));
	    Object info = this.getInfo(c);
	    if (info != null) {
		pen.print("(" + info + ")");
	    } // if there's information
	} catch (Exception e) {
	    pen.print("<invalid object>");
	} // try/catch
    } // dump(PrintWriter)

    // +----------+--------------------------------------------------------
    // | Mutators |
    // +----------+

    /**
     * Add a new element immediately after the cursor.
     */
    public void add(Cursor c, T val) {
	@SuppressWarnings("unchecked")
	CursorLL cll = (CursorLL) c;
	cll.current.next = new NodeLL(val, cll.current.next);
    } // add(Cursor, T)

    /**
     * Remove all elements from the list.
     * 
     * @post The list has no elements.
     * @post All iterators are invalid.
     */
    public void clear() {
	this.front = new NodeLL(null);
	this.front.next = front;
    } // clear()

    /**
     * Replace the value immediately after the cursor.
     */
    public void replace(Cursor c, T val) throws Exception {
	if (this.atEnd(c)) {
	    throw new Exception("Beyond end of list");
	} // if (this.atEnd(c))
	@SuppressWarnings("unchecked")
	CursorLL cll = (CursorLL) c;
	cll.current.next.value = val;
    } // replace(Cursor, T)

    /**
     * Set the info for the value immediately after the cursor
     */
    public void setInfo(Cursor c, Object info) throws Exception {
	if (this.atEnd(c)) {
	    throw new Exception("Beyond end of list");
	} // if (this.atEnd(c))
	@SuppressWarnings("unchecked")
	CursorLL cll = (CursorLL) c;
	cll.current.next.info = info;
    } // setInfo(Cursor, Object)

    // +------------+------------------------------------------------------
    // | Annotators |
    // +------------+

    /**
     * Mark every node with its distance to the end of the list.
     */
    public void computeDistances() {
	// STUB
    } // computeDistances()

    // +-----------+-------------------------------------------------------
    // | Iterators |
    // +-----------+

    public Iterator<T> iterator() {
	return new Iterator<T>() {
	    NodeLL here = new NodeLL(null, front);

	    public T next() {
		if (!this.hasNext()) {
		    throw new NoSuchElementException();
		} // if we've reached the end
		this.here = this.here.next;
		return this.here.next.value;
	    } // next

	    public boolean hasNext() {
		return (this.here.next.next != front);
	    } // hasNext

	    public void remove() throws UnsupportedOperationException {
		// The next element is the thing we've just returned, so skip
		// over it.
		this.here.next = this.here.next.next;
		this.here = new NodeLL(null, this.here);
	    } // remove
	}; // new Iterator<T>
    } // iterator

    // +---------------+---------------------------------------------------
    // | Inner Classes |
    // +---------------+

    /**
     * Nodes in the list.
     */
    class NodeLL {
	/**
	 * The value in the node.
	 */
	T value;

	/**
	 * Additional information on the node.
	 */
	Object info;

	/**
	 * The next element in the list.
	 */
	NodeLL next;

	/**
	 * Construct a new node with no successor.
	 */
	public NodeLL(T val) {
	    this(val, null);
	} // NodeLL(T)

	/**
	 * Construct a new node with a specified successor.
	 */
	public NodeLL(T value, NodeLL next) {
	    this.value = value;
	    this.next = next;
	    this.info = null;
	} // NodeLL(T, NodeLL)
    } // class NodeLL

    /**
     * Cursors for the linked list.
     */
    class CursorLL implements Cursor {
	/**
	 * The node that *precedes* the node of interest.
	 */
	NodeLL current;

	/**
	 * Create a new cursor.
	 */
	public CursorLL(NodeLL current) {
	    this.current = current;
	} // CursorLL(NodeLL)
    } // class CursorLL

} // LinkedList<T>

