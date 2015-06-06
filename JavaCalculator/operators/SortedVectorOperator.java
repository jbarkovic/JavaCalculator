package operators;

import java.util.Collections;

public class SortedVectorOperator extends VectorOperator {
	public SortedVectorOperator(Operator op0) {
		super(op0);
		sort();
	}
	protected void sort () { // Sorts ascending
		Collections.sort(list);
	}
}
