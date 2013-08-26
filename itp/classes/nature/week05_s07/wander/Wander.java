import processing.core.*; import noc.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class Wander extends PApplet {// Wander
// Daniel Shiffman <http://www.shiffman.net>

// Demonstration of Craig Reynolds' "Wandering" behavior
// See: http://www.red3d.com/cwr/

// Click mouse to turn on and off rendering of the wander circle

// Created 2 May 2005 (Revised Feb 2007)



Boid wanderer;
boolean drawwandercircle = true;

public void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  wanderer = new Boid(new Vector3D(width/2,height/2),3.0f,0.1f);
  smooth();
}

public void draw() {
  background(100);
  wanderer.wander();
  wanderer.run();
}

public void mousePressed() {
  drawwandercircle = !drawwandercircle;
}



// Seek_Arrive
// Daniel Shiffman <http://www.shiffman.net>

// The "Boid" class

// Created 2 May 2005

class Boid {

  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float r;
  float wandertheta;
  float maxforce;    // Maximum steering force
  float maxspeed;    // Maximum speed

  Boid(Vector3D l, float ms, float mf) {
    acc = new Vector3D(0,0);
    vel = new Vector3D(0,0);
    loc = l.copy();
    r = 2.0f;
    wandertheta = 0.0f;
    maxspeed = ms;
    maxforce = mf;
  }
  
  public void run() {
    update();
    borders();
    render();
  }
  
  // Method to update location
  public void update() {
    // Update velocity
    vel.add(acc);
    // Limit speed
    vel.limit(maxspeed);
    loc.add(vel);
    // Reset accelertion to 0 each cycle
    acc.setXYZ(0,0,0);
  }

  public void seek(Vector3D target) {
    acc.add(steer(target,false));
  }
 
  public void arrive(Vector3D target) {
    acc.add(steer(target,true));
  }
  
  public void wander() {
    float wanderR = 16.0f;         // Radius for our "wander circle"
    float wanderD = 60.0f;         // Distance for our "wander circle"
    float change = 0.25f;
    wandertheta += random(-change,change);     // Randomly change wander theta

    // Now we have to calculate the new location to steer towards on the wander circle
    Vector3D circleloc = vel.copy();  // Start with velocity
    circleloc.normalize();            // Normalize to get heading
    circleloc.mult(wanderD);          // Multiply by distance
    circleloc.add(loc);               // Make it relative to boid's location
    
    Vector3D circleOffSet = new Vector3D(wanderR*cos(wandertheta),wanderR*sin(wandertheta));
    Vector3D target = Vector3D.add(circleloc,circleOffSet);
    acc.add(steer(target,false));  // Steer towards it
    
    // Render wandering circle, etc. 
    if (drawwandercircle) drawWanderStuff(loc,circleloc,target,wanderR);
    
  }  
  
  // A method that calculates a steering vector towards a target
  // Takes a second argument, if true, it slows down as it approaches the target
  public Vector3D steer(Vector3D target, boolean slowdown) {
    Vector3D steer;  // The steering vector
    Vector3D desired = Vector3D.sub(target,loc);  // A vector pointing from the location to the target
    float d = desired.magnitude(); // Distance from the target is the magnitude of the vector
    // If the distance is greater than 0, calc steering (otherwise return zero vector)
    if (d > 0) {
      // Normalize desired
      desired.normalize();
      // Two options for desired vector magnitude (1 -- based on distance, 2 -- maxspeed)
      if ((slowdown) && (d < 100.0f)) desired.mult(maxspeed*(d/100.0f)); // This damping is somewhat arbitrary
      else desired.mult(maxspeed);
      // Steering = Desired minus Velocity
      steer = Vector3D.sub(desired,vel);
      steer.limit(maxforce);  // Limit to maximum steering force
    } else {
      steer = new Vector3D(0,0);
    }
    return steer;
  }
  
  public void render() {
    // Draw a triangle rotated in the direction of velocity
    float theta = vel.heading2D() + radians(90);
    fill(255);
    stroke(255);
    pushMatrix();
    translate(loc.x,loc.y);
    rotate(theta);
    beginShape(TRIANGLES);
    vertex(0, -r*2);
    vertex(-r, r*2);
    vertex(r, r*2);
    endShape();
    popMatrix();
  }
  
  // Wraparound
  public void borders() {
    if (loc.x < -r) loc.x = width+r;
    if (loc.y < -r) loc.y = height+r;
    if (loc.x > width+r) loc.x = -r;
    if (loc.y > height+r) loc.y = -r;
  }

}


// A method just to draw the circle associated with wandering
public void drawWanderStuff(Vector3D loc, Vector3D circle, Vector3D target, float R) {
  stroke(200); noFill();
  ellipseMode(CENTER);
  ellipse(circle.x,circle.y,R*2,R*2);
  ellipse(target.x,target.y,4,4);
  line(loc.x,loc.y,circle.x,circle.y);
  line(circle.x,circle.y,target.x,target.y);
}
static public void main(String args[]) {   PApplet.main(new String[] { "Wander" });}}