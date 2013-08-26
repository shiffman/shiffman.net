// Flocking
// Daniel Shiffman <http://www.shiffman.net>

// Demonstration of Craig Reynolds' "Flocking" behavior
// See: http://www.red3d.com/cwr/
// Rules: Cohesion, Separation, Alignment

// Click mouse to add boids into the system

// Created 2 May 2005 (Revised Feb 2007)

import noc.*;

Flock flock;

void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  flock = new Flock();
  // Add an initial set of boids into the system
  for (int i = 0; i < 50; i++) {
    flock.addBoid(new Boid(new Vector3D(width/2,height/2),2.0f,0.05f));
  }
  smooth();
}

void draw() {

  background(100);
  flock.run();
}

// Add a new boid into the System
void mousePressed() {
  flock.addBoid(new Boid(new Vector3D(mouseX,mouseY),2.0f,0.05f));
}


