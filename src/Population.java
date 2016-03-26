import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Population {
	private Rocket[] pop;
	private Mover[] obstacles;
	private boolean done;
	private Mover target;
	private double mutationRate = 0.01;
	private int generation;
	public Population(int num, Mover m){
		generation = 0;
		this.target = m;
		done = false;
		pop = new Rocket[num];

		for (int i = 0; i < num; i++){
			pop[i] = new Rocket();
		}
	}

	public void draw(Graphics g){
		for (Rocket r : pop){
			r.draw(g);
		}
	}

	public void update(){
		int stopped = 0;
		for (Rocket r : pop){
			if (!r.getShouldMove()){
				stopped++;
				
			}
			
			if (stopped >= pop.length || r.getGeneCounter() >= r.getDna().genes.length){
				done = true;
			}
		}

		if (!done){
			for (Rocket r : pop){
				r.update();
			}
		}
		
		if (obstacles != null){
			for (Mover m : obstacles){
				for (Rocket r : pop){
					if (m.intersects(new Mover(r.location.getX(), r.location.getY(), r.getHeight(), r.getHeight()))){
						r.velocity.mult(0);
						r.setShouldMove(false);
					}
				}
			}
		}
	}

	public Rocket fittest(){
		Rocket mostFit = pop[0];
		for (Rocket r : pop){
			if (r.getFitness() > mostFit.getFitness()){
				mostFit = r;
			}
		}
		return mostFit;
	}

	public static Rocket selectMate(ArrayList<Rocket> population){
		Rocket choice = null;

		while (choice == null){
			int pos = (int) (Math.random() * population.size());

			if (Math.random () < population.get(pos).getFitness()){
				choice = population.get(pos);
			}
		}

		return choice;
	}

	public void calculateFitness() {
		double worst = worstDistance();
		for (int i = 0; i < pop.length; i++){
			Rocket r = pop[i];
			if (!r.getShouldMove()){
				r.setFitness(0.01);
				continue;
			}
			int distance = (int) (Point2D.distance(r.location.getX(), r.location.getY(), target.getX(), target.getY()));
			double fit = 1.0 / distance;
			fit *= worst / 100;
			if (distance <= 1){
				pop[i].setFitness(1);
			}else{
				pop[i].setFitness(fit);
			}
		}
	}

	public double worstDistance(){
		double worst = Integer.MIN_VALUE;

		for (int i = 0; i < pop.length; i++){
			Rocket r = pop[i];
			double distance = Point2D.distance(r.location.getX(), r.location.getY(), target.getX(), target.getY());
			if (distance > worst){
				worst = distance;
			}
		}

		return worst;
	}

	public void evolveNextGen(){
		ArrayList<Rocket> matingPool = new ArrayList<Rocket>();

		for (int i = 0; i < pop.length; i++) {
			matingPool.add(pop[i]);
		}

		//Step 3: Reproduction
		for (int i = 0; i < pop.length; i++) {

			Rocket partnerA = selectMate(matingPool);
			Rocket partnerB = selectMate(matingPool);

			while (partnerA == partnerB){
				partnerB = selectMate(matingPool);
			}
			//Step 3a: Crossover
			Rocket child = new Rocket();
			child.setDna(partnerA.getDna().crossover(partnerB.getDna()));
			//Step 3b: Mutation
			child.getDna().mutate(mutationRate);

			//Note that we are overwriting the pop with the new children. When draw() loops, we will perform all the same steps with the new pop of children.
			pop[i] = child;
		}
		
		generation++;
	}


	public void setAllX(double x){
		for (Rocket r : pop){
			r.setLocation(new PVector(x, r.location.getY()));
		}
	}

	public void setAllY(double y){
		for (Rocket r : pop){
			r.setLocation(new PVector(r.location.getX(), y));
		}
	}

	public Rocket[] getPop() {
		return pop;
	}

	public void setPop(Rocket[] pop) {
		this.pop = pop;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Mover getTarget() {
		return target;
	}

	public void setTarget(Mover target) {
		this.target = target;
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public Mover[] getObstacles() {
		return obstacles;
	}

	public void setObstacles(Mover[] obstacles) {
		this.obstacles = obstacles;
	}

}