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

public class SimpleSpring extends PApplet {

// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// Toxiclibs example: http://toxiclibs.org/

// Simple two particles and spring example





// Reference to physics world
VerletPhysics2D physics;

Particle p1;
Particle p2;

public void setup() {
  size(200,200);
  smooth();
  frameRate(30);

  // Initialize the physics
  physics=new VerletPhysics2D();
  physics.addBehavior(new GravityBehavior(new Vec2D(0,0.5f)));

  // This is the center of the world
  Vec2D center = new Vec2D(width/2,height/2);
  // these are the worlds dimensions (50%, a vector pointing out from the center in both directions)
  Vec2D extent = new Vec2D(width/2,height/2);

  // Set the world's bounding box
  physics.setWorldBounds(Rect.fromCenterExtent(center,extent));
  
  // Make two particles
  p1 = new Particle(100,20);
  p2 = new Particle(100,180);
  // Lock one in place
  p1.lock();

  // Make a spring connecting both Particles
  VerletSpring2D spring=new VerletSpring2D(p1,p2,80,0.01f);

  // Anything we make, we have to add into the physics world
  physics.addParticle(p1);
  physics.addParticle(p2);
  physics.addSpring(spring);
}

public void draw() {

  // Update the physics world
  physics.update();

  background(255);

  // Draw a line between the particles
  line(p1.x,p1.y,p2.x,p2.y);

  // Display the particles
  p1.display();
  p2.display();

  // Move the second one according to the mouse
  if (mousePressed) {
    p2.lock();
    p2.x = mouseX;
    p2.y = mouseY;
    p2.unlock();
  }
}


// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// Toxiclibs example: http://toxiclibs.org/

// Notice how we are using inheritance here!
// We could have just stored a reference to a VerletParticle object
// inside the Particle class, but inheritance is a nice alternative

class Particle extends VerletParticle2D {

  Particle(float x, float y) {
    super(x,y);
  }

  // All we're doing really is adding a display() function to a VerletParticle
  public void display() {
    fill(175);
    stroke(0);
    ellipse(x,y,16,16);
  }
}


  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "SimpleSpring" });
  }
}
