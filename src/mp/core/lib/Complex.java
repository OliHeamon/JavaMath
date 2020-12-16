package mp.core.lib;

import java.util.ArrayList;

public class Complex {
	private final double real;
	private final double imaginary;
	
	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	/**
	 * The real component of this number.
	 * @return The real component of this number.
	 */
	public double real() {
		return real;
	}
	
	/**
	 * The imaginary component of this number.
	 * @return The imaginary component of this number.
	 */
	public double imaginary() {
		return imaginary;
	}
	
	/**
	 * Returns a string formatted as (a + bi) representing this number.
	 */
	@Override
	public String toString() {
		String op = "";
		
		if (imaginary > 0) {
			op = "+";
		}
		
		return String.format("%s%s%si", real, op, imaginary);
	}
	
	/**
	 * Parses a string formatted as (a + bi) into a complex number.
	 * @param z The string representation of z.
	 * @return The instance representing z.
	 */
	public static Complex parse(String z) {
		z = z.replaceAll("\\s+", "");
		
		String[] split = z.split("[+-]");
		
		ArrayList<String> parts = new ArrayList<>();
		
		for (String s : split) {
			if (!s.equals("")) {
				parts.add(s);
			}
		}
		
		double real = Double.valueOf(parts.get(0));
		
		double imaginary = parts.get(1).equals("i") ? 1 : Double.valueOf(parts.get(1).substring(0, parts.get(1).length() - 1));
		
		if (z.charAt(0) == '-') {
			real *= -1;
		}
		
		for (char c : z.substring(1, z.length()).toCharArray()) {
			if (c == '-') {
				imaginary *= -1;
			}
		}
		
		return new Complex(real, imaginary);
	}
	
	/**
	 * Returns the modulus of this; if z = a + bi then |z| = sqrt(a^2 + b^2). This may also be expressed as the distance that z lies from the origin.
	 * @param z The complex number.
	 * @return |z|.
	 */
	public double mod() {
		double a = real;
		double b = imaginary;
		
		return Math.sqrt((a * a) + (b * b));
	}
	
	/**
	 * Returns the argument of this; if z = a + bi then Arg(z) = <br>
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
	public double arg() {
		double a = real;
		double b = imaginary;
		
		if (b != 0) {
			return 2 * Math.atan2(b, mod() + a);
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
	 * Returns the complex conjugate of this.
	 * @param z The complex number.
	 * @return z*.
	 */
	public Complex conjugate() {
		return new Complex(real, -imaginary);
	}
	
	/**
	 * Adds a complex number to this.
	 * @param z The value being added.
	 * @return (a + bi) + (c + di).
	 */
	public Complex add(Complex z) {
		return new Complex(real + z.real(), imaginary + z.imaginary());
	}
	
	/**
	 * Subtracts a complex number from this.
	 * @param z The value being subtracted.
	 * @return (a + bi) - (c + di).
	 */
	public Complex sub(Complex z) {
		return new Complex(real - z.real(), imaginary - z.imaginary());
	}
	
	/**
	 * Multiplies this complex number with another.
	 * @param z The second complex number.
	 * @return (a + bi)(c + di).
	 */
	public Complex mul(Complex z) {
		double a = real;
		double b = imaginary;
		double c = z.real();
		double d = z.imaginary();
		
		return new Complex((a * c) - (b * d), (b * c) + (a * d));
	}
	
	/**
	 * Divides this complex number by another.
	 * @param z The divisor.
	 * @return (a + bi) / (c + di).
	 */
	public Complex div(Complex z) {
		double a = real;
		double b = imaginary;
		double c = z.real();
		double d = z.imaginary();
		
		double cSqPlusDSq = (c * c) + (d * d);
		
		double real = ((a * c) + (b * d)) / cSqPlusDSq;
		double imaginary = ((b * c) - (a * d)) / cSqPlusDSq;
		
		return new Complex(real, imaginary);
	}
}
