package postfix;

import java.util.Stack;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains a method to evaluate an arithmetic expression
 * that is in Postfix notation (or Reverse Polish Notation).
 * See <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Wikipedia</a>
 * for details on the notation.
 *
 */
public class PostfixEvaluator {
	
	private String arithmeticExpr;
	
	/**
	 * This is the only constructor for this class.
	 * It takes a string that represents an arithmetic expression
	 * as input argument.
	 * 
	 * @param expr is a string that represents an arithmetic expression 
	 * <strong>in Postfix notation</strong>.
	 */
	public PostfixEvaluator( String expr ) {
		arithmeticExpr = expr;
	}
	
	/**
	 * This method evaluates the arithmetic expression that 
	 * was passed as a string to the constructor for this class.
	 * 
	 * @return the value of the arithmetic expression
	 * @throws MalformedExpressionException if the provided expression is not
	 * 	a valid expression in Postfix notation
	 */
	double eval( ) throws MalformedExpressionException {

		Stack arithmeticStack = new Stack();
		Scanner scanner = new Scanner(arithmeticExpr);
		Double Operand1, Operand2;
		String Operator;
		double instantResult;

		Token currToken = scanner.getToken();

		while (!scanner.isEmpty()){

				if (currToken.isDouble()) {
					arithmeticStack.push(currToken.getValue());
					scanner.eatToken();
					currToken = scanner.getToken();
				}
				else {
					if (!arithmeticStack.isEmpty())
					    Operand2 = (Double) arithmeticStack.pop();
					else
						throw new MalformedExpressionException();
					if (!arithmeticStack.isEmpty())
						Operand1 = (Double) arithmeticStack.pop();
					else
						throw new MalformedExpressionException();

					Operator = currToken.toString();

					switch (Operator) {
						case "+":
							instantResult = Operand1 + Operand2;
							arithmeticStack.push(instantResult);
							break;

						case "-":
							instantResult = Operand1 - Operand2;
							arithmeticStack.push(instantResult);
							break;

						case "*":
							instantResult = Operand1 * Operand2;
							arithmeticStack.push(instantResult);
							break;

						case "/":
							instantResult = Operand1 / Operand2;
							arithmeticStack.push(instantResult);
							break;
					}

					scanner.eatToken();
					currToken = scanner.getToken();

				}

		}

		return (Double) arithmeticStack.pop();
	}
	
}
