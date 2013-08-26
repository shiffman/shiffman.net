import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class Seek_Arrive extends PApplet {// Seek_Arrive
// Daniel Shiffman <http://www.shiffman.net>

// Two "boids" follow the mouse position

// Implements Craig Reynold's autonomous steering behaviors
// One boid "seeks"
// One boid "arrives"
// See: http://www.red3d.com/cwr/

// Created 2 May 2005

Boid seeker;
Boid arriver;

public void setup() {
  size(200,200);
  framerate(30);
  colorMode(RGB,255,255,255,100);
  seeker = new Boid(new Vector3D(width/2+50,height/2),4.0f,0.1f);
  arriver = new Boid(new Vector3D(width/2-50,height/2),4.0f,0.1f);
  smooth();
}

public void draw() {
  background(100);
  // Draw an ellipse at the mouse location
  int mx = mouseX;
  int my = mouseY;
  fill(200,75);
  noStroke();
  ellipse(mx,my,30,30);
  
  // Call the appropriate steering behaviors for our agents
  seeker.seek(new Vector3D(mx,my));
  seeker.run();
  arriver.arrive(new Vector3D(mx,my));
  arriver.run();
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
  float maxforce;    // Maximum steering force
  float maxspeed;    // Maximum speed

  Boid(Vector3D l, float ms, float mf) {
    acc = new Vector3D(0,0);
    vel = new Vector3D(0,0);
    loc = l.copy();
    r = 2.0f;
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
    fill(200);
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
static public void main(String args[]) {   PApplet.main(new String[] { "Seek_Arrive" });}}