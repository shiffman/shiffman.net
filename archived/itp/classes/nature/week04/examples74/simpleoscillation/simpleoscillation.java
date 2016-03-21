import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class simpleoscillation extends PApplet {//doing oscillation with velocity
float xtheta;
float xtheta_vel;

//Having two separate variables for x,y is not good
//This could be improved by using our Vector2D class!
float ytheta;
float ytheta_vel;

//a counter in case we want to oscillate with period calculated as number of frames
//float framecounter = 0.0f;

public void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,255);
  smooth();
  background(0);
  xtheta = ytheta = 0.0f;
  xtheta_vel = 0.01f;
  ytheta_vel = 0.0132f;
}

public void draw() {
  //an alpha background
  fill(0,1);
  noStroke();
  rect(0,0,width,height);

  //calculate oscillation along x axis
  float amplitude = 75.0f;
  float x = amplitude * sin(xtheta);
  xtheta += xtheta_vel;

  //calculate oscillation along y axis
  amplitude = 50.0f;
  float y = amplitude * cos(ytheta);
  ytheta += ytheta_vel;  
      
  //period as # of frames
  /*float period = 600.0f;
  float amplitude = 75.0f;
  float x = amplitude * cos(TWO_PI * framecounter / period);
  framecounter++;*/
  
  ellipseMode(CENTER);
  noStroke();
  fill(0,0,255);
  translate(width/2,height/2);  //move origin to center of screen
  ellipse(x,y,20,20);

}
}