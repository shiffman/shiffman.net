// Simple Inheritance
// Daniel Shiffman <http://www.shiffman.net>

// Object oriented programming allows us define classes in terms of other classes.
// A class can be a subclass (aka "child") of a super class (aka "parent"). 
// This is a simple example demonstrating this concept, known as "inheritance." 

// Created 2 May 2005

Square s;
Circle c;

void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  smooth();
  
  s = new Square(75,75,10);
  c = new Circle(125,125,20,color(200));
}

void draw() {
  background(100);
  
  c.jiggle();
  s.jiggle();
   
  c.render();
  s.render();
}

