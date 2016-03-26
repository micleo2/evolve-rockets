import java.awt.Color;

public class CVector extends PVector {
	private Color color;
	
	CVector (double x, double y, Color c){
		super(x, y);
		color = c;
	}
	
	public CVector() {
		super();
		color = new Color(0, 0, 0);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}