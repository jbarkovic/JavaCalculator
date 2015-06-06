package operators;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;

public class Operator implements Comparable<Operator> {
	double cachedResult = 0.0d;
	boolean cacheValid = false;
	public double eval () throws SyntaxException, MathException, OperatorException{		
		if (cacheValid&&!changed()) {
			return cachedResult;
		} else {
			cacheValid = true;
			return (cachedResult=internalCalc()); 
		}
	}
	public boolean changed () {
		return true;
	}
	public int compareTo (Operator other) {
		double ours;
		try {
			ours = this.eval();
		} catch (SyntaxException | MathException | OperatorException e) {
			return -1;
		}
		double theirs;
		try {
			theirs = other.eval();
		} catch (SyntaxException | MathException | OperatorException e) {
			return 1;
		}
		if (ours == theirs) return 0;
		else if (ours < theirs) return -1;
		else return 1;
	}
	public boolean equals (Operator other) {
		double ours = Double.NaN;
		double theirs = Double.NaN;
		try {
			ours = this.eval();		
		} catch (SyntaxException | MathException | OperatorException e) {			
		}
		try {
			theirs = other.eval();
		} catch (SyntaxException | MathException | OperatorException e) {			
		}
		return ours == theirs;
	}
	protected double internalCalc () throws SyntaxException, MathException, OperatorException {
		return 0.0d;
	}
}
