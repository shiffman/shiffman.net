// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// Toxiclibs example: http://toxiclibs.org/

// A soft pendulum (series of connected springs)

import toxi.physics2d.*;
import toxi.physics2d.behaviors.*;
import toxi.geom.*;


// Reference to physics "world" (2D)
VerletPhysics2D physics;

// Our "Chain" object
Chain chain;

void setup() {
  size(400,300);
  smooth();

  // Initialize the physics world
  physics=new VerletPhysics2D();
  physics.addBehavior(new GravityBehavior(new Vec2D(0,0.1)));
  physics.setWorldBounds(new Rect(0,0,width,height));

  // Initialize the chain
  chain = new Chain(200,20,12,0.2);
}

void draw() {
  background(255);

  // Update physics
  physics.update();
  // Update chain's tail according to mouse location 
  chain.updateTail(mouseX,mouseY);
  // Display chain
  chain.display();

}

void mousePressed() {
  // Check to see if we're grabbing the chain
  chain.contains(mouseX,mouseY);
}

void mouseReleased() {
  // Release the chain
  chain.release();
}



