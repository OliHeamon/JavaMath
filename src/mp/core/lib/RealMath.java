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
}
