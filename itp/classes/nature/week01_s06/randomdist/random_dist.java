import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class random_dist extends PApplet {// Daniel Shiffman
// The Nature of Code
// Testing Distribution of Perlin Noise generated #'s vs. Randoms

float[] vals;
float[] norms;
float xoff = 0.0f;

public void setup() {
  size(300,200);
  vals = new float[width];
  norms = new float[width];
}

public void draw() {
  background(100);
  //float n = noise(xoff);
  int index = PApplet.toInt(random(width)); //int(n*width);
  vals[index]++;
  xoff += 0.01f;
 stroke(255);
  boolean normalization = false;
  float maxy = 0.0f;
  for (int x = 0; x < vals.length; x++) {
    line(x,height,x,height-norms[x]);
    if (vals[x] > height) normalization = true;
    if(vals[x] > maxy) maxy = vals[x];
  }
  for (int x = 0; x < vals.length; x++) {
    if (normalization) norms[x] = (vals[x] / maxy) * (height);
    else norms[x] = vals[x];
  }  
}
static public void main(String args[]) {   PApplet.main(new String[] { "random_dist" });}}