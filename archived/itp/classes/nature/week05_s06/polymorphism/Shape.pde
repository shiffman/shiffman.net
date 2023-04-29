// Simple Inheritance
// Daniel Shiffman <http://www.shiffman.net>

// A general Shape, the parent class for Circle and Square

// Created 2 May 2005

class Shape {
  float x;
  float y;
  float r;

  Shape(float x_, float y_, float r_) {
    x = x_;
    y = y_;
    r = r_;
  }
  
  // To bo overridden by each subclass
  void render() {
    stroke(255);
    point(x,y);
  }

  void jiggle() {
    x += random(-1,1);
    y += random(-1,1);
  }

}

