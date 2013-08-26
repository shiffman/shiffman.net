import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class snow extends PApplet {float increment = 0.02f;

public void setup() {
  size(200,200);
  framerate(30);
}

public void draw() {
  background(0);
  loadPixels();
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      float bright = random(255);
      pixels[x+y*width] = color(bright);
    }
  }
  updatePixels();
}
static public void main(String args[]) {   PApplet.main(new String[] { "snow" });}}