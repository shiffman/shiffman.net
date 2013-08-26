import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class MutualAttraction extends PApplet {// Mutual Attraction
// Daniel Shiffman <http://www.shiffman.net>

// Demonstrates gravitational pull amongst a group of moving objects
// G ---> universal gravitational constant
// m1 --> mass of object #1
// m2 --> mass of object #2
// d ---> distance between objects
// F = (G*m1*m2)/(d*d)

// Mouse click resets all bodies to random locations
// Key press turns on and off vector display

// Created 2 May 2005

int MAX = 5;
Thing[] t = new Thing[MAX];
boolean showVectors = false;

public void setup() {
  size(200,200);
  smooth();
  framerate(30);
  colorMode(RGB,255,255,255,100);
  reset();
}

public void draw() {
  background(0);

  for (int i = 0; i < t.length; i++) {          // For every Thing t[i]
    for (int j = 0; j < t.length; j++) {        // For every Thing t[j]
      if (i != j) {                             // Make sure we are not calculating gravtional pull on oneself
        Vector3D f = t[i].calcGravForce(t[j]);  // Use the function we wrote above
        t[i].add_force(f);                      // Add the force to the object to affect its acceleration
      }
    }
    t[i].go();                                  // Implement the rest of the object's functionality
  }

}

public void reset() {
  // Some random bodies
  for (int i = 0; i < t.length; i++) {
    Vector3D ac = new Vector3D(0.0f,0.0f);
    Vector3D ve = new Vector3D(0.0f,0.0f);
    Vector3D lo = new Vector3D(random(width),random(height));
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
public void drawVector(Vector3D v, Vector3D loc, float scayl) {
  if (v.magnitude() > 0.0f) {
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
}


// Attraction
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a thing in our world, has vectors for location, velocity, and acceleration
// Also includes scalar values for mass, maximum velocity, and elasticity

// Created 2 May 2005

class Thing {
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float mass;
  float max_vel;
  float bounce = 1.0f; // How "elastic" is the object
  float G;             // Universal gravitational constant
    
  Thing(Vector3D a, Vector3D v, Vector3D l, float m_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    mass = m_;
    max_vel = 5.0f;
    G = 0.2f;
  }
  
  public Vector3D getLoc() {
    return loc;
  }

  public Vector3D getVel() {
    return vel;
  }
  public float getMass() {
    return mass;
  }
  
  public Vector3D calcGravForce(Thing t) {
    Vector3D dir = Vector3D.sub(t.getLoc(), loc);     // Calculate direction of force
    float d = dir.magnitude();                        // Distance between objects
    d = constrain(d,20.0f,50.0f);                      // Limiting the distance to eliminate "extreme" results for very close or very far objects
    dir.normalize();                                  // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = (G * mass * t.getMass()) / (d * d); // Calculate gravitional force magnitude
    dir.mult(force);                                  // Get force vector --> magnitude * direction
    return dir;
  }
  
  public void add_force(Vector3D force) {
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
    acc.setXY(0.0f,0.0f);
  }
  
  // Method to display
  public void render() {
    ellipseMode(CENTER);
    noStroke();
    fill(200,90);
    ellipse(loc.x,loc.y,mass*2,mass*2);
    if (showVectors) {
      drawVector(vel,loc,20);
      drawVector(acc,loc,10000);
    }
  }
}

static public void main(String args[]) {   PApplet.main(new String[] { "MutualAttraction" });}}