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
    acc = new Vector3D(0,0.05,0);
    vel = new Vector3D(random(-1,1),random(-2,0),0);
    loc = l.copy();
    r = 10.0;
    timer = 100.0;
  }


  void run() {
    update();
    render();
  }

  // Method to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
    acc.mult(0);
    timer -= 0.5;
  }
  
   void applyForce(Vector3D force) {
    float mass = 1; // We aren't bothering with mass here
    force.div(mass);
    acc.add(force);
  }


  // Method to display
  void render() {
    ellipseMode(CENTER);
    noStroke();
    fill(255,timer);
    ellipse(loc.x,loc.y,r,r);
  }
  
  // Is the particle still useful?
  boolean dead() {
    if (timer <= 0.0) {
      return true;
    } else {
      return false;
    }
  }
}


