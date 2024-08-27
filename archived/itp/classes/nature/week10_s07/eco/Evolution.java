import processing.core.*; import noc.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class Evolution extends PApplet {// Evolution EcoSystem
// Daniel Shiffman <http://www.shiffman.net>
// Spring 2007, The Nature of Code

// A World of creatures that eat food
// The more they eat, the longer they survive
// The longer they survive, the more likely they are to reproduce
// The bigger they are, the easier it is to land on food
// The bigger they are, the slower they are to find food
// When the creatures die, food is left behind



World world;

public void setup() {
  size(600,400);
  // World starts with 20 creatures
  // and 20 pieces of food
  world = new World(20);
  smooth();
}

public void draw() {
  background(56,37,19);
  world.run();
}

// We can add a creature manually if we so desire
public void mousePressed() {
  world.born(mouseX,mouseY); 
}



// Evolution EcoSystem
// Daniel Shiffman <http://www.shiffman.net>
// Spring 2007, The Nature of Code

// Creature class

class Bloop {
  Vector3D loc;   // Location
  DNA dna;        // DNA
  float timer;    // Life timer
  float xoff;     // for perlin noise
  float yoff;
  // DNA will determine size and maxspeed
  float r;
  float maxspeed;

  // Create a "bloop" creature
  Bloop(Vector3D l, DNA dna_) {
    loc = l.copy();
    timer = 200.0f;
    xoff = random(1000);
    yoff = random(1000);
    dna = dna_;
    // Gene 0 determines maxspeed and r
    // The bigger the bloop, the slower it is
    maxspeed = (1.0f - dna.getGene(0))*15;
    r = dna.getGene(0)*50;
  }

  public void run() {
    update();
    borders();
    render();
  }

  // A bloop can find food and eat it
  public void eat(Food f) {
    ArrayList  food = f.getFood();
    // Are we touching any food objects?
    for (int i = food.size()-1; i >= 0; i--) {
      Vector3D l = (Vector3D) food.get(i);
      float d = Vector3D.distance(loc,l);
      // If we are, juice up our strength!
      if (d < r/2) {
        timer += 100.0f; 
        food.remove(i);
      }
    }
  }

  // At any moment there is a teeny, tiny chance a bloop will reproduce
  public Bloop reproduce() {
    // asexual reproduction
    if (random(1) < 0.0005f) {
      // Child is exact copy of single parent
      DNA childDNA = dna.copy();
      // Child DNA can mutate
      childDNA.mutate(0.01f);
      return new Bloop(loc,childDNA);
    } 
    else {
      return null;
    }
  }

  // Method to update location
  public void update() {
    // Simple movement based on perlin noise
    Vector3D vel = new Vector3D(noise(xoff)*maxspeed - maxspeed/2,noise(yoff)*maxspeed-maxspeed/2);
    xoff += 0.01f;
    yoff += 0.01f;

    loc.add(vel);
    // Death always looming
    timer -= 0.2f;
  }

  // Wraparound
  public void borders() {
    if (loc.x < -r) loc.x = width+r;
    if (loc.y < -r) loc.y = height+r;
    if (loc.x > width+r) loc.x = -r;
    if (loc.y > height+r) loc.y = -r; 
    //loc.x = constrain(loc.x,0,width); 
    //loc.y = constrain(loc.y,0,height); 
  }

  // Method to display
  public void render() {
    ellipseMode(CENTER);
    noStroke();
    // Genes say something about color
    float blu = dna.getGene(1)*255;
    float rd = dna.getGene(2)*255;
    fill(rd,100,blu,timer);
    ellipse(loc.x,loc.y,r,r);
  }

  // Death
  public boolean dead() {
    if (timer <= 0.0f) {
      return true;
    } 
    else {
      return false;
    }
  }
}



// Evolution EcoSystem
// Daniel Shiffman <http://www.shiffman.net>
// Spring 2007, The Nature of Code

// Class to describe DNA
// Has more features for two parent mating (not used in this example)

class DNA {

  //The genetic sequence
  float[] dna;
  int len = 3;  //arbitrary length
  
  //Constructor (makes a random DNA)
  DNA() {
    //DNA is random floating point values between 0 and 1 (!!)
    dna = new float[len];
    for (int i = 0; i < dna.length; i++) {
      dna[i] = random(0,1);
    }
  }
  
  DNA(float[] newdna) {
    // dna = (float []) newdna.clone();  //not working as an applet?
    dna = newdna;
  }
  
  public DNA copy() {
    float[] newdna = new float[dna.length];
    arraycopy(dna,newdna);
    return new DNA(newdna);
  }
  
  // Returns one element from array 
  public float getGene(int index) {
    return dna[index];
  }
  
  // Crossover
  // Creates new DNA sequence from two (this & 
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
  
  // Based on a mutation probability, picks a new random character in array spots
  public void mutate(float m) {
    for (int i = 0; i < dna.length; i++) {
      if (random(1) < m) {
         dna[i] = random(0,1);
      }
    }
  }
}

// Evolution EcoSystem
// Daniel Shiffman <http://www.shiffman.net>
// Spring 2007, The Nature of Code

// A collection of food in the world

class Food {
  ArrayList food;
 
  Food(int num) {
    // Start with some food
    food = new ArrayList();
    for (int i = 0; i < num; i++) {
       food.add(new Vector3D(random(width),random(height))); 
    }
  } 
  
  // Add some food at a location
  public void add(Vector3D l) {
     food.add(l.copy()); 
  }
  
  // Display the food
  public void run() {
    for (int i = 0; i < food.size(); i++) {
       Vector3D loc = (Vector3D) food.get(i);
       rectMode(CENTER);
       fill(199,218,236);
       rect(loc.x,loc.y,8,8);
    } 
    
    // There's a small chance food will appear randomly
    if (random(1) < 0.001f) {
       food.add(new Vector3D(random(width),random(height))); 
    }
  }
  
  // Return the list of food
  public ArrayList getFood() {
    return food; 
  }
}
// Evolution EcoSystem
// Daniel Shiffman <http://www.shiffman.net>
// Spring 2007, The Nature of Code

// The World we live in
// Has bloops and food

class World {

  ArrayList bloops;    // An arraylist for all the creatures
  Food food;

  // Constructor
  World(int num) {
    // Start with initial food and creatures
    food = new Food(num);
    bloops = new ArrayList();              // Initialize the arraylist
    for (int i = 0; i < num; i++) {
      Vector3D l = new Vector3D(random(width),random(height));
      DNA dna = new DNA();
      bloops.add(new Bloop(l,dna));
    }
  }

  // Make a new creature
  public void born(float x, float y) {
    Vector3D l = new Vector3D(x,y);
    DNA dna = new DNA();
    bloops.add(new Bloop(l,dna));
  }

  // Run the world
  public void run() {
    // Deal with food
    food.run();
    
    // Cycle through the ArrayList backwards b/c we are deleting
    for (int i = bloops.size()-1; i >= 0; i--) {
      // All bloops run and eat
      Bloop b = (Bloop) bloops.get(i);
      b.run();
      b.eat(food);
      // If it's dead, kill it and make food
      if (b.dead()) {
        bloops.remove(i);
        food.add(b.loc);
      }
      // Perhaps this bloop would like to make a baby?
      Bloop child = b.reproduce();
      if (child != null) bloops.add(child);
    }
  }
}



static public void main(String args[]) {   PApplet.main(new String[] { "Evolution" });}}