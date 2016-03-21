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

public class PolarToCartesian extends PApplet {

// PolarToCartesian
// Daniel Shiffman <http://www.shiffman.net>

// Convert a polar coordinate (r,theta) to cartesian (x,y)
// x = r * cos(theta)
// y = r * sin(theta)

float r;

// Angle and angular velocity, accleration
float theta;
float theta_vel;
float theta_acc;

public void setup() {
  size(200,200);
  smooth();
  
  // Initialize all values
  r = 50.0f;
  theta = 0.0f;
  theta_vel = 0.0f;
  theta_acc = 0.0001f;
}

public void draw() {
  background(255);
  // Translate the origin point to the center of the screen
  translate(width/2,height/2);
  
  // Convert polar to cartesian
  float x = r * cos(theta);
  float y = r * sin(theta);
  
  // Draw the rectangle at the cartesian coordinate
  ellipseMode(CENTER);
  stroke(0);
  fill(175);
  line(0,0,x,y);
  ellipse(x,y,16,16);
  
  // Apply acceleration and velocity to angle (r remains static in this example)
  theta_vel += theta_acc;
  theta += theta_vel;

}





  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "PolarToCartesian" });
  }
}
