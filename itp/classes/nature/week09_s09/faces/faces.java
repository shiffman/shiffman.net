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

public class faces extends PApplet {

// Interactive Selection
// http://www.genarts.com/karl/papers/siggraph91.html
// Daniel Shiffman <http://www.shiffman.net>

PFont f;
Population popul;
Button button;

public void setup() {
  size(780,200);
  colorMode(RGB,1.0f);
  f = loadFont("GillSans-12.vlw");
  smooth();
  int popmax = 10;
  float mutationRate = 0.05f;  // A pretty high mutation rate here, our population is rather small we need to enforce variety
  // Create a population with a target phrase, mutation rate, and population max
  popul = new Population(mutationRate,popmax);
  // A simple button class
  button = new Button(15,30,190,20, "evolve new generation");
}

public void draw() {
  background(1.0f);
  int mx = mouseX; int my = mouseY;
  // Display the faces
  popul.display();
  popul.rollover(mx,my);
  // Display some text
  textFont(f);
  textAlign(LEFT);
  fill(0);
  text("Generation #:" + popul.getGenerations(),15,18);

  // Display the button
  button.render();
  button.rollover(mx,my);

}

// If the button is clicked, evolve next generation
public void mousePressed() {
  if (button.clicked(mouseX,mouseY)) {
    popul.naturalSelection();
    popul.generate();
  }
}

public void mouseReleased() {
  button.released();
}
// Interactive Selection
// http://www.genarts.com/karl/papers/siggraph91.html
// Daniel Shiffman <http://www.shiffman.net>

class Button {
  Rectangle r;  //button's rectangle
  String txt;   //button's text
  boolean clicked;  //did i click on it?
  boolean rollover; //did i rollover it?

  Button(int x, int y, int w, int h, String s) {
    r = new Rectangle(x,y,w,h);
    txt = s;
  }

  public void render() {
    //draw rectangle and text based on whether rollover or clicked
    rectMode(CORNER);
    stroke(0); noFill();
    if (rollover) fill(0.5f);
    if (clicked) fill(0);
    rect(r.x,r.y,r.width,r.height);
    float b = 0.0f;
    if (clicked) b = 1;
    else if (rollover) b = 0.2f;
    else b = 0;
    fill(b);
    textAlign(LEFT);
    text(txt,r.x+10,r.y+14);

  }
  
  
  //methods to check rollover, clicked, or released (must be called from appropriate
  //places in draw, mousePressed, mouseReleased
  public boolean rollover(int mx, int my) {
    if (r.contains(mx,my)) rollover = true;
    else rollover = false;
    return rollover;
  }

  public boolean clicked(int mx, int my) {
    if (r.contains(mx,my)) clicked = true;
    return clicked;
  }

  public void released() {
    clicked = false;
  }

}
// Interactive Selection
// http://www.genarts.com/karl/papers/siggraph91.html
// Daniel Shiffman <http://www.shiffman.net>

class DNA {

  //The genetic sequence
  float[] dna;
  int len = 20;  //arbitrary length
  
  //Constructor (makes a random DNA)
  DNA() {
    //DNA is random floating point values between 0 and 1 (!!)
    dna = new float[len];
    for (int i = 0; i < dna.length; i++) {
      dna[i] = random(0,1);
    }
  }
  
  DNA(float[] newdna) {
    //dna = (float []) newdna.clone();  //not working as an applet?
    dna = newdna;
  }
  
  //returns one element from array 
  public float getGene(int index) {
    return dna[index];
  }
  
  //**CROSSOVER***//
  //creates new DNA sequence from two (this & 
  public DNA mate(DNA partner) {
    float[] child = new float[dna.length];
    int crossover = PApplet.parseInt(random(dna.length));
    for (int i = 0; i < dna.length; i++) {
      if (i > crossover) child[i] = getGene(i);
      else               child[i] = partner.getGene(i);
    }
    DNA newdna = new DNA(child);
    return newdna;
  }
  
  //based on a mutation probability, picks a new random character in array spots
  public void mutate(float m) {
    for (int i = 0; i < dna.length; i++) {
      if (random(1) < m) {
         dna[i] = random(0,1);
      }
    }
  }
}
// Interactive Selection
// http://www.genarts.com/karl/papers/siggraph91.html
// Daniel Shiffman <http://www.shiffman.net>

// The class for our "face", contains DNA sequence, fitness value, position on screen

// Fitness Function f(t) = t (where t is "time" mouse rolls over face)

class Face {

  DNA genes;      //face's DNA
  float fitness;  //how good is this face?
  float x,y;      //position on screen
  int wh = 70;    //size of rectangle enclosing face
  boolean rollover; //are we rolling over this face?

  //Create a new face
  Face(DNA dna, float x_, float y_) {
    genes = dna;
    x = x_; y = y_;
    fitness = 1;
  }

  //render the face
  public void render() {
     /* ok, so here, we are using the elements from the "genes" to pick properties for this face
        such as: head size, color, eye position, etc.
        Now, since every gene is a floating point between 0 and 1, we scale those values
        appropriately.*/
    float r = genes.getGene(0)*70.0f;
    int c = color(genes.getGene(1),genes.getGene(2),genes.getGene(3));
    float eye_y = genes.getGene(4)*5.0f;
    float eye_x = genes.getGene(5)*10.0f;
    float eye_size = genes.getGene(6)*10.0f;
    int eyecolor = color(genes.getGene(4),genes.getGene(5),genes.getGene(6));
    int mouthColor = color(genes.getGene(7),genes.getGene(8),genes.getGene(9));
    float mouth_y = genes.getGene(10)*25.0f;
    float mouth_x = genes.getGene(11)*50.0f-25.0f;
    float mouthw = genes.getGene(11)*50;
    float mouthh = genes.getGene(12)*10;
    
    //once we calculate all the above properties, we use those variables to draw rects, ellipses, etc.
    pushMatrix();
    translate(x,y);
    noStroke();
    
    //*draw the "head"*//
    smooth();
    fill(c);
    ellipseMode(CENTER);
    ellipse(0,0,r,r);

    //*draw the "eyes"*//
    fill(eyecolor);
    rectMode(CENTER);
    rect(-eye_x,-eye_y,eye_size,eye_size);
    rect( eye_x,-eye_y,eye_size,eye_size);

    //*draw the "mouth"*//
    fill(mouthColor);
    rectMode(CENTER);
    rect(mouth_x,mouth_y,mouthw,mouthh);

    //*draw the bounding box*//
    stroke(0.25f);
    if (rollover) fill(0,0.25f);
    else noFill();
    rectMode(CENTER);
    rect(0,0,wh,wh);
    popMatrix();

    //*write the fitness to the screen*//
    textFont(f);
    textAlign(CENTER);

    if (rollover) fill(0);
    else fill(0.25f);
    text(PApplet.parseInt(fitness),x,y+70);

  }

  public float getFitness() {
    return fitness;
  }

  public DNA getGenes() {
    return genes;
  }

  //increment fitness if mouse is rolling over face
  public void rollover(int mx, int my) {
    //using java.awt.Rectangle (see: http://java.sun.com/j2se/1.4.2/docs/api/java/awt/Rectangle.html)
    Rectangle r = new Rectangle(PApplet.parseInt(x-wh/2),PApplet.parseInt(y-wh/2),PApplet.parseInt(wh),PApplet.parseInt(wh));
    if (r.contains(mx,my)) {
      rollover = true;
      fitness += 0.25f;
    } else {
      rollover = false;
    }
  }

}
// Interactive Selection
// http://www.genarts.com/karl/papers/siggraph91.html
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a population of faces
// this hasn't changed very much from example to example

class Population {

  int MAX;                      //population maximum
  float mutationRate;           //mutation rate
  Face[] population;            //arraylist to hold the current population
  ArrayList darwin;             //ArrayList which we will use for our "mating pool"
  int generations;              //number of generations

  //*INITIALIZE THE POPULATION*//
  Population(float m, int num) {
    mutationRate = m;
    MAX = num;
    population = new Face[MAX];
    darwin = new ArrayList();
    generations = 0;
    for (int i = 0; i < population.length; i++) {
      population[i] = new Face(new DNA(),50+i*75,height/2);
    }
  }

  //display all faces
  public void display() {
    for (int i = 0; i < population.length; i++) {
      population[i].render();
    }
  }

  //are we rolling over any of the faces?
  public void rollover(int mx, int my) {
    for (int i = 0; i < population.length; i++) {
      population[i].rollover(mx,my);
    }
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
      int n = (int) (fitnessNormal * 1000.0f);
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
      Face mom = (Face) darwin.get(m);
      Face dad =  (Face) darwin.get(d);
      //get their genes
      DNA momgenes = mom.getGenes();
      DNA dadgenes = dad.getGenes();
      //mate their genes
      DNA child = momgenes.mate(dadgenes);
      //mutate their genese
      child.mutate(mutationRate);
      //fill the new population with the new child
      population[i] = new Face(child,50+i*75,height/2);
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
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "faces" });
  }
}
