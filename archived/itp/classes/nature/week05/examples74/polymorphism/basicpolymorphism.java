import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class basicpolymorphism extends PApplet {
//an array of shapes (we can put any type of shape in here!)
Shape[] s = new Shape[25];

public void setup() {
  size(320,240);
  colorMode(RGB,255,255,255,100);
  smooth();
  
  for (int i = 0; i < s.length; i++) {
   int r = PApplet.toInt(random(2));
   //randomly put either circles or squares in our array
   if (r == 0) {
     s[i] = new Circle(160,120,10,color(0,0,200));
   } else {
     s[i] = new Square(160,120,10);
   }
  }
}

public void draw() {
  background(0);
  //the magic of polymorphism, we can treat circles and squares 
  //together as shapes, and the right "render" method will be called!
  for (int i = 0; i < s.length; i++) {
    Shape ashape = s[i];
    ashape.jiggle();
    ashape.render();
  }
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
  
  //a boring general render function for all shapes
  //this will get overridden
  void render() {
    stroke(255);
    point(x,y);
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
 
  //overwrites the render method from parent
  void render() {
    rectMode(CENTER);
    fill(255);
    noStroke();
    rect(x,y,r,r);
  }
}
}