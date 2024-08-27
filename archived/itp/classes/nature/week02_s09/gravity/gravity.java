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

public class gravity extends PApplet {

// Simple PVector: Gravity
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Demonstrates simple bouncing ball with Gravity

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
  public void go() {
    update();
    render();
  }
  
  //function to update location
  public void update() {
    if (loc.y > height) {
      vel.y *= -1;
      loc.y = height;
    }
    vel.add(acc);
    loc.add(vel);
  }

  //function to display
  public void render() {
    rectMode(CENTER);
    stroke(0);
    fill(175);
    ellipse(loc.x,loc.y,16,16);

    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

}






  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "gravity" });
  }
}
