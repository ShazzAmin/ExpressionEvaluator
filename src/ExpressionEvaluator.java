import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * An evaluator for mathematical expressions. Supports postfix, prefix and infix notation.
 *
 * @author Shazz Amin
 * @version 1.0 2016-11-10
 */
public class ExpressionEvaluator
{
	/* class variables */
	private static final int NOTATION_NONE = 0;
	private static final int NOTATION_INFIX = 1;
	private static final int NOTATION_POSTFIX = 2;
	private static final int NOTATION_PREFIX = 3;

	/*
	 static methods
	*/

	private static Stack<ExpressionFragment> convertExpressionToStack(String expression) throws InvalidExpressionException
	{
		String[] array = expression.trim().split("\\s+");
		Stack<ExpressionFragment> stack = new Stack<ExpressionFragment>(array.length);
		for (int i = 0; i < array.length; i++)
		{
			// TODO: refactor
			try
			{
				stack.push(new Operator(array[i]));
			}
			catch (IllegalArgumentException exception)
			{
				try
				{
					stack.push(new Operand(array[i]));
				}
				catch (IllegalArgumentException exception2)
				{
					try
					{
						stack.push(new OpenBracket(array[i]));
					}
					catch (IllegalArgumentException exception3)
					{
						try
						{
							stack.push(new CloseBracket(array[i]));
						}
						catch (IllegalArgumentException exception4)
						{
							throw new InvalidExpressionException(/*"invalid symbol: " + array[i]*/);
						}
					}
				}
			}
		}

		return stack;
	}

	private static <T> Stack<T> reverseStack(Stack<T> stack)
	{
		Stack<T> newStack = new Stack<T>(stack.getSize());

		while (!stack.isEmpty())
		{
			newStack.push(stack.pop());
		}

		return newStack;
	}

	private static Operand evaluateInfixExpression(Stack<ExpressionFragment> expression) throws InvalidExpressionException
	{
		expression = reverseStack(expression);

		/* Shunting-yard algorithm */

		Stack<ExpressionFragment> result = new Stack<ExpressionFragment>(expression.getSize());
		Stack<ExpressionFragment> operator = new Stack<ExpressionFragment>(expression.getSize());

		while (!expression.isEmpty())
		{
			ExpressionFragment fragment = expression.pop();

			if (fragment instanceof Operand)
			{
				result.push(fragment);
			}
			else if (fragment instanceof OpenBracket)
			{
				operator.push(fragment);
			}
			else if (fragment instanceof CloseBracket)
			{
				while (!operator.isEmpty())
				{
					if (operator.peek() instanceof OpenBracket) break;
					else result.push(operator.pop());
				}

				if (operator.isEmpty()) throw new InvalidExpressionException(/*"bracket mismatch"*/);
				else operator.pop();
			}
			else if (fragment instanceof Operator)
			{
				Operator currentOperator = (Operator) fragment;

				while (!operator.isEmpty() && !(operator.peek() instanceof OpenBracket))
				{
					Operator operatorTop = (Operator) operator.peek();
					int precedenceDiff = operatorTop.compareTo(currentOperator);

					if (precedenceDiff <= 0 && !(precedenceDiff == 0 && operatorTop.isLeftAssociative())) break;

					result.push(operator.pop());
				}

				operator.push(currentOperator);
			}
			else
			{
				throw new InvalidExpressionException(/*"invalid expression"*/);
			}
		}

		while (!operator.isEmpty())
		{
			ExpressionFragment fragment = operator.pop();

			if (fragment instanceof OpenBracket || fragment instanceof CloseBracket)
			{
				throw new InvalidExpressionException(/*"bracket mismatch"*/);
			}

			result.push(fragment);
		}

		return evaluatePostfixExpression(result);
	}

	private static Operand evaluatePostfixExpression(Stack<ExpressionFragment> expression) throws InvalidExpressionException
	{
		expression = reverseStack(expression);
		Stack<Operand> operand = new Stack<Operand>(expression.getSize());

		while (!expression.isEmpty())
		{
			ExpressionFragment fragment = expression.pop();

			if (fragment instanceof Operand)
			{
				operand.push((Operand) fragment);
			}
			else if (fragment instanceof Operator)
			{
				Operand rightOperand = operand.pop();
				operand.push(((Operator) fragment).apply(operand.pop(), rightOperand));
			}
			else
			{
				throw new InvalidExpressionException(/*"invalid expression"*/);
			}
		}

		return operand.pop();
	}

	private static Operand evaluatePrefixExpression(Stack<ExpressionFragment> expression) throws InvalidExpressionException
	{
		Stack<Operand> operand = new Stack<Operand>(expression.getSize());

		while (!expression.isEmpty())
		{
			ExpressionFragment fragment = expression.pop();

			if (fragment instanceof Operand)
			{
				operand.push((Operand) fragment);
			}
			else if (fragment instanceof Operator)
			{
				operand.push(((Operator) fragment).apply(operand.pop(), operand.pop()));
			}
			else
			{
				throw new InvalidExpressionException(/*"invalid expression"*/);
			}
		}

		return operand.pop();
	}

	/*
	 main method
	*/

	/**
	 * Provides a CLI for intreacting with <code>ExpressionEvaluator</code>.
	 *
	 * @param argument not used
	 */
	public static void main(String[] argument) throws IOException
	{
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		int notation = NOTATION_NONE;

		System.out.println("Select a notation:");
		System.out.println(NOTATION_INFIX + ": Infix");
		System.out.println(NOTATION_POSTFIX + ": Postfix");
		System.out.println(NOTATION_PREFIX + ": Prefix");

		do
		{
			try
			{
				System.out.print("> ");
				int inputValue = Integer.parseInt(console.readLine());
				if (inputValue == NOTATION_INFIX || inputValue == NOTATION_POSTFIX || inputValue == NOTATION_PREFIX)
				{
					notation = inputValue;
				}
				else
				{
					System.out.println("Invalid input, please try again.");
				}
			}
			catch (NumberFormatException exception)
			{
				System.out.println("Invalid input, please try again.");
			}
		}
		while (notation == NOTATION_NONE);

		System.out.println("Input expression:");
		System.out.print("> ");
		String expression = console.readLine();

		try
		{
			if (notation == NOTATION_INFIX)
			{
				System.out.println("Result: " + evaluateInfixExpression(convertExpressionToStack(expression)).getValue());
			}
			else if (notation == NOTATION_POSTFIX)
			{
				System.out.println("Result: " + evaluatePostfixExpression(convertExpressionToStack(expression)).getValue());
			}
			else if (notation == NOTATION_PREFIX)
			{
				System.out.println("Result: " + evaluatePrefixExpression(convertExpressionToStack(expression)).getValue());
			}
		}
		catch (InvalidExpressionException exception)
		{
			System.out.println("Invalid expression!");
			// exception.printStackTrace();
		}
	}

	/*
	 inner classes
	*/

	private static class InvalidExpressionException extends RuntimeException
	{
		/* class variables */
		private static final long serialVersionUID = 2081450544602701889L;
	}
}
