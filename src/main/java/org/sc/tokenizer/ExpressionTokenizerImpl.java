package org.sc.tokenizer;

import java.util.ArrayList;
import java.util.List;

import org.sc.parser.validator.ExpressionValidator;
import org.sc.parser.validator.InvalidExpressionException;

/**
 * Basic implementation class for the ExpressionTokenizer interface.
 *
 * @author Joe Hall
 */
public class ExpressionTokenizerImpl implements ExpressionTokenizer {

	/* (non-Javadoc)
	 * @see org.sc.parser.ExpressionTokenizer#getTokenizedList(java.lang.String, org.sc.parser.validator.ExpressionValidator)
	 */
	@Override
	public List<String> getTokenizedList(String expression, ExpressionValidator validator) throws InvalidExpressionException {
		expression = expression.replaceAll("\\s", "");
		char[] exAr = expression.toCharArray();
		String currentNumber = "";
		ArrayList<String> al = new ArrayList<>();
		for (char c : exAr) {
			if (validator.isNumber(c)) {
				currentNumber += c;
			} else {
				if (!currentNumber.equals("")) {
					al.add(currentNumber);
				}
				al.add(String.valueOf(c));
				currentNumber = "";
			}
		}
		if (currentNumber != null && !currentNumber.equals("")) {
			al.add(currentNumber);
		}
		if (!validator.validExpression(al)) {
			throw new InvalidExpressionException("Invalid format for expression.");
		}
		return al;
	}
}
