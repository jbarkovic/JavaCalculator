package operators.trig.unary;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.Operator;
import operators.UnaryOperator;

public class SinhOperator extends UnaryOperator {
	public SinhOperator(Operator op0) {
		super(op0);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		return Math.sinh(op0.eval());
	}
}
