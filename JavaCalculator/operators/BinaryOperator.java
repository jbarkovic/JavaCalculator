package operators;



public class BinaryOperator extends Operator {
	protected Operator op0;
	protected Operator op1;
	
	public BinaryOperator (Operator op0, Operator op1) {
		this.op0 = op0;
		this.op1 = op1;
	}
	@Override
	public boolean changed () {
		return op0.changed() || op1.changed();
	}
}
