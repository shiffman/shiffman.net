import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class randomgraph extends PApplet {// The Nature of Code
// ITP, Spring 2006
// http://www.shiffman.net/

// TIME
float t = 0.0f;

public void setup() {
  size(200,200);
  smooth();
  framerate(30);
}


public void draw() {

  background(0);
  float xoff = t;
  for (int i = 0; i < width; i++) {
    //float y = noise(xoff)*height;
    //xoff += 0.01;
    float y = random(height);
    stroke(255);
    line(i,height,i,height-y);
  }
  t+= 0.01f;

}
static public void main(String args[]) {   PApplet.main(new String[] { "randomgraph" });}}