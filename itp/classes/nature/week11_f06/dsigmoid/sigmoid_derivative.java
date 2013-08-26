import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class sigmoid_derivative extends PApplet {// Daniel Shiffman
// Graphing the Sigmoid Function
// Nov 2006
// http://en.wikipedia.org/wiki/Sigmoid_function

// Sigmoid function
public float f(float x) {
  return 1.0f / (1.0f + exp(-x));
}

public float df(float x) {
  return f(x)*(1-f(x)); 
}


public void setup() {
  size(200,200);
  smooth(); 
}

public void draw() {
  background(0);
  float x = -6.0f;
  float dx = 12.0f / width;
  stroke(50,50,100);
  noFill();
  beginShape();
  for (int i = 0; i < width; i++) {
     float y = f(x);
     vertex(i,height-y*height);
     x+=dx;
  }
  endShape();
  
  x = -6.0f;
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
static public void main(String args[]) {   PApplet.main(new String[] { "sigmoid_derivative" });}}