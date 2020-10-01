// Dynamic Acceleration: PVector
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Demonstrates a dynamically changing acceleration (towards the mouse)

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
  
   if (mousePressed) {
    // Compute difference vector between mouse and object location
    // 3 steps -- (1) Get Mouse Location, (2) Get Difference Vector, (3) Normalize difference vector
    PVector m = new PVector(mouseX,mouseY);
    PVector diff = PVector.sub(m,t.getLoc());
    diff.normalize();
    float factor = 1.0;  // Magnitude of Acceleration (not increasing it right now)
    diff.mult(factor);
    //object accelerates towards mouse
    t.setAcc(diff);
  } else {
    t.setAcc(new PVector(0,0));
  }
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


void keyPressed() {
  showVectors = !showVectors;
}


