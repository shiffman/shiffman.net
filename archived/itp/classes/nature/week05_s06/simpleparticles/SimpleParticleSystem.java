import processing.core.*; import noc.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class SimpleParticleSystem extends PApplet {// Simple Particle System
// Daniel Shiffman <http://www.shiffman.net>

// Particles are generated each cycle through draw(),
// fall with gravity and fade out over time
// A ParticleSystem object manages a variable size (ArrayList) 
// list of particles.

// Created 2 May 2005



ParticleSystem ps;

public void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  ps = new ParticleSystem(1,new Vector3D(width/2,height/2,0));
  smooth();
}

public void draw() {
  background(100);
  ps.run();
  ps.addParticle();
}




// Simple Particle System
// Daniel Shiffman <http://www.shiffman.net>

// A simple Particle class

// Created 2 May 2005

class Particle {
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float r;
  float timer;

  // One constructor
  Particle(Vector3D a, Vector3D v, Vector3D l, float r_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    r = r_;
    timer = 100.0f;
  }
  
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
    timer -= 1.0f;
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
    } else {
      return false;
    }
  }

}



static public void main(String args[]) {   PApplet.main(new String[] { "SimpleParticleSystem" });}}