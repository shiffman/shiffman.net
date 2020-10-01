import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class GA_Shakespeare extends PApplet {// Genetic Algorithm, Evolving Shakespeare
// Daniel Shiffman <http://www.shiffman.net>

// Demonstration of using a genetic algorithm to perform a search

// setup()
//  # Step 1: The Population 
//    # Create an empty population (an array or ArrayList)
//    # Fill it with DNA encoded objects (pick random values to start)

// draw()
//  # Step 1: Selection 
//    # Create an empty mating pool (an empty ArrayList)
//    # For every member of the population, evaluate its fitness based on some criteria / function, 
//      and add it to the mating pool in a manner consistant with its fitness, i.e. the more fit it 
//      is the more times it appears in the mating pool, in order to be more likely picked for reproduction.

//  # Step 2: Reproduction Create a new empty population
//    # Fill the new population by executing the following steps:
//       1. Pick two "parent" objects from the mating pool.
//       2. Crossover -- create a "child" object by mating these two parents.
//       3. Mutation -- mutate the child's DNA based on a given probability.
//       4. Add the child object to the new population.
//    # Replace the old population with the new population
//  
//   # Rinse and repeat

// Created 2 May 2005

PFont f,fb;
String phrase;
int popmax;
float mutationRate;
Population popul;

public void setup() {
  size(200,200);
  fb = loadFont("Georgia-Bold-12.vlw");
  f = loadFont("Arial-BoldMT-11.vlw");
  phrase = "To be or not to be.";
  popmax = 150;
  mutationRate = 0.01f;

  // Create a population with a target phrase, mutation rate, and population max
  popul = new Population(phrase,mutationRate,popmax);
}

public void draw() {
  framerate(30);
  // Generate mating pool
  popul.naturalSelection();
  // Create next generation
  popul.generate();
  // Calculate fitness
  popul.calcFitness();
  displayInfo();
  
  // If we found the target phrase, stop
  if (popul.finished()) {
    noLoop();
  }
}

public void displayInfo() {
  background(100);
  // Display current status of population
  String answer = popul.getBest();
  textFont(fb);
  //textMode(SCREEN);
  textAlign(LEFT);
  fill(255);
  text(answer,20,100);
  
  textFont(f);
  text("total generations: " + popul.getGenerations(),20,140);
  text("average fitness: " + popul.getAverageFitness(),20,155);
  text("total population: " + popmax,20,170);
  text("mutation rate: " + PApplet.toInt(mutationRate * 100) + "%",20,185);
}




// Genetic Algorithm, Evolving Shakespeare
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a psuedo-DNA, i.e. genotype
//   Here, a virtual organism's DNA is an array of character.
//   Functionality:
//      -- convert DNA into a string
//      -- calculate DNA's "fitness"
//      -- mate DNA with another set of DNA
//      -- mutate DNA

// Created 2 May 2005

class DNA {

  // The genetic sequence
  char[] dna;
  
  // Constructor (makes a random DNA)
  DNA(int num) {
    dna = new char[num];
    for (int i = 0; i < dna.length; i++) {
      dna[i] = (char) random(32,128);  // Pick from range of chars
    }
  }
  
  // Constructor #2, creates the instance based on an existing character array
  DNA(char[] newdna) {
    dna = (char []) newdna.clone();
  }
  
  // Converts character array to a String
  public String getString() {
    return new String(dna);
  }
  
  // Fitness function (returns floating point % of "correct" characters)
  public float fitness (String target) {
     int score = 0;
     for (int i = 0; i < dna.length; i++) {
        if (dna[i] == target.charAt(i)) {
          score++;
        }
     }
     float fitness = (float)score / (float)target.length();
     return fitness;
  }
  
  // Returns one element from char array 
  public char getDNA(int index) {
    return dna[index];
  }
  
  // Crossover
  public DNA mate(DNA partner) {
    // A new child
    char[] child = new char[dna.length];
    int crossover = PApplet.toInt(random(dna.length)); // Pick a midpoint
    // Half from one, half from the other
    for (int i = 0; i < dna.length; i++) {
      if (i > crossover) child[i] = dna[i];
      else               child[i] = partner.getDNA(i);
    }
    // make a new DNA object
    DNA newdna = new DNA(child);
    return newdna;
  }
  
  // Based on a mutation probability, picks a new random character
  public void mutate(float m) {
    for (int i = 0; i < dna.length; i++) {
      if (random(1) < m) {
        dna[i] = (char) random(32,128);
      }
    }
  }
}

// Genetic Algorithm, Evolving Shakespeare
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a population of virtual organisms
// In this case, each organism is just an instance of a DNA object

// Created 2 May 2005



class Population {

  int MAX;                      // Population maximum
  float mutationRate;           // Mutation rate
  DNA[] population;             // Array to hold the current population
  float[] fitness;              // Separate array to hold corresponding fitness value for each "organism"
  ArrayList darwin;             // ArrayList which we will use for our "mating pool"
  String phrase;                // Target phrase
  int generations;              // Number of generations
  boolean finished;             // Are we finished evolving?

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

  // Fill our fitness array with a value for every member of the population
  public void calcFitness() {
    for (int i = 0; i < population.length; i++) {
      fitness[i] = population[i].fitness(phrase);
    }
  }

  // Generate a mating pool
  public void naturalSelection() {
    // Clear the ArrayList
    darwin.clear();

    // Calculate total fitness of whole population
    float totalFitness = getTotalFitness();

    // Calculate *normalized* fitness for each member of the population
    // based on normalized fitness, each member will get added to the mating pool a certain number of times
    // a higher fitness = more entries to mating pool = more likely to be picked as a parent
    // a lower fitness = fewer entries to mating pool = less likely to be picked as a parent
    for (int i = 0; i < population.length; i++) {
      float fitnessNormal = fitness[i] / totalFitness;
      int n = (int) (fitnessNormal * 10000.0f);  // There are better ways to do this. . . 
      for (int j = 0; j < n; j++) {
        darwin.add(population[i]);
      }
    }
  }

  // Create a new generation
  public void generate() {
    // Refill the population with children from the mating pool
    for (int i = 0; i < population.length; i++) {
      int m = PApplet.toInt(random(darwin.size()));
      int d = PApplet.toInt(random(darwin.size()));
      DNA mom = (DNA) darwin.get(m);
      DNA dad = (DNA) darwin.get(d);
      DNA child = mom.mate(dad);
      child.mutate(mutationRate);
      population[i] = child;
    }
    generations++;
  }
  
  
  // Compute the current "most fit" member of the population
  public String getBest() {
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

  public boolean finished() {
    return finished;
  }

  public int getGenerations() {
    return generations;
  }

  // Compute total fitness for the population
  public float getTotalFitness() {
    float total = 0;
    for (int i = 0; i < population.length; i++) {
      total += fitness[i];
    }
    return total;
  }


  // Compute average fitness for the population
  public float getAverageFitness() {
    float total = 0;
    for (int i = 0; i < population.length; i++) {
      total += fitness[i];
    }
    return total / (float) population.length;
  }

}
static public void main(String args[]) {   PApplet.main(new String[] { "GA_Shakespeare" });}}