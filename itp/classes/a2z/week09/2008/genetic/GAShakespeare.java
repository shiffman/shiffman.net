//Genetic Algorithm, Evolving Shakespeare
//Daniel Shiffman <http://www.shiffman.net>

//Demonstration of using a genetic algorithm to perform a search
//# Initialization
//# Step 1: The Population 
//# Create an empty population (an array or ArrayList)
//# Fill it with DNA encoded objects (pick random values to start)

//# Loop Over and Over
//# Step 1: Selection 
//# Create an empty mating pool (an empty ArrayList)
//# For every member of the population, evaluate its fitness based on some criteria / function, 
//and add it to the mating pool in a manner consistant with its fitness, i.e. the more fit it 
//is the more times it appears in the mating pool, in order to be more likely picked for reproduction.
//# Step 2: Reproduction Create a new empty population
//# Fill the new population by executing the following steps:
//1. Pick two "parent" objects from the mating pool.
//2. Crossover -- create a "child" object by mating these two parents.
//3. Mutation -- mutate the child's DNA based on a given probability.
//4. Add the child object to the new population.
//# Replace the old population with the new population
//# Rinse and repeat


package genetic;

public class GAShakespeare {

	public static void main(String[] args) {

		String phrase = "To be or not to be.";
		int popmax = 150;
		float mutationRate = 0.01f;

		Population popul = new Population(phrase,mutationRate,popmax);

		while (!popul.finished()) {
			// Generate mating pool
			popul.naturalSelection();
			// Create next generation
			popul.generate();
			// Calculate fitness
			popul.calcFitness();

			String answer = popul.getBest();
			System.out.print(answer);
			System.out.print("  total generations: " + popul.getGenerations());
			System.out.print("  average fitness: " + popul.getAverageFitness());
			System.out.println();
		}


	}
}
