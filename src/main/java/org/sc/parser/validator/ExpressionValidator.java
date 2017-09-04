package org.sc.parser.validator;

import java.util.List;

/**
 * ExpressionValidator - validates the expression.  Also contains methods for checking numbers and operators.
 * @author Joe Hall
 */
public interface ExpressionValidator {

	boolean isNumber(String num);

	boolean isNumber(char c);

	boolean isOperator(char c);

	boolean isOperator(String s);
	
	/**
	 * Validate the express to verify it is Number Operator Number, etc...
	 * @param expression - The Expression string...
	 */
	public void validateExpression(List<String> expression) throws InvalidExpressionException;

}