package org.sc.tokenizer;


import java.util.List;
import org.sc.parser.validator.ExpressionValidator;
import org.sc.parser.validator.InvalidExpressionException;

/**
 * ExpressionTokenizer - Converts the expression into a list of tokens.
 * @author Joe Hall
 */
public interface ExpressionTokenizer {

	List<String> getTokenizedList(String expression, ExpressionValidator validator) throws InvalidExpressionException;

}