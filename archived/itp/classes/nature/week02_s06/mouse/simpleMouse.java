import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class simpleMouse extends PApplet {// Simple Vector3D
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
  
   if (mousePressed) {
    // Compute difference vector between mouse and object location
    // 3 steps -- (1) Get Mouse Location, (2) Get Difference Vector, (3) Normalize difference vector
    Vector3D m = new Vector3D(mouseX,mouseY);
    Vector3D diff = Vector3D.sub(m,t.getLoc());
    diff.normalize();
    float factor = 1.0f;  // Magnitude of Acceleration (not increasing it right now)
    diff.mult(factor);
    //object accelerates towards mouse
    t.setAcc(diff);
  } else {
    t.setAcc(new Vector3D(0,0));
  }
  
  

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


public void keyPressed() {
  showVectors = !showVectors;
}



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
  public Vector3D getVel() {
    return vel.copy();
  }

  public Vector3D getAcc() {
    return acc.copy();
  }

  public Vector3D getLoc() {
    return loc.copy();
  }

  public void setLoc(Vector3D v) {
    loc = v.copy();
  }

  public void setVel(Vector3D v) {
    vel = v.copy();
  }

  public void setAcc(Vector3D v) {
    acc = v.copy();
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
    if (vel.magnitude() > maxvel) {
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
static public void main(String args[]) {   PApplet.main(new String[] { "simpleMouse" });}}