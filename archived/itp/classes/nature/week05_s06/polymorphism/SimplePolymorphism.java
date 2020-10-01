import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class SimplePolymorphism extends PApplet {// Simple Polymorphism
// Daniel Shiffman <http://www.shiffman.net>

// Polymorphism (i.e. many forms) refers to the concept that we can treat 
// an object instance in multiple ways. 

// Created 2 May 2005

// An array of shapes (we can put any type of shape in here!)
Shape[] s = new Shape[25];

public void setup() {
  size(200,200);
  framerate(30);
  colorMode(RGB,255,255,255,100);
  smooth();
  
  for (int i = 0; i < s.length; i++) {
   int r = PApplet.toInt(random(2));
   // Randomly put either circles or squares in our array
   if (r == 0) {
     s[i] = new Circle(width/2,height/2,10,color(200,75));
   } else {
     s[i] = new Square(width/2,height/2,10);
   }
  }
}

public void draw() {
  background(100);
  // The magic of polymorphism, we can treat circles and squares 
  // together as Shapes, and the appropriate "render" and "jiggle" method will be called!
  for (int i = 0; i < s.length; i++) {
    Shape ashape = s[i];
    ashape.jiggle();
    ashape.render();
  }
}



// Simple Inheritance
// Daniel Shiffman <http://www.shiffman.net>

// A Circle, subclass of Shape

// Created 2 May 2005

class Circle extends Shape {

  // Inherits all instance variables from parent
  // We add one more field here, color
  int c;

  Circle(float x_, float y_, float r_, int c_) {
    super(x_,y_,r_);  // Call the parent constructor
    c = c_;           // Also deal with this new instance variable
  }

  public void jiggle() {
    // Call the parent jiggle
    super.jiggle();
    // Add some additional functionality
    r += random(-1,1);
    r = constrain(r,0,100);
  }

  // An additional method to render the shape
  public void render() {
    ellipseMode(CENTER);
    fill(c);
    noStroke();
    ellipse(x,y,r,r);
  }
}

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
  public void render() {
    stroke(255);
    point(x,y);
  }

  public void jiggle() {
    x += random(-1,1);
    y += random(-1,1);
  }

}


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
  public void render() {
    rectMode(CENTER);
    fill(255,75);
    noStroke();
    rect(x,y,r,r);
  }
}
static public void main(String args[]) {   PApplet.main(new String[] { "SimplePolymorphism" });}}