package operators.stats.array;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.VectorOperator;
import operators.Operator;

public class AverageOperator extends VectorOperator {
	public AverageOperator (Operator op0) {
		super (op0);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		int count = 0;
		double avg = 0.0d;
		for (Operator op : this.list) {
			if (op != null) {
				avg += op.eval();
				count++;
			}
		}
		return avg/(double)count;
	}
}
