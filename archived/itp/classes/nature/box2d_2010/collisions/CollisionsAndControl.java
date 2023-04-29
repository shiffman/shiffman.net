import processing.core.*; 
import processing.xml.*; 

import pbox2d.*; 
import org.jbox2d.common.*; 
import org.jbox2d.dynamics.joints.*; 
import org.jbox2d.collision.shapes.*; 
import org.jbox2d.collision.shapes.Shape; 
import org.jbox2d.common.*; 
import org.jbox2d.dynamics.*; 
import org.jbox2d.dynamics.contacts.*; 

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

public class CollisionsAndControl extends PApplet {

// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// PBox2D example

// Basic example of controlling an object with our own motion (by attaching a MouseJoint)
// Also demonstrates how to know which object was hit










// A reference to our box2d world
PBox2D box2d;

// Just a single box this time
Box box;

// An ArrayList of particles that will fall on the surface
ArrayList particles;

// The Spring that will attach to the box from the mouse
Spring spring;

// Perlin noise values
float xoff = 0;
float yoff = 1000;


public void setup() {
  size(400,300);
  smooth();

  // Initialize box2d physics and create the world
  box2d = new PBox2D(this);
  box2d.createWorld();

  // Add a listener to listen for collisions!
  box2d.world.setContactListener(new CustomListener());

  // Make the box
  box = new Box(width/2,height/2);

  // Make the spring (it doesn't really get initialized until the mouse is clicked)
  spring = new Spring();
  spring.bind(width/2,height/2,box);

  // Create the empty list
  particles = new ArrayList();


}

public void draw() {
  background(255);

  if (random(1) < 0.2f) {
    float sz = random(4,8);
    particles.add(new Particle(width/2,-20,sz));
  }


  // We must always step through time!
  box2d.step();

  // Make an x,y coordinate out of perlin noise
  float x = noise(xoff)*width;
  float y = noise(yoff)*height;
  xoff += 0.01f;
  yoff += 0.01f;

  // This is tempting but will not work!
  // box.body.setXForm(box2d.screenToWorld(x,y),0);

  // Instead update the spring which pulls the mouse along
  if (mousePressed) {
    spring.update(mouseX,mouseY);
  } else {
    spring.update(x,y);
  }
  box.body.setAngularVelocity(0);

  // Look at all particles
  for (int i = particles.size()-1; i >= 0; i--) {
    Particle p = (Particle) particles.get(i);
    p.display();
    // Particles that leave the screen, we delete them
    // (note they have to be deleted from both the box2d world and our list
    if (p.done()) {
      particles.remove(i);
    }
  }

  // Draw the box
  box.display();

  // Draw the spring
  // spring.display();
}










// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// PBox2D example

// A rectangular box

class Box {

  // We need to keep track of a Body and a width and height
  Body body;
  float w;
  float h;

  // Constructor
  Box(float x_, float y_) {
    float x = x_;
    float y = y_;
    w = 24;
    h = 24;
    // Add the box to the box2d world
    makeBody(new Vec2(x,y),w,h);
    body.setUserData(this);
  }

  // This function removes the particle from the box2d world
  public void killBody() {
    box2d.destroyBody(body);
  }

  public boolean contains(float x, float y) {
    Vec2 worldPoint = box2d.screenToWorld(x, y);
    Shape s = body.getShapeList();
    boolean inside = s.testPoint(body.getMemberXForm(),worldPoint);
    return inside;
  }

  // Drawing the box
  public void display() {
    // We look at each body and get its screen position
    Vec2 pos = box2d.getScreenPos(body);
    // Get its angle of rotation
    float a = body.getAngle();

    rectMode(PConstants.CENTER);
    pushMatrix();
    translate(pos.x,pos.y);
    rotate(a);
    fill(175);
    stroke(0);
    rect(0,0,w,h);
    popMatrix();
  }

  // This function adds the rectangle to the box2d world
  public void makeBody(Vec2 center, float w_, float h_) {
    // Define and create the body
    BodyDef bd = new BodyDef();
    bd.position.set(box2d.screenToWorld(center));
    body = box2d.createBody(bd);

    // Define the shape -- a polygon (this is what we use for a rectangle)
    PolygonDef sd = new PolygonDef();
    float box2dW = box2d.scaleScreenToWorld(w_/2);
    float box2dH = box2d.scaleScreenToWorld(h_/2);
    sd.setAsBox(box2dW, box2dH);
    // Parameters that affect physics
    sd.density = 1.0f;
    sd.friction = 0.3f;
    sd.restitution = 0.5f;

    // Attach that shape to our body!
    body.createShape(sd);
    body.setMassFromShapes();

    // Give it some initial random velocity
    body.setLinearVelocity(new Vec2(random(-5,5),random(2,5)));
    body.setAngularVelocity(random(-5,5));
  }

}



// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// PBox2D example

// ContactListener to listen for collisions!

class CustomListener implements ContactListener {
  CustomListener(){

  }

  // This function is called when a new collision occurs
  public void add(ContactPoint cp) {
    // Get both shapes
    Shape s1 = cp.shape1;
    Shape s2 = cp.shape2;
    // Get both bodies
    Body b1 = s1.getBody();
    Body b2 = s2.getBody();
    // Get our objects that reference these bodies
    Object o1 = b1.getUserData();
    Object o2 = b2.getUserData();
    
    // What class are they?  Box or Particle?
    String c1 = o1.getClass().getName();
    String c2 = o2.getClass().getName();
    
    // If object 1 is a Box, then object 2 must be a particle
    // Note we are ignoring particle on particle collisions
    if (c1.contains("Box")) {
      Particle p = (Particle) o2;
      p.change();
    } 
    // If object 2 is a Box, then object 1 must be a particle
    else if (c2.contains("Box")) {
      Particle p = (Particle) o1;
      p.change();
    }
  }


  // Contacts continue to collide - i.e. resting on each other
  public void persist(ContactPoint cp)  {
  }

  // Objects stop touching each other
  public void remove(ContactPoint cp)  {
  }

  // Contact point is resolved into an add, persist etc
  public void result(ContactResult cr) {

  }
}






// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// PBox2D example

// A circular particle

class Particle {

  // We need to keep track of a Body and a radius
  Body body;
  float r;
  
  int col;

  Particle(float x, float y, float r_) {
    r = r_;
    // This function puts the particle in the Box2d world
    makeBody(x,y,r);
    body.setUserData(this);
    
    col = color(175);
  }

  // This function removes the particle from the box2d world
  public void killBody() {
    box2d.destroyBody(body);
  }
  
  // Change color when hit
  public void change() {
    col = color(255,0,0); 
  }

  // Is the particle ready for deletion?
  public boolean done() {
    // Let's find the screen position of the particle
    Vec2 pos = box2d.getScreenPos(body);
    // Is it off the bottom of the screen?
    if (pos.y > height+r*2) {
      killBody();
      return true;
    }
    return false;
  }

  // 
  public void display() {
    // We look at each body and get its screen position
    Vec2 pos = box2d.getScreenPos(body);
    // Get its angle of rotation
    float a = body.getAngle();
    pushMatrix();
    translate(pos.x,pos.y);
    rotate(a);
    fill(col);
    stroke(0);
    strokeWeight(1);
    ellipse(0,0,r*2,r*2);
    // Let's add a line so we can see the rotation
    line(0,0,r,0);
    popMatrix();
  }

  // Here's our function that adds the particle to the Box2D world
  public void makeBody(float x, float y, float r) {
    // Define a body
    BodyDef bd = new BodyDef();
    // Set its position
    bd.position = box2d.screenToWorld(x,y);
    body = box2d.world.createBody(bd);

    // Make the body's shape a circle
    CircleDef cd = new CircleDef();
    cd.radius = box2d.scaleScreenToWorld(r);
    cd.density = 1.0f;
    cd.friction = 0.01f;
    cd.restitution = 0.3f; // Restitution is bounciness
    body.createShape(cd);

    // Always do this at the end
    body.setMassFromShapes();

    // Give it a random initial velocity (and angular velocity)
    //body.setLinearVelocity(new Vec2(random(-10f,10f),random(5f,10f)));
    body.setAngularVelocity(random(-10,10));
  }






}


// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// PBox2D example

// Class to describe the spring joint (displayed as a line)

class Spring {

  // This is the box2d object we need to create
  MouseJoint mouseJoint;

  Spring() {
    // At first it doesn't exist
    mouseJoint = null;
  }

  // If it exists we set its target to the mouse location 
  public void update(float x, float y) {
    if (mouseJoint != null) {
      // Always convert to world coordinates!
      Vec2 mouseWorld = box2d.screenToWorld(x,y);
      mouseJoint.setTarget(mouseWorld);
    }
  }

  public void display() {
    if (mouseJoint != null) {
      // We can get the two anchor points
      Vec2 v1 = mouseJoint.getAnchor1();
      Vec2 v2 = mouseJoint.getAnchor2();
      // Convert them to screen coordinates
      v1 = box2d.worldToScreen(v1);
      v2 = box2d.worldToScreen(v2);
      // And just draw a line
      stroke(0);
      strokeWeight(1);
      line(v1.x,v1.y,v2.x,v2.y);
    }
  }


  // This is the key function where
  // we attach the spring to an x,y location
  // and the Box object's location
  public void bind(float x, float y, Box box) {
    // Define the joint
    MouseJointDef md = new MouseJointDef();
    // Body 1 is just a fake ground body for simplicity (there isn't anything at the mouse)
    md.body1 = box2d.world.getGroundBody();
    // Body 2 is the box's boxy
    md.body2 = box.body;
    // Get the mouse location in world coordinates
    Vec2 mp = box2d.screenToWorld(x,y);
    // And that's the target
    md.target.set(mp);
    // Some stuff about how strong and bouncy the spring should be
    md.maxForce = 1000.0f * box.body.m_mass;
    md.frequencyHz = 5.0f;
    md.dampingRatio = 0.9f;

    // Wake up body!
    box.body.wakeUp();

    // Make the joint!
    mouseJoint = (MouseJoint) box2d.world.createJoint(md);
  }

  public void destroy() {
    // We can get rid of the joint when the mouse is released
    if (mouseJoint != null) {
      box2d.world.destroyJoint(mouseJoint);
      mouseJoint = null;
    }
  }

}



  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "CollisionsAndControl" });
  }
}
