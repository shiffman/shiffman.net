import processing.core.*; 
import processing.xml.*; 

import toxi.physics2d.*; 
import toxi.physics2d.behaviors.*; 
import toxi.geom.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class ChainDemo extends PApplet {

// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// Toxiclibs example: http://toxiclibs.org/

// A soft pendulum (series of connected springs)






// Reference to physics "world" (2D)
VerletPhysics2D physics;

// Our "Chain" object
Chain chain;

public void setup() {
  size(400,300);
  smooth();

  // Initialize the physics world
  physics=new VerletPhysics2D();
  physics.addBehavior(new GravityBehavior(new Vec2D(0,0.1f)));
  physics.setWorldBounds(new Rect(0,0,width,height));

  // Initialize the chain
  chain = new Chain(200,20,12,0.2f);
}

public void draw() {
  background(255);

  // Update physics
  physics.update();
  // Update chain's tail according to mouse location 
  chain.updateTail(mouseX,mouseY);
  // Display chain
  chain.display();

}

public void mousePressed() {
  // Check to see if we're grabbing the chain
  chain.contains(mouseX,mouseY);
}

public void mouseReleased() {
  // Release the chain
  chain.release();
}



// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// Toxiclibs example

// A soft pendulum (series of connected springs)


class Chain {

  // Chain properties
  float totalLength;  // How long
  int numPoints;      // How many points
  float strength;     // Strength of springs
  float radius;       // Radius of ball at tail

  // Our chain is a list of VerletParticles
  ArrayList particles;

  // Let's keep an extra reference to the tail particle
  // This is just the last particle in the ArrayList
  VerletParticle2D tail;

  // Some variables for mouse dragging
  PVector offset = new PVector();
  boolean dragged = false;

  // Chain constructor
  Chain(float l, int n, float r, float s) {

    totalLength = l;
    numPoints = n;
    radius = r;
    strength = s;

    particles = new ArrayList();

    float len = totalLength / numPoints;

    // Here is the real work, go through and add particles to the chain itself
    for(int i=0; i < numPoints; i++) {
      // Make a new particle with an initial starting location
      VerletParticle2D particle=new VerletParticle2D(width/2,i*len);

      // Redundancy, we put the particles both in physics and in our own ArrayList
      physics.addParticle(particle);
      particles.add(particle);

      // Connect the particles with a Spring (except for the head)
      if (i>0) {
        VerletParticle2D previous = (VerletParticle2D) particles.get(i-1);
        VerletSpring2D spring=new VerletSpring2D(particle,previous,len,strength);
        // Add the spring to the physics world
        physics.addSpring(spring);
      }
    }

    // Keep the top fixed
    VerletParticle2D head=(VerletParticle2D) particles.get(0);
    head.lock();

    // Store reference to the tail
    tail = (VerletParticle2D) particles.get(numPoints-1);
  }


  // Check if a point is within the ball at the end of the chain
  // If so, set dragged = true;
  public void contains(int x, int y) {
    float d = dist(x,y,tail.x,tail.y);
    if (d < radius) {
      offset.x = tail.x - x;
      offset.y = tail.y - y;
      dragged = true;
    }
  }

  // Release the ball
  public void release() {
    dragged = false;
  }

  // Update tail location if being dragged
  public void updateTail(int x, int y) {
    if (dragged) {
      tail.set(x+offset.x,y+offset.y);
    }
  }

  // Draw the chain
  public void display() {

    // Draw line connecting all points
    for(int i=0; i < particles.size()-1; i++) {
      VerletParticle2D p1= (VerletParticle2D) particles.get(i);
      VerletParticle2D p2= (VerletParticle2D) particles.get(i+1);
      stroke(0);
      line(p1.x,p1.y,p2.x,p2.y);
    }

    // Draw a ball at the tail
    stroke(0);
    fill(175);
    ellipse(tail.x,tail.y,radius*2,radius*2);

  }

}



  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "ChainDemo" });
  }
}
