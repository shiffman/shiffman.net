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

public class Flow extends PApplet {

// Flow Field Following
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Via Reynolds: http://www.red3d.com/cwr/steer/FlowFollow.html

// Using this variable to decide whether to draw all the stuff
boolean debug = true;

PFont f;

// Flowfield object
FlowField flowfield;
// An ArrayList of boids
ArrayList boids;

public void setup() {
  size(640,320);
  smooth();
  f = createFont("Georgia",12,true);
  // Make a new flow field with "resolution" of 16
  flowfield = new FlowField(16);
  boids = new ArrayList();
  // Make a whole bunch of boids with random maxspeed and maxforce values
  for (int i = 0; i < 120; i++) {
    boids.add(new Boid(new PVector(random(width),random(height)),random(2,5),random(0.1f,0.5f)));
  }
}

public void draw() {
  background(255);
  // Display the flowfield in "debug" mode
  if (debug) flowfield.display();
  // Tell all the boids to follow the flow field
  for (int i = 0; i < boids.size(); i++) {
    Boid b = (Boid) boids.get(i);
    b.follow(flowfield);
    b.run();
  }

  // Instructions
  textFont(f);
  fill(0);
  text("Hit space bar to toggle debugging lines.\nClick the mouse to generate a new path.",10,height-30);

}


 public void keyPressed() {
  debug = !debug;
}

// Make a new flowfield
 public void mousePressed() {
  flowfield.init();
}




// Flow Field Following
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

class Boid {

  // The usual stuff
  PVector loc;
  PVector vel;
  PVector acc;
  float r;
  float maxforce;    // Maximum steering force
  float maxspeed;    // Maximum speed

  Boid(PVector l, float ms, float mf) {
    loc = l.get();
    r = 3.0f;
    maxspeed = ms;
    maxforce = mf;
    acc = new PVector(0,0);
    vel = new PVector(0,0);
  }

  public void run() {
    update();
    borders();
    render();
  }


  // Implementing Reynolds' flow field following algorithm
  // http://www.red3d.com/cwr/steer/FlowFollow.html
  public void follow(FlowField f) {

    // Look ahead
    PVector ahead = vel.get();
    ahead.normalize();
    ahead.mult(32); // Arbitrarily look 32 pixels ahead
    PVector lookup = PVector.add(loc,ahead);

    // Draw in debug mode
    if (debug) {
      stroke(0);
      line(loc.x,loc.y,lookup.x,lookup.y);
      fill(0);
      ellipse(lookup.x,lookup.y,3,3);
    }

    // What is the vector at that spot in the flow field?
    PVector desired = f.lookup(lookup);
    // Scale it up by maxspeed
    desired.mult(maxspeed);
    // Steering is desired minus velocity
    PVector steer = PVector.sub(desired, vel);
    steer.limit(maxforce);  // Limit to maximum steering force
    acc.add(steer);
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
    if (loc.y < -r) loc.y = height+r;
    if (loc.x > width+r) loc.x = -r;
    if (loc.y > height+r) loc.y = -r;
  }

}



// Flow Field Following
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

class FlowField {

  // A flow field is a two dimensional array of PVectors
  PVector[][] field;
  int cols, rows; // Columns and Rows
  int resolution; // How large is each "cell" of the flow field

  FlowField(int r) {
    resolution = r;
    // Determine the number of columns and rows based on sketch's width and height
    cols = width/resolution;
    rows = height/resolution;
    field = new PVector[cols][rows];
    init();
  }

  public void init() {
    // Reseed noise so we get a new flow field every time
    noiseSeed((int)random(10000));
    float xoff = 0;
    for (int i = 0; i < cols; i++) {
      float yoff = 0;
      for (int j = 0; j < rows; j++) {
        // Use perlin noise to get an angle between 0 and 2 PI
        float theta = map(noise(xoff,yoff),0,1,0,TWO_PI);
        // Polar to cartesian coordinate transformation to get x and y components of the vector
        field[i][j] = new PVector(cos(theta),sin(theta));
        yoff += 0.1f;
      }
      xoff += 0.1f;
    }
  }

  // Draw every vector
  public void display() {
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        drawVector(field[i][j],i*resolution,j*resolution,resolution-2);
      }
    }

  }

  // Renders a vector object 'v' as an arrow and a location 'x,y'
  public void drawVector(PVector v, float x, float y, float scayl) {
    pushMatrix();
    float arrowsize = 4;
    // Translate to location to render vector
    translate(x,y);
    stroke(100);
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

  public PVector lookup(PVector lookup) {
    int i = (int) constrain(lookup.x/resolution,0,cols-1);
    int j = (int) constrain(lookup.y/resolution,0,rows-1);
    return field[i][j].get();
  }


}






  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "Flow" });
  }
}
