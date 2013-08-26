import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class multiple_probability extends PApplet {// Daniel Shiffman
// The Nature of Code
// ITP, Spring 2006
// http://www.shiffman.net/

int x,y;

public void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,255);
  smooth();
  framerate(30);
}

public void draw() {
  //create an alpha blended background
  fill(0,1);
  rect(0,0,width,height);

  //probabilities for 3 different cases (these need to add up to 100% since something always occurs here!)
  float p1   = 0.05f;               // 5% chance of pure white occurring
  float p2 = 0.80f + p1;    // 80% chance of gray occuring
  //float p3  = 1.0 - p2 ; // 15% chance of black (we don't actually need this line since it is
  // by definit n, the "in all other cases" part of our else
    float num = random(1);                 // pick a random number between 0 and 1
  if (num <p1) {
    fill(255);
  } else if (num < p2) {
    fill(150);
  } else {
    fill(0);
  }
  
  stroke(200);
  rect(x,y,10,10);

  // X and Y walk through a grid
  x = (x + 10) % width;
  if (x == 0) y = (y + 10) % width;
}
static public void main(String args[]) {   PApplet.main(new String[] { "multiple_probability" });}}