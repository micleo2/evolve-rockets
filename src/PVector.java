import java.awt.Color;

public class PVector {
	private double x, y;
	private Color color;
	
	PVector (double x, double y){
		this.x = x;
		this.y = y;
		color = new Color(0, 0, 0);
	}
	
	public PVector() {
		x = y = 0;
		color = new Color(0, 0, 0);
	}

	public static PVector random() {
		return new PVector(Math.random() * 2 - 1, Math.random() * 2 - 1);
	}

	public PVector mult(double d) {
		return new PVector(this.x * d, this.y * d);
	}
	
	public PVector add(PVector velocity) {
		return new PVector(this.x + velocity.x, this.y + velocity.y);
	}
	
	public String toString(){
		return x + ", " + y;
	}
	
	/***GETTERS AND SETTERS***/
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
