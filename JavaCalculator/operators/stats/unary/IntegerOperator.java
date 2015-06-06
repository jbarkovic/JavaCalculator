package operators.stats.unary;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.Operator;
import operators.UnaryOperator;

public class IntegerOperator extends UnaryOperator{
	private Function func;
	public enum Function {
		ABS, CEIL, FLOOR, ROUND
	}
	public IntegerOperator (Operator op0, Function func) {
		super(op0);
		this.func = func;
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		switch (func) {
		case ABS : {
			return Math.abs(op0.eval());
		}
		case CEIL : {
			return Math.ceil(op0.eval());
		}
		case FLOOR : {
			return Math.floor(op0.eval());
		}
		case ROUND : {
			return Math.round(op0.eval());
		}
		default : return op0.eval();
		}
	}
}
