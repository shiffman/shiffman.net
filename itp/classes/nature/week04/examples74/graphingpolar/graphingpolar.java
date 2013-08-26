import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class graphingpolar extends PApplet {//GRAPH ALL POINTS FOR WHICH THIS FORMULA IS TRUE
//sin(6*cos(r) + 5*theta) < -0.3f

public void setup() {
  size(200,200);
}

public void draw() {
  float interact = (mouseX * 10.0f) / width;
  float w = 16.0f;         //2D space width
  float h = 16.0f;         //2D space height
  float dx = w / width;    //increment x this amount per pixel
  float dy = h / height;   //increment y this amount per pixel
  float x = -w/2;          //start x at -1 * width / 2
  for (int i = 0; i < width; i++) {
    float y = -h/2;        //start y at -1 * height / 2
    for (int j = 0; j < height; j++) {
      float r = sqrt((x*x) + (y*y));    //convert cartesian to polar
      float theta = atan2(y,x);         //convert cartesian to polar
      //GRAPH A FUNCTION BASED ON POLAR COORDINATES HERE
      //THIS EXAMPLE MAPS A GRAYSCALE VALUE TO THE FUNCTION'S RESULT
      //float val = cos(r);
      //float val = sin(theta);
      float val = sin(interact*cos(r) + 5 * theta);           //calculate a value between -1 and 1
      pixels[i+j*width] = color((val + 1.0f)* 255.0f/2.0f);     //scale that value to 0 and 255
      y += dy;                //increment y
    }
    x += dx;                  //increment x
  }
}

public void mousePressed() {
  saveFrame();
}
}