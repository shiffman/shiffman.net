import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class SimpleInheritance extends PApplet {// Simple Inheritance
// Daniel Shiffman <http://www.shiffman.net>

// Object oriented programming allows us define classes in terms of other classes.
// A class can be a subclass (aka "child") of a super class (aka "parent"). 
// This is a simple example demonstrating this concept, known as "inheritance." 

// Created 2 May 2005

Square s;
Circle c;

public void setup() {
  size(200,200);
  framerate(30);
  colorMode(RGB,255,255,255,100);
  smooth();
  
  s = new Square(75,75,10);
  c = new Circle(125,125,20,color(200));
}

public void draw() {
  background(100);
  
  c.jiggle();
  s.jiggle();
   
  c.render();
  s.render();
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
    fill(255);
    noStroke();
    rect(x,y,r,r);
  }
}
static public void main(String args[]) {   PApplet.main(new String[] { "SimpleInheritance" });}}