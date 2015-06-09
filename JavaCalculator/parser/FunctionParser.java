package parser;

import java.util.TreeMap;

import operators.Operator;
import operators.arithmetic.array.GCDOperator;
import operators.arithmetic.array.SumOperator;
import operators.arithmetic.binary.CubedRootOperator;
import operators.arithmetic.binary.NthRootOperator;
import operators.stats.array.*;

public class FunctionParser {
	private static TreeMap<String,FunctionLoader<? extends Operator>> functions = new TreeMap<String,FunctionLoader<? extends Operator>> ();
	static {
		addFunction(new String [] {"min","minimum"},MinOperator.class);
		addFunction(new String [] {"max","maximum"},MaxOperator.class);
		addFunction(new String [] {"mean","avg","average"},AverageOperator.class);
		addFunction(new String [] {"mode"},ModeOperator.class);
		addFunction(new String [] {"med","median","middle"},MedianOperator.class);
		addFunction(new String [] {"sum","add","addition"},SumOperator.class);
		addFunction(new String [] {"gcd","greatestcomondivisor"},GCDOperator.class);
		addFunction(new String [] {"sqrt","root","nroot","nrt","nthroot","nthrt"},NthRootOperator.class);
		addFunction(new String [] {"cbrt","cubedroot","3root","3rt"},CubedRootOperator.class);

	}
	private static <F extends Operator> void addFunction(String [] keys, Class<F> clazz) {
		FunctionLoader<F> value = new FunctionLoader<F>(clazz);
		for (String key : keys) {
			functions.put(key, value);
		}
	}
	protected Operator parse (char [] text, int start, int end, Parser parser) {
		String funcStr = String.copyValueOf(text,start,end-start);
		FunctionLoader<? extends Operator> loader = null;
		for (int i=1;i<funcStr.length();i++) {
			if ((loader=functions.get(funcStr.subSequence(0, i)))!=null) {
				return loader.load(parser,funcStr.substring(i));
			}
		}
		return null;
	}

}
