import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class pathfinding extends PApplet {

// Pathfinding w/ Genetic Algorithms
// Daniel Shiffman <http://www.shiffman.net>

// This example produces an obstacle course with a start and finish
// Virtual "creatures" are rewarded for making it closer to the finish
// a simple "pathfinding" AI program

// For ever creature, we grid out the screen and their DNA offers
// a steering vector for each cell on the screen

int gridscale = 24;              //scale of grid is 1/24 of screen size

// DNA needs one vector for every spot on the grid 
// (it's like a pixel array, but with vectors instead of colors)
int dnasize;

int lifetime;// = 200;  // How long should each generation live

// Global maxforce and maxspeed (hmmm, could make this part of DNA??)
float maxspeed = 4.0f;
float maxforce = 1.0f;

Population popul;  //population
int lifecycle;     //timer for cycle of generation
int recordtime;    //fastest time to target
Obstacle target;   //target location
Obstacle start;    //start location
int diam = 24;     //size of target
PFont f;           //font for display

ArrayList obstacles;  //an array list to keep track of all the obstacles!

public void setup() {
  size(640,480);
  dnasize = (width / gridscale) * (height / gridscale); 

  f = loadFont("GillSans-12.vlw");
  smooth();
  
  
  lifetime = width/2;

  //*initialize the globals*//
  lifecycle = 0;
  recordtime = lifetime;
  target = new Obstacle(width-diam-diam/2,height/2-diam/2,diam,diam);
  start = new Obstacle(diam/2,height/2-diam/2,diam,diam);

  //*create a population with a mutation rate, and population max*//
  int popmax = 1000;
  float mutationRate = 0.05f;
  popul = new Population(mutationRate,popmax);

  //*Create the obstacle course!*//
  
  obstacles = new ArrayList();
  obstacles.add(new Obstacle(width/4,40,10,height-80));
  obstacles.add(new Obstacle(width/2,0,10,height/2-10));
  obstacles.add(new Obstacle(width/2,height-height/2+10,10,height/2-10));
  obstacles.add(new Obstacle(2*width/3,height/2-height/8,10,height/4));
}

public void draw() {
  background(255);

  //*DRAW THE START NAND TARGET*//
 start.render();
 target.render();
  
  //*DRAW THE OBSTACLES*//
  for (int i = 0; i < obstacles.size(); i++) {
    Obstacle obs = (Obstacle) obstacles.get(i);
    obs.render();
  }

  //**IF WE HAVEN'T REACHED THE END OF OUR LIVES, KEEP GOING!*//
  if (lifecycle < lifetime) {
    popul.live(obstacles);
    if ((popul.targetReached()) && (lifecycle < recordtime)) {
      recordtime = lifecycle;
    }
    lifecycle++;
    //**OTHERWISE, LET'S MAKE A NEW GENERATION*//
  } 
  else {
    lifecycle = 0;
    popul.calcFitness();
    popul.naturalSelection();
    popul.generate();
  }

  //DISPLAY SOME INFO
  textFont(f);
   textAlign(RIGHT);
   fill(0);
   text("Generation #:" + popul.getGenerations(),width-10,18);
   text("Cycles left:" + ((lifetime-lifecycle)/10),width-10,36);
   text("Record cycles: " + recordtime,width-10,54);
   
}

//**MOVE THE TARGET IF WE CLICK THE MOUSE**//
public void mousePressed() {
  //obstacles.add(new Obstacle(mouseX,mouseY));
  target = new Obstacle(mouseX,mouseY,diam,diam);
  recordtime = lifetime;
}

// Pathfinding w/ Genetic Algorithms
// Daniel Shiffman <http://www.shiffman.net>

// Creature class -- this is just like our Boid / Particle class
// the only difference is that it has DNA & fitness

class Creature {

  //*ALL THE STUFF WE HAD BEFORE*//
  PVector loc;
  PVector vel;
  PVector acc;
  float r;
  float recordDist;

  //**FITNESS AND DNA**//
  float fitness;
  DNA genes;

  boolean stopped;  //am I stuck?
  int finish;       //what was my finish? (first, second, etc. . . )

  //constructor
  Creature(PVector l, DNA dna, int f) {
    acc = new PVector(0.0f,0.0f,0.0f);
    vel = new PVector(0.0f,0.0f,0.0f);
    loc = l.get();
    r = 4.0f;
    genes = dna;
    stopped = false;
    finish = f;
    recordDist = width;
  }

  //***FITNESS FUNCTION*****//
  // distance = distance from target
  // finish = what order did i finish (first, second, etc. . .)
  // f(distance,finish) =   (1.0f / finish^1.5) * (1.0f / distance^6);
  // a lower finish is rewarded (exponentially) and/or shorter distance to target (exponetially)
  public void calcFitness() {
    float d = recordDist; //PVector.distance(loc,target);
    if (d < diam/2) {
      d = 1.0f;
    }
    //reward finishing faster and getting closer
    fitness = (1.0f / pow(finish,1.5f)) * (1.0f / (pow(d,6)));
  }

  public void setFinish(int f) {
    finish = f;
    //print(finish + " ");
  }

  //Run in relation to all the obstacles
  //If I'm stuck, don't bother updating or checking for intersection
  public void run(ArrayList o) {
    if (!stopped) {
      update();
      //if I hit an edge or an obstacle
      if ((borders()) || (obstacles(o))) {
        stopped = true;
      }
    }
    //draw me!
    render();
  }

  //*DID I HIT AN EDGE??*//
  public boolean borders() {
    if ((loc.x < 0) || (loc.y < 0) || (loc.x > width) || (loc.y > height)) {
      return true;
    } 
    else {
      return false;
    }
  }

  //**DID I MAKE IT TO THE TARGET??*//
  public boolean finished() {
    float d = dist(loc.x,loc.y,target.r.x,target.r.y);
    if (d < recordDist) recordDist = d;
    //if ( d < diam/2) {
    if (target.contains(loc)) {
      stopped = true;
      return true;
    }
    return false;
  }

  //**DID I HIT AN OBSTACLE?**//
  public boolean obstacles(ArrayList o) {
    for (int i = 0; i < o.size(); i++) {
      Obstacle obs = (Obstacle) o.get(i);
      if (obs.contains(loc)) {
        return true;
      }
    }
    return false;
  }

  public void update() {
    if (!finished()) {
      //where are we?  Our location will tell us what steering vector to look up in our DNA;
      int x = (int) loc.x/gridscale;
      int y = (int) loc.y/gridscale;
      x = constrain(x,0,width/gridscale-1);  //make sure we are not off the edge
      y = constrain(y,0,height/gridscale-1); //make sure we are not off the edge

      //Get the steering vector out of our genes in the right spot
      acc.add(genes.getGene(x+y*width/gridscale));

      //this is all the same stuff we've done before
      acc.mult(maxforce);
      vel.add(acc);
      vel.limit(maxspeed);
      loc.add(vel);
      acc.mult(0);
    }
  }

  public void render() {
    fill(0,150);
    stroke(0);
    ellipse(loc.x,loc.y,r,r);
  }

  public float getFitness() {
    return fitness;
  }

  public DNA getGenes() {
    return genes;
  }

  public boolean stopped() {
    return stopped;
  }

}
// Pathfinding w/ Genetic Algorithms
// Daniel Shiffman <http://www.shiffman.net>

// DNA is an array of vectors

class DNA {

  //The genetic sequence
  PVector[] dna;
  
  //Constructor (makes a DNA of random Vectors)
  DNA(int num) {
    dna = new PVector[num];
    for (int i = 0; i < dna.length; i++) {
      dna[i] = new PVector(random(-1,1),random(-1,1));
      dna[i].normalize();
    }
  }
  
  //Constructor #2, creates the instance based on an existing array
  DNA(PVector[] newdna) {
    //dna = (PVector []) newdna.clone();  //not working as an applet?
    dna = newdna;
  }
  
  //returns one element from char array 
  public PVector getGene(int index) {
    // just doing some error handling
    if (index < dna.length) return dna[index].get();
    else return new PVector(0,0);
  }
  
  //**CROSSOVER***//
  //creates new DNA sequence from two (this & and a partner)
  public DNA mate(DNA partner) {
    PVector[] child = new PVector[dna.length];
    //*pick a midpoint*//
    int crossover = PApplet.parseInt(random(dna.length));
    //*take "half" from one and "half" from the other*//
    for (int i = 0; i < dna.length; i++) {
      if (i > crossover) child[i] = getGene(i);
      else               child[i] = partner.getGene(i);
    }
    
    DNA newdna = new DNA(child);
    return newdna;
  }
  
  //based on a mutation probability, picks a new random Vector
  public void mutate(float m) {
    for (int i = 0; i < dna.length; i++) {
      if (random(1) < m) {
         dna[i] = new PVector(random(-1,1),random(-1,1));
         dna[i].normalize();
      }
    }
  }
}
// Pathfinding w/ Genetic Algorithms
// Daniel Shiffman <http://www.shiffman.net>

// A class for an obstacle, just a simple rectangle that is drawn
// and can check if a creature touches it

// Also using this class for starting point and target location

class Obstacle {

  Rectangle r;

  Obstacle(int x, int y, int w, int h) {
    r = new Rectangle(x,y,w,h);
  }



  public void render() {
    smooth();
    stroke(0);
    fill(175);
    rectMode(CORNER);
    rect(r.x,r.y,r.width,r.height);
  }

  public boolean contains(PVector spot) {
    if (r.contains((int)spot.x,(int)spot.y)) {
      return true;
    } else {
      return false;
    }
  }

}
// Pathfinding w/ Genetic Algorithms
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a population of "creatures"

class Population {

  int MAX;                      //population maximum
  float mutationRate;           //mutation rate
  Creature[] population;        //array to hold the current population
  ArrayList darwin;             //ArrayList which we will use for our "mating pool"
  int generations;              //number of generations

  int countfinished;            //keep track of the order of creature's finishing the maze

  //*INITIALIZE THE POPULATION*//
  Population(float m, int num) {
    mutationRate = m;
    MAX = num;
    population = new Creature[MAX];
    darwin = new ArrayList();
    generations = 0;
    //make a new set of creatures
    for (int i = 0; i < population.length; i++) {
      population[i] = new Creature(new PVector(start.r.x+start.r.width/2,start.r.y+start.r.height/2), new DNA(dnasize),population.length);
    }
    countfinished = 1;  //the first one to finish will be #1
  }

  public void live(ArrayList o) {
    //For every creature
    for (int i = 0; i < population.length; i++) {
      //if it finishes, mark it down as done!
      if ((population[i].finished()) && (!population[i].stopped())) {
        population[i].setFinish(countfinished);
        countfinished++;
      }
      //run it
      population[i].run(o);
    }
  }

  //did anything finish?
  public boolean targetReached() {
    for (int i = 0; i < population.length; i++) {
      if (population[i].finished()) return true;
    }
    return false;
  }

  //calculate fitness for each creature
  public void calcFitness() {
    for (int i = 0; i < population.length; i++) {
      population[i].calcFitness();
    }
    countfinished = 1;  //hmmm, awkward place for this, we have to reset this for the next generation
  }

  //generate a mating pool
  public void naturalSelection() {
    //clear the ArrayList
    darwin.clear();

    //Calculate total fitness of whole population
    float totalFitness = getTotalFitness();

    //Calculate *normalized* fitness for each member of the population
    //based on normalized fitness, each member will get added to the mating pool a certain number of times a la roulette wheel
    //a higher fitness = more entries to mating pool = more likely to be picked as a parent
    //a lower fitness = fewer entries to mating pool = less likely to be picked as a parent
    for (int i = 0; i < population.length; i++) {
      float fitnessNormal = population[i].getFitness() / totalFitness;
      int n = (int) (fitnessNormal * 50000.0f);
      //print(n + " ");
      for (int j = 0; j < n; j++) {
        darwin.add(population[i]);
      }
    }
    //println();
    //println("-------------------------------------------------------");
  }

  //*CREATE A NEW GENERATION**//
  public void generate() {
    //refill the population with children from the mating pool
    for (int i = 0; i < population.length; i++) {
      int m = PApplet.parseInt(random(darwin.size()));
      int d = PApplet.parseInt(random(darwin.size()));
      //pick two parents
      Creature mom = (Creature) darwin.get(m);
      Creature dad =  (Creature) darwin.get(d);
      //get their genes
      DNA momgenes = mom.getGenes();
      DNA dadgenes = dad.getGenes();
      //mate their genes
      DNA child = momgenes.mate(dadgenes);
      //mutate their genes
      child.mutate(mutationRate);
      //fill the new population with the new child
      population[i] = new Creature(new PVector(start.r.x,start.r.y), child,population.length);
    }
    generations++;
  }

  public int getGenerations() {
    return generations;
  }

  //compute total fitness for the population
  public float getTotalFitness() {
    float total = 0;
    for (int i = 0; i < population.length; i++) {
      total += population[i].getFitness();
    }
    return total;
  }

}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "pathfinding" });
  }
}
