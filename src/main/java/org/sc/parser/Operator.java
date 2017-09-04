package org.sc.parser;

/**
 * Operator class for storing operators, precedence, and if right association.
 * @author Joe Hall
 */
public class Operator {
	private final char op;
    private final boolean right;
    private final int prec;

    public Operator(char op, int prec, boolean right) {
        this.op = op;
        this.prec = prec;
        this.right = right;
    }

    public boolean isRight() {
        return this.right;
    }

    public int compare(Operator o) {
    	return this.prec > o.prec ? 1 : o.prec == this.prec ? 0 : -1;
    }

    public char getOp() {
        return this.op;
    }
}
