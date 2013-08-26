import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class gravity extends PApplet {// Simple Vector3D
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2006

// Demonstrates simple motion with a Thing class

// Created 2 May 2005


// Declare a "Thing" object
Thing t;

boolean showVectors = true;

public void setup() {
  size(200,200);
  framerate(30);
  smooth();
  // Create the thing object
  Vector3D a = new Vector3D(0.0f,0.125f);
  Vector3D v = new Vector3D(0.0f,0.0f);
  Vector3D l = new Vector3D(width/2,0);
  t = new Thing(a,v,l);}

public void draw() {
  background(100);
  
  // Run the Thing object
  t.go();

}

// Renders a vector object 'v' as an arrow and a location 'loc'
public void drawVector(Vector3D v, Vector3D loc, float scayl) {
  pushMatrix();
  float arrowsize = 4;
  // Translate to location to render vector
  translate(loc.x,loc.y);
  stroke(255);
  // Call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading2D());
  // Calculate length of vector & scale it to be bigger or smaller if necessary
  float len = v.magnitude()*scayl;
  // Draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  popMatrix();
}




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
    noStroke();
    fill(200);
    ellipse(loc.x,loc.y,16,16);

    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

}





static public void main(String args[]) {   PApplet.main(new String[] { "gravity" });}}