// Seek_Arrive
// Daniel Shiffman <http://www.shiffman.net>

// Two "boids" follow the mouse position

// Implements Craig Reynold's autonomous steering behaviors
// One boid "seeks"
// One boid "arrives"
// See: http://www.red3d.com/cwr/

// Created 2 May 2005 (Revised Feb 2007)

import noc.*;

Boid seeker;
Boid arriver;

void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  seeker = new Boid(new Vector3D(width/2+50,height/2),4.0f,0.1f);
  arriver = new Boid(new Vector3D(width/2-50,height/2),4.0f,0.1f);
  smooth();
}

void draw() {
  background(100);
  // Draw an ellipse at the mouse location
  int mx = mouseX;
  int my = mouseY;
  fill(200,75);
  noStroke();
  ellipse(mx,my,30,30);
  
  // Call the appropriate steering behaviors for our agents
  seeker.seek(new Vector3D(mx,my));
  seeker.run();
  arriver.arrive(new Vector3D(mx,my));
  arriver.run();
}


