// Simple Vector3D
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2006

// Demonstrates simple motion with a Thing class

// Created 2 May 2005

// A class to describe a thing in our world
// Has variables for location, velocity, and acceleration

class Thing {
  //*using private now to encapsulate data*//
  private Vector3D loc;
  private Vector3D vel;
  private Vector3D acc;
  private float maxvel;

  //The Constructor (called when the object is first created)
  Thing(Vector3D a, Vector3D v, Vector3D l) {
    acc = a;
    vel = v;
    loc = l;
    maxvel = 8;
  }

  /*Add functions to our thing object to access the location, velocity and acceleration from outside the class*/
  Vector3D getVel() {
    return vel.copy();
  }

  Vector3D getAcc() {
    return acc.copy();
  }

  Vector3D getLoc() {
    return loc.copy();
  }

  void setLoc(Vector3D v) {
    loc = v.copy();
  }

  void setVel(Vector3D v) {
    vel = v.copy();
  }

  void setAcc(Vector3D v) {
    acc = v.copy();
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
    if (vel.magnitude() > maxvel) {
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
    noStroke();
    fill(200);
    ellipse(loc.x,loc.y,16,16);
    if (showVectors) {
      // Draws both velocity and acceleration vectors, this could be improved to differentiate by color, etc.
      drawVector(vel,loc,5);
      drawVector(acc,loc,25);
    }
  }


}
