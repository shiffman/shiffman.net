// Simple Polymorphism
// Daniel Shiffman <http://www.shiffman.net>

// Polymorphism (i.e. many forms) refers to the concept that we can treat 
// an object instance in multiple ways. 

// An array of shapes (we can put any type of shape in here!)
Shape[] s = new Shape[25];

void setup() {
  size(200,200);
  smooth();
  
  for (int i = 0; i < s.length; i++) {
   int r = int(random(2));
   // Randomly put either circles or squares in our array
   if (r == 0) {
     s[i] = new Circle(width/2,height/2,10,color(200,75));
   } else {
     s[i] = new Square(width/2,height/2,10);
   }
  }
}

void draw() {
  background(255);
  // The magic of polymorphism, we can treat circles and squares 
  // together as Shapes, and the appropriate "render" and "jiggle" method will be called!
  for (int i = 0; i < s.length; i++) {
    Shape ashape = s[i];
    ashape.jiggle();
    ashape.render();
  }
}


