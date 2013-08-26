// Simple PVector: Gravity
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Demonstrates simple bouncing ball with Gravity

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
    render();
  }
  
  //function to update location
  void update() {
    if (loc.y > height) {
      vel.y *= -1;
      loc.y = height;
    }
    vel.add(acc);
    loc.add(vel);
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





