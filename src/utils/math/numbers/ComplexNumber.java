package utils.math.numbers;

import java.text.DecimalFormat;

public class ComplexNumber {
	/*
	 * Addition Rule: (a + bi) + (c + di) = (a + c) + (b + d)i
	 * Subtraction Rule: (a + bi) - (c + di) = (a - c) + (b - d)i
	 * Multiplication Rule: (a + bi) • (c + di) = (ac - bd) + (ad + bc)i
	 * 
	 * (pain)
	 */
	
	double real;
	double imaginary;
	
	public ComplexNumber(double r, double i) {
		this.real = r;
		this.imaginary = i;
	}
	
	
	public double getReal() {
		return this.real;
	}
	
	
	public double getImaginary() {
		return this.imaginary;
	}
	
	
	public String toString() {
        DecimalFormat df = new DecimalFormat("#.#####");
        String formattedR = df.format(real);
        String formattedI = df.format(imaginary);
		if(imaginary >= 0) {
			return this.real + "+" + this.imaginary + "i";
		} else {
			return this.real + "" + this.imaginary + "i";
		}
	}
	
	
	public void add(double scalar) {
		this.real += scalar;
	}
	
	
	public void add(ComplexNumber cn) {
		this.real += cn.real;
		this.imaginary += cn.imaginary;
	}
	
	
	public void subtract(double scalar) {
		this.real -= scalar;
	}
	
	
	public void subtract(ComplexNumber cn) {
		this.real -= cn.real;
		this.imaginary -= cn.imaginary;
	}
	
	
	public void multiply(double scalar) {
		this.real *= scalar;
	}
	
	
	public void multiply(ComplexNumber cn) {
		double R1 = this.real * cn.real - this.imaginary * cn.imaginary;
		double I1 = this.real * cn.imaginary + this.imaginary * cn.real;
		
		this.real = R1;
		this.imaginary = I1;
	}
	
	
	public void divide(double scalar) {
		this.real /= scalar;
	}
	
	
	public void divide(ComplexNumber cn) {
		double divisorReal = cn.getReal();
		double divisorImaginary = cn.getImaginary();
		
		double divisorSquared = divisorReal * divisorReal + divisorImaginary * divisorImaginary;
		
	    double newReal = (this.real * divisorReal + this.imaginary * divisorImaginary) / divisorSquared;
	    double newImaginary = (this.imaginary * divisorReal - this.real * divisorImaginary) / divisorSquared;
	    
	    this.real = newReal;
	    this.imaginary = newImaginary;
	}
}
