import java.awt.Color;

public class DNA {
	PVector[] genes;
	

	DNA(int num) {
		genes = new PVector[num];
		Color rand = randomColor();
		for (int i = 0; i < genes.length; i++) {
			genes[i] = new PVector();
			genes[i] = (PVector) PVector.random();
			genes[i] = (PVector) genes[i].mult(0.2);
			genes[i].setColor(rand);
		}
	}

	public DNA crossover(DNA partner) {
		DNA child = new DNA(this.genes.length);

		//Picking a random “midpoint” in the genes array
		int midpoint = (int)(Math.random() * genes.length);

		for (int i = 0; i < genes.length; i++) {
			//Before midpoint copy genes from one parent, after midpoint copy genes from the other parent
			if (i > midpoint) child.genes[i] = genes[i];
			else child.genes[i] = partner.genes[i];
		}

		//Return the new child DNA
		return child;
	}

	void mutate(double mutationRate) {
		for (int i = 0; i < genes.length; i++) {
			if (Math.random() < mutationRate) {
				genes[i] = (PVector) PVector.random();
				genes[i] = (PVector) genes[i].mult(0.2);
				genes[i].setColor(randomColor());
			}
		}
	}
	
	public static Color randomColor() {
		return new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
	}
}