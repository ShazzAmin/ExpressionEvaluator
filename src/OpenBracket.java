/**
 * An open bracket (a fragment of an expression) reprented by "(" or "[".
 *
 * @author Shazz Amin
 * @version 1.0 2016-12-01
 */
class OpenBracket extends ExpressionFragment
{
    /*
     constructors
    */

    public OpenBracket(String symbol)
    {
        if (symbol == null) throw new IllegalArgumentException("cannot be null");
        
        if (!(symbol.equals("(") || symbol.equals("["))) throw new IllegalArgumentException("invalid symbol");
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
