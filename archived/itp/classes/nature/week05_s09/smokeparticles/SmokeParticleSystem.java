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

public class SmokeParticleSystem extends PApplet {

// Smoke Particle System
// Daniel Shiffman <http://www.shiffman.net>

// A basic smoke effect using a particle system
// Each particle is rendered as an alpha masked image

ParticleSystem ps;
Random generator;

public void setup() {
  size(200,200);
  generator = new Random();
    
  // Create an alpha masked image to be applied as the particle's texture
  PImage msk = loadImage("texture.gif");
  PImage img = new PImage(msk.width,msk.height);
  for (int i = 0; i < img.pixels.length; i++) img.pixels[i] = color(255);
  img.mask(msk);

  ps = new ParticleSystem(0,new PVector(width/2,height-20),img);
  smooth();
}

public void draw() {
  background(0);
  
  // Calculate a "wind" force based on mouse horizontal position
  float dx = (mouseX - width/2) / 1000.0f;
  PVector wind = new PVector(dx,0,0);
  ps.applyForce(wind);
  ps.run();
  for (int i = 0; i < 2; i++) {
    ps.addParticle();
  }
  
  // Draw an arrow representing the wind force
  drawVector(wind, new PVector(width/2,50,0),500);

}

// Renders a vector object 'v' as an arrow and a location 'loc'
public void drawVector(PVector v, PVector loc, float scayl) {
  pushMatrix();
  float arrowsize = 4;
  // Translate to location to render vector
  translate(loc.x,loc.y);
  stroke(255);
  // Call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading2D());
  // Calculate length of vector & scale it to be bigger or smaller if necessary
  float len = v.mag()*scayl;
  // Draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  popMatrix();
}

// Simple Particle System
// Daniel Shiffman <http://www.shiffman.net>

// A simple Particle class, renders the particle as an image

// Created 2 May 2005

class Particle {
  PVector loc;
  PVector vel;
  PVector acc;
  float timer;
  PImage img;

  // One constructor
  Particle(PVector a, PVector v, PVector l, PImage img_) {
    acc = a.get();
    vel = v.get();
    loc = l.get();
    timer = 100.0f;
    img = img_;
  }

  // Another constructor (the one we are using here)
  Particle(PVector l,PImage img_) {
    acc = new PVector(0.0f,0.0f,0.0f);
    float x = (float) generator.nextGaussian()*0.3f;
    float y = (float) generator.nextGaussian()*0.3f - 1.0f;
    vel = new PVector(x,y,0);
    loc = l.get();
    timer = 100.0f;
    img = img_;
  }

  public void run() {
    update();
    render();
  }
  
  // Method to apply a force vector to the Particle object
  // Note we are ignoring "mass" here
  public void applyForce(PVector f) {
    acc.add(f);
  }  

  // Method to update location
  public void update() {
    vel.add(acc);
    loc.add(vel);
    timer -= 2.5f;
    acc.mult(0); // clear Acceleration
  }

  // Method to display
  public void render() {
    imageMode(CORNER);
    tint(255,timer);
    image(img,loc.x-img.width/2,loc.y-img.height/2);
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

// Smoke Particle Syste
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a group of Particles
// An ArrayList is used to manage the list of Particles 

class ParticleSystem {

  ArrayList particles;    // An arraylist for all the particles
  PVector origin;        // An origin point for where particles are birthed
  PImage img;
  
  ParticleSystem(int num, PVector v, PImage img_) {
    particles = new ArrayList();              // Initialize the arraylist
    origin = v.get();                        // Store the origin point
    img = img_;
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin, img));    // Add "num" amount of particles to the arraylist
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
  
  // Method to add a force vector to all particles currently in the system
  public void applyForce(PVector dir) {
    for (int i = particles.size()-1; i >= 0; i--) {
      Particle p = (Particle) particles.get(i);
      p.applyForce(dir);
    }
  
  }  

  public void addParticle() {
    particles.add(new Particle(origin,img));
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




  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#c0c0c0", "SmokeParticleSystem" });
  }
}
