package org.sc.parser;

import org.sc.parser.validator.ExpressionValidator;
import org.sc.parser.validator.ExpressionValidatorImpl;
import org.sc.tokenizer.ExpressionTokenizer;
import org.sc.tokenizer.ExpressionTokenizerImpl;

/**
 * Factory for getting the Parser implementation
 * @author Joe Hall
 */
public class StringParserFactory {
	
	public static StringParser getDefaultStringParser() {
		ExpressionTokenizer tokenizer = new ExpressionTokenizerImpl(); // Note: I was going to use a factory but Dependency Injection is preferred over the Abstract Factory pattern.
		ExpressionValidator validator = new ExpressionValidatorImpl(); 
		StringParser parser = new StringParserImpl(tokenizer, validator); // Inject into the parser.
		return parser;
	}
}
