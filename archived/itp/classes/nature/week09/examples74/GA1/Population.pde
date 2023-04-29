//A class to describe a population of virtual organisms
//in this case, each organism is just an instance of a DNA object

class Population {

  int MAX;                      //population maximum
  float mutationRate;           //mutation rate
  DNA[] population;             //array to hold the current population
  float[] fitness;              //separate array to hold corresponding fitness value for each "organism"
  ArrayList darwin;             //ArrayList which we will use for our "mating pool"
  String phrase;                //Target phrase
  int generations;              //number of generations
  boolean finished;             //are we finished evolving?

  //*INITIALIZE THE POPULATION*//
  Population(String p, float m, int num) {
    phrase = p;
    mutationRate = m;
    MAX = num;
    population = new DNA[MAX];
    fitness = new float[MAX];
    for (int i = 0; i < population.length; i++) {
      population[i] = new DNA(phrase.length());
    }
    calcFitness();
    darwin = new ArrayList();
    finished = false;
    generations = 0;
  }

  //Fill our fitness array with a value for every member of the population
  void calcFitness() {
    for (int i = 0; i < population.length; i++) {
      fitness[i] = population[i].fitness(phrase);
    }
  }

  //generate a mating pool
  void naturalSelection() {
    //clear the ArrayList
    darwin.clear();

    //Calculate total fitness of whole population
    float totalFitness = getTotalFitness();

    //Calculate *normalized* fitness for each member of the population
    //based on normalized fitness, each member will get added to the mating pool a certain number of times
    //a higher fitness = more entries to mating pool = more likely to be picked as a parent
    //a lower fitness = fewer entries to mating pool = less likely to be picked as a parent
    for (int i = 0; i < population.length; i++) {
      float fitnessNormal = fitness[i] / totalFitness;
      int n = (int) (fitnessNormal * 10000.0f);
      for (int j = 0; j < n; j++) {
        darwin.add(population[i]);
      }
    }
  }

  //*CREATE A NEW GENERATION**//
  void generate() {
    //refill the population with children from the mating pool
    for (int i = 0; i < population.length; i++) {
      int m = int(random(darwin.size()));
      int d = int(random(darwin.size()));
      DNA mom = (DNA) darwin.get(m);
      DNA dad = (DNA) darwin.get(d);
      DNA child = mom.mate(dad);
      child.mutate(mutationRate);
      population[i] = child;
    }
    generations++;
  }
  
  
  //Compute the current "most fit" member of the population
  String getBest() {
    float worldrecord = 0.0f;
    int index = 0;
    for (int i = 0; i < population.length; i++) {
      if (fitness[i] > worldrecord) {
        index = i;
        worldrecord = fitness[i];
      }
    }

    if (worldrecord == 1.0f) finished = true;
    return population[index].getString();
  }

  boolean finished() {
    return finished;
  }

  int getGenerations() {
    return generations;
  }

  //compute total fitness for the population
  float getTotalFitness() {
    float total = 0;
    for (int i = 0; i < population.length; i++) {
      total += fitness[i];
    }
    return total;
  }


  //compute average fitness for the population
  float getAverageFitness() {
    float total = 0;
    for (int i = 0; i < population.length; i++) {
      total += fitness[i];
    }
    return total / (float) population.length;
  }

}
