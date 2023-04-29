// Simple Inheritance
// Daniel Shiffman <http://www.shiffman.net>

// A Circle, subclass of Shape

class Circle extends Shape {

  // Inherits all instance variables from parent
  // We add one more field here, color
  color c;

  Circle(float x_, float y_, float r_, color c_) {
    super(x_,y_,r_);  // Call the parent constructor
    c = c_;           // Also deal with this new instance variable
  }

  void jiggle() {
    // Call the parent jiggle
    super.jiggle();
    // Add some additional functionality
    r += random(-1,1);
    r = constrain(r,0,100);
  }

  // An additional method to render the shape
  void render() {
    ellipseMode(CENTER);
    fill(c);
    stroke(0);
    ellipse(x,y,r,r);
  }
}
