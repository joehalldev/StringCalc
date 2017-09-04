package org.sc.parser;

import org.sc.parser.validator.InvalidExpressionException;

/**
 * 
 * @author Joe Hall
 * String Parser - Class for parsing string into CalcNodes.
 */
public interface StringParser {

	/**
	 * Parse string into tree of nodes.
	 * @param expression - the expression string to parse.
	 * @return Root calc node
	 * @throws InvalidExpressionException 
	 */
	CalcNode parse(String expression) throws InvalidExpressionException;


}