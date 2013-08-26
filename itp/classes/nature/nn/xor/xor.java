import processing.core.*; 
import processing.xml.*; 

import processing.opengl.*; 
import nn.*; 

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

public class xor extends PApplet {

// Daniel Shiffman
// The Nature of Code
// http://www.shiffman.net/teaching/nature

// XOR Multi-Layered Neural Network Example
// Neural network code is all in the "code" folder




ArrayList inputs;  // List of training input values
Network nn;        // Neural Network Object
int count;         // Total training interations
Landscape land;    // Solution space
float theta = 0.0f; // Angle of rotation
PFont f;           // Font


public void setup() {

  size(400,400,OPENGL);

  // Create a landscape object
  land = new Landscape(20,300,300);

  f = createFont("Courier",12,true);

  nn = new Network(2,4);

  // Create a list of 4 training inputs
  inputs = new ArrayList();
  float[] input = new float[2];
  input[0] = 1; 
  input[1] = 0;  
  inputs.add((float []) input.clone());
  input[0] = 0; 
  input[1] = 1;  
  inputs.add((float []) input.clone());
  input[0] = 1; 
  input[1] = 1;  
  inputs.add((float []) input.clone());
  input[0] = 0; 
  input[1] = 0;  
  inputs.add((float []) input.clone());
}

public void draw() {

  int trainingIterationsPerFrame = 5;

  for (int i = 0; i < trainingIterationsPerFrame; i++) {
    // Pick a random training input
    int pick = PApplet.parseInt(random(inputs.size()));
    // Grab that input
    float[] inp = (float[]) inputs.get(pick); 
    // Compute XOR
    float known = 1;
    if ((inp[0] == 1.0f && inp[1] == 1.0f) || (inp[0] == 0 && inp[1] == 0)) known = 0;
    // Train that sucker!
    float result = nn.train(inp,known);
    count++;
  }

  // Ok, visualize the solution space
  background(175);
  pushMatrix();
  translate(width/2,height/2+20,-160);
  rotateX(PI/3);
  rotateZ(theta);

  // Put a little BOX on screen
  pushMatrix();
  stroke(50);
  noFill();
  translate(-10,-10,0);
  box(280);

  // Draw the landscape
  popMatrix();
  land.calculate(nn);
  land.render(); 
  theta += 0.0025f;
  popMatrix();

  // Display overal neural net stats
  networkStatus();

}


public void networkStatus() {
  float mse = 0.0f;

  textFont(f);
  fill(0);
  text("Your friendly neighborhood neural network solving XOR.",10,20);
  text("Total iterations: " + count,10,40);

  for (int i = 0; i < inputs.size(); i++) {
    float[] inp = (float[]) inputs.get(i); 
    float known = 1;
    if ((inp[0] == 1.0f && inp[1] == 1.0f) || (inp[0] == 0 && inp[1] == 0)) known = 0;
    float result = nn.feedForward(inp);
    //System.out.println("For: " + inp[0] + " " + inp[1] + ":  " + result);
    mse += (result - known)*(result - known);
  }

  float rmse = sqrt(mse/4.0f);
  DecimalFormat df = new DecimalFormat("0.000");
  text("Root mean squared error: " + df.format(rmse), 10,60);

}


// Daniel Shiffman
// <http://www.shiffman.net>
// "Landscape" example

class Landscape {

  int scl;           // size of each cell
  int w,h;           // width and height of thingie
  int rows, cols;    // number of rows and columns
  float zoff = 0.0f;  // perlin noise argument
  float[][] z;       // using an array to store all the height values 

  Landscape(int scl_, int w_, int h_) {
    scl = scl_;
    w = w_;
    h = h_;
    cols = w/scl;
    rows = h/scl;
    z = new float[cols][rows];
  }


  // Calculate height values (based off a neural netork)
  public void calculate(Network nn) {
    float x = 0;
    float dx = (float) 1.0f / cols;
    for (int i = 0; i < cols; i++)
    { 
      float y = 0;
      float dy = (float) 1.0f / rows;
      for (int j = 0; j < rows; j++)
      {
        float[] input = new float[2];
        input[0] = x; 
        input[1] = y;
        float result = nn.feedForward(input);
        z[i][j] = z[i][j]*0.95f + 0.05f*(float)(result*280.0f-140.0f);
        y += dy;
      }
      x += dx;
    }

  }

  // Render landscape as grid of quads
  public void render() {
    // Every cell is an individual quad
    // (could use quad_strip here, but produces funny results, investigate this)
    for (int x = 0; x < z.length-1; x++)
    {
      for (int y = 0; y < z[x].length-1; y++)
      {
        // one quad at a time
        // each quad's color is determined by the height value at each vertex
        // (clean this part up)
        noStroke();
        pushMatrix();
        beginShape(QUADS);
        translate(x*scl-w/2,y*scl-h/2,0);
        fill(z[x][y]+127,220);
        vertex(0,0,z[x][y]);
        fill(z[x+1][y]+127,220);
        vertex(scl,0,z[x+1][y]);
        fill(z[x+1][y+1]+127,220);
        vertex(scl,scl,z[x+1][y+1]);
        fill(z[x][y+1]+127,220);
        vertex(0,scl,z[x][y+1]);
        endShape();
        popMatrix();
      }
    }
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "xor" });
  }
}
