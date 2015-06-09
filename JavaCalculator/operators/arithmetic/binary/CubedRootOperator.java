package operators.arithmetic.binary;

import operators.Constant;
import operators.Operator;

public class CubedRootOperator extends NthRootOperator {
	public CubedRootOperator(Operator op0) {
		super(new Constant(3),op0);
	}
}
