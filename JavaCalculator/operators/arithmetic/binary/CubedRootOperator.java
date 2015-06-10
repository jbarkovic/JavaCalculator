package operators.arithmetic.binary;

import operators.Constant;
import operators.Operator;
import operators.VectorOperand;

public class CubedRootOperator extends NthRootOperator {
	public CubedRootOperator(VectorOperand op0) {
		super(new Constant(3),op0);
	}
}
