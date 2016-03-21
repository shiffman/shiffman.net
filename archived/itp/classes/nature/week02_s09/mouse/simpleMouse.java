import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class simpleMouse extends PApplet {

// Dynamic Acceleration: PVector
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Demonstrates a dynamically changing acceleration (towards the mouse)

// Declare a "Thing" object
Thing t;

boolean showVectors = true;

public void setup() {
  size(200,200);
  smooth();
  // Create the thing object
  PVector a = new PVector(0.0f,0.125f);
  PVector v = new PVector(0.0f,0.0f);
  PVector l = new PVector(width/2,0);
  t = new Thing(a,v,l);}

public void draw() {
  background(255);
  
  // Run the Thing object
  t.go();
  
   if (mousePressed) {
    // Compute difference vector between mouse and object location
    // 3 steps -- (1) Get Mouse Location, (2) Get Difference Vector, (3) Normalize difference vector
    PVector m = new PVector(mouseX,mouseY);
    PVector diff = PVector.sub(m,t.getLoc());
    diff.normalize();
    float factor = 1.0f;  // Magnitude of Acceleration (not increasing it right now)
    diff.mult(factor);
    //object accelerates towards mouse
    t.setAcc(diff);
  } else {
    t.setAcc(new PVector(0,0));
  }
}

// Renders a vector object 'v' as an arrow and a location 'loc'
public void drawVector(PVector v, PVector loc, float scayl) {
  pushMatrix();
  float arrowsize = 4;
  // Translate to location to render vector
  translate(loc.x,loc.y);
  stroke(0);
  // Call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading2D());
  // Calculate length of vector & scale it to be bigger or smaller if necessary
  float len = v.mag()*scayl;
  // Draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  popMatrix();
}


public void keyPressed() {
  showVectors = !showVectors;
}


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
  public void go() {
    update();
    borders();
    render();
  }

  //function to update location
  public void update() {
    vel.add(acc);
    loc.add(vel);
    //limit speed to max
    if (vel.mag() > maxvel) {
      vel.normalize();
      vel.mult(maxvel);
    }
  }

  public void borders() {
    if ((loc.y > height) || (loc.y < 0))  {
      vel.y *= -1;
    }
    if ((loc.x < 0) || (loc.x > width)) {
      vel.x *= -1;
    }
  }

  //function to display
  public void render() {
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
  public PVector getVel() {
    return vel.get();
  }

  public PVector getAcc() {
    return acc.get();
  }

  public PVector getLoc() {
    return loc.get();
  }

  public void setLoc(PVector v) {
    loc = v.get();
  }

  public void setVel(PVector v) {
    vel = v.get();
  }

  public void setAcc(PVector v) {
    acc = v.get();
  }

}


  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "simpleMouse" });
  }
}
