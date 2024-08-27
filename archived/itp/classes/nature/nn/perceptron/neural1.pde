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
float f(float x) {
  return x*0.9-0.2; 
}

void setup() {
  size(400,400);
  scl = width/2.0;  // Scale based on width
  
  // The perceptron has 3 inputs -- x, y, and bias
  // Second value is "Learning Constant"
  ptron = new Perceptron(3,0.002);  // Learning Constant is low just b/c it's fun to watch, this is not optimal
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


void draw() {
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
  for (float x = -2; x <= 2; x+=0.1) {
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
