package parser;


import exceptions.BracketsException;
import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import operators.*;
import operators.arithmetic.array.GCDOperator;
import operators.arithmetic.array.SumOperator;
import operators.arithmetic.binary.AdditionOperator;
import operators.arithmetic.binary.DifferenceOperator;
import operators.arithmetic.binary.DivisionOperator;
import operators.arithmetic.binary.ExponentialOperator;
import operators.arithmetic.binary.MultiplicationOperator;
import operators.arithmetic.unary.NthRootOperator;
import operators.stats.array.AverageOperator;
import operators.stats.array.MaxOperator;
import operators.stats.array.MedianOperator;
import operators.stats.array.MinOperator;
import operators.stats.array.ModeOperator;
import operators.trig.unary.CoshOperator;
import operators.trig.unary.SinhOperator;
import operators.trig.unary.TanhOperator;
import operators.trig.unary.TrigOperator;
public class Parser {
	public int DEBUG = 0;
	public double evaluate (String text) throws BracketsException, SyntaxException, MathException, OperatorException {
			return parse(text).eval();		
	}
	public Operator parse (String text) throws BracketsException, SyntaxException {
		char [] textArray = text.toCharArray();
		int bCount;
		if ((bCount=countBrackets(textArray,0,textArray.length))!=0) throw new BracketsException (""+bCount); 
		return recursiveOrderOfOperations (textArray,0,textArray.length);
	}
	private Operator recursiveOrderOfOperations (char [] text, int start, int end) throws SyntaxException {
		Operator op = null;
		if (start >= end) {
			if (DEBUG > 0) System.out.println("ERROR: start >= end: start: " + start + " , end: " + end);
			return null;
		}
		if ((op=recursiveBracketParse(text,start,end))!=null) return op;
		if ((op=functionArgsParser(text,start,end))!=null) return op;
		if ((op=recursiveAddSubParse(text,start,end))!=null) return op;
		if ((op=recursiveMultDivParse(text,start,end))!=null) return op;
		if ((op=recursiveExponentParse(text,start,end))!=null) return op;
		if ((op=recursiveFunctionParse(text,start,end))!=null) return op;
		if ((op=recursiveTrigFunctionParse(text,start,end))!=null) return op;
		if ((op=numberParse(text,start,end))!=null)  return op;
		throw new SyntaxException("Could not parse: " + String.copyValueOf(text, start, end-start));
	}
	private int countBrackets (char [] text, int start, int end) {
		int count = 0;
		for (int ptr = start;ptr<end;ptr++) {
			char point = text[ptr];
			if (point=='(') count++;
			else if (point==')') count--;
		}
		return count;
	}
	private Operator numberParse(char [] text, int start, int end) {
		if (DEBUG > 0) System.out.println ("NUMBERS checking string: " + String.copyValueOf(text, start, end-start));
		if (start >= end) return null;
		String numString = String.copyValueOf(text, start, end-start).replaceAll(" ", "");
		try {
			Constant c = new Constant(Double.parseDouble(numString));
			if (DEBUG>0) System.out.println("NUMBERS: found " + c.eval());
			return c;
		} catch (NumberFormatException nfe) {
			if (DEBUG > 0) System.out.println("NUMBERS: Could not resolve \""+numString+"\"");
			return null;
		}
	}
	private Operator recursiveBracketParse (char [] text, int start, int end) throws SyntaxException {
		if (DEBUG > 0) System.out.println ("BRACKETS checking string: " + String.copyValueOf(text, start, end-start));
		if (start >= end) return null;
		for (int ptr = start;ptr<end;ptr++) {
			char point = text[ptr];
			if (point == '(') {
				for (int rptr = end-1;rptr>=ptr;rptr--) {
					char point2 = text[rptr];
					if (point2==')') {
						// Keep going deeper until we start seeing end-brackets;
						return recursiveOrderOfOperations(text,ptr+1,rptr);
					} else if (point2 != ' ') return null;
				}
			} else if (point != ' ') break;
		}
		return null;
	}
	private Operator recursiveExponentParse (char [] text, int start, int end) throws SyntaxException {
		if (DEBUG > 0) System.out.println ("EXPONENT checking string: " + String.copyValueOf(text, start, end-start));
		if (start >= end) return null;
		int bracketCount = 0;
		for (int ptr = start;ptr<end;ptr++) {
			char point = text[ptr];
			if (point == '(') bracketCount++;
			else if (point == ')') bracketCount--;
			else if (point == '^' && bracketCount == 0) {
				return new ExponentialOperator (recursiveOrderOfOperations(text,start,ptr),recursiveOrderOfOperations(text,ptr+1,end));
			}
		}
		return null;
	}
	private Operator recursiveMultDivParse (char [] text, int start, int end) throws SyntaxException {
		if (DEBUG > 0) System.out.println ("MUL/DIV checking string: " + String.copyValueOf(text, start, end-start));
		if (start >= end) return null;
		int bracketCount = 0;
		for (int ptr = start;ptr<end;ptr++) {
			char point = text[ptr];
			if (point == '(') bracketCount++;
			else if (point == ')') bracketCount--;
			else if (point == '*' && bracketCount == 0) {
				return new MultiplicationOperator (recursiveOrderOfOperations(text,start,ptr),recursiveOrderOfOperations(text,ptr+1,end));
			} else if (point == '/' && bracketCount == 0) {
				return new DivisionOperator (recursiveOrderOfOperations(text,start,ptr),recursiveOrderOfOperations(text,ptr+1,end));
			}
		}
		return null;
	}
	private Operator recursiveAddSubParse (char [] text, int start, int end) throws SyntaxException {
		if (DEBUG > 0) System.out.println ("ADD/SUB checking string: " + String.copyValueOf(text, start, end-start));
		if (start >= end) return null;
		int bracketCount = 0;
		for (int ptr = start;ptr<end;ptr++) {
			char point = text[ptr];
			if (point == '(') bracketCount++;
			else if (point == ')') bracketCount--;
			else if (point == '+' && bracketCount == 0) {
				return new AdditionOperator (recursiveOrderOfOperations(text,start,ptr),recursiveOrderOfOperations(text,ptr+1,end));
			} else if (point == '-' && bracketCount == 0) {
				return new DifferenceOperator (recursiveOrderOfOperations(text,start,ptr),recursiveOrderOfOperations(text,ptr+1,end));
			}
		}
		return null;
	}
	private VectorOperand functionArgsParser (char [] text, int start, int end) throws SyntaxException {
		if (DEBUG > 0) System.out.println ("COMMA stating checking string: " + String.copyValueOf(text, start, end-start));
		int bracketCount = 0;
		for (int ptr=start;ptr<end;ptr++) {
			char point = text[ptr];
			if (point == ',' && bracketCount==0) {
				return recursiveFunctionArgsParse (text,start,end);
			} else if (point=='(') bracketCount++;
			else if (point==')') bracketCount--;
		}
		return null;
	}
	private VectorOperand recursiveFunctionArgsParse (char [] text, int start, int end) throws SyntaxException {
		if (DEBUG > 0) System.out.println ("COMMA checking string: " + String.copyValueOf(text, start, end-start));
		int bracketCount = 0;
		for (int ptr=start;ptr<end;ptr++) {
			char point = text[ptr];
			
			if (point == ','&&bracketCount==0) {
				VectorOperand vector = new VectorOperand(recursiveOrderOfOperations(text,start,ptr));
				vector.add(recursiveOrderOfOperations(text,ptr+1,end));
				return vector;
			} else if (point=='(') bracketCount++;
			else if (point==')') bracketCount--;
		}
		return new VectorOperand(recursiveOrderOfOperations(text,start,end));
	}
	private Operator recursiveFunctionParse (char [] text, int start, int end) throws SyntaxException {
		if (DEBUG > 0) System.out.println ("FUNCTION checking string: " + String.copyValueOf(text, start, end-start));
		if (start >= end) return null;
		String fnStr = String.copyValueOf(text, start, end-start);
		fnStr = fnStr.toLowerCase();
		if (firstNonSpaceCharIndex (text,start,end) > start) {
			if (DEBUG > 1) System.out.println("Function: stripping spaces");
			return recursiveFunctionParse (text,firstNonSpaceCharIndex(text,start,end),end);
		}
		if (fnStr.startsWith("max")) {
			fnStr = fnStr.replaceFirst("max","");
			return new MaxOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		else if (fnStr.startsWith("min")) {
			fnStr = fnStr.replaceFirst("min","");
			return new MinOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		else if (fnStr.startsWith("average")||fnStr.startsWith("avg")) {
			fnStr = fnStr.replaceFirst("average","");
			fnStr = fnStr.replaceFirst("avg","");
			return new AverageOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		else if (fnStr.startsWith("mode")) {
			fnStr = fnStr.replaceFirst("mode","");
			return new ModeOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		else if (fnStr.startsWith("median")||fnStr.startsWith("med")) {
			fnStr = fnStr.replaceFirst("median","");
			fnStr = fnStr.replaceFirst("med","");
			return new MedianOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		else if (fnStr.startsWith("sum")||fnStr.startsWith("add")) {
			fnStr = fnStr.replaceFirst("sum","");
			fnStr = fnStr.replaceFirst("add","");
			return new SumOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		else if (fnStr.startsWith("gcd")) {
			fnStr = fnStr.replaceFirst("gcd","");
			return new GCDOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		else if (fnStr.startsWith("sqrt")) {
			fnStr = fnStr.replaceFirst("sqrt","");
			return new NthRootOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		if (DEBUG>0) System.out.println("FUNCTION no functions were found");
		return null;
	}
	private Operator recursiveTrigFunctionParse (char [] text, int start, int end) throws SyntaxException {
		if (DEBUG > 0) System.out.println ("TRIG checking string: " + java.lang.String.copyValueOf(text, start, end-start));
		if (start >= end) return null;
		String fnStr = java.lang.String.copyValueOf(text, start, end-start);
		fnStr = fnStr.toLowerCase();		
		if (firstNonSpaceCharIndex (text,start,end) > start) {
			if (DEBUG > 1) System.out.println("TRIG: stripping spaces");
			return recursiveTrigFunctionParse (text,firstNonSpaceCharIndex(text,start,end),end);
		}
		TrigOperator.Function func = null;
		if (isFunction("sin",fnStr).matches) {
			fnStr = fnStr.replaceFirst("sin", "");
			func = TrigOperator.Function.SIN;
		}
		else if (isFunction("cos",fnStr).matches) {
			fnStr = fnStr.replaceFirst("cos", "");
			func = TrigOperator.Function.COS;			
		}
		else if (isFunction("tan",fnStr).matches) {
			fnStr = fnStr.replaceFirst("tan", "");
			func = TrigOperator.Function.TAN;
		}
		else if (isFunction("asin",fnStr).matches) {
			fnStr = fnStr.replaceFirst("asin", "");
			func = TrigOperator.Function.ASIN;
		}
		else if (isFunction("acos",fnStr).matches) {
			fnStr = fnStr.replaceFirst("acos", "");
			func = TrigOperator.Function.ACOS;
		}
		else if (isFunction("atan",fnStr).matches) {
			fnStr = fnStr.replaceFirst("atan", "");
			func = TrigOperator.Function.ATAN;
		}
		else if (isFunction("sinh",fnStr).matches) {
			fnStr = fnStr.replaceFirst("sinh", "");
			func = null;
			return new SinhOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		else if (isFunction("cosh",fnStr).matches) {
			fnStr = fnStr.replaceFirst("cosh", "");
			func = null;
			return new CoshOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		else if (isFunction("tanh",fnStr).matches) {
			fnStr = fnStr.replaceFirst("tanh", "");
			func = null;
			return new TanhOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()));
		}
		if (func != null) return new TrigOperator(recursiveOrderOfOperations(fnStr.toCharArray(),0,fnStr.length()),func);
		else return null;
	}
	private Match isFunction (String [] possibles, String text) {
		if (possibles == null) return new Match(false);
		else {
			for (String s : possibles) {
				if(text.startsWith(s)) {
					return new Match (true,s,text.replaceFirst(s, ""));
				}
			}
			return new Match (false);
		}
	}
	private Match isFunction (String functionName, String text) {
		if(memcmp(functionName,text) == (functionName.length())) {
			return new Match (true, functionName, text.replaceFirst(functionName, ""));
		} else {
			return new Match(false);
		}
	}
	private int memcmp (String str0, String str1) {
		if (str0 == null || str1 == null) return 0;
		char [] str0Arr = str0.toCharArray();
		char [] str1Arr = str1.toCharArray();
		int end = Math.min(str0Arr.length, str1Arr.length);
		for (int ptr=0;ptr<end;ptr++) {
			if (str0Arr[ptr] != str1Arr[ptr]) return ptr;
		}
		return end;
	}
	private int firstNonSpaceCharIndex (char [] text, int start, int end) {
		int ptr = start;
		for (;ptr<end;ptr++) {
			if (text[ptr] != ' ') break;
		}
		return ptr;
	}
	private class Match {
		private boolean matches;
		private String match;
		private String stripped;
		{
			matches = false;
			match  = "";
			stripped = "";
		}
		protected Match (boolean result) {
			matches = result;
		}
		protected Match (boolean result, String winner, String strippedText) {
			matches = result;
			match = winner;
			stripped = strippedText;
		}
	}
}
