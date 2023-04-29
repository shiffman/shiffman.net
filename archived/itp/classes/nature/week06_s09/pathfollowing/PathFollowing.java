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

public class PathFollowing extends PApplet {

// Path Following
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Via Reynolds: // http://www.red3d.com/cwr/steer/PathFollow.html

// Using this variable to decide whether to draw all the stuff
boolean debug = true;

PFont f;

// A path object (series of connected points)
Path path;

// Two boids
Boid boid0;
Boid boid1;

public void setup() {
  size(640,320);
  smooth();
  f = createFont("Georgia",12,true);

  // Call a function to generate new Path object
  newPath();

  //Each boid has different maxspeed and maxforce for demo purposes
  boid0 = new Boid(new PVector(0,height/2),3f,0.1f);
  boid1 = new Boid(new PVector(0,height/2),5f,1f);
}

public void draw() {
  background(255);
  // Display the path
  path.display();
  // The boids follow the path
  boid0.follow(path);
  boid1.follow(path);
  // Call the generic run method (update, borders, display, etc.)
  boid0.run();
  boid1.run();

  // Instructions
  textFont(f);
  fill(0);
  text("Hit space bar to toggle debugging lines.\nClick the mouse to generate a new path.",10,height-30);
}

public void newPath() {
  // A path is a series of connected points
  // A more sophisticated path might be a curve
  path = new Path();
  path.addPoint(0,height/2);
  path.addPoint(random(0,width/2),random(0,height));
  path.addPoint(random(width/2,width),random(0,height));
  path.addPoint(width,height/2);
}

public void keyPressed() {
  debug = !debug;
}

public void mousePressed() {
  newPath();
}



// Path Following
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Boid class

class Boid {

  // All the usual stuff
  PVector loc;
  PVector vel;
  PVector acc;
  float r;
  float maxforce;    // Maximum steering force
  float maxspeed;    // Maximum speed

  // Constructor initialize all values
  Boid( PVector l, float ms, float mf) {
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


  // This function implements Craig Reynolds' path following algorithm
  // http://www.red3d.com/cwr/steer/PathFollow.html
  public void follow(Path p) {

    // Predict location 25 (arbitrary choice) frames ahead
    PVector predict = vel.get();
    predict.normalize();
    predict.mult(25);
    PVector predictLoc = PVector.add(loc, predict);

    // Draw the predicted location
    if (debug) {
      fill(0);
      stroke(0);
      line(loc.x,loc.y,predictLoc.x, predictLoc.y);
      ellipse(predictLoc.x, predictLoc.y,4,4);
    }

    // Now we must find the normal to the path from the predicted location
    // We look at the normal for each line segment and pick out the closest one
    PVector target = null;
    PVector dir = null;
    float record = 1000000;  // Start with a very high record distance that can easily be beaten

    // Loop through all points of the path
    for (int i = 0; i < p.points.size()-1; i++) {

      // Look at a line segment
      PVector a = (PVector) p.points.get(i);
      PVector b = (PVector) p.points.get(i+1);

      // Get the normal point to that line
      PVector normal = getNormalPoint(predictLoc,a,b);

      // Check if normal is on line segment
      float da = PVector.dist(normal,a);
      float db = PVector.dist(normal,b);
      PVector line = PVector.sub(b,a);
      // If it's not within the line segment, consider the normal to just be the end of the line segment (point b)
      if (da + db > line.mag()+1) {
        normal = b.get();
      }

      // How far away are we from the path?
      float d = PVector.dist(predictLoc,normal);
      // Did we beat the record and find the closest line segment?
      if (d < record) {
        record = d;
        // If so the target we want to steer towards is the normal
        target = normal;

        // Look at the direction of the line segment so we can seek a little bit ahead of the normal
        dir = line;
        dir.normalize();
        // This is an oversimplification
        // Should be based on distance to path & velocity
        dir.mult(10);
      }
    }

    // Draw the debugging stuff
    if (debug) {
      // Draw normal location
      fill(0);
      noStroke();
      line(predictLoc.x,predictLoc.y,target.x,target.y);
      ellipse(target.x,target.y,4,4);
      stroke(0);
      // Draw actual target (red if steering towards it)
      line(predictLoc.x,predictLoc.y,target.x,target.y);
      if (record > p.radius) fill(255,0,0);
      noStroke();
      ellipse(target.x+dir.x, target.y+dir.y, 8, 8);
    }

    // Only if the distance is greater than the path's radius do we bother to steer
    if (record > p.radius) {
      target.add(dir);
      seek(target);			
    }
  }


  // A function to get the normal point from a point (p) to a line segment (a-b)
  // This function could be optimized to make fewer new Vector objects
  public PVector getNormalPoint(PVector p, PVector a, PVector b) {
    // Vector from a to p
    PVector ap = PVector.sub(p,a);
    // Vector from a to b
    PVector ab = PVector.sub(b,a);
    ab.normalize(); // Normalize the line
    // Project vector "diff" onto line by using the dot product
    ab.mult(ap.dot(ab));
    PVector normalPoint = PVector.add(a,ab);
    return normalPoint;
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

  public void seek(PVector target) {
    acc.add(steer(target,false));
  }

  public void arrive(PVector target) {
    acc.add(steer(target,true));
  }

  // A method that calculates a steering vector towards a target
  // Takes a second argument, if true, it slows down as it approaches the target
  public PVector steer(PVector target, boolean slowdown) {
    PVector steer;  // The steering vector
    PVector desired = PVector.sub(target,loc);  // A vector pointing from the location to the target
    float d = desired.mag(); // Distance from the target is the magnitude of the vector
    // If the distance is greater than 0, calc steering (otherwise return zero vector)
    if (d > 0) {
      // Normalize desired
      desired.normalize();
      // Two options for desired vector magnitude (1 -- based on distance, 2 -- maxspeed)
      if ((slowdown) && (d < 100.0f)) desired.mult(maxspeed*(d/100.0f)); // This damping is somewhat arbitrary
      else desired.mult(maxspeed);
      // Steering = Desired minus Velocity
      steer = PVector.sub(desired,vel);
      steer.limit(maxforce);  // Limit to maximum steering force
    } 
    else {
      steer = new PVector(0,0);
    }
    return steer;
  }

  public void render() {
    // Draw a triangle rotated in the direction of velocity
    float theta = vel.heading2D() + PApplet.radians(90);
    fill(175);
    stroke(0);
    pushMatrix();
    translate(loc.x,loc.y);
    rotate(theta);
    beginShape(PConstants.TRIANGLES);
    vertex(0, -r*2);
    vertex(-r, r*2);
    vertex(r, r*2);
    endShape();
    popMatrix();
  }

  // Wraparound
  public void borders() {
    if (loc.x < -r) loc.x = width+r;
    //if (loc.y < -r) loc.y = height+r;
    if (loc.x > width+r) loc.x = -r;
    //if (loc.y > height+r) loc.y = -r;
  }

}



// Path Following
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

class Path {

  // A Path is an arraylist of points (PVector objects)
  ArrayList points;
  // A path has a radius, i.e how far is it ok for the boid to wander off
  float radius;

  Path() {
    // Arbitrary radius of 20
    radius = 20;
    points = new ArrayList();
  }

  // Add a point to the path
  public void addPoint(float x, float y) {
    PVector point = new PVector(x,y);
    points.add(point);
  }

  // Draw the path
  public void display() {

    // Draw the radius as thick lines and circles
    if (debug) {
      // Draw end points
      for (int i = 0; i < points.size(); i++) {
        PVector point = (PVector) points.get(i);
        fill(175);
        noStroke();
        ellipse(point.x,point.y,radius*2,radius*2);
      }

      // Draw Polygon around path
      for (int i = 0; i < points.size()-1; i++) {
        PVector start = (PVector) points.get(i);
        PVector end = (PVector) points.get(i+1);
        PVector line = PVector.sub(end,start);
        PVector normal = new PVector(line.y,-line.x);
        normal.normalize();
        normal.mult(radius);

        // Polygon has four vertices
        PVector a = PVector.add(start, normal);
        PVector b = PVector.add(end, normal);
        PVector c = PVector.sub(end, normal);
        PVector d = PVector.sub(start, normal);

        fill(175);
        noStroke();
        beginShape();
        vertex(a.x,a.y);
        vertex(b.x,b.y);
        vertex(c.x,c.y);
        vertex(d.x,d.y);
        endShape();
      }
    }

    // Draw Regular Line
    stroke(0);
    noFill();
    beginShape();
    for (int i = 0; i < points.size(); i++) {
      PVector loc = (PVector) points.get(i);
      vertex(loc.x,loc.y);
    }
    endShape();

  }

}





  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "PathFollowing" });
  }
}
