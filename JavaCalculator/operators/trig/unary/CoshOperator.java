package operators.trig.unary;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.Operator;
import operators.UnaryOperator;

public class CoshOperator extends UnaryOperator {
	public CoshOperator(Operator op0) {
		super(op0);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		return Math.cosh(op0.eval());
	}
}