package org.sc.parser.validator;

import java.util.HashSet;
import java.util.List;

/**
 * Class for verifying pieces of the expression.
 * Numbers and Operators
 * @author Joe Hall
 *
 */
public class ExpressionValidatorImpl implements ExpressionValidator {
	
	private HashSet<Character> numbers = new HashSet<>(11);
	private HashSet<String> operators = new HashSet<>(5);
	
	public ExpressionValidatorImpl() {
		for(int i = 0; i < 10; i++) {
			this.numbers.add(Character.forDigit(i, 10));
		}
		this.numbers.add('.');
		String[] opsAr = {"+", "-", "*", "/", "^" };
		for(String op : opsAr) {
			this.operators.add(op);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.sc.parser.IExpressionValidator#isNumber(java.lang.String)
	 */
	@Override
	public boolean isNumber(String num) {
		try {
			Double d = Double.parseDouble(num);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.sc.parser.IExpressionValidator#isNumber(char)
	 */
	@Override
	public boolean isNumber(char c) {
		return this.numbers.contains(c); // Hashset lookup faster than loop.  Constant time O(1) vs O(n) for a loop.
		/*char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
		for(char n : nums) {
			if(n == c)
				return true;
		}
		return false;*/
	}
	
	/* (non-Javadoc)
	 * @see org.sc.parser.IExpressionValidator#isOperator(char)
	 */
	@Override
	public boolean isOperator(char c) {
		return isOperator(String.valueOf(c));
	}
	
	/* (non-Javadoc)
	 * @see org.sc.parser.IExpressionValidator#isOperator(java.lang.String)
	 */
	@Override
	public boolean isOperator(String s) {
		return this.operators.contains(s); // Hashset lookup faster than loop - O(1) vs O(n)
		/*String[] opsAr = {"+", "-", "*", "/", "^" };
		for(String op : opsAr) {
			if(op.equals(s))
				return true;
		}
		return false;*/
	}
	
	/**
	 * Validate the express to verify it is Number Operator Number, etc...
	 * @param expression - The Expression string...
	 */
	@Override
	public void validateExpression(List<String> expression) throws InvalidExpressionException {
		int leftParen = 0, rightParen = 0;
		ExpressionStates expressionStateMachine = new ExpressionStates();
		for(String s : expression) {
			if(s.equals("(")) {
				leftParen++;
				continue;
			} else if(s.equals(")")) {
				rightParen++;
				continue;
			}
			boolean isNo = isNumber(s);
			boolean isOp = isOperator(s);
			expressionStateMachine.transition(isNo, isOp);
		}
		if(leftParen != rightParen)
			throw new InvalidExpressionException("Parens do not match.");
	}
	
	/**
	 * Simple finite state machine to manage the state of the expression.
	 */
	private static class ExpressionStates {
		enum States { START, NUMBER, OPERATOR };
		private States currentState = States.START;
		
		public void transition(boolean isNumber, boolean isOperator) throws InvalidExpressionException {
			if(currentState.equals(States.START)) {
				if(isNumber)
					currentState = States.NUMBER;
				else
					throw new InvalidExpressionException("Expression must begin with a number.");
			} else if(currentState.equals(States.NUMBER)) {
				if(isOperator)
					currentState = States.OPERATOR;
				else
					throw new InvalidExpressionException("Missing operator on numbers");
			} else if(currentState.equals(States.OPERATOR)) {
				if(isNumber)
					currentState = States.NUMBER;
				else
					throw new InvalidExpressionException("Must have operand after operator.");
			}
		}
	}
}
