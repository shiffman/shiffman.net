// Simple Motion with PVector
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Demonstrates simple motion with a Thing class

// A class to describe a thing in our world
// Has variables for location, velocity, and acceleration

class Thing {
  PVector loc;
  PVector vel;
  PVector acc;
  
  //The Constructor (called when the object is first created)
  Thing(PVector a, PVector v, PVector l) {
    acc = a.get();
    vel = v.get();
    loc = l.get();
  }

  //main function to operate object)
  void go() {
    update();
    borders();
    render();
  }
  
  //function to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
  }
  
  void borders() {
    if (loc.x > width ) loc.x = 0;
    if (loc.x < 0     ) loc.x = width;
    if (loc.y > height) loc.y = 0;
    if (loc.x < 0     ) loc.y = height;
  }

  //function to display
  void render() {
    rectMode(CENTER);
    stroke(0);
    fill(175);
    ellipse(loc.x,loc.y,16,16);
    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

}





