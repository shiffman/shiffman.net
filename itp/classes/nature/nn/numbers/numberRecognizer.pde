// Daniel Shiffman
// The Nature of Code
// http://www.shiffman.net/teaching/nature

// Using a Neural Network to recognize pixels of an input image
// Neural network code is all in the "code" folder

import nn.*;

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

void setup() {
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

void draw() {
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




