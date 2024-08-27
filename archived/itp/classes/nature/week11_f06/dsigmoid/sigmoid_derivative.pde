// Daniel Shiffman
// Graphing the Sigmoid Function
// Nov 2006
// http://en.wikipedia.org/wiki/Sigmoid_function

// Sigmoid function
float f(float x) {
  return 1.0 / (1.0 + exp(-x));
}

float df(float x) {
  return f(x)*(1-f(x)); 
}


void setup() {
  size(200,200);
  smooth(); 
}

void draw() {
  background(0);
  float x = -6.0;
  float dx = 12.0 / width;
  stroke(50,50,100);
  noFill();
  beginShape();
  for (int i = 0; i < width; i++) {
     float y = f(x);
     vertex(i,height-y*height);
     x+=dx;
  }
  endShape();
  
  x = -6.0;
  stroke(255);
  noFill();
  beginShape();
  for (int i = 0; i < width; i++) {
     float y = df(x);
     vertex(i,height-y*height*4);
     x+=dx;
  }
  endShape();

  
  noLoop();
}
