package org.sc.parser.validator;

import java.util.List;

/**
 * Class for verifying pieces of the expression.
 * Numbers and Operators
 * @author Joe Hall
 *
 */
public class ExpressionValidatorImpl implements ExpressionValidator {
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
		char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
		for(char n : nums) {
			if(n == c)
				return true;
		}
		return false;
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
		String[] opsAr = {"+", "-", "*", "/", "^" };
		for(String op : opsAr) {
			if(op.equals(s))
				return true;
		}
		return false;
	}
	
	/**
	 * Validate the express to verify it is Number Operator Number, etc...
	 * @param expression - The Expression string...
	 */
	public boolean validExpression(List<String> expression) {
		boolean returnVal = true;
		int leftParen = 0, rightParen = 0;
		boolean lastNumber = false;
		for(String s : expression) {
			if(s.equals("(")) {
				leftParen++;
				continue;
			} else if(s.equals(")")) {
				rightParen++;
				continue;
			}
			boolean isNo = isNumber(s);
			boolean isOp = false;
			if(!isNo)
				isOp = isOperator(s);
			if(isNo && !lastNumber)
				lastNumber = true;
			else if (isOp && lastNumber)
				lastNumber = false;
			else if(lastNumber && isNo)
				returnVal = false;
			else if(!lastNumber && isOp)
				returnVal = false;
		}
		if(leftParen != rightParen)
			returnVal = false;
		return returnVal;
	}
}
