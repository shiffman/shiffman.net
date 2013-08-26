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

public class Attraction extends PApplet {

// Attraction
// Daniel Shiffman <http://www.shiffman.net>

// Demonstrates attractive force one body exerts on another
// G ---> universal gravitational constant
// m1 --> mass of object #1
// m2 --> mass of object #2
// d ---> distance between objects
// F = (G*m1*m2)/(d*d)

// Click and drag attractive body to move throughout space
// Keypress turns on and off vector display

Thing t;
Attractor a;
boolean showVectors = true;

public void setup() {
  size(200,200);
  smooth();

  PVector ac = new PVector(0.0f,0.0f);
  PVector ve = new PVector(0.0f,1.0f);
  PVector lo = new PVector(50,50);
  // Create new thing with some initial settings
  t = new Thing(ac,ve,lo,10);
  // Create an attractive body
  a = new Attractor(new PVector(width/2,height/2),20,0.4f);
}

public void draw() {
  background(255);

  a.rollover(mouseX,mouseY);
  a.go();

  // Calculate a force exerted by "attractor" on "thing"
  PVector f = a.calcGravForce(t);
  // Apply that force to the thing
  t.applyForce(f);
  // Update and render the positions of both objects
  t.go();

}

public void mousePressed() {
  a.clicked(mouseX,mouseY);
}

public void mouseReleased() {
  a.stopDragging();
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

// A class for a draggable attractive body in our world

class Attractor {
  float mass;    // Mass, tied to size
  float G;       // Gravitational Constant
  PVector loc;   // Location
  boolean dragging = false; // Is the object being dragged?
  boolean rollover = false; // Is the mouse over the ellipse?
  PVector drag;  // holds the offset for when object is clicked on

  Attractor(PVector l_,float m_, float g_) {
    loc = l_.get();
    mass = m_;
    G = g_;
    drag = new PVector(0.0f,0.0f);
  }

  public void go() {
    render();
    drag();
  }

  public PVector calcGravForce(Thing t) {
    PVector dir = PVector.sub(loc,t.getLoc());        // Calculate direction of force
    float d = dir.mag();                              // Distance between objects
    d = constrain(d,5.0f,25.0f);                        // Limiting the distance to eliminate "extreme" results for very close or very far objects
    dir.normalize();                                  // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = (G * mass * t.getMass()) / (d * d); // Calculate gravitional force magnitude
    dir.mult(force);                                  // Get force vector --> magnitude * direction
    return dir;
  }

  // Method to display
  public void render() {
    ellipseMode(CENTER);
    stroke(0);
    if (dragging) fill (50);
    else if (rollover) fill(100);
    else fill(175,200);
    ellipse(loc.x,loc.y,mass*2,mass*2);
  }

  // The methods below are for mouse interaction
  public void clicked(int mx, int my) {
    float d = dist(mx,my,loc.x,loc.y);
    if (d < mass) {
      dragging = true;
      drag.x = loc.x-mx;
      drag.y = loc.y-my;
    }
  }

  public void rollover(int mx, int my) {
    float d = dist(mx,my,loc.x,loc.y);
    if (d < mass) {
      rollover = true;
    } else {
      rollover = false;
    }
  }

  public void stopDragging() {
    dragging = false;
  }
  
 

  public void drag() {
    if (dragging) {
      loc.x = mouseX + drag.x;
      loc.y = mouseY + drag.y;
    }
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
    
  Thing(PVector a, PVector v, PVector l, float m_) {
    acc = a.get();
    vel = v.get();
    loc = l.get();
    mass = m_;
    max_vel = 20.0f;
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

  public void applyForce(PVector force) {
    force.div(mass);
    acc.add(force);
    if (showVectors) {
      drawVector(force,loc,1000);
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
    // Multiplying by 0 sets the all the components to 0
    acc.mult(0);
  }
  
  // Method to display
  public void render() {
    ellipseMode(CENTER);
    stroke(0);
    fill(175,100);
    ellipse(loc.x,loc.y,mass*2,mass*2);
    if (showVectors) {
      drawVector(vel,loc,20);
    }
  }
}


  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "Attraction" });
  }
}
