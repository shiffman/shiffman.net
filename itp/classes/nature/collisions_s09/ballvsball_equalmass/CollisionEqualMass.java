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

public class CollisionEqualMass extends PApplet {

// Collisions -- Elastic, Equal Mass, Two objects only
// Nature of Code, Spring 2009
// Daniel Shiffman <http://www.shiffman.net>

// Based off of Chapter 9: Resolving Collisions
// Mathematics and Physics for Programmers by Danny Kodicek

// A Thing class for idealized collisions

Thing a;
Thing b;

boolean showVectors = true;

public void setup() {
  size(200,200);
  smooth();
  a = new Thing(new PVector(random(5),random(-5,5)),new PVector(10,10));
  b = new Thing(new PVector(-2,1),new PVector(150,150));
}

public void draw() {  
  background(255);
  a.go();
  b.go();  
  
  // Note this function will ONLY WORK with two objects
  // Needs to be revised in the case of an array of objects  
  a.collideEqualMass(b);
}



// Collisions
// Daniel Shiffman <http://www.shiffman.net>

// A Thing class for idealized collision

class Thing {

  PVector loc;
  PVector vel;
  float bounce = 1.0f;
  float r = 20;
  boolean colliding = false;

  Thing(PVector v, PVector l) {
    vel = v.get();
    loc = l.get();
  }

  // Main method to operate object
  public void go() {
    update();
    borders();
    render();
  }

  // Method to update location
  public void update() {
    loc.add(vel);
  }

  // Check for bouncing off borders
  public void borders() {
    if (loc.y > height) {
      vel.y *= -bounce;
      loc.y = height;
    } else if (loc.y < 0) {
      vel.y *= -bounce;
      loc.y = 0;
    } 
    if (loc.x > width) {
      vel.x *= -bounce;
      loc.x = width;
    }  else if (loc.x < 0) {
      vel.x *= -bounce;
      loc.x = 0;
    } 
  }  

  // Method to display
  public void render() {
    ellipseMode(CENTER);
    stroke(0);
    fill(175,200);
    ellipse(loc.x,loc.y,r*2,r*2);
    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

  public void collideEqualMass(Thing t) {
    float d = PVector.dist(loc,t.loc);
    float sumR = r + t.r;
    // Are they colliding?
    if (!colliding && d < sumR) {
      // Yes, make new velocities!
      colliding = true;
      // Direction of one object another
      PVector n = PVector.sub(t.loc,loc);
      n.normalize();
      
      // Difference of velocities so that we think of one object as stationary
      PVector u = PVector.sub(vel,t.vel);
      
      // Separate out components -- one in direction of normal
      PVector un = componentVector(u,n);
      // Other component
      u.sub(un);
      // These are the new velocities plus the velocity of the object we consider as stastionary
      vel = PVector.add(u,t.vel);
      t.vel = PVector.add(un,t.vel);
    } 
    else if (d > sumR) {
      colliding = false;
    }

  }


}


public PVector componentVector (PVector vector, PVector directionVector) {
  //--! ARGUMENTS: vector, directionVector (2D vectors)
  //--! RETURNS: the component vector of vector in the direction directionVector
  //-- normalize directionVector
  directionVector.normalize();
  directionVector.mult(vector.dot(directionVector));
  return directionVector;
}



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


public void mousePressed() {
  showVectors = !showVectors;
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#c0c0c0", "CollisionEqualMass" });
  }
}
