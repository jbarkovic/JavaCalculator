package parser;

import java.lang.reflect.InvocationTargetException;

import exceptions.BracketsException;
import exceptions.SyntaxException;
import operators.Operator;
import operators.VectorOperand;

public class FunctionLoader<F extends Operator> {
	private Class<F> funcClass;
	public FunctionLoader(Class<F> clazz) {
		this.funcClass = clazz;
	}
	public F load (Parser parser, String remain) {
		try {
			return this.funcClass.getConstructor(VectorOperand.class).newInstance(parser.parse(remain));
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | BracketsException
				| SyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

}
