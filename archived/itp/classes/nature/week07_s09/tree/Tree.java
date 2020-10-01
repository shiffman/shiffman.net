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

public class Tree extends PApplet {

// Recursive Tree
// Daniel Shiffman <http://www.shiffman.net>

// Renders a simple tree-like structure via recursion
// Branching angle calculated as a function of horizontal mouse location

float theta;   

public void setup() {
  size(200,200);
  smooth();
}

public void draw() {
  background(255);
  stroke(0);
  // Let's pick an angle 0 to 90 degrees based on the mouse position
  float a = (mouseX / (float) width) * 90f;
  // Convert it to radians
  theta = radians(a);
  // Start the tree from the bottom of the screen
  translate(width/2,height);
  // Draw a line 60 pixels
  line(0,0,0,-60);
  // Move to the end of that line
  translate(0,-60);
  // Start the recursive branching!
  branch(60);

}

public void branch(float h) {
  // Each branch will be 2/3rds the size of the previous one
  h *= 0.66f;
  
  // All recursive functions must have an exit condition!!!!
  // Here, ours is when the length of the branch is 2 pixels or less
  if (h > 2) {
    pushMatrix();    // Save the current state of transformation (i.e. where are we now)
    rotate(theta);   // Rotate by theta
    line(0,0,0,-h);  // Draw the branch
    translate(0,-h); // Move to the end of the branch
    branch(h);       // Ok, now call myself to draw two new branches!!
    popMatrix();     // Whenever we get back here, we "pop" in order to restore the previous matrix state
    
    // Repeat the same thing, only branch off to the "left" this time!
    pushMatrix();
    rotate(-theta);
    line(0,0,0,-h);
    translate(0,-h);
    branch(h);
    popMatrix();
  }
}



  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "Tree" });
  }
}
