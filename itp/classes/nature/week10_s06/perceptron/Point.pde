// Daniel Shiffman
// The Nature of Code, Fall 2006
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
