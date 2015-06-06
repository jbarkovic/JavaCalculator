package operators.arithmetic.binary;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.BinaryOperator;
import operators.Operator;


public class DifferenceOperator extends BinaryOperator{
	public DifferenceOperator(Operator op0, Operator op1) {
		super(op0, op1);
	}
	@Override
	public double internalCalc() throws SyntaxException, MathException, OperatorException {
		return op0.eval()-op1.eval();
	}
}
