import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.sc.parser.validator.ExpressionValidatorImpl;
import org.sc.parser.validator.InvalidExpressionException;
import org.sc.tokenizer.ExpressionTokenizer;
import org.sc.tokenizer.ExpressionTokenizerImpl;

public class ExpressionTokenizerImplTest {

	@Test
	public void testGetTokenizedList() throws InvalidExpressionException {
		ExpressionTokenizer et = new ExpressionTokenizerImpl();
		List<String> list = et.getTokenizedList("20*40", new ExpressionValidatorImpl());
		assertTrue(list.size() == 3);
		list = et.getTokenizedList("3.14 + 2", new ExpressionValidatorImpl());
		assertTrue(list.size() == 3);

	}

}
