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

public class Tree2 extends PApplet {

// Recursive Tree (w/ ArrayList)
// Daniel Shiffman <http://www.shiffman.net>

// Recursive branching "structure" without an explicitly recursive function
// Instead we have an ArrayList to hold onto N number of elements
// For every element in the ArrayList, we add 2 more elements, etc. (this is the recursion)

// An arraylist that will keep track of all current branches
ArrayList a;

public void setup() {
  size(200,200);
  background(255);
  smooth();
  
  // Setup the arraylist and add one branch to it
  a = new ArrayList();
  // A branch has a starting location, a starting "velocity", and a starting "timer" 
  Branch b = new Branch(new PVector(width/2,height),new PVector(0f,-0.5f),100);
  // Add to arraylist
  a.add(b);
}

public void draw() {
  // Try erasing the background to see how it works
  // background(255);
  
  // Let's stop when the arraylist gets too big
  if (a.size() < 1024) {
    // For every branch in the arraylist
    for (int i = a.size()-1; i >= 0; i--) {
      // Get the branch, update and draw it
      Branch b = (Branch) a.get(i);
      b.update();
      b.render();
      // If it's ready to split
      if (b.timeToBranch()) {
        a.remove(i);             // Delete it
        a.add(b.branch( 30f));   // Add one going right
        a.add(b.branch(-25f));   // Add one going left
      }
    }
  }
}


// Recursive Tree (w/ ArrayList)
// Daniel Shiffman <http://www.shiffman.net>

// A class for one branch in the system

// Created 2 May 2005

class Branch {
  // Each has a location, velocity, and timer 
  // We could implement this same idea with different data
  PVector loc;
  PVector vel;
  float timer;
  float timerstart;

  Branch(PVector l, PVector v, float n) {
    loc = l.get();
    vel = v.get();
    timerstart = n;
    timer = timerstart;
  }
  
  // Move location
  public void update() {
    loc.add(vel);
  }
  
  // Draw a dot at location
  public void render() {
    fill(0);
    noStroke();
    ellipseMode(CENTER);
    ellipse(loc.x,loc.y,2,2);
  }
  
  // Did the timer run out?
  public boolean timeToBranch() {
    timer--;
    if (timer < 0) {
      return true;
    } else {
      return false;
    }
  }

  // Create a new branch at the current location, but change direction by a given angle
  public Branch branch(float angle) {
    // What is my current heading
    float theta = vel.heading2D();
    // What is my current speed
    float mag = vel.mag();
    // Turn me
    theta += radians(angle);
    // Look, polar coordinates to cartesian!!
    PVector newvel = new PVector(mag*cos(theta),mag*sin(theta));
    // Return a new Branch
    return new Branch(loc,newvel,timerstart*0.66f);
  }
  
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "Tree2" });
  }
}
