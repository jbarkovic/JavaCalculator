package operators.stats.array;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.VectorOperator;
import operators.Operator;

public class MinOperator extends VectorOperator {
	public MinOperator (Operator op0) {
		super (op0);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		double min = Double.POSITIVE_INFINITY;
		for (Operator op : this.list) {
			min = Math.min(min,op.eval());				
		}
		return min;
	}	
}
