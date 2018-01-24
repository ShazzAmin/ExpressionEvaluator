/**
 * An operand (a fragment of an expression). Represents a <code>Number</code>.
 *
 * @author Shazz Amin
 * @version 1.0 2016-11-10
 */
public class Operand extends ExpressionFragment
{
	/* instance fields */
	private double value;

	/*
	 constructors
	*/

	public Operand(String symbol)
	{
		if (symbol == null) throw new IllegalArgumentException("cannot be null");

		try
		{
			this.value = Double.parseDouble(symbol);
		}
		catch (NumberFormatException exception)
		{
			throw new IllegalArgumentException("invalid symbol");
		}
	}

	public Operand(double value)
	{
		this.value = value;
	}

	/*
	 accessors
	*/

	public double getValue()
	{
		return value;
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
