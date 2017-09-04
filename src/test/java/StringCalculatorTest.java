import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.sc.StringCalc;
import org.sc.parser.CalcNode;
import org.sc.parser.StringParser;
import org.sc.parser.StringParserFactory;
import org.sc.parser.validator.InvalidExpressionException;

public class StringCalculatorTest {

	private StringCalc sc = new StringCalc();
	private StringParser parser = StringParserFactory.getDefaultStringParser();

	@Test
	public void testEvaluateTree() throws InvalidExpressionException {
		validateAnswer("3 + 2", 5d);
		validateAnswer("3 + 2 * 5", 13);
		validateAnswer("3 * 3 / 2", 4.5);
		validateAnswer("3 * (4+4)", 24);
		validateAnswer("20 * 2", 40);
		validateAnswer("3.14 + 2", 5.14);
		validateAnswer("3 ^ 2", 9);
	}
	
	@Test 
	public void operatorPrecedence2() throws InvalidExpressionException {
		validateAnswer("3 * 10 + 10", 40);
	}
	
	@Test
	public void round15() throws InvalidExpressionException {
		validateAnswer("3 / 7", 0.428571428571429);
	}

	@Test
	public void divideZero() throws InvalidExpressionException {
		boolean divZero = false;
		try {
			validateAnswer("2 * 2 / (2 - 2)", Double.POSITIVE_INFINITY);
		} catch (ArithmeticException ae) {
			divZero = true;
		}
		assertTrue(divZero);
	}

	private void validateAnswer(String expression, double expectedAnswer) throws InvalidExpressionException {
		CalcNode tree = parser.parse(expression);
		final BigDecimal rawAnswer = sc.evaluateTree(tree);
		BigDecimal answer = rawAnswer.stripTrailingZeros();
		BigDecimal ea = BigDecimal.valueOf(expectedAnswer).stripTrailingZeros();
		assertTrue(answer.equals(ea));
	}

}
