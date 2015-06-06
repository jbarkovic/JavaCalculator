package operators.arithmetic.binary;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.BinaryOperator;
import operators.Operator;

public class DivisionOperator extends BinaryOperator{
	public DivisionOperator(Operator op0, Operator op1) {
		super(op0, op1);
	}
	@Override
	public double internalCalc() throws SyntaxException, MathException, OperatorException {
		double op0Val = op0.eval();
		double op1Val = op1.eval();
		if (op1Val == 0) throw new MathException ("Divide by zero");
		else {
			return op0Val/op1Val;
		}
	}
}
