import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class Walker_noVectors extends PApplet {// Daniel Shiffman
// The Nature of Code
// ITP, Spring 2006
// http://www.shiffman.net/

Walker w;

public void setup() {
  size(400,400);
  background(0);
  colorMode(RGB,255,255,255,255);

  // Create a walker object
  w = new Walker();

}

public void draw() {
  background(0);
  // Run the walker object
  w.walk();
  w.render();
}



// Daniel Shiffman
// The Nature of Code
// ITP, Spring 2006
// http://www.shiffman.net/

// A random walker object!

class Walker {
  float x,y;

  Walker() {
    x = width/2;
    y = height/2;
  }

  public void render() {
    stroke(255);
    fill(100);
    rectMode(CENTER);
    rect(x,y,40,40);
  }

  // Randomly move up, down, left, right, or stay in one place
  public void walk() {
    float vx = random(-2,2);
    float vy = random(-2,2);
    x += vx;
    y += vy;
    
    // Stay on the screen
    x = constrain(x,0,width-1);
    y = constrain(y,0,height-1);
  }
}
static public void main(String args[]) {   PApplet.main(new String[] { "Walker_noVectors" });}}