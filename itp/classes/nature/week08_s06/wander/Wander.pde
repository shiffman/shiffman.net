// Wander
// Daniel Shiffman <http://www.shiffman.net>

// Demonstration of Craig Reynolds' "Wandering" behavior
// See: http://www.red3d.com/cwr/

// Click mouse to turn on and off rendering of the wander circle

// Created 2 May 2005

Boid wanderer;
boolean drawwandercircle = true;

void setup() {
  size(200,200);
  framerate(30);
  colorMode(RGB,255,255,255,100);
  wanderer = new Boid(new Vector3D(width/2,height/2),3.0f,0.1f);
  smooth();
}

void draw() {
  background(100);
  wanderer.wander();
  wanderer.run();
}

void mousePressed() {
  drawwandercircle = !drawwandercircle;
}


