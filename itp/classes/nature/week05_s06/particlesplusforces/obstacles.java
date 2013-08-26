import processing.core.*; import noc.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class obstacles extends PApplet {// Demo of forces + particle system
// Particles interact with a list of "Repeller" objects
// Daniel Shiffman <http://www.shiffman.net>

// Created 28 Feb 2008



ParticleSystem ps;
ArrayList repellers;

public void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  ps = new ParticleSystem(1,new Vector3D(width/2,height/4,0));
  
  // Create a list of Repeller objects
  repellers = new ArrayList();
  repellers.add(new Repeller(150,150));
  repellers.add(new Repeller(50,150));
  smooth();
}

public void draw() {
  background(100);
  
  // Apply gravity force to all Particles
  Vector3D gravity = new Vector3D(0,0.05f,0);
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
  }
  
}

// Simple Particle System
// Daniel Shiffman <http://www.shiffman.net>

// A simple Particle class
// Incorporates forces code

// Created 2 May 2005

class Particle {
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float r;
  float timer;

  // Another constructor (the one we are using here)
  Particle(Vector3D l) {
    acc = new Vector3D(0,0.05f,0);
    vel = new Vector3D(random(-1,1),random(-2,0),0);
    loc = l.copy();
    r = 10.0f;
    timer = 100.0f;
  }


  public void run() {
    update();
    render();
  }

  // Method to update location
  public void update() {
    vel.add(acc);
    loc.add(vel);
    acc.mult(0);
    timer -= 0.5f;
  }
  
   public void applyForce(Vector3D force) {
    float mass = 1; // We aren't bothering with mass here
    force.div(mass);
    acc.add(force);
  }


  // Method to display
  public void render() {
    ellipseMode(CENTER);
    noStroke();
    fill(255,timer);
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



// Simple Particle System
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a group of Particles
// An ArrayList is used to manage the list of Particles 
// Functions are added to apply forces to all particles
// and all particles interact with Repeller objects

class ParticleSystem {

  ArrayList particles;    // An arraylist for all the particles
  Vector3D origin;        // An origin point for where particles are birthed

  ParticleSystem(int num, Vector3D v) {
    particles = new ArrayList();              // Initialize the arraylist
    origin = v.copy();                        // Store the origin point
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin));    // Add "num" amount of particles to the arraylist
    }
  }


  // A function to apply a force to all Particles
  public void applyForce(Vector3D f) {
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
        Vector3D repel = r.pushParticle(p);        
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




// Simple Particle System
// Daniel Shiffman <http://www.shiffman.net>

// A very basic Repeller class
class Repeller {

  // Location
  Vector3D loc;

  Repeller(float x, float y)  {
    loc = new Vector3D(x,y);
  }

  public void display() {
    stroke(255);
    noFill();
    ellipse(loc.x,loc.y,20,20);
  }

  // Calculate a force to push particle away from repeller
  public Vector3D pushParticle(Particle p) {
    Vector3D dir = Vector3D.sub(loc,p.loc);      // Calculate direction of force
    float d = dir.magnitude();                   // Distance between objects
    dir.normalize();                             // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = -150.0f / (d * d);              // Repelling force is inversely proportional to distance
    dir.mult(force);                             // Get force vector --> magnitude * direction
    return dir;
  }  

}

  static public void main(String args[]) {     PApplet.main(new String[] { "obstacles" });  }}