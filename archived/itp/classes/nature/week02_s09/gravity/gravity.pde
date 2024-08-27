// Simple PVector: Gravity
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Demonstrates simple bouncing ball with Gravity

// Declare a "Thing" object
Thing t;

boolean showVectors = true;

void setup() {
  size(200,200);
  smooth();
  // Create the thing object
  PVector a = new PVector(0.0,0.125);
  PVector v = new PVector(0.0,0.0);
  PVector l = new PVector(width/2,0);
  t = new Thing(a,v,l);}

void draw() {
  background(255);
  
  // Run the Thing object
  t.go();

}

// Renders a vector object 'v' as an arrow and a location 'loc'
void drawVector(PVector v, PVector loc, float scayl) {
  pushMatrix();
  float arrowsize = 4;
  // Translate to location to render vector
  translate(loc.x,loc.y);
  stroke(0);
  // Call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading2D());
  // Calculate length of vector & scale it to be bigger or smaller if necessary
  float len = v.mag()*scayl;
  // Draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  popMatrix();
}



