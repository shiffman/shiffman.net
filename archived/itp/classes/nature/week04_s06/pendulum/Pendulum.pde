// Pendulum
// Daniel Shiffman <http://www.shiffman.net>

// A simple pendulum simulation
// Given a pendulum with an angle theta (0 being the pendulum at rest) and a radius r
// we can use sine to calculate the angular component of the gravitational force.

// Gravity Force = Mass * Gravitational Constant;
// Pendulum Force = Gravity Force * sine(theta)
// Angular Acceleration = Pendulum Force / Mass = Gravitational Constant * sine(theta);

// Note this is an ideal world scenario with no tension in the 
// pendulum arm, a more realistic formula might be:
// Angular Acceleration = (G / R) * sine(theta)

// For a more substantial explanation, visit:
// http://www.myphysicslab.com/pendulum1.html 

// Created 5 May 2005

Pendul p;

void setup() {
  size(200,200);
  framerate(30);
  smooth();
  colorMode(RGB,255,255,255,100);
  
  // Make a new Pendulum with an origin location and armlength
  p = new Pendul(new Vector3D(width/2,height/2),75.0f);

}

void draw() {

  background(100);
  p.go();
}

void mousePressed() {
  p.clicked(mouseX,mouseY);
}

void mouseReleased() {
  p.stopDragging();
}

