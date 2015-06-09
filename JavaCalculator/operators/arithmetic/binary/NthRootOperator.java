package operators.arithmetic.binary;

import exceptions.*;
import operators.BinaryOperator;
import operators.Constant;
import operators.Operator;
import operators.VectorOperand;
import platform.Platform;

public class NthRootOperator extends BinaryOperator {
	public NthRootOperator(Operator a) {
		super(new Constant(2d), a);
	}
	public NthRootOperator(VectorOperand v) {
		/* If v has only one element, treat as a square root
		 * Else If v has multiple elements, treat as nthRoot, but only take the first two elements
		 * Else set up with what ever v is (probably empty list)
		 */
		super((v.size()<=1)?new Constant(2d):v.get(0),(v.size()>0)?(v.size()==1?v.get(0):v.get(1)):null);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		double n = op0.eval();
		if (op1==null) throw new SyntaxException("No argument to root");
		else {
			Double a = new Double(op1.eval());
			if (a < 0d) {
				throw new MathException("Root cannot take negative number");
			} else if (n%1!=0d){
				throw new MathException("N must be an Integer");
			} else {
				return iterrativeNthRootFinder((int)n,a);
			}
		}
	}
	private double iterrativeNthRootFinder (final int n, final double a) {
		double xk = a/(n*n);
		final double invN = (1/(double)n);
		final double e = Platform.PRECISION;
		final int n_m1 = n-1;
		double dxk = e + 1d;

		while (dxk >= e) {
			// Loop unrolled 3 times (may end up being a little bit more precise)
			xk = xk + dxk;
			dxk = invN*((a/Math.pow(xk,n_m1) - xk));
			
			xk = xk + dxk;
			dxk = invN*((a/Math.pow(xk,n_m1) - xk));
			
			xk = xk + dxk;
			dxk = invN*((a/Math.pow(xk,n_m1) - xk));
		}
		return xk;
	}

}
