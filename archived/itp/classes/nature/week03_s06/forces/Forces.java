import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class Forces extends PApplet {// Forces
// Daniel Shiffman <http://www.shiffman.net>

// Demonstrates the accumulation of forces
// on a moving object (wind, drag, gravity)
// Force = Mass * Acceleration
// Acceleration = Force / Mass
// Viscous Force = -c * (Velocity) 

// Mouse press turns on and off displaying vectors

// Created 2 May 2005

Thing t;
boolean showVectors = true;

public void setup() {
  size(200,200);
  framerate(30);  
  smooth();
  background(0);
  colorMode(RGB,255,255,255,100);
  Vector3D a = new Vector3D(0.0f,0.0f);
  Vector3D v = new Vector3D(0.0f,0.0f);
  Vector3D l = new Vector3D(10,0);
  t = new Thing(a,v,l,1.5f);
}

public void draw() {
  background(0);

  // Add gravity to thing
  // This isn't "real" gravity, just a made up force vector
  Vector3D grav = new Vector3D(0,0.05f);
  t.add_force(grav);

  // Add wind to thing
  // Again, just making up a force vector
  Vector3D wind = new Vector3D(0.03f,0.0f);
  t.add_force(wind);

  // Add drag force
  float liq_start    = 75; // Liquid location
  float liq_height   = 40; // Liquid height
  // Test if thing intersects liquid
    if ((t.getLoc().y > liq_start) && (t.getLoc().y < liq_start + liq_height)) {
    // Drag is calculated as force vector in the negative direction of velocity
    float c = -0.02f;                           // Drag coefficient
    Vector3D thingVel = t.getVel();             // Velocity of our thing
    Vector3D force = Vector3D.mult(thingVel,c); // Following the formula above
    t.add_force(force);                         // Adding the force to our object, which will ultimately affect its acceleration
  }

  t.go();

  // Draw the "liquid"
  // Note it would probably be a good idea to make a liquid class
  noStroke();
  fill(200,50);
  rectMode(CORNER);
  rect(0,liq_start,width,liq_height);
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


public void mousePressed() {
  showVectors = !showVectors;
}





// Gravity 101
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
    
  Thing(Vector3D a, Vector3D v, Vector3D l, float m_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    mass = m_;
    max_vel = 20.0f;
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
    borders();
    render();
  }
  
  // Method to update location
  public void update() {
    vel.add(acc);
    vel.limit(max_vel);
    loc.add(vel);
    acc.setXY(0.0f,0.0f);
  }
  
  // Check for bouncing off borders
  public void borders() {
    if (loc.y > height) {
      vel.y *= -bounce;
      loc.y = height;
    }
    if ((loc.x > width) || (loc.x < 0)) {
      vel.x *= -bounce;
    }    
    //if (loc.x < 0)     loc.x = width;
    //if (loc.x > width) loc.x = 0;
  }  
  
  // Method to display
  public void render() {
    ellipseMode(CENTER);
    noStroke();
    fill(200,90);
    ellipse(loc.x,loc.y,mass*10,mass*10);
    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }
}

static public void main(String args[]) {   PApplet.main(new String[] { "Forces" });}}