// Daniel Shiffman
// The Nature of Code
// ITP, Spring 2006
// http://www.shiffman.net/

Walker w;

void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,255);
  
  // Create a walker object
  w = new Walker();
  framerate(60);
  background(0);
}

void draw() {
  // Run the walker object
  w.walk();
  w.render();
}


