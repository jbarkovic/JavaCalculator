package operators.stats.array;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.VectorOperator;
import operators.Operator;

public class MaxOperator extends VectorOperator {
	public MaxOperator (Operator op0) {
		super (op0);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		double max = Double.NEGATIVE_INFINITY;
		for (Operator op : this.list) {
			max = Math.max(max,op.eval());				
		}
		return max;
	}	
}
