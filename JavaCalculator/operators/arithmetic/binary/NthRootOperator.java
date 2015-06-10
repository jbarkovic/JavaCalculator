package operators.arithmetic.binary;

import exceptions.*;
import operators.BinaryOperator;
import operators.Operator;
import operators.VectorOperand;
import platform.Platform;

public class NthRootOperator extends BinaryOperator {
	boolean tooManyArguments = false;
	public NthRootOperator(Operator op0) {
		super(op0);
		if (op0 instanceof VectorOperand) {
			tooManyArguments = true;
		}
	}
	public NthRootOperator(Operator op0, Operator op1) {
		super(op0,op1);
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		double n = 2d;
		Double a = 0d;
		if (op0!=null&&op1!=null) {
			n=op0.eval();
			a = new Double(op1.eval());
		} else if (op1!=null) {
			a = new Double(op1.eval());
		} else if (op0 !=null) {
			a = new Double(op0.eval());
		} else {
			throw new SyntaxException("No arguments to NthRoot");
		}
		if (a < 0d) {
			throw new MathException("Root cannot take negative number");
		} else if (n%1!=0d){
			throw new MathException(n + " must be an Integer");
		} else {
			if (tooManyArguments) System.out.println("Calculating " + n + " ROOT of " + a);
			return iterrativeNthRootFinder((int)n,a);
		}
	}
	private double iterrativeNthRootFinder (final int n, final double a) {
		double xk = a/(n*n);
		final double invN = (1/(double)n);
		final double e = Platform.PRECISION;
		final int n_m1 = n-1;
		double dxk = e + 1d;
		while (Math.abs(dxk) >= e) {
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
