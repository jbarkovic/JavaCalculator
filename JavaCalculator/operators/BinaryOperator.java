package operators;

public abstract class BinaryOperator extends Operator {
	protected Operator op0;
	protected Operator op1;
	public BinaryOperator (Operator op0) {
		super (op0);
	}
	@Override
	public void initialize (VectorOperand list) {
		if (list!=null && list.size() > 0) {
			 op0 = list.get(0);
			 if (list.size() > 1) op1 = list.get(1);
		}
	}
	public BinaryOperator (Operator op0, Operator op1) {
		super();
		this.op0 = op0;
		this.op1 = op1;
	}
	@Override
	public boolean changed () {
		return op0.changed() || op1.changed();
	}
}
