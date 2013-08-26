import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class distributionprobability_gaussian_simple extends PApplet {
//declare a Random number generator object
Random generator;

public void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,100);
  generator = new Random();   //initialize generator
  smooth();
}

public void draw() {
  //create an alpha blended background
  fill(0,1);
  rect(0,0,width,height);
  
  //get a gaussian random number w/ mean of 0 and standard deviation of 1.0
  float xloc = (float) generator.nextGaussian();
  
  float sd = 25;                //define a standard deviation
  float mean = width/2;         //define a mean value (middle of the screen along the x-axis)
  xloc = ( xloc * sd ) + mean;  //scale the gaussian random number by standard deviation and mean

  fill(255);
  noStroke();
  ellipse(xloc,height/2,8,8);   //draw an ellipse at our "normal" random location
}

}