package operators;

import java.util.ArrayList;
import java.util.List;


public abstract class VectorOperator extends Operator {
	protected List<Operator> list;
	public VectorOperator (Operator op0) {
		super(op0);
	}
	@Override
	public void initialize(Operator op0) { 
		list = new ArrayList<Operator> (1);
		if (op0 != null) list.add(op0);
	}
	@Override
	public void initialize(VectorOperand op0) {
		list = new ArrayList<Operator> ();
		list.addAll(((VectorOperand)op0).list);
	}
	public VectorOperator (VectorOperand op0) {
		initialize(op0);
	}
	@Override
	public boolean changed () {
		for (Operator op : list) {
			if (op.changed()) return true;
		}
		return false;
	}
}
