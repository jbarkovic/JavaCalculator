package operators.stats.array;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.Operator;
import operators.SortedVectorOperator;

public class MedianOperator extends SortedVectorOperator {
	public MedianOperator (Operator op0) {
		super (op0);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		sort();
		double median = Double.NaN;
		if (list.size()%2==0) {
			int middle = list.size()/2;
			median = list.get(middle-1).eval();
			median += list.get(middle).eval();
			median /= 2.0d;
		} else {
			median = list.get(list.size()/2).eval();
		}
		return median;
	}
}
