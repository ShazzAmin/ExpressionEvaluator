/**
 * A close bracket (a fragment of an expression) reprented by ")" or "]".
 *
 * @author Shazz Amin
 * @version 1.0 2016-12-01
 */
class CloseBracket extends ExpressionFragment
{
    /*
     constructors
    */

    public CloseBracket(String symbol)
    {
        if (symbol == null) throw new IllegalArgumentException("cannot be null");

        if (!(symbol.equals(")") || symbol.equals("]"))) throw new IllegalArgumentException("invalid symbol");
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
