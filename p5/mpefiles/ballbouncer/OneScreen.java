import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class OneScreen extends PApplet {/**
 * Simple Bouncing Ball Demo
 * <http://code.google.com/p/mostpixelsever/>
 * @author Shiffman
 */


// The list of balls
ArrayList balls;


public void setup() {
  size(320,240);
  smooth();

  // Start with 10 balls
  balls = new  ArrayList();
  for (int i = 0; i < 10; i++) {
    Ball ball= new Ball(random(width),random(height));
    balls.add(ball);
  }
}


public void draw() {

  background(255);

  for (int i = 0; i < balls.size(); i++) {
    Ball ball = (Ball) balls.get(i);
    ball.calc();
    ball.display();
  }
}

public void mousePressed() {
  balls.add(new Ball(mouseX,mouseY));
}

/**
 * Ball class for simple bouncing ball demo
 * <http://http://code.google.com/p/mostpixelsever/>
 * @author Shiffman
 */

class Ball {

  float x = 0;    // Ellipse x location
  float y = 0;    // Ellipse y location
  float xdir = 1; // x velocity
  float ydir = 1; // y velocity

  float r = 24;   // size

  Ball(float _x, float _y){
    xdir = random(-5,5);
    ydir = random(-5,5);
    x = _x;
    y = _y;
  }

  // A simple bounce across the screen
  public void calc(){
    if (x < 0 || x > width) xdir *= -1;
    if (y < 0 || y > height) ydir *= -1;
    x += xdir;
    y += ydir;
  }

  public void display(){
    stroke(0);
    fill(0,100);
    ellipse(x,y,r,r);
  }
}

  static public void main(String args[]) {     PApplet.main(new String[] { "OneScreen" });  }}