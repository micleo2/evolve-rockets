import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Rocket {
	private DNA dna;
	private double fitness; //between 0 and 1 ~ (0, 1]
	private int geneCounter, width, height;
	private BufferedImage img;

	PVector location;
	PVector velocity;
	PVector acceleration;
	PVector[] trail;
	private boolean shouldMove;
	private void init(){
		trail = new PVector[EvolveScreen.lifeTime];
		location = new PVector();
		velocity = new PVector();
		acceleration = new PVector();
		dna = new DNA(EvolveScreen.lifeTime);
		shouldMove = true;
		width = 30;
		height = 20;
		img = ImageLoader.loadImage("rocket.png");
		geneCounter = 0;
	}
	public Rocket(double x, double y) {
		init();
		location.setX(x);
		location.setY(y);
	}

	public Rocket() {
		init();
	}

	//Accumulating forces into acceleration (Newton’s 2nd law)
	void applyForce(PVector f) {
		acceleration = acceleration.add(f);
	}

	//Our simple physics model (Euler integration)
	void update() {
		if (shouldMove && geneCounter < getDna().genes.length){
			trail[geneCounter] = new PVector(location.getX() + width/2, location.getY() + height/2);
			applyForce(dna.genes[geneCounter]);
			//Go to the next force in the genes array.
			geneCounter++;
			//Velocity changes according to acceleration.
			velocity = velocity.add(acceleration);
			//Location changes according to velocity.
			location = location.add(velocity);
			acceleration = acceleration.mult(0);
		}
	}

	public void draw(Graphics g){		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform t = g2d.getTransform();
		g2d.rotate(Math.atan2(velocity.getY(), velocity.getX()), location.getX() + width/2, location.getY() + height / 2);
		g.drawImage(img, (int)location.getX(), (int)location.getY(), width, height, null);
		g2d.setTransform(t);
		
		for (int i = 1; i < geneCounter - 1; i++){
			if (trail[i] == null){
				break;
			}
			g.setColor(dna.genes[i].getColor());
			g.drawLine((int)trail[i-1].getX(), (int)trail[i-1].getY(), (int)trail[i].getX(), (int)trail[i].getY());
		}
		
		//g.drawString("" + fitness, (int)location.getX(), (int)location.getY() - 6);
	}

	public DNA getDna() {
		return dna;
	}

	public void setDna(DNA dna) {
		this.dna = dna;
	}

	public PVector getLocation() {
		return location;
	}

	public void setLocation(PVector location) {
		this.location = location;
	}

	public PVector getVelocity() {
		return velocity;
	}

	public void setVelocity(PVector velocity) {
		this.velocity = velocity;
	}

	public PVector getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(PVector acceleration) {
		this.acceleration = acceleration;
	}

	public int getGeneCounter() {
		return geneCounter;
	}

	public void setGeneCounter(int geneCounter) {
		this.geneCounter = geneCounter;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void setShouldMove(boolean b) {
		shouldMove = b;
	}

	public boolean getShouldMove() {
		return shouldMove;
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
}
