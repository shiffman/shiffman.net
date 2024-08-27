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

public class MutualAttraction extends PApplet {

// Mutual Attraction
// Daniel Shiffman <http://www.shiffman.net>

// Demonstrates gravitational pull amongst a group of moving objects
// G ---> universal gravitational constant
// m1 --> mass of object #1
// m2 --> mass of object #2
// d ---> distance between objects
// F = (G*m1*m2)/(d*d)

// Mouse click resets all bodies to random locations
// Key press turns on and off vector display


int MAX = 5;
Thing[] t = new Thing[MAX];
boolean showVectors = false;

public void setup() {
  size(200,200);
  smooth();
  reset();
}

public void draw() {
  background(255);

  for (int i = 0; i < t.length; i++) {          // For every Thing t[i]
    for (int j = 0; j < t.length; j++) {        // For every Thing t[j]
      if (i != j) {                             // Make sure we are not calculating gravtional pull on oneself
        PVector f = t[i].calcGravForce(t[j]);   // Use the function we wrote above
        t[i].applyForce(f);                     // Add the force to the object to affect its acceleration
      }
    }
    t[i].go();                                  // Implement the rest of the object's functionality
  }

}

public void reset() {
  // Some random bodies
  for (int i = 0; i < t.length; i++) {
    PVector ac = new PVector(0.0f,0.0f);
    PVector ve = new PVector(0.0f,0.0f);
    PVector lo = new PVector(random(width),random(height));
    t[i] = new Thing(ac,ve,lo,random(8,16));
  }
}

public void mousePressed() {
  reset();
}

public void keyPressed() {
  showVectors = !showVectors;
}

// Renders a vector object 'v' as an arrow and a location 'loc'
public void drawVector(PVector v, PVector loc, float scayl) {
  if (v.mag() > 0.0f) {
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
}

// Attraction
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a thing in our world, has vectors for location, velocity, and acceleration
// Also includes scalar values for mass, maximum velocity, and elasticity

class Thing {
  PVector loc;
  PVector vel;
  PVector acc;
  float mass;
  float max_vel;
  float bounce = 1.0f; // How "elastic" is the object
  float G;             // Universal gravitational constant
    
  Thing(PVector a, PVector v, PVector l, float m_) {
    acc = a.get();
    vel = v.get();
    loc = l.get();
    mass = m_;
    max_vel = 5.0f;
    G = 0.2f;
  }
  
  public PVector getLoc() {
    return loc;
  }

  public PVector getVel() {
    return vel;
  }
  public float getMass() {
    return mass;
  }
  
  public PVector calcGravForce(Thing t) {
    PVector dir = PVector.sub(t.getLoc(), loc);        // Calculate direction of force
    float d = dir.mag();                               // Distance between objects
    d = constrain(d,20.0f,50.0f);                        // Limiting the distance to eliminate "extreme" results for very close or very far objects
    dir.normalize();                                   // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = (G * mass * t.getMass()) / (d * d);  // Calculate gravitional force magnitude
    dir.mult(force);                                   // Get force vector --> magnitude * direction
    return dir;
  }
  
  public void applyForce(PVector force) {
    force.div(mass);
    acc.add(force);
    if (showVectors) {
      drawVector(force,loc,5000);
    }    
  }

  // Main method to operate object
  public void go() {
    update();
    render();
  }
  
  // Method to update location
  public void update() {
    vel.add(acc);
    vel.limit(max_vel);
    loc.add(vel);
    acc.mult(0);
  }
  
  // Method to display
  public void render() {
    ellipseMode(CENTER);
    stroke(0);
    fill(100,127);
    ellipse(loc.x,loc.y,mass*2,mass*2);
    if (showVectors) {
      drawVector(vel,loc,20);
    }
  }
}


  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "MutualAttraction" });
  }
}
