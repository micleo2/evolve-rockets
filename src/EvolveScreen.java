import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;


public class EvolveScreen extends Screen {
	public static int lifeTime = 500;
	Population population;
	Mover target;
	Mover[] obstacles;
	public static Font font;
	public EvolveScreen(GameState s, int w, int h) {
		super(s, w, h);
		start();
		InputStream is = getClass().getResourceAsStream("megaman_2.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		font = font.deriveFont(25f);
	}

	public void start(){
		target = new Mover(width / 2, 20, 30);
		int gap = 30;
		obstacles = new Mover[]{
				new Mover(0, 300, 800,15),
				new Mover(0, 500, 800,15)
		};
		for (Mover m : obstacles){
			m.setColor(Color.red);
		}
		population = new Population(70, target);
		population.setObstacles(obstacles);
		population.setAllX(width / 2);
		population.setAllY(height - 20);
	}

	@Override
	public void render(Graphics g) {
		population.draw(g);
		
		target.draw(g);

		for (Mover m : obstacles){
			m.draw(g);
		}

		g.setColor(Color.white);
		g.setFont(font);
		g.drawString("GENERATION " + population.getGeneration() + ": ", 2, 27);
	}

	@Override
	public void update() {
		population.update();
		if (population.isDone()){
			population.calculateFitness();
			population.evolveNextGen();
			population.setDone(false);
			population.setAllX(width / 2);
			population.setAllY(height - 20);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(Point2D p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(Point2D p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(Point2D p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(Point2D p) {
		// TODO Auto-generated method stub

	}

}
