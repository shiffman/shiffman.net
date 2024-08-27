// Demo of forces + particle system
// Particles interact with a list of "Repeller" objects
// Daniel Shiffman <http://www.shiffman.net>

// Created 28 Feb 2008

import noc.*;

ParticleSystem ps;
ArrayList repellers;

void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  ps = new ParticleSystem(1,new Vector3D(width/2,height/4,0));
  
  // Create a list of Repeller objects
  repellers = new ArrayList();
  repellers.add(new Repeller(150,150));
  repellers.add(new Repeller(50,150));
  smooth();
}

void draw() {
  background(100);
  
  // Apply gravity force to all Particles
  Vector3D gravity = new Vector3D(0,0.05,0);
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
