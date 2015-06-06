package operators.arithmetic.array;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.VectorOperator;
import operators.Operator;

public class SumOperator extends VectorOperator {
	public SumOperator (Operator op0) {
		super (op0);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		double sum = 0.0d;
		for (Operator op : this.list) {
			if (op != null) {
				sum += op.eval();
			}
		}
		return sum;
	}	
}
