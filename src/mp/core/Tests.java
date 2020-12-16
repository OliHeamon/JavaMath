package mp.core;

import mp.core.lib.Complex;
import mp.core.lib.ComplexMath;
import mp.core.lib.RealMath;

public class Tests {
	private static int failed;
	
	// Test results will be accurate to within 1e-15 (1 / 1,000,000,000,000,000) of their genuine values.
	// This is required due to the fact that floating-point arithmetic errors will mean two values are extremely unlikely to be perfectly equal, even if they are supposed to be.
	// As such, the best means of checking equality is to see if the difference between the values is lower than a given difference, known as an epsilon.
	private static final double EPSILON = 1e-15;
	
	public static void main(String[] args) {
		failed = 0;
		
		test("Factorial for integers (5!, 120 expected)", RealMath.fact(5), 120);
		
		test("Complex number parsing (1 + i)", Complex.parse("1 + i"), new Complex(1, 1));
		test("Complex number parsing (1 - 2i)", Complex.parse("1 - 2i"), new Complex(1, -2));
		test("Complex number parsing (-1 + 2i)", Complex.parse("-1 + 2i"), new Complex(-1, 2));
		test("Complex number parsing (-1 - i)", Complex.parse("-1 - i"), new Complex(-1, -1));
		test("Complex number parsing (1.912871 - 7.837i)", Complex.parse("1.912871 - 7.837i"), new Complex(1.912871, -7.837));
		
		test("Logarithms (Logarithm base 2 of 2, 1 expected)", RealMath.logB(2, 2), 1);
		test("Logarithms (Natural logarithm of e^2, 2 expected)", RealMath.ln(Math.E * Math.E), 2);
		test("Logarithms (Natural logarithm of 0 + i, ipi/2 expected)", ComplexMath.ln(new Complex(0, 1)), new Complex(0, Math.PI / 2));
		test("Logarithms (Natural logarithm of -1 + 0i, ipi expected)", ComplexMath.ln(new Complex(-1, 0)), new Complex(0, Math.PI));
		
		test("Arguments (1 + i, pi/4 expected)", new Complex(1, 1).arg(), Math.PI / 4);
		test("Arguments (-1 + i, 3pi/4 expected)", new Complex(-1, 1).arg(), (3 * Math.PI) / 4);
		test("Arguments (-1 - i, -3pi/4 expected)", new Complex(-1, -1).arg(), -(3 * Math.PI) / 4);
		test("Arguments (1 - i, -pi/4 expected)", new Complex(1, -1).arg(), -Math.PI / 4);
		
		test("Complex numbers with real exponents ((1 + i)^2, 2i expected)", ComplexMath.pow(new Complex(1, 1), 2), new Complex(0, 2));
		test("Complex numbers with complex exponents (i^i, e^-pi/2 expected)", ComplexMath.pow(new Complex(0, 1), new Complex(0, 1)), new Complex(Math.pow(Math.E, -Math.PI / 2), 0));
		test("Real numbers with complex exponents (e^(0 + ipi), -1 expected)", ComplexMath.pow(Math.E, new Complex(0, Math.PI)),
			new Complex(-1, 0));
		
		test("Complex addition ((1 + i) + (2 - 3i), (3 - 2i) expected", new Complex(1, 1).add(new Complex(2, -3)), new Complex(3, -2));
		test("Complex subtraction ((2 - 5i) - (3 + 3i), (-1 - 8i) expected", new Complex(2, -5).sub(new Complex(3, 3)), new Complex(-1, -8));
		test("Complex multiplication ((1 + i)(2 + 2i), 4i expected", new Complex(1, 1).mul(new Complex(2, 2)), new Complex(0, 4));
		test("Complex division ((6 - 4i) / (2 - 2i), (2.5 + 0.5i) expected", new Complex(6, -4).div(new Complex(2, -2)), new Complex(2.5, 0.5));

		test("Complex trigonometry (sin(pi/2 -ln(2 + sqrt(3))i), 2 expected)",
			ComplexMath.sin(new Complex(Math.PI / 2, -RealMath.ln(2 + Math.sqrt(3)))), new Complex(2, 0));
		
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
	
	private static boolean fuzzyEquals(double a, double b) {
		return Math.abs(a - b) < EPSILON;
	}
	
	private static boolean fuzzyEquals(Complex a, Complex b) {
		return Math.abs(a.real() - b.real()) < EPSILON && Math.abs(a.imaginary() - b.imaginary()) < EPSILON;
	}
}
