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
		boolean timeIt = false;
		for (String s : cases) {
			double answer;
			if (s.equals("-v")) {
				parser.DEBUG = (parser.DEBUG != 0)?0:1;
				n--;
				continue;
			} else if (s.equals("-t")) {
				timeIt = true;
				n--;
				continue;
			}
			long startTime = System.nanoTime();
			try {
				answer = parser.evaluate(s);
				System.out.println((n>1?s+" = ":" ") + answer);
			} catch (BracketsException e) {
				System.out.println("Brackets Error: " + e.getMessage());
			} catch (SyntaxException e) {				
				System.out.println("Syntax Error: " + e.getMessage());
			} catch (MathException e) {
				System.out.println("Math Error: " + e.getMessage());
			} catch (OperatorException e) {
				System.out.println("Illegal use of Operator: " + e.getMessage());
			}
			long endTime = System.nanoTime();
			long elapsed = endTime - startTime;
			if (timeIt) System.out.println("took " + elapsed + "ns, or " + (elapsed/1000000) + "ms");
		}
		return true;
	}
}
