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
		validateAnswer("3 / 7", 0.428571428571429);
		try {
			validateAnswer("2 * 2 / (2 - 2)", Double.POSITIVE_INFINITY);
		} catch (ArithmeticException ae) {
			System.out.println("Divide by 0");
		}
		validateAnswer("20 * 2", 40);
		validateAnswer("3.14 + 2", 5.14);
		validateAnswer("3 ^ 2", 9);
		//validateAnswer("a * b", 9);
	}

	private void validateAnswer(String expression, double expectedAnswer) throws InvalidExpressionException {
		CalcNode tree = parser.parse(expression);
		BigDecimal answer = sc.evaluateTree(tree).stripTrailingZeros();
		BigDecimal ea = BigDecimal.valueOf(expectedAnswer).stripTrailingZeros();
		assertTrue(answer.equals(ea));
	}

}
