// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com
// Notice how we are using inheritance here!

// We could have just stored a reference to a VerletParticle object
// inside the Particle class, but inheritance is a nice alternative

class Particle  {
  float r;

  VerletParticle2D p;
  PImage img;


  boolean drag;


  Particle(Vec2D loc, float r_) {
    p = new VerletParticle2D(loc);
    r = r_;
    //physics.addBehavior(new AttractionBehavior(p, r*2, -r));
  }
  Particle(Vec2D loc, float r_, String s) {
    p = new VerletParticle2D(loc);
    r = r_;
    img = loadImage(s);
    //physics.addBehavior(new AttractionBehavior(p, r*2, -r));
  }

  // All we're doing really is adding a display() function to a VerletParticle
  void display() {
    fill(127);
    stroke(0);
    strokeWeight(2);
    if (img != null) {
      imageMode(CENTER);
      image(img, p.x, p.y);
    } 
    else {
      ellipse(p.x, p.y, r*2, r*2);
    }
  }

  void control() {
    if (drag) {
      //lock();
      //p.x = mouseX;
      //p.y = mouseY;
      //unlock();
    }
  }

  void grab() {
    drag = true;
  }
  
  void lock() {
    p.lock(); 
  }

  void unlock() {
    p.unlock(); 
  }
  void release() {
    drag = false;
  }

  boolean over(float px, float py) {
    if (dist(p.x, p.y, px, py) < r*2) {
      return true;
    } 
    else {
      return false;
    }
  }
}

