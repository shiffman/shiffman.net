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

public class Tree3 extends PApplet {

// Recursive Tree (w/ ArrayList)
// Daniel Shiffman <http://www.shiffman.net>

// Recursive branching "structure" without an explicitly recursive function
// Instead we have an ArrayList to hold onto N number of elements
// For every element in the ArrayList, we add 2 more elements, etc. (this is the recursion)

// An arraylist that will keep track of all current branches
ArrayList a;
ArrayList leaves;

public void setup() {
  size(200,200);
  background(255);
  smooth();

  // Setup the arraylist and add one branch to it
  a = new ArrayList();
  leaves = new ArrayList();
  // A branch has a starting location, a starting "velocity", and a starting "timer" 
  Branch b = new Branch(new PVector(width/2,height),new PVector(0f,-0.5f),100);
  // Add to arraylist
  a.add(b);
}

public void draw() {
  background(255);

  // Let's stop when the arraylist gets too big
  // For every branch in the arraylist
  for (int i = a.size()-1; i >= 0; i--) {
    // Get the branch, update and draw it
    Branch b = (Branch) a.get(i);
    b.update();
    b.render();
    // If it's ready to split
    if (b.timeToBranch()) {
      if (a.size() < 1024) {
        //a.remove(i);             // Delete it
        a.add(b.branch( 30f));   // Add one going right
        a.add(b.branch(-25f));   // Add one going left
      } 
      else {
        leaves.add(new Leaf(b.end));
      }
    }
  }
  
  for (int i = 0; i < leaves.size(); i++) {
     Leaf leaf = (Leaf) leaves.get(i);
     leaf.display(); 
  }

}




// Recursive Tree (w/ ArrayList)
// Daniel Shiffman <http://www.shiffman.net>

// A class for one branch in the system

// Created 2 May 2005

class Branch {
  // Each has a location, velocity, and timer 
  // We could implement this same idea with different data
  PVector start;
  PVector end;
  PVector vel;
  float timer;
  float timerstart;

  boolean growing = true;

  Branch(PVector l, PVector v, float n) {
    start = l.get();
    end = l.get();
    vel = v.get();
    timerstart = n;
    timer = timerstart;
  }

  // Move location
  public void update() {
    if (growing) {
      end.add(vel);
    }
  }

  // Draw a dot at location
  public void render() {
    stroke(0);
    line(start.x,start.y,end.x,end.y);
  }

  // Did the timer run out?
  public boolean timeToBranch() {
    timer--;
    if (timer < 0 && growing) {
      growing = false;
      return true;
    } 
    else {
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
    return new Branch(end,newvel,timerstart*0.66f);
  }

}

class Leaf {
  PVector loc;

  Leaf(PVector l) {
    loc = l.get();
  }

  public void display() {
    noStroke();
    fill(50,100);
    ellipse(loc.x,loc.y,4,4);   
  }
}


  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "Tree3" });
  }
}
