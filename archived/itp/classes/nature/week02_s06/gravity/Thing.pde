// Simple Vector3D
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2006

// Demonstrates simple motion with a Thing class

// Created 2 May 2005

// A class to describe a thing in our world
// Has variables for location, velocity, and acceleration

class Thing {
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  
  //The Constructor (called when the object is first created)
  Thing(Vector3D a, Vector3D v, Vector3D l) {
    acc = a;
    vel = v;
    loc = l;
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
    noStroke();
    fill(200);
    ellipse(loc.x,loc.y,16,16);

    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

}





