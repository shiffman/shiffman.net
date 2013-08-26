import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class noise2D extends PApplet {float increment = 0.02f;

public void setup() {
  size(200,200);
}

public void draw() {
  background(0);
  
  //try different values for noisedetail here
  //noiseDetail(8,0.65f);

  float xoff = 0.0f; //start x at 0
  
  //for every x,y coordinate in a 2D space, calculate a noise value
  for (int x = 0; x < width; x++) {
    xoff += increment;   //increment for every x
    float yoff = 0.0f;   //for every x start y at 0
    for (int y = 0; y < height; y++) {
      yoff += increment; //increment every y
      
      /**calculate noise and scale by 255**/
      float bright = noise(xoff,yoff)*255;

      /**try using this line instead***/
      //float bright = random(0,255);
      
      //set each pixel onscreen to a greyscale value
      pixels[x+y*width] = color(bright,bright,bright);
    }
  }
}
}