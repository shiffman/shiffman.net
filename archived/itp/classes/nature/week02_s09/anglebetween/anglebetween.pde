// Angle Between Two Vectors
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Using the dot product to compute the angle between two vectors

PFont f;

void setup() {
  size(200,200);
  smooth();
  f = createFont("Arial",16,true);
}

void draw() {
  background(255);
  
  // A "vector" (really a point) to store the mouse location and screen center location
  PVector mouseLoc = new PVector(mouseX,mouseY);
  PVector centerLoc = new PVector(width/2,height/2);  
  
  // Aha, a vector to store the displacement between the mouse and center
  PVector v = PVector.sub(mouseLoc,centerLoc);
  
  PVector blah = new PVector(75,0);
  // Render the vector
  drawVector(v,centerLoc,1.0f);
  drawVector(blah,centerLoc,1.0f);
  
  
  float theta = PVector.angleBetween(v,blah);
  
  textFont(f);
  fill(0);
  text(int(degrees(theta)),10,40);

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







