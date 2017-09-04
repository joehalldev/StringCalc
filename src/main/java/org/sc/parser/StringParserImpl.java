package org.sc.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.sc.parser.validator.ExpressionValidator;
import org.sc.parser.validator.InvalidExpressionException;
import org.sc.tokenizer.ExpressionTokenizer;

/**
 * 
 * @author Joe Hall
 * Shunting yard algorithm
 * https://en.wikipedia.org/wiki/Shunting-yard_algorithm
 * Parse string into a abstract tree of nodes
 */
public class StringParserImpl implements StringParser {

	HashMap<Character, Operator> operators = new HashMap<>();
	private ExpressionTokenizer tokenizer;
	private ExpressionValidator validator;

	public StringParserImpl(ExpressionTokenizer tokenizer, ExpressionValidator validator) {
		this.tokenizer = tokenizer;
		this.validator = validator;
		operators.put('^', new Operator('^', 3, true));
		operators.put('*', new Operator('*', 2, false));
		operators.put('/', new Operator('/', 2, false));
		operators.put('+', new Operator('+', 1, false));
		operators.put('-', new Operator('-', 1, false));
	}
	
	/* (non-Javadoc)
	 * @see org.sc.parser.StringParser#parse(java.lang.String)
	 */
	@Override
	public CalcNode parse(String expression) throws InvalidExpressionException {
		Stack<String> operatorStack = new Stack<>();
		Stack<CalcNode> calcStack = new Stack<>();
		//char[] expAr = expression.toCharArray();
		List<String> expAr = this.tokenizer.getTokenizedList(expression, this.validator);
		for(String c : expAr)
		{
			String poppedOp;
			if(c.equals("(")) {
				operatorStack.push(c);
			} else if(c.equals(")")) {
				while(!operatorStack.isEmpty()) {
                    poppedOp = operatorStack.pop();
                    if("(".equals(poppedOp)) {
                    	break;
                    } else {
                        addNode(calcStack, poppedOp);
                    }
                }
			} else if(this.validator.isOperator(c)) { 
                final Operator o1 = operators.get(c);
                Operator o2;
                while(!operatorStack.isEmpty() && null != (o2 = operators.get(operatorStack.peek()))) {
                    if((!o1.isRight() && 0 == o1.compare(o2)) || o1.compare(o2) < 0) {
                        operatorStack.pop();
                        addNode(calcStack, String.valueOf(o2.getOp()));
                    } else {
                        break;
                    }
                }
                operatorStack.push(c);
            } else if(this.validator.isNumber(c)) {
                calcStack.push(new CalcNode(c, null, null));
            }
			// ignore other characters
		}
		while(!operatorStack.isEmpty()) {
            addNode(calcStack, operatorStack.pop());
        }
        return calcStack.pop();
	}
		
	private static void addNode(Stack<CalcNode> stack, String operator) {
        final CalcNode rightASTNode = (!stack.isEmpty()) ? stack.pop() : null;
        final CalcNode leftASTNode = (!stack.isEmpty()) ? stack.pop() : null;
        stack.push(new CalcNode(operator, leftASTNode, rightASTNode));
    }
}
