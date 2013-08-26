// Simple Particle System
// Daniel Shiffman <http://www.shiffman.net>

// Particles are generated each cycle through draw(),
// fall with gravity and fade out over time
// A ParticleSystem object manages a variable size (ArrayList) 
// list of particles.

// Created 2 May 2005

import noc.*;

ParticleSystem ps;

void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  ps = new ParticleSystem(1,new Vector3D(width/2,height/2,0));
  smooth();
}

void draw() {
  background(100);
  ps.run();
  ps.addParticle();
}



