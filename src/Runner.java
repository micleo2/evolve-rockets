import java.util.ArrayList;

public class Runner {
	/*public static String target = "ellie";

	public static void main(String[] args){
		int life = 700;
		double mutationRate;
		//Population total
		int totalPopulation = 200;

		//Population array
		DNA[] population;

		mutationRate = 0.01;

		//Step 1: Initialize Population
		population = new DNA[totalPopulation];
		for (int i = 0; i < population.length; i++) {
			population[i] = new DNA();
		}
		DNA mostFit = null;
		int g = 0;
		for (;;){
			g++;
			System.out.println("GENERATION " + g + ":");
			mostFit = population[0];
			//Step 2: Selection

			//Step 2a: Calculate fitness.
			for (int i = 0; i < population.length; i++) {
				population[i].fitness();
				
				if (mostFit.fitness < population[i].fitness){
					mostFit = population[i];
				}
				
			}
			
			System.out.println("\tMost fit: " + mostFit.getPhrase());
			
			if (mostFit.fitness == 1){
				break;
			}

			//Step 2b: Build mating pool.
			ArrayList<DNA> matingPool = new ArrayList<DNA>();

			for (int i = 0; i < population.length; i++) {
				//Add each member n times according to its fitness score.
				int n = (int)(population[i].fitness * 100);
				for (int j = 0; j < n; j++) {
					matingPool.add(population[i]);
				}
			}
			
			//Step 3: Reproduction
			for (int i = 0; i < population.length; i++) {
				int a = (int)(Math.random() * matingPool.size());
				int b = (int)(Math.random() * matingPool.size());
				while (b != a){
					b = (int)(Math.random() * matingPool.size());
				}
				DNA partnerA = matingPool.get(a);
				DNA partnerB = matingPool.get(b);
				//Step 3a: Crossover
				DNA child = partnerA.crossover(partnerB);
				//Step 3b: Mutation
				child.mutate(mutationRate);

				//Note that we are overwriting the population with the new children. When draw() loops, we will perform all the same steps with the new population of children.
				population[i] = child;
			}
		}
		double avgFit = 0;
		for (DNA d : population){
			avgFit += d.fitness;
		}
		avgFit /= population.length;
		System.out.println("Evolution of system finished. After " + g + " generations, the product is: " + mostFit.getPhrase());
		System.out.println("Average fitness: " + (avgFit * 100) + "%");
		System.out.println("Members in population: " + totalPopulation);
	}*/
}