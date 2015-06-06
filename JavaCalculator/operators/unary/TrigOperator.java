package operators.unary;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.Operator;
import operators.UnaryOperator;

public class TrigOperator extends UnaryOperator{
	Function fn;
	public enum Function {
		SIN, COS, TAN, ATAN, ACOS, ASIN
	}
	public TrigOperator (Operator op0, Function fn) {
		super(op0);
		this.fn = fn;
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		switch (fn) {
		case SIN : {
			return Math.sin(op0.eval());
		}
		case COS : {
			return Math.cos(op0.eval());
		}
		case TAN : {
			return Math.tan(op0.eval());
		}
		case ASIN : {
			return Math.asin(op0.eval());
		}
		case ACOS : {
			return Math.acos(op0.eval());
		}
		case ATAN : {
			return Math.atan(op0.eval());
		}
		default : return Double.NaN;
		}		
	}
}
