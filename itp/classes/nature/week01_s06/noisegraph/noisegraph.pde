// The Nature of Code
// ITP, Spring 2006
// http://www.shiffman.net/

// TIME
float t = 0.0;

void setup() {
  size(200,200);
  smooth();
  framerate(30);
}


void draw() {

  background(0);
  float xoff = t;
  for (int i = 0; i < width; i++) {
    float y = noise(xoff)*height;
    xoff += 0.01;
    stroke(255);
    line(i,height,i,height-y);
  }
  t+= 0.01;

}
