import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;


public class Mover {
	private double x, y;
	private int width, height;
	private Color c;
	
	public Mover(double x, double y, int size){
		this.x = x;
		this.y = y;
		this.width = this.height = size;
		c = Color.WHITE;
	}
	
	public Mover(double x, double y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		c = Color.WHITE;
	}
	
	public void draw(Graphics g){
		g.setColor(getColor());
		g.fillRect((int)x, (int)y, width, height);
	}

	public void setDimensions(int change){
		this.width = this.height = change;
	}
	public Color getColor(){
		return c;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
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

	public boolean intersects(Mover m){
		Rectangle r = new Rectangle((int)x, (int)y, width, height);
		Rectangle other = new Rectangle((int)m.getX(), (int)m.getY(), m.getWidth(), m.height);
		return r.intersects(other);
	}
	
	public double distance(double x, double y){
		return Point2D.distance(this.x + width/2, this.y - this.height / 2, x, y);
	}
}
