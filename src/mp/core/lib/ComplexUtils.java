package mp.core.lib;

public final class ComplexUtils {
	/**
	 * Returns the modulus of z; if z = a + bi then |z| = sqrt(a^2 + b^2). This may also be expressed as the distance that z lies from the origin.
	 * @param z The complex number.
	 * @return |z|.
	 */
	public static double mod(Complex z) {
		double a = z.real();
		double b = z.imaginary();
		
		return Math.sqrt((a * a) + (b * b));
	}
	
	/**
	 * Returns the argument of z; if z = a + bi then Arg(z) = <br>
	 * {<br>
	 *     a > 0, b = 0: 0<br>
	 *     a < 0, b = 0: pi<br>
	 *     b != 0: 2 * arctan(b / (sqrt(a^2 + b^2) + a))<br>
	 *     else: NaN<br>
	 * }<br>
	 * This can also be described as the rotation away from the real number line where z is.
	 * @param z The complex number.
	 * @return Arg(z).
	 */
	public static double arg(Complex z) {
		double a = z.real();
		double b = z.imaginary();
		
		if (b != 0) {
			return 2 * Math.atan2(b, Math.sqrt((a * a) + (b * b)) + a);
		}
		else if (a > 0 && b == 0) {
			return 0;
		}
		else if (a < 0 && b == 0) {
			return Math.PI;
		}
		
		return Double.NaN;
	}
	
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
		double theta = arg(z) + (Math.PI * 2 * branch);
		
		return new Complex(RealUtils.logB(mod(z), b), RealUtils.logB(Math.E, b) * theta);
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
		double r = mod(z);
		
		double rToTheN = Math.pow(r, exp);
		
		double theta = arg(z) + (Math.PI * 2 * branch);
		
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
		double r = mod(z);
		double theta = arg(z) + (Math.PI * 2 * branch);
		
		double logR = RealUtils.ln(r);
		
		double c = exp.real();
		double d = exp.imaginary();
		
		double firstPart = Math.pow(Math.E, ((c / 2) * logR) - (d * theta));
		
		double inner = ((d / 2) * logR) + (c * theta);
		
		double real = firstPart * Math.cos(inner);
		double imaginary = firstPart * Math.sin(inner);
		
		return new Complex(real, imaginary);
	}
}
