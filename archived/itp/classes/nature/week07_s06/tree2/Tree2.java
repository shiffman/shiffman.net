import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class Tree2 extends PApplet {// Recursive Tree (w/ ArrayList)
// Daniel Shiffman <http://www.shiffman.net>

// Recursive branching "structure" without an explicitly recursive function
// Instead we have an ArrayList to hold onto N number of elements
// For every element in the ArrayList, we add 2 more elements, etc. (this is the recursion)

// Created 2 May 2005

// An arraylist that will keep track of all current branches
ArrayList a;

public void setup() {
  size(200,200);
  framerate(30);
  colorMode(RGB,255,255,255,100);
  background(100);
  smooth();
  
  // Setup the arraylist and add one branch to it
  a = new ArrayList();
  // A branch has a starting location, a starting "velocity", and a starting "timer" 
  Branch b = new Branch(new Vector3D(width/2,height),new Vector3D(0f,-0.5f),100f);
  // Add to arraylist
  a.add(b);
}

public void draw() {
  // Try erasing the background to see how it works
  // background(100);
  
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
  Vector3D loc;
  Vector3D vel;
  float timer;
  float timerstart;

  Branch(Vector3D l, Vector3D v, float n) {
    loc = l.copy();
    vel = v.copy();
    timerstart = n;
    timer = timerstart;
  }
  
  // Move location
  public void update() {
    loc.add(vel);
  }
  
  // Draw a dot at location
  public void render() {
    fill(255);
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
    float mag = vel.magnitude();
    // Turn me
    theta += radians(angle);
    // Look, polar coordinates to cartesian!!
    Vector3D newvel = new Vector3D(mag*cos(theta),mag*sin(theta));
    // Return a new Branch
    return new Branch(loc,newvel,timerstart*0.66f);
  }
  
}
static public void main(String args[]) {   PApplet.main(new String[] { "Tree2" });}}