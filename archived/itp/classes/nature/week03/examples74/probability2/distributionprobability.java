import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class distributionprobability extends PApplet {
public void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,255);
  smooth();
}

public void draw() {
  //create an alpha blended background
  noStroke();
  fill(0,1);
  rect(0,0,width,height);

  //probabilities for 3 different cases (these need to add up to 100% since something always occurs here!)
  float red_prob   = 0.05f;               // 5% chance of red color occurring
  float green_prob = 0.80f + red_prob;    // 80% chance of green color occuring
  //float blue_prob  = 1.0 - green_prob; // 15% chance of blue color occuring (we don't actually need this line since it is, by definition, the "in all other cases" part of our else
  float num = random(1);                 // pick a random number between 0 and 1
  if (num < red_prob) {
    fill(255,0,0);
  } else if (num < green_prob) {
    fill(0,255,0);
  } else {
    fill(0,0,255);
  }
  ellipse(random(width),random(height),16,16);

}
}