import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class noise1D extends PApplet {float xoff = 0.0f;
float xincrement = 0.005f;

public void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,100);
  smooth();
  noStroke();
}

public void draw()
{
  //create an alpha blended background
  fill(0,1);
  rect(0,0,width,height);
  
  /***float n = random(0,width);  //TRY THIS LINE INSTEAD OF NOISE AND SEE WHAT HAPPENS****/
  
  //get a noise value based on x and scale it according to the window's width
  float n = noise(xoff)*width;
  //with each cycle, increment xoff
  xoff += xincrement;
  
  //draw the ellipse at the value produced by perlin noise
  fill(255);
  ellipse(n,height/2,8,8);
}

}