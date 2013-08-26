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

public class ParticlesPlusForces extends PApplet {

// Demo of forces + particle system
// Particles interact with a list of "Repeller" objects
// Daniel Shiffman <http://www.shiffman.net>

ParticleSystem ps;

ArrayList repellers;

public void setup() {
  size(300,300);
  ps = new ParticleSystem(1,new PVector(width/2,height/4));

  // Create a list of Repeller objects
  repellers = new ArrayList();
  for (int i = 0; i < 15; i++) {
    repellers.add(new Repeller(random(width),random(height)));

  }
  smooth();
}

public void draw() {
  background(255);

  // Apply gravity force to all Particles
  PVector gravity = new PVector(0,0.1f);
  ps.applyForce(gravity);
  // Apply repeller objects to all Particles
  ps.applyRepellers(repellers);
  // Run the Particle System
  ps.run();
  // Add more particles
  ps.addParticle();

  // Display all repellers
  for (int i = 0; i < repellers.size(); i++) {
    Repeller r = (Repeller) repellers.get(i); 
    r.display();
    r.drag();
  }

}

public void mousePressed() {
  for (int i = 0; i < repellers.size(); i++) {
    Repeller r = (Repeller) repellers.get(i); 
    r.clicked(mouseX,mouseY);
  }
}

public void mouseReleased() {
  for (int i = 0; i < repellers.size(); i++) {
    Repeller r = (Repeller) repellers.get(i); 
    r.stopDragging();
  }
}


// Particles + Forces
// Daniel Shiffman <http://www.shiffman.net>

// A simple Particle class
// Incorporates forces code

class Particle {
  PVector loc;
  PVector vel;
  PVector acc;
  float r;
  float timer;
  float maxspeed;

  // Another constructor (the one we are using here)
  Particle(PVector l) {
    acc = new PVector(0,0);
    vel = new PVector(random(-1,1),random(-1,1));
    loc = l.get();
    r = 10.0f;
    timer = 100.0f;
    maxspeed = 2;
  }


  public void run() {
    update();
    render();
  }

  // Method to update location
  public void update() {
    vel.add(acc);
    vel.limit(maxspeed);
    loc.add(vel);
    acc.mult(0);
    timer -= 0.5f;
  }
  
   public void applyForce(PVector force) {
    float mass = 1; // We aren't bothering with mass here
    force.div(mass);
    acc.add(force);
  }


  // Method to display
  public void render() {
    ellipseMode(CENTER);
    stroke(0,timer);
    fill(0,timer);
    ellipse(loc.x,loc.y,r,r);
  }
  
  // Is the particle still useful?
  public boolean dead() {
    if (timer <= 0.0f) {
      return true;
    } else {
      return false;
    }
  }
}


// Particles + Forces
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a group of Particles
// An ArrayList is used to manage the list of Particles 
// Functions are added to apply forces to all particles
// and all particles interact with Repeller objects

class ParticleSystem {

  ArrayList particles;    // An arraylist for all the particles
  PVector origin;        // An origin point for where particles are birthed

  ParticleSystem(int num, PVector v) {
    particles = new ArrayList();              // Initialize the arraylist
    origin = v.get();                        // Store the origin point
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin));    // Add "num" amount of particles to the arraylist
    }
  }


  // A function to apply a force to all Particles
  public void applyForce(PVector f) {
    for (int i = 0; i < particles.size(); i++) {
      Particle p = (Particle) particles.get(i);
      p.applyForce(f);
    }
  }

  // A function for particles to interact with all Repellers
  public void applyRepellers(ArrayList a) {
    // For every Particle
    for (int i = 0; i < particles.size(); i++) {
      Particle p = (Particle) particles.get(i);
      // For every Repeller
      for (int j = 0; j < repellers.size(); j++) {
        Repeller r = (Repeller) repellers.get(j);
        // Calculate and apply a force from Repeller to Particle
        PVector repel = r.pushParticle(p);        
        p.applyForce(repel);
      }
    }
  }

  public void run() {
    // Cycle through the ArrayList backwards b/c we are deleting
    for (int i = particles.size()-1; i >= 0; i--) {
      Particle p = (Particle) particles.get(i);
      p.run();
      if (p.dead()) {
        particles.remove(i);
      }
    }
  }

  public void addParticle() {
    particles.add(new Particle(origin));
  }

  public void addParticle(Particle p) {
    particles.add(p);
  }

  // A method to test if the particle system still has particles
  public boolean dead() {
    if (particles.isEmpty()) {
      return true;
    } 
    else {
      return false;
    }
  }

}



// Particles + Forces
// Daniel Shiffman <http://www.shiffman.net>

// A very basic Repeller class
class Repeller {
  
  // Gravitational Constant
  float G = 100;

  // Location
  PVector loc;

  float r = 10;

  // For mouse interaction
  boolean dragging = false; // Is the object being dragged?
  boolean rollover = false; // Is the mouse over the ellipse?
  PVector drag;  // holds the offset for when object is clicked on

  Repeller(float x, float y)  {
    loc = new PVector(x,y);
    drag = new PVector(0,0);
  }

  public void display() {
    stroke(0);
    if (dragging) fill (0);
    else if (rollover) fill(150);
    else noFill();
    ellipse(loc.x,loc.y,r*2,r*2);
  }

  // Calculate a force to push particle away from repeller
  public PVector pushParticle(Particle p) {
    PVector dir = PVector.sub(loc,p.loc);      // Calculate direction of force
    float d = dir.mag();                       // Distance between objects
    dir.normalize();                           // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    d = constrain(d,5,100);                     // Keep distance within a reasonable range
    float force = -1 * G / (d * d);            // Repelling force is inversely proportional to distance
    dir.mult(force);                           // Get force vector --> magnitude * direction
    return dir;
  }  

  // The methods below are for mouse interaction
  public void clicked(int mx, int my) {
    float d = dist(mx,my,loc.x,loc.y);
    if (d < r) {
      dragging = true;
      drag.x = loc.x-mx;
      drag.y = loc.y-my;
    }
  }

  public void rollover(int mx, int my) {
    float d = dist(mx,my,loc.x,loc.y);
    if (d < r) {
      rollover = true;
    } 
    else {
      rollover = false;
    }
  }

  public void stopDragging() {
    dragging = false;
  }

  public void drag() {
    if (dragging) {
      loc.x = mouseX + drag.x;
      loc.y = mouseY + drag.y;
    }
  }


}



  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#c0c0c0", "ParticlesPlusForces" });
  }
}
