package operators.arithmetic.binary;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.BinaryOperator;
import operators.Operator;

public class ExponentialOperator extends BinaryOperator {
	public ExponentialOperator (Operator base, Operator exponent) {
		super(base, exponent);
	}
	@Override
	public double internalCalc() throws SyntaxException, MathException, OperatorException {
		return Math.pow(op0.eval(), op1.eval());
	}
}
