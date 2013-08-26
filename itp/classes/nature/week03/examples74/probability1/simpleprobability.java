import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class simpleprobability extends PApplet {
public void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,255);
  smooth();
}

public void draw() {
  //create an alpha blended background
  fill(0,1);
  rect(0,0,width,height);
  
  //calculate a probability between 0 and 10% based on mouseX location
  float prob = (mouseX / (float) width) * 0.10f;     
  
  //get a random floating point value between 0 and 1
  float r = random(1);   
  
  //test the random value against the probability and trigger an event
  if (r < prob) {
    noStroke();
    fill(255);
    ellipse(random(width),random(height),16,16);
  }
}
}