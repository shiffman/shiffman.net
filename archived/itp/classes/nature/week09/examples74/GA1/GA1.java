import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class GA1 extends PApplet {PFont f48,f18;
String phrase;
int popmax;
float mutationRate;
Population popul;

public void setup() {
  size(500,160);
  f48 = loadFont("Georgia-48.vlw");
  f18 = loadFont("ArialMT-18.vlw");
  phrase = "To be or not to be.";
  popmax = 150;
  mutationRate = 0.01f;

  //*create a population with a target phrase, mutation rate, and population max*//
  popul = new Population(phrase,mutationRate,popmax);
}

public void draw() {
  popul.naturalSelection();
  popul.generate();
  popul.calcFitness();
  displayInfo();

  if (popul.finished()) {
    noLoop();
  }
}

void displayInfo() {
  background(0);
  //*Display current status of population*//
  String answer = popul.getBest();
  textFont(f48,48);
  textMode(ALIGN_LEFT);
  stroke(255);
  text(answer,10,75);
  textFont(f18,18);
  text("total generations: " + popul.getGenerations(),10,120);
  text("average fitness: " + popul.getAverageFitness(),220,120);
  text("total population: " + popmax,10,140);
  text("mutation rate: " + PApplet.toInt(mutationRate * 100) + "%",220,140);
}

/* A class to describe a psuedo-DNA, i.e. genotype
   Here, a virtual organism's DNA is an array of character.
   Functionality:
      -- convert DNA into a string
      -- calculate DNA's "fitness"
      -- mate DNA with another set of DNA
      -- mutate DNA
*/       

class DNA {

  //The genetic sequence
  char[] dna;
  
  //Constructor (makes a random DNA)
  DNA(int num) {
    dna = new char[num];
    for (int i = 0; i < dna.length; i++) {
      dna[i] = (char) random(0,255);
    }
  }
  
  //Constructor #2, creates the instance based on an existing character array
  DNA(char[] newdna) {
    dna = (char []) newdna.clone();
  }
  
  //converts character array to a String and returns in
  String getString() {
    return new String(dna);
  }
  
  //Fitness function (returns floating point % of "correct character)
  float fitness (String target) {
     int score = 0;
     for (int i = 0; i < dna.length; i++) {
        if (dna[i] == target.charAt(i)) {
          score++;
        }
     }
     float fitness = (float)score / (float)target.length();
     return fitness;
  }
  
  //returns one element from char array 
  char getDNA(int index) {
    return dna[index];
  }
  
  //**CROSSOVER***//
  //creates new DNA sequence from two (this & 
  DNA mate(DNA partner) {
    char[] child = new char[dna.length];
    int crossover = PApplet.toInt(random(dna.length));
    
    for (int i = 0; i < dna.length; i++) {
      if (i > crossover) child[i] = dna[i];
      else               child[i] = partner.getDNA(i);
    }
    
    DNA newdna = new DNA(child);
    return newdna;
  }
  
  //based on a mutation probability, picks a new random character in array spots
  void mutate(float m) {
    for (int i = 0; i < dna.length; i++) {
      if (random(1) < m) {
        dna[i] = (char) random(0,255);
      }
    }
  }
}

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
}