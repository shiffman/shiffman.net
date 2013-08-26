import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class random_walk extends PApplet {// Daniel Shiffman
// The Nature of Code
// ITP, Spring 2006
// http://www.shiffman.net/

Walker w;

public void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,255);
  
  // Create a walker object
  w = new Walker();
  framerate(60);
  background(0);
}

public void draw() {
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
  int x,y;

  Walker() {
    x = width/2;
    y = height/2;
  }

  public void render() {
    stroke(255,100);
    point(x,y);
  }

  // Randomly move up, down, left, right, or stay in one place
  public void walk() {
    int vx = PApplet.toInt(random(3))-1;
    int vy = PApplet.toInt(random(3))-1;
    x += vx;
    y += vy;
    x = constrain(x,0,width-1);
    y = constrain(y,0,height-1);
  }
}
static public void main(String args[]) {   PApplet.main(new String[] { "random_walk" });}}