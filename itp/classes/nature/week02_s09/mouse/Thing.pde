// Dynamic Acceleration: PVector
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Demonstrates a dynamically changing acceleration (towards the mouse)

// A class to describe a thing in our world
// Has variables for location, velocity, and acceleration

class Thing {
  PVector loc;
  PVector vel;
  PVector acc;
  float maxvel;

  //The Constructor (called when the object is first created)
  Thing(PVector a, PVector v, PVector l) {
    acc = a;
    vel = v;
    loc = l;
    maxvel = 8;
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
    //limit speed to max
    if (vel.mag() > maxvel) {
      vel.normalize();
      vel.mult(maxvel);
    }
  }

  void borders() {
    if ((loc.y > height) || (loc.y < 0))  {
      vel.y *= -1;
    }
    if ((loc.x < 0) || (loc.x > width)) {
      vel.x *= -1;
    }
  }

  //function to display
  void render() {
    rectMode(CENTER);
    stroke(0);
    fill(175);
    ellipse(loc.x,loc.y,16,16);
    if (showVectors) {
      // Draws both velocity and acceleration vectors, this could be improved to differentiate by color, etc.
      drawVector(vel,loc,5);
      drawVector(acc,loc,25);
    }
  }

  /*Add functions to our thing object to access the location, velocity and acceleration from outside the class*/
  PVector getVel() {
    return vel.get();
  }

  PVector getAcc() {
    return acc.get();
  }

  PVector getLoc() {
    return loc.get();
  }

  void setLoc(PVector v) {
    loc = v.get();
  }

  void setVel(PVector v) {
    vel = v.get();
  }

  void setAcc(PVector v) {
    acc = v.get();
  }

}

