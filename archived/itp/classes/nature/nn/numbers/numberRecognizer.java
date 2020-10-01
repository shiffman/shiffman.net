import processing.core.*; 
import processing.xml.*; 

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

public class numberRecognizer extends PApplet {

// Daniel Shiffman
// The Nature of Code
// http://www.shiffman.net/teaching/nature

// Using a Neural Network to recognize pixels of an input image
// Neural network code is all in the "code" folder



// The pixel data from each image file will be our training set
ArrayList pixelData;

// The size of our image data
int w = 18;
int h = 24;

// How many numbers are we going to try to guess
int total = 2; // Just doing 0's and 1's, but this will sort of work for more.
// Here are the training images
PImage[] trainingImages = new PImage[total];

// The Neural Network
Network nn;         

// How many training cycles have we done
int trainingCount;  

// We will train 5,000 iterations
int maxTrainingIterations = 15000;

// A submit button
Button submit;

// The points a user has drawn
ArrayList points;

// The input image from the user
PImage inputImage;

// Some booleans to keep track of what is going on
boolean welcome = true;
boolean training = false;
boolean trainingComplete = false;
boolean guessing = false;

PFont f;

// What is the neural network's guess
float guess = 0;

public void setup() {
  size(360,480);
  background(255);
  points = new ArrayList();

  // Load all the images
  loadTrainingImages();

  // Setup network
  nn = new Network(w*h,16);
  f = createFont("Georgia",16,true);

  // Make a blank input image
  inputImage = createImage(w,h,RGB);

  // Submit button
  submit = new Button(100,440,160,25, "submit");

}

public void draw() {
  background(255);

  // Welcome screen
  if (welcome) {
    welcome();
    // Training screen
  } 
  else if (training) {
    if (!trainingComplete) {
      train();
    }
    showTraining();
    // Guessing screen
  } 
  else {
    userDraw();
    guess();
  }


  // Draw the guessing image and training images
  noFill();
  stroke(0);
  strokeWeight(1);
  image(inputImage,0,0);
  rect(0,0,w,h);
  for (int i = 0; i < trainingImages.length; i++) {
    image(trainingImages[i],(i+1)*w,0);
    rect((i+1)*w,0,w,h);
  }
}





class Button {
  Rectangle r;      // Button's rectangle
  String txt;       // Button's text
  boolean clicked;  // Did i click on it?
  boolean rollover; // Did i rollover it?

  Button(int x, int y, int w, int h, String s) {
    r = new Rectangle(x,y,w,h);
    txt = s;
  }

  public void render() {
    // Draw rectangle and text based on whether rollover or clicked
    rectMode(PConstants.CORNER);
    strokeWeight(1);
    stroke(0); 
    fill(255);
    if (rollover) fill(127);
    if (clicked) fill(0);
    rect(r.x,r.y,r.width,r.height);
    int b = 0;
    if (clicked) b = 255;
    else if (rollover) b = 50;
    else b = 0;
    fill(b);
    textAlign(PConstants.CENTER);
    text(txt,r.x+r.width/2,r.y+r.height-6);
  }

  // Methods to check rollover, clicked, or released (must be called from appropriate
  // places in draw, mousePressed, mouseReleased
  public boolean rollover(int mx, int my) {
    if (r.contains(mx,my)) rollover = true;
    else rollover = false;
    return rollover;
  }

  public boolean clicked(int mx, int my) {
    if (r.contains(mx,my)) clicked = true;
    return clicked;
  }

  public void released() {
    clicked = false;
  }

}




class Point {

  float x,y;

  Point(float x_, float y_) {
    x = x_;
    y = y_;
  }

}


// Deal with button release
public void mouseReleased() {
  submit.released();
}

public void mousePressed() {
  // Clicking the mouse exits the welcome screen
  if (welcome) {
    welcome = false;
    training = true;
    // Deal with the submit button
  } 
  else if (submit.clicked(mouseX,mouseY)) {
    // Time to guess!
    guessing = true;
    float[] binaryPixels = getPixels(inputImage);
    float result = nn.feedForward(binaryPixels);
    // Here is the result
    guess = result * total;
  }
}

public void keyPressed() {
  // Quit training
  if (training && trainingComplete) {
    training = false;
    // If guessing
  } 
  else if (guessing) {
    // No longer guessing
    guessing = false;
    // What number did user  type
    int num = Integer.parseInt("" + key);
    float[] binaryPixels = getPixels(inputImage);
    // Retrain the network according to those pixels
    nn.train(binaryPixels, (float)num/total);
    // Clear what user has drawn for next time
    points.clear();
  }
}

// A little welcome message
public void welcome() {
  textFont(f);
  textAlign(CENTER);
  fill(0);
  text("Hello.  I am a very simple neural network. \n" + 
    "I can recognize 0's and 1's. \n" + 
    "Click the mouse and I'll start training.",width/2,height/2);
}


// Train the network
public void train() {
  // Adjust this number to make training appear to be faster / slower
  int cyclesPerFrame = 50;

  for (int i = 0; i < cyclesPerFrame; i++) {
    // Pick a number 
    int num = (int) random(total);
    // Look at the pixels for that number
    float[] pixies = (float[]) pixelData.get(num);  
    // Train the network according to those pixels
    nn.train(pixies, (float)num/total);
    // Increase the training iteration count
    trainingCount++;
  }
}

// Display info about the training
public void showTraining() {
  pushMatrix();
  translate(20,150);
  textFont(f);
  textAlign(LEFT);

  // How much training is complete
  float percentage = (float) trainingCount / maxTrainingIterations;
  // Draw status bar
  float statusBar = percentage*width/2;
  stroke(0);
  noFill();
  rect(5,5,width/2,10);
  fill(0);
  rect(5,5,statusBar,10);
  text("Training. . .",5,30);

  // Now we'll show what the network is currently guessing for each input image
  fill(0);
  float mse = 0;
  for (int i = 0; i < pixelData.size(); i++) {
    float[] inp = (float[]) pixelData.get(i); 
    float known = (float) i/total;
    float result = nn.feedForward(inp);
    text("My guess for image # " + i + " is " + nf((float)result * total,1,2),5,100+i*20);
    mse += (result - known)*(result - known);
  }
  // How many interations
  text("Total iterations: " + trainingCount,5,60);
  // Root mean squarted error
  float rmse = sqrt(mse/pixelData.size());
  text("Root mean squared error: " + nf(rmse,1,4), 5,80);
  // If we've finished training
  if (percentage >= 1.0f) {
    trainingComplete = true;
    text("I'm done training!  Press the space bar\nto draw a shape for me to guess.",5,-50);
  }
  popMatrix();
}

// Load all the images
public void loadTrainingImages() {
  pixelData = new ArrayList();
  for (int i = 0; i < total; i++) {
    trainingImages[i] = loadImage("numbers/"+i+".tif");
    float[] binaryPixels = getPixels(trainingImages[i]);
    pixelData.add(binaryPixels);
  }
}

public void userDraw() {
  // Keep a list of all mouse locations
  if (mousePressed && !submit.clicked && !guessing) {
    points.add(new Point(mouseX,mouseY));
  }

  // Draw those mouse locations as really wide continous line
  // This could really be improved
  noFill();
  stroke(0);
  strokeWeight(50);
  beginShape();
  for (int i = 0; i < points.size(); i++) {
    Point p = (Point) points.get(i);
    vertex(p.x,p.y);
  }
  endShape();
  // copy what was drawn onto the neural network's input image
  inputImage.copy(this.g,0, 0,width,height,0,0,w,h);
  inputImage.updatePixels();
}

// What to do with the user's input image
public void guess() {
  // If it's not time to guess yet just give the user some instructions
  // and show submit button
  if (!guessing) {
    textFont(f);
    fill(50);
    textAlign(CENTER);
    text("Draw something and submit!",width/2,20);
    submit.rollover(mouseX,mouseY);
    submit.render();
    // Otherwise, show the result of the guess!
  } 
  else {
    noStroke();
    fill(255);
    rect(0,400,width,height);
    fill(0);
    textAlign(CENTER);
    textSize(14);
    text("My output is " + nf(guess,1,2) + " so I guess " + (int)(round(guess)) + 
      "\n Please type the correct # to help train me.",width/2,420);
  }
}

// Get an array of floats 0 to 1 from an input image
public float[] getPixels(PImage img) {
  // This isn't terribly optimized, making floating point arrays for pixels that are 1 or 0
  // However, this would theoretically work with grayscale pixels mapped from 0 to 1
  float[] binaryPixels = new float[w*h];
  for (int pix = 0; pix < w*h; pix++) {
    float b = brightness(img.pixels[pix]);
    // Each pixel is back or what (1 or 0)
    if (b > 0) binaryPixels[pix] = 1;
    else binaryPixels[pix] = 0;
  }
  return binaryPixels;
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "numberRecognizer" });
  }
}
