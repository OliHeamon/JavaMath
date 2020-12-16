package mp.core.lib;

/**
 * A class providing useful functions that aren't included in the normal java.lang.Math class.
 */
public final class RealMath {
	/**
	 * Returns the logarithm base b of x.
	 * @param x The real number.
	 * @param b The base.
	 * @return logB(x).
	 */
	public static double logB(double x, double b) {
		return Math.log(x) / Math.log(b);
	}
	
	/**
	 * Returns the natural logarithm of x.
	 * @param x The real number.
	 * @return ln(x).
	 */
	public static double ln(double x) {
		return logB(x, Math.E);
	}
	
	/**
	 * Returns the factorial of x.
	 * @param x The integer.
	 * @return n(n - 1)(n - 2)...(3)(2)(1).
	 */
	public static long fact(long x) {
		if (x <= 1) {
			return x;
		}
		
		return x * fact(x - 1);
	}
	
	/**
	 * Returns the pi function of x, which is an extension of the factorial operation to all real numbers.
	 * @param x The real number.
	 * @return PI(x).
	 */
	public static double pi(double x) {
		return gamma(x + 1);
	}
	
	/**
	 * Returns the gamma function of x, which is an extension of the factorial operation to all real numbers in that n! = GAMMA(n + 1).
	 * @param x The real number.
	 * @return GAMMA(x).
	 */
	public static double gamma(double x) {
		return x; // WIP
	}
}
