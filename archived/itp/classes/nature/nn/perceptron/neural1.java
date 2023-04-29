import processing.core.*; 
import processing.xml.*; 

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

public class neural1 extends PApplet {

// Daniel Shiffman
// The Nature of Code
// http://www.shiffman.net/teaching/nature
// Simple Perceptron Example
// See: http://en.wikipedia.org/wiki/Perceptron

// Code based on text "Artificial Intelligence", George Luger

// A list of points we will use to "train" the perceptron
Point[] training = new Point[2000];
// A Perceptron object
Perceptron ptron;

// We will train the perceptron with one "Point" object at a time
int count = 0;

// Scale (note we are living in a space -2 < y < 2, -2 < x < 2)
float scl;

// The function to describe a line 
public float f(float x) {
  return x*0.9f-0.2f; 
}

public void setup() {
  size(400,400);
  scl = width/2.0f;  // Scale based on width
  
  // The perceptron has 3 inputs -- x, y, and bias
  // Second value is "Learning Constant"
  ptron = new Perceptron(3,0.002f);  // Learning Constant is low just b/c it's fun to watch, this is not optimal
  // ptron = new Perceptron(3,0.1);  // A bit better, perhaps

  // Create a random set of training points and calculate the "known" answer
  for (int i = 0; i < training.length; i++) {
    float x = random(-2,2);
    float y = random(-2,2);
    int answer = 1;
    if (y < f(x)) answer = -1;
    training[i] = new Point(x,y,answer);
  }
  smooth();
  //frameRate(5);
}


public void draw() {
  background(255);
  
  float[] weights = ptron.getWeights();
  // Debug the weights
  // println("weights: " + weights[0] + " " + weights[1] + " " + weights[2]);

  // Draw the line based on the current weights
  // Formula is weights[0]*x + weights[1]*y + weights[2] = 0
  stroke(0);
  beginShape();
  for (int x = -2; x <= 2; x++) {
    float y = (-weights[2] - weights[0]*x)/weights[1]; // solve for Y
    vertex(x*scl+scl,y*scl+scl);  // scale up for pixels
  }
  endShape();

  // Draw the line for the "correct" answer 
  beginShape();
  stroke(255,200,100);
  for (float x = -2; x <= 2; x+=0.1f) {
    float y = f(x);  
    vertex(x*scl+scl,y*scl+scl); // scale up for pixels
  }
  endShape();

  
  // Train the Perceptron with one "training" point at a time
  ptron.train(training[count].vals,training[count].output);
  count = (count + 1) % training.length;
  
  // Draw all the points based on what the Perceptron would "guess"
  // Does not use the "known" correct answer
  for (int i = 0; i < count; i++) {
    noStroke();
    fill(255);
    int guess = ptron.guess(training[i].vals);
    if (guess > 0) fill(200,100,200);
    else fill(100,200,200);
    ellipse(training[i].vals[0]*200+200,training[i].vals[1]*200+200,8,8);
  }
}
// Daniel Shiffman
// The Nature of Code
// http://www.shiffman.net/teaching/nature
// Simple Perceptron Example
// See: http://en.wikipedia.org/wiki/Perceptron

// Perceptron Class

class Perceptron {
  float[] weights;  // Array of weights for inputs
  float c;          // learning constant

  // Perceptron is created with n weights and learning constant
  Perceptron(int n, float c_) {
    weights = new float[n];
    // Start with random weights
    for (int i = 0; i < weights.length; i++) {
      weights[i] = random(-1,1); 
    }
    c = c_;
  }

  // Function to train the Perceptron
  // Weights are adjusted based on "desired" answer
  public void train(float[] vals, int desired) {
    // Guess the result
    int result = guess(vals);
    // Compute the factor for changing the weight based on the error
    // Error = desired output - guessed output
    // Note this can only be 0, -2, or 2
    // Multiply by learning constant
    float weightChange = c*(desired - result);
    // Adjust weights based on weightChange * input
    for (int i = 0; i < weights.length; i++) {
      weights[i] += weightChange * vals[i];         
    }
  }

  // Guess -1 or 1 based on input values
  public int guess(float[] vals) {
    // Sum all values
    float sum = 0;
    for (int i = 0; i < weights.length; i++) {
      sum += vals[i]*weights[i];
    }
    // Result is sign of the sum, -1 or 1
    int result = 1;
    if (sum < 0) result = -1;
    return result;
  }

  // Return weights
  public float[] getWeights() {
    return weights; 
  }
}
// Daniel Shiffman
// The Nature of Code
// http://www.shiffman.net/teaching/nature
// Simple Perceptron Example
// See: http://en.wikipedia.org/wiki/Perceptron

// A class to describe a training point
// Has an x and y, a "bias" (1) and known output
// Could also add a variable for "guess" but not required here

class Point {
  
  float[] vals;
  int output; 
  
  Point(float x_, float y_, int output_) {
    vals = new float[3];
    vals[0] = x_;
    vals[1] = y_;
    vals[2] = 1;
    output = output_;
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "neural1" });
  }
}
