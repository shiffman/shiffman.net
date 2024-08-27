import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class walker_vectors extends PApplet {

// Random Walker (Vectors)
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

Walker w;

public void setup() {
  size(400,400);
  frameRate(30);

  // Create a walker object
  w = new Walker();

}

public void draw() {
  background(255);
  // Run the walker object
  w.walk();
  w.render();
}



// Random Walker (No Vectors)
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// A random walker class!

class Walker {
  PVector loc;

  Walker() {
    loc = new PVector(width/2,height/2);
  }

  public void render() {
    stroke(0);
    fill(175);
    rectMode(CENTER);
    rect(loc.x,loc.y,40,40);
  }

  // Randomly move up, down, left, right, or stay in one place
  public void walk() {
    PVector vel = new PVector(random(-2,2),random(-2,2));
    loc.add(vel);
    
    // Stay on the screen
    loc.x = constrain(loc.x,0,width-1);
    loc.y = constrain(loc.y,0,height-1);
  }
}


  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "walker_vectors" });
  }
}
