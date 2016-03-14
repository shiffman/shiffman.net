// Simple Inheritance
// Daniel Shiffman <http://www.shiffman.net>

// A Square, subclass of Shape

// Created 2 May 2005

class Square extends Shape {

  // Inherits all instance variables from parent

  Square(float x_, float y_, float r_) {
    super(x_,y_,r_);  // Call the parent constructor
  }

  // Inherits "jiggle" method from parent
  // We don't have to rewrite it

  // An additional method to render the square
  void render() {
    rectMode(CENTER);
    fill(0,75);
    stroke(0);
    rect(x,y,r,r);
  }
}
