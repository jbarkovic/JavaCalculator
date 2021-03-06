package operators;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;

public abstract class UnaryOperator extends Operator{
	protected Operator op0;
	public UnaryOperator (Operator op0) {
		this.op0 = op0;
	}
	public UnaryOperator(VectorOperand list) {
		if (list != null&&list.size()>0) {
			this.op0 = list.get(0);
		}
	}
	@Override
	public boolean changed () {
		return op0.changed();
	}
	public boolean equals (UnaryOperator other) throws SyntaxException, MathException, OperatorException {
		return this.op0.eval() == other.op0.eval();
	}
}
