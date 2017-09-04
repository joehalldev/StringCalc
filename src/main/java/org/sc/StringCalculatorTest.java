package org.sc;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.sc.parser.CalcNode;
import org.sc.parser.StringParser;
import org.sc.parser.StringParserImpl;
import org.sc.parser.validator.ExpressionValidatorImpl;
import org.sc.parser.validator.InvalidExpressionException;
import org.sc.tokenizer.ExpressionTokenizerImpl;

public class StringCalculatorTest {

	private StringCalc sc = new StringCalc();
	private StringParser parser = new StringParserImpl(new ExpressionTokenizerImpl(), new ExpressionValidatorImpl());

	@Test
	public void testEvaluateTree() throws InvalidExpressionException {
		validateAnswer("3 + 2", 5d);
		validateAnswer("3 + 2 * 5", 13);
		validateAnswer("3 * 3 / 2", 4.5);
		validateAnswer("3 * (4+4)", 24);
		try {
			validateAnswer("2 * 2 / (2 - 2)", Double.POSITIVE_INFINITY);
		} catch(ArithmeticException ae) {
			System.out.println("Divide by 0");
		}
		validateAnswer("20 * 2", 40);
		validateAnswer("3.14 + 2", 5.14); 
		validateAnswer("3 ^ 2", 9);
	}

	private void validateAnswer(String expression, double expectedAnswer) throws InvalidExpressionException {
		CalcNode tree = parser.parse(expression);
		BigDecimal answer = sc.evaluateTree(tree).stripTrailingZeros();
		BigDecimal ea = BigDecimal.valueOf(expectedAnswer).stripTrailingZeros();
		assertTrue(answer.equals(ea));
	}

}
