// Daniel Shiffman
// Graphing the Sigmoid Function
// Nov 2006
// http://en.wikipedia.org/wiki/Sigmoid_function

// Sigmoid function
float f(float x) {
  return 1.0 / (1.0 + exp(-x));
}


void setup() {
  size(200,200);
  smooth(); 
}

void draw() {
  background(0);
  float x = -6.0;
  float dx = 12.0 / width;
  stroke(255);
  noFill();
  beginShape();
  for (int i = 0; i < width; i++) {
     float y = f(x);
     vertex(i,height-y*height);
     x+=dx;
  }
  endShape();
  noLoop();
}
