package mp.core.lib;

/**
 * A class which provides an extension of the normal java.lang.Math functions for complex arithmetic.
 */
public final class ComplexMath {
	/**
	 * Returns the logarithm base b of z.
	 * @param z The complex number.
	 * @param b The base.
	 * @return logB(z).
	 */
	public static Complex logB(Complex z, double b) {
		return logB(z, b, 0);
	}
	
	/**
	 * Returns the logarithm base b of z.
	 * @param z The complex number.
	 * @param b The base.
	 * @param branch The branch of the function to use (a given branch N will add 2 * pi * N to the argument).
	 * @return logB(z).
	 */
	public static Complex logB(Complex z, double b, int branch) {
		double theta = z.arg() + (Math.PI * 2 * branch);
		
		return new Complex(RealMath.logB(z.mod(), b), RealMath.logB(Math.E, b) * theta);
	}
	
	/**
	 * Returns the natural logarithm of z.
	 * @param z The complex number.
	 * @return ln(z).
	 */
	public static Complex ln(Complex z) {
		return ln(z, 0);
	}
	
	/**
	 * Returns the natural logarithm of z.
	 * @param z The complex number.
	 * @param branch The branch of the function to use (a given branch N will add 2 * pi * N to the argument).
	 * @return ln(z).
	 */
	public static Complex ln(Complex z, int branch) {
		return logB(z, Math.E, branch);
	}
	
	/**
	 * Returns z raised to the given real exponent.
	 * @param z The complex number.
	 * @param exp The real exponent.
	 * @return z^exp.
	 */
	public static Complex pow(Complex z, double exp) {
		return pow(z, exp, 0);
	}
	
	/**
	 * Returns z raised to the given real exponent.
	 * @param z The complex number.
	 * @param exp The real exponent.
	 * @param branch The branch of the function to use (a given branch N will add 2 * pi * N to the argument).
	 * @return z^exp, or (a + bi)^n.
	 */
	public static Complex pow(Complex z, double exp, int branch) {		
		double r = z.mod();
		
		double rToTheN = Math.pow(r, exp);
		
		double theta = z.arg() + (Math.PI * 2 * branch);
		
		double real = rToTheN * Math.cos(exp * theta);
		double imaginary = rToTheN * Math.sin(exp * theta);
		
		return new Complex(real, imaginary);
	}
	
	/**
	 * Returns z raised to the given complex exponent.
	 * @param z The complex number.
	 * @param exp The complex exponent.
	 * @return z^exp, or (a + bi)^(c + di).
	 */
	public static Complex pow(Complex z, Complex exp) {
		return pow(z, exp, 0);
	}
	
	/**
	 * Returns z raised to the given complex exponent.
	 * @param z The complex number.
	 * @param exp The complex exponent.
	 * @param branch The branch of the function to use (a given branch N will add 2 * pi * N to the argument).
	 * @return z^exp, or (a + bi)^(c + di).
	 */
	public static Complex pow(Complex z, Complex exp, int branch) {
		double a = z.real();
		double b = z.imaginary();
		
		double rSquared = (a * a) + (b * b);
		double theta = z.arg() + (Math.PI * 2 * branch);
		
		double lnRSquared = RealMath.ln(rSquared);
		
		double c = exp.real();
		double d = exp.imaginary();
		
		double powerOfE = Math.pow(Math.E, ((c / 2) * lnRSquared) - (d * theta));
		
		double inner = ((d / 2) * lnRSquared) + (c * theta);
		
		double real = powerOfE * Math.cos(inner);
		double imaginary = powerOfE * Math.sin(inner);
		
		return new Complex(real, imaginary);
	}
}
