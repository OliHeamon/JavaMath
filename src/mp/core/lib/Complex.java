package mp.core.lib;

import java.util.ArrayList;

public class Complex {
	private double real;
	private double imaginary;
	
	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public Complex(String z) {
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
		
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public double real() {
		return real;
	}
	
	public double imaginary() {
		return imaginary;
	}
	
	@Override
	public String toString() {
		String op = "";
		
		if (imaginary > 0) {
			op = "+";
		}
		
		return String.format("%s%s%si", real, op, imaginary);
	}
}
