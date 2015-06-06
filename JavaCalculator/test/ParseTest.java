package test;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;
import parser.Parser;
import parser.Parser.BracketsException;

public class ParseTest {
	public static void main (String [] args) {
		runTestCases(args);
	}
	public static boolean runTestCases (String [] cases) {
		Parser parser = new Parser ();
		int n = cases.length;
		for (String s : cases) {
			double answer;
			if (s.equals("-v")) {
				parser.DEBUG = (parser.DEBUG != 0)?0:1;
				n--;
				continue;
			}
			try {
				answer = parser.evaluate(s);
				System.out.println((n>1?s+" = ":" ") + answer);
			} catch (BracketsException e) {
				System.out.println("Brackets Error");
			} catch (SyntaxException e) {				
				System.out.println("Syntax Error");
			} catch (MathException e) {
				System.out.println("Math Error");
			} catch (OperatorException e) {
				System.out.println("Illegal use of Operator");
			}
		}
		return true;
	}
}
