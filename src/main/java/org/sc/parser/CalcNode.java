package org.sc.parser;

/**
 * The node data structure.
 * @author Joe Hall
 */
public class CalcNode {
	private final String val;
	private final CalcNode leftNode;
	private final CalcNode rightNode;
	
	public CalcNode(String val, CalcNode leftNode, CalcNode rightNode) {
		this.val = val;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	public String getVal() {
		return this.val;
	}

	public CalcNode getLeftNode() {
		return this.leftNode;
	}

	public CalcNode getRightNode() {
		return this.rightNode;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{ val: '")
				.append(this.val)
				.append("', right: ")
				.append(rightNode)
				.append(", left: ")
				.append(leftNode)
				.append("}");
		return sb.toString();
	}
}
