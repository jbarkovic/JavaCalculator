package operators.stats.array;

import java.util.ListIterator;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.Operator;
import operators.SortedVectorOperator;

public class ModeOperator extends SortedVectorOperator {
	public ModeOperator(Operator op0) {
		super(op0);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {		
		if (list.size() > 0) {
			sort();
			ListIterator<Operator> li = list.listIterator();			
			double last = Double.NaN;
			double mode = Double.NaN;
			int bestCount = -1;
			int currentCount = 0;
			while (li.hasNext()) {
				Operator op = li.next();
				double current = op.eval();
				if (current == last) currentCount++;
				if (currentCount > bestCount) {
					bestCount = currentCount;
					mode = current;
				}
			}
			return mode;
		} else {
			return Double.NaN;
		}
	}
}
