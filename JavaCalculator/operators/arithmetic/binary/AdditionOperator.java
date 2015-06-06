package operators.arithmetic.binary;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.BinaryOperator;
import operators.Operator;

public class AdditionOperator extends BinaryOperator{
	public AdditionOperator(Operator op0, Operator op1) {
		super(op0, op1);
	}
	@Override
	public double internalCalc() throws SyntaxException, MathException, OperatorException {
		return op0.eval()+op1.eval();
	}
}
