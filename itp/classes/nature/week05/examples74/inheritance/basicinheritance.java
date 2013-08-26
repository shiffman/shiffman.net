import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class basicinheritance extends PApplet {
Square s;
Circle c;

public void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  smooth();
  
  s = new Square(75,75,10);
  c = new Circle(125,125,20,color(0,0,200));
}

public void draw() {
  background(0);
  
  c.jiggle();
  s.jiggle();
   
  c.render();
  s.render();
}


class Circle extends Shape {

  //inherits all instance variables from parent + adding one
  int c;

  Circle(float x_, float y_, float r_, int c_) {
    super(x_,y_,r_);  // call the parent constructor
    c = c_;           // also deal with this new instance variable
  }

  //call the parent jiggle, but do some more stuff too
  void jiggle() {
    super.jiggle();
    r += random(-1,1);
    r = constrain(r,0,100);
  }

   //adds a new render method
  void render() {
    ellipseMode(CENTER);
    fill(c);
    noStroke();
    ellipse(x,y,r,r);
  }
}

//**Our parent class for all shapes****/

class Shape {
  float x;
  float y;
  float r;

  Shape(float x_, float y_, float r_) {
    x = x_;
    y = y_;
    r = r_;
  }

  void jiggle() {
    x += random(-1,1);
    y += random(-1,1);
  }

}


//A subclass of shape

class Square extends Shape {

  //inherits all instance variables from parent
  //we could add variables for only Square here if we want to

  Square(float x_, float y_, float r_) {
    super(x_,y_,r_);
  }

  //inherits jiggle method from parent
 
   //add a render method
  void render() {
    rectMode(CENTER);
    fill(255);
    noStroke();
    rect(x,y,r,r);
  }
}
}