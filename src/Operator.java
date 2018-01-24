/**
 * An operator (a fragment of an expression). Represents any one of the following operations:
 * <table>
 * <tr><th>Operation</th><th>Symbol</th><th>Precedence</th><th>Associativity</th></tr>
 * <tr><td>Exponentiation</td> <td><code>^</code></td> <td>1</td> <td>Right</td></tr>
 * <tr><td>Multiplication</td> <td><code>*</code></td> <td>2</td> <td>Left</td></tr>
 * <tr><td>Division</td> <td><code>/</code></td> <td>2</td> <td>Left</td></tr>
 * <tr><td>Modulus</td> <td><code>%</code></td> <td>2</td> <td>Left</td></tr>
 * <tr><td>Addition</td> <td><code>+</code></td> <td>3</td> <td>Left</td></tr>
 * <tr><td>Subtraction</td> <td><code>-</code></td> <td>3</td> <td>Left</td></tr>
 * </table>
 *
 * @author Shazz Amin
 * @version 1.0 2016-11-10
 */
public class Operator extends ExpressionFragment implements Comparable<Operator>
{
	/* class variables */
	/**
	 * The symbol for exponentiation.
	 */
	public static final String EXPONENTIATION = "^";
	/**
	 * The symbol for multiplication.
	 */
	public static final String MULTIPLICATION = "*";
	/**
	 * The symbol for division.
	 */
	public static final String DIVISION = "/";
	/**
	 * The symbol for modulus.
	 */
	public static final String MODULUS = "%";
	/**
	 * The symbol for addition.
	 */
	public static final String ADDITION = "+";
	/**
	 * The symbol for subtraction.
	 */
	public static final String SUBTRACTION = "-";

	private static final boolean EXPONENTIATION_ISLEFTASSOCIATIVE = false;
	private static final int EXPONENTIATION_PRECEDENCE = 1;
	private static final boolean MULTIPLICATION_ISLEFTASSOCIATIVE = true;
	private static final int MULTIPLICATION_PRECEDENCE = 2;
	private static final boolean DIVISION_ISLEFTASSOCIATIVE = true;
	private static final int DIVISION_PRECEDENCE = 2;
	private static final boolean MODULUS_ISLEFTASSOCIATIVE = true;
	private static final int MODULUS_PRECEDENCE = 2;
	private static final boolean ADDITION_ISLEFTASSOCIATIVE = true;
	private static final int ADDITION_PRECEDENCE = 3;
	private static final boolean SUBTRACTION_ISLEFTASSOCIATIVE = true;
	private static final int SUBTRACTION_PRECEDENCE = 3;

	/* instance fields */
	private boolean isLeftAssociative;
	private int precedence;
	private String symbol;

	/*
	 constructors
	*/

	/**
	 * Constructs an operator represented by the specified symbol.
	 *
	 * @param symbol the symbol representing this operator;<br>
	 * <i>pre-condition:</i> must be equal to one of the class variables
	 */
	public Operator(String symbol)
	{
		if (symbol == null) throw new IllegalArgumentException("cannot be null");

		if (symbol.equals(EXPONENTIATION))
		{
			this.isLeftAssociative = EXPONENTIATION_ISLEFTASSOCIATIVE;
			this.precedence = EXPONENTIATION_PRECEDENCE;
		}
		else if (symbol.equals(MULTIPLICATION))
		{
			this.isLeftAssociative = MULTIPLICATION_ISLEFTASSOCIATIVE;
			this.precedence = MULTIPLICATION_PRECEDENCE;
		}
		else if (symbol.equals(DIVISION))
		{
			this.isLeftAssociative = DIVISION_ISLEFTASSOCIATIVE;
			this.precedence = DIVISION_PRECEDENCE;
		}
		else if (symbol.equals(MODULUS))
		{
			this.isLeftAssociative = MODULUS_ISLEFTASSOCIATIVE;
			this.precedence = MODULUS_PRECEDENCE;
		}
		else if (symbol.equals(ADDITION))
		{
			this.isLeftAssociative = ADDITION_ISLEFTASSOCIATIVE;
			this.precedence = ADDITION_PRECEDENCE;
		}
		else if (symbol.equals(SUBTRACTION))
		{
			this.isLeftAssociative = SUBTRACTION_ISLEFTASSOCIATIVE;
			this.precedence = SUBTRACTION_PRECEDENCE;
		}
		else
		{
			throw new IllegalArgumentException("invalid symbol");
		}

		this.symbol = symbol;
	}

	/*
	 accessors
	*/

	/**
	 * Compares this operator with the specified operator with respect to precedence.
	 *
	 * @param that the operator to compare this operator to
	 * @return a negative integer if the specified operator has greater precedence;
	 * <code>0</code> if they have equal precedence; a positive integer otherwise
	 */
	public int compareTo(Operator that)
	{
		return that.precedence - this.precedence;
	}

	/**
	 * Returns the symbol that represents this operator.
	 *
	 * @return the symbol that represents this operator
	 */
	public String getSymbol()
	{
		return symbol;
	}

	/**
	 * Returns whether this operator is left associative.
	 *
	 * @return <code>true</code> if this operator is left associative;
	 * <code>false</code> otherwise
	 */
	public boolean isLeftAssociative()
	{
		return isLeftAssociative;
	}

	/**
	 * Returns a string representation of this operator.
	 *
	 * @return a string representing this operator
	 */
	public String toString()
	{
		return
			getClass().getName()
			+ "["
			+ "symbol: " + symbol
			+ "]";
	}

	/*
	 instance methods
	*/

	/**
	 * Applies this operator on the specified operands.
	 *
	 * @param left the operand to place to the left of this operator
	 * @param right the operand to place to the right of this operator
	 * @return the result after applying this operator
	 */
	public Operand apply(Operand left, Operand right)
	{
		switch (symbol)
		{
			case EXPONENTIATION:
				return new Operand(Math.pow(left.getValue(), right.getValue()));

			case MULTIPLICATION:
				return new Operand(left.getValue() * right.getValue());

			case DIVISION:
				return new Operand(left.getValue() / right.getValue());

			case MODULUS:
				return new Operand(left.getValue() % right.getValue());

			case ADDITION:
				return new Operand(left.getValue() + right.getValue());

			case SUBTRACTION:
				return new Operand(left.getValue() - right.getValue());
		}

		return null;
	}

	/*
	 static methods
	*/

	// TODO: implement and document
	public static boolean isValidSymbol(String symbol)
	{
		return true;
	}
}
