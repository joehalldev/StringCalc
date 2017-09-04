import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.sc.parser.validator.ExpressionValidatorImpl;
import org.sc.parser.validator.InvalidExpressionException;

public class ExpressionValidatorDefaultTest {
	
	@Test
	public void testIsNumberString() {
		ExpressionValidatorImpl v = new ExpressionValidatorImpl();
		assertTrue(v.isNumber("0"));
		assertTrue(v.isNumber("1"));
		assertTrue(v.isNumber("2"));
		assertTrue(v.isNumber("3"));
		assertTrue(v.isNumber("4"));
		assertTrue(v.isNumber("5"));
		assertTrue(v.isNumber("6"));
		assertTrue(v.isNumber("7"));
		assertTrue(v.isNumber("8"));
		assertTrue(v.isNumber("9"));
		assertTrue(v.isNumber("3.14"));
		assertFalse(v.isNumber("+"));
	}

	@Test
	public void testIsNumberChar() {
		ExpressionValidatorImpl v = new ExpressionValidatorImpl();
		assertTrue(v.isNumber('0'));
		assertTrue(v.isNumber('1'));
		assertTrue(v.isNumber('2'));
		assertTrue(v.isNumber('3'));
		assertTrue(v.isNumber('4'));
		assertTrue(v.isNumber('5'));
		assertTrue(v.isNumber('6'));
		assertTrue(v.isNumber('7'));
		assertTrue(v.isNumber('8'));
		assertTrue(v.isNumber('9'));
		assertTrue(v.isNumber('.'));
		assertFalse(v.isNumber('+'));
	}

	@Test
	public void testIsOperatorChar() {
		ExpressionValidatorImpl v = new ExpressionValidatorImpl();
		assertFalse(v.isOperator('0'));
		assertTrue(v.isOperator('+'));
		assertTrue(v.isOperator('-'));
		assertTrue(v.isOperator('*'));
		assertTrue(v.isOperator('/'));
		assertTrue(v.isOperator('^'));
	}

	@Test
	public void testIsOperatorString() {
		ExpressionValidatorImpl v = new ExpressionValidatorImpl();
		assertFalse(v.isOperator("0"));
		assertTrue(v.isOperator("+"));
		assertTrue(v.isOperator("-"));
		assertTrue(v.isOperator("*"));
		assertTrue(v.isOperator("/"));
		assertTrue(v.isOperator("^"));
	}

	@Test
	public void testValidExpression() throws InvalidExpressionException {
		ExpressionValidatorImpl v = new ExpressionValidatorImpl();
		List<String> al = new ArrayList<>();
		al.add("3");
		al.add("+");
		al.add("4");
		v.validateExpression(al);
		v.validateExpression(Arrays.asList(new String[]{"3","+","4","*","2","-","1","/","4"}));
		v.validateExpression(Arrays.asList(new String[]{"3","+","(","4","+","2",")"}));
		boolean testInvalid = false;
		try {
			v.validateExpression(Arrays.asList(new String[]{"3","3","4","*","2","-","1","/","4"})); 
		} catch(InvalidExpressionException iee) {
			testInvalid = true;
		}
		assertTrue(testInvalid);
	}

}
