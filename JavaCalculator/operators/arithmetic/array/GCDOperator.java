package operators.arithmetic.array;

import java.util.List;
import java.util.ListIterator;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.Operator;
import operators.SortedVectorOperator;

public class GCDOperator extends SortedVectorOperator {
	public GCDOperator(Operator op0) {
		super(op0);
	}
	@Override
	public double internalCalc() throws SyntaxException, MathException, OperatorException {
		if (list.size()>=1) {
			return arrayGCD (list);
		} else {
			throw new SyntaxException("Missing values");
		}
	}
	protected double arrayGCD (List<Operator> list) throws SyntaxException, MathException, OperatorException { // Requires there is at least 1 element in the list
		sort();
		Operator min = list.get(0);
		double gcd = min.eval();
		if (gcd % 1 != 0) throw new MathException("Not an Integer: " + gcd);
		ListIterator<Operator> li = list.listIterator(1);
		while (li.hasNext()) {
			double val = li.next().eval();
			if (val % 1 != 0) throw new MathException("Not an Integer: " + val);
			if (Math.floor(val) != val) throw new MathException("Not an Integer: " + val);
			gcd = recursiveGCD(gcd,val);
		}
		return gcd;
	}
	private double recursiveGCD (double num0, double num1) {		
		double large = Math.max(num0, num1);
		double small = Math.min(num0, num1);
		double div = large / small;
		if (Math.floor(div) == div) {
			return small;
		} else {
			return recursiveGCD(large-small,small);
		}
	}
}
