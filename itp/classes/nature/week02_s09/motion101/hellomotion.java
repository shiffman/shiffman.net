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

public class hellomotion extends PApplet {

// Simple Motion with PVector
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Demonstrates simple motion with a Thing class

// Declare a "Thing" object
Thing t;

boolean showVectors = true;

public void setup() {
  size(200,200);
  smooth();
  // Create the thing object
  t = new Thing(new PVector(0.01f,0.01f),new PVector(0,0),new PVector(0,0));
}

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
  public void go() {
    update();
    borders();
    render();
  }
  
  //function to update location
  public void update() {
    vel.add(acc);
    loc.add(vel);
  }
  
  public void borders() {
    if (loc.x > width ) loc.x = 0;
    if (loc.x < 0     ) loc.x = width;
    if (loc.y > height) loc.y = 0;
    if (loc.x < 0     ) loc.y = height;
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
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "hellomotion" });
  }
}
