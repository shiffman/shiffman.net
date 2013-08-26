// Simple Inheritance
// Daniel Shiffman <http://www.shiffman.net>

// Object oriented programming allows us define classes in terms of other classes.
// A class can be a subclass (aka "child") of a super class (aka "parent"). 
// This is a simple example demonstrating this concept, known as "inheritance." 

Square s;
Circle c;

void setup() {
  size(200,200);
  smooth();
  s = new Square(75,75,10);
  c = new Circle(125,125,20,color(50));
}

void draw() {
  background(255);
  
  c.jiggle();
  s.jiggle();
   
  c.render();
  s.render();
}

