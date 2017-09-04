package org.sc;

import java.math.BigDecimal;

import org.sc.parser.CalcNode;
import org.sc.parser.StringParser;
import org.sc.parser.StringParserFactory;
import org.sc.parser.validator.InvalidExpressionException;

/**
 * Main Class - Calculates the result of an expression string.
 * Example: 3 * 4 / 2
 * @author Joe Hall
 */
public class StringCalc {

	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: java -jar StringCalc.jar \"Expression\"\nExample: java -jar StringCalc.jar \"2 + 2\"");
			return;
		}
		StringParser parser = StringParserFactory.getDefaultStringParser();
		CalcNode tree;
		try {
			tree = parser.parse(args[0]);
		} catch (InvalidExpressionException e) {
			System.err.println(e.getMessage());
			return;
		}
		BigDecimal answer;
		try {
			StringCalc sc = new StringCalc();
			answer = sc.evaluateTree(tree).stripTrailingZeros();
		} catch (ArithmeticException ae) {
			System.err.println("Divide by 0 is not allowed.");
			return;
		}
		System.out.println(answer.stripTrailingZeros());
	}

	/**
	 * Recursive method to calculate tree
	 * @param tree - Abstract Tree
	 * @return double - The result
	 */
	public BigDecimal evaluateTree(CalcNode tree) {
        switch(tree.getVal()) {
            case "^":
                return evaluateTree(tree.getLeftNode()).pow(evaluateTree(tree.getRightNode()).intValue());
            case "*":
                return evaluateTree(tree.getLeftNode()).multiply(evaluateTree(tree.getRightNode()));
            case "/":
                return evaluateTree(tree.getLeftNode()).divide(evaluateTree(tree.getRightNode())); 
            case "+":
                return evaluateTree(tree.getLeftNode()).add(evaluateTree(tree.getRightNode()));
            case "-":
                return evaluateTree(tree.getLeftNode()).subtract(evaluateTree(tree.getRightNode()));
            default:
                return BigDecimal.valueOf(Double.valueOf(tree.getVal()));
        }
    }

}
