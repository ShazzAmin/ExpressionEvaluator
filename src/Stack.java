import java.util.NoSuchElementException;

/**
 * A fixed-size stack (abstract data structure).
 *
 * @author Shazz Amin
 * @version 1.0 2016-11-10
 */
public class Stack<T>
{
	/* class variables */
	/**
	 * The default size.
	 */
	public static final int DEFAULT_SIZE = 10;

	private static final int EMPTY_STACK = -1;

	/* instance fields */
	private final Object[] element;
	private int topIndex;

	/*
	 constructors
	*/

	/**
	 * Constructs an empty stack with the {@link #DEFAULT_SIZE default size}.
	 */
	public Stack()
	{
		element = new Object[DEFAULT_SIZE];
		topIndex = EMPTY_STACK;
	}

	/**
	 * Constructs an empty stack with the specified size.
	 *
	 * @param size the size;<br><i>pre-condition:</i> must be positive
	 */
	public Stack(int size)
	{
		if (size <= 0) size = DEFAULT_SIZE;

		element = new Object[size];
		topIndex = EMPTY_STACK;
	}

	/*
	 accessors
	*/

	/**
	 * Returns the number of elements in this stack.
	 *
	 * @return the number of elements in this stack
	 */
	public int getNumberOfElements()
	{
		return topIndex + 1;
	}

	/**
	 * Returns the size of this stack.
	 *
	 * @return the size of this stack
	 */
	public int getSize()
	{
		return element.length;
	}

	/**
	 * Returns <code>true</code> if this stack is empty; <code>false</code>
	 * otherwise.
	 *
	 * @return <code>true</code> if this stack is empty; <code>false</code>
	 * otherwise
	 */
	public boolean isEmpty()
	{
		/*
		 This stack is empty if and only if topIndex is not pointing to any element of the array.
		*/
		return topIndex == EMPTY_STACK;
	}

	/**
	 * Returns <code>true</code> if this stack is full; <code>false</code>
	 * otherwise.
	 *
	 * @return <code>true</code> if this stack is full; <code>false</code>
	 * otherwise
	 */
	public boolean isFull()
	{
		/*
		 This stack is full if and only if topIndex is pointing to the last
		 element of the array. element.length - 1 represents the index of the
		 last element of the array.
		*/
		return topIndex == element.length - 1;
	}

	/**
	 * Returns the top-most element in this stack.<br>
	 * <i>pre-condition:</i> this stack is not empty
	 *
	 * @return the top-most element in this stack
	 * @throws NoSuchElementException if this stack is empty
	 */
	@SuppressWarnings("unchecked")
	public T peek()
	{
		// If this stack is empty, there is no element to return.
		if (isEmpty()) throw new NoSuchElementException("this stack is empty");

		return (T) element[topIndex];
	}

	/**
	 * Returns a string representation of this stack.
	 *
	 * @return a string representing this stack
	 */
	public String toString()
	{
		/*
		 Loop through the array and build a comma-separated list of its elements.
		*/
		String elementList = "";
		for (int i = 0; i < element.length; i++)
		{
			// Append this element to the string.
			elementList += element[i];
			/*
			 If this is not the last iteration of the loop (i.e. this is not the
			 last element of the array), append a comma and a space to the string.
			*/
			if (i < element.length - 1) elementList += ", ";
		}

		return
			getClass().getName()
			+ "["
			+ "element: [" + elementList + "]"
			+ ", topIndex: " + topIndex
			+ "]";
	}

	/*
	 mutators
	*/

	/**
	 * Removes the top-most element from this stack and returns it.<br>
	 * <i>pre-condition</i>: this stack is not empty
	 *
	 * @return the element which was removed
	 * @throws NoSuchElementException if this stack is empty
	 */
	@SuppressWarnings("unchecked")
	public T pop()
	{
		// If the stack is empty, there is no element to remove.
		if (isEmpty()) throw new NoSuchElementException("this stack is empty");

		// Return the top-most element in this stack and decrement topIndex by 1.
		return (T) element[topIndex--];
	}

	/**
	 * Adds the specified element to the top of this stack.<br>
	 * <i>pre-condition</i>: this stack is not full
	 *
	 * @param element the new element
	 * @return <code>false</code> if this stack is full;
	 * <code>true</code> otherwise
	 */
	public boolean push(T element)
	{
		// If this stack is full, a new element cannot be added.
		if (isFull()) return false;

		// Increment topIndex and set the top-most element to the one specified.
		this.element[++topIndex] = element;
		return true;
	}
}
