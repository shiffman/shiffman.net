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

public class ObstacleAvoidance extends PApplet {

// Obstacle Avoidance
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009


// Using this variable to decide whether to draw all the stuff
boolean debug = true;

PFont f;

// A bunch of obstacles
ArrayList obstacles;
// A bunch of boids
ArrayList boids;

public void setup() {
  size(600,400);
  smooth();
  f = createFont("Georgia",12,true);

  obstacles = new ArrayList();
  boids = new ArrayList();

  // A little algorithm to pick a bunch of random obstacles that don't overlap
  for (int i = 0; i < 100; i++) {
    float x = random(width);
    float y = random(height);
    float r = random(50-i/2,50);
    boolean ok = true;
    for (int j = 0; j < obstacles.size(); j++) {
      Obstacle o = (Obstacle) obstacles.get(j);
      if (dist(x,y,o.loc.x,o.loc.y) < o.radius + r + 20) {
        ok = false;
      }
    }
    if (ok) obstacles.add(new Obstacle(x,y,r));
  }

  // Starting with three boids
  boids.add(new Boid(new PVector(random(width),random(height)),3f,0.2f));
  boids.add(new Boid(new PVector(random(width),random(height)),3f,0.1f));
  boids.add(new Boid(new PVector(random(width),random(height)),2f,0.05f));

}

public void draw() {
  background(255);

  // Turn off highlighting for all obstalces
  for (int i = 0; i < obstacles.size(); i++) {
    Obstacle o = (Obstacle) obstacles.get(i);
    o.highlight(false);
  }

  // Act on all boids
  for (int i = 0; i < boids.size(); i++) {
    Boid b = (Boid) boids.get(i);
    b.avoid(obstacles);
    b.run();
  }

  // Display the obstacles
  for (int i = 0; i < obstacles.size(); i++) {
    Obstacle o = (Obstacle) obstacles.get(i);
    o.display();
  }

  // Instructions
  textFont(f);
  fill(0);
  text("Hit space bar to toggle debugging lines.\nClick the mouse to generate a new boids.",10,height-30);
}

public void keyPressed() {
  debug = !debug;
}

public void mousePressed() {
  boids.add(new Boid(new PVector(mouseX,mouseY),random(2,5),random(0.05f,0.2f)));
}



// Obstacle Avoidance
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

class Boid {

  // All the usual stuff
  PVector loc;
  PVector vel;
  PVector acc;
  float r;
  float maxforce;    // Maximum steering force
  float maxspeed;    // Maximum speed

    // How far ahead can the boid see
  float sight = 100;

  // Constructor initialize all values
  Boid(PVector l, float ms, float mf) {
    loc = l.get();
    r = 4.0f;
    maxspeed = ms;
    maxforce = mf;
    acc = new PVector(0,0);
    vel = new PVector(maxspeed,0);
  }

  // Main "run" function
   public void run() {
    update();
    borders();
    render();
  }



  // A function to get a perpendicular vector
  public PVector getNormal(PVector v) {
    return new PVector(-v.y,v.x);
  }

  // A function to rotate a vector
  public void rotateVector(PVector v, float theta) {
    float m = v.mag();
    float a = v.heading2D();
    a += theta;
    v.x = m*PApplet.cos(a);
    v.y = m*PApplet.sin(a);
  }

  // This function implements Craig Reynolds' obstacle avoidance algorithm
  // http://www.red3d.com/cwr/steer/Obstacle.html
  // Algorithm also based on James Tu's example from Dynamic Bodies

  public void avoid(ArrayList obstacles) {


    // Make a vector that will be the position of the object
    // relative to the Boid rotated in the direction of boid's velocity
    PVector closestRotated = new PVector(sight+1,sight+1);
    float closestDistance = 99999;
    Obstacle avoid = null;

    // Let's look at each obstacle
    for (int i = 0; i < obstacles.size(); i++) {  
      Obstacle o = (Obstacle) obstacles.get(i);

      float d = PVector.dist(loc,o.loc);
      PVector dir = vel.get();
      dir.normalize();
      PVector diff = PVector.sub(o.loc,loc);

      // Now we use the dot product to rotate the vector that points from boid to obstacle
      // Velocity is the new x-axis
      PVector rotated = new PVector(diff.dot(dir),diff.dot(getNormal(dir)));

      // Is the obstacle in our path?
      if ( PApplet.abs(rotated.y) < (o.radius + r) ) {
        // Is it the closest obstacle?
        if ( (rotated.x > 0) && (rotated.x < closestRotated.x) ) {
          closestRotated = rotated;
          avoid = o;
        }
      }
    }


    // Can we actually see the closest one?
    if ( PApplet.abs(closestRotated.x) < sight ) {

      // The desired vector should point away from the obstacle
      // The closer to the obstacle, the more it should steer
      PVector desired = new PVector (closestRotated.x, -closestRotated.y*sight/closestRotated.x);
      desired.normalize();
      desired.mult(closestDistance);
      desired.limit(maxspeed);
      // Rotate back to the regular coordinate system
      rotateVector(desired,vel.heading2D());

      // Draw some debugging stuff
      if (debug) {
        stroke(0);
        line(loc.x,loc.y,loc.x+desired.x*10,loc.y+desired.y*10);
        avoid.highlight(true);
      }

      // Apply Reynolds steering rules
      desired.sub(vel);
      desired.limit(maxforce);
      acc.add(desired);
    } 
  }





  // Method to update location
  public void update() {
    // Update velocity
    vel.add(acc);
    // Limit speed
    vel.limit(maxspeed);
    loc.add(vel);
    // Reset accelertion to 0 each cycle
    acc.mult(0);
  }

  public void render() {
    // Draw a triangle rotated in the direction of velocity
    float theta = vel.heading2D() + PApplet.radians(90);
    fill(100);
    stroke(0);
    pushMatrix();
    translate(loc.x,loc.y);
    rotate(theta);
    beginShape(PConstants.TRIANGLES);
    vertex(0, -r*2);
    vertex(-r, r*2);
    vertex(r, r*2);
    endShape();

    if (debug) {
      stroke(50);
      line(0,0,0,-sight);
    }
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



// Obstacle Avoidance
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009


class Obstacle {

  // Obstacle has location and radius
  PVector loc;
  float radius;
  int c;

  Obstacle(float x, float y, float r) {
    radius = r;
    loc = new PVector(x,y);
    c = color(225);
  }

  // Draw the obstacle
  public void display() {
    stroke(0);
    fill(c);
    ellipse(loc.x,loc.y,radius*2,radius*2);
  }

  // Highlight it for debugging
  public void highlight(boolean b) {
    if (b) c = color(255,0,0);
    else c = color(175);
  }

}






  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "ObstacleAvoidance" });
  }
}
