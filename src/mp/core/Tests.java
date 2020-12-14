package mp.core;

import mp.core.lib.Complex;
import mp.core.lib.ComplexUtils;
import mp.core.lib.RealUtils;

public class Tests {
	private static int failed;
	
	private static final double EPSILON = 1e-9; // Rest results will be accurate to within 1/1000000000 of the genuine values.
	
	public static void main(String[] args) {
		failed = 0;
		
		test("Complex number parsing (1 + i)", new Complex("1 + i"), new Complex(1, 1));
		test("Complex number parsing (1 - 2i)", new Complex("1 - 2i"), new Complex(1, -2));
		test("Complex number parsing (-1 + 2i)", new Complex("-1 + 2i"), new Complex(-1, 2));
		test("Complex number parsing (-1 - i)", new Complex("-1 - i"), new Complex(-1, -1));
		test("Complex number parsing (1.912871 - 7.837i)", new Complex("1.912871 - 7.837i"), new Complex(1.912871, -7.837));
		
		test("Logarithms (Logarithm base 2 of 2, 1 expected)", RealUtils.logB(2, 2), 1);
		test("Logarithms (Natural logarithm of e^2, 2 expected)", RealUtils.ln(Math.E * Math.E), 2);
		test("Logarithms (Natural logarithm of 0 + i, ipi/2 expected)", ComplexUtils.ln(new Complex(0, 1)), new Complex(0, Math.PI / 2));
		test("Logarithms (Natural logarithm of -1 + 0i, ipi expected)", ComplexUtils.ln(new Complex(-1, 0)), new Complex(0, Math.PI));
		
		test("Arguments (1 + i, pi/4 expected)", ComplexUtils.arg(new Complex(1, 1)), Math.PI / 4);
		test("Arguments (-1 + i, 3pi/4 expected)", ComplexUtils.arg(new Complex(-1, 1)), (3 * Math.PI) / 4);
		test("Arguments (-1 - i, -3pi/4 expected)", ComplexUtils.arg(new Complex(-1, -1)), -(3 * Math.PI) / 4);
		test("Arguments (1 - i, -pi/4 expected)", ComplexUtils.arg(new Complex(1, -1)), -Math.PI / 4);
		
		test("Complex numbers with real exponents ((1 + i)^2)", ComplexUtils.pow(new Complex(1, 1), 2), new Complex(0, 2));
		test("Complex numbers with complex exponents (i^i)", ComplexUtils.pow(new Complex(0, 1), new Complex(0, 1)), new Complex(Math.pow(Math.E, -Math.PI / 2), 0));
		
		System.out.println("Test comparisons accurate within an epsilon of " + EPSILON);
		System.out.println("Failed: " + failed);
	}
	
	private static void test(String description, double value, double expected) {
		if (fuzzyEquals(value, expected)) {
			System.out.println(description + ": Passed!");
			return;
		}
		
		failed++;
		
		System.out.println(description + String.format(": Failed! (%s returned).", value));
	}
	
	private static void test(String description, Complex value, Complex expected) {
		if (fuzzyEquals(value, expected)) {
			System.out.println(description + ": Passed!");
			return;
		}
		
		failed++;
		
		System.out.println(description + String.format(": Failed! (%s returned).", value));
	}
	
	// The equality tests use an epsilon because floating-point errors mean it's extremely unlikely that desired results and obtained results will be absolutely identical.
	
	private static boolean fuzzyEquals(double a, double b) {
		return Math.abs(a - b) < EPSILON;
	}
	
	private static boolean fuzzyEquals(Complex a, Complex b) {
		return Math.abs(a.real() - b.real()) < EPSILON && Math.abs(a.imaginary() - b.imaginary()) < EPSILON;
	}
}
