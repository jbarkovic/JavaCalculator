package operators;

public class Constant extends Operator{
	private final double value;
	public Constant (double value) {
		super(null);
		this.value = value;
	}
	@Override
	public boolean changed () {
		return false;
	}
	@Override
	public double eval () {
		return value;
	}
	@Override
	public double internalCalc () {
		return value;
	}
}
