

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.sc.parser.CalcNode;
import org.sc.parser.StringParser;
import org.sc.parser.StringParserImpl;
import org.sc.parser.validator.ExpressionValidatorImpl;
import org.sc.parser.validator.InvalidExpressionException;
import org.sc.tokenizer.ExpressionTokenizerImpl;

public class StringParserImplTest {
	
	private StringParser sp = new StringParserImpl(new ExpressionTokenizerImpl(), new ExpressionValidatorImpl());

	@Test
	public void testParse() throws InvalidExpressionException {
		CalcNode cn = sp.parse("3 + 2 * 4 / 1 + (3 + 2)");
		//System.out.println("cn: " + cn);
		assertTrue(cn != null);
	}
}
