// Simple Vector3D
// Daniel Shiffman <http://www.shiffman.net>

// A Vector3D object is generated based 
// mouse location relative to window's center

// Created 2 May 2005
PFont f;

void setup() {
  size(200,200);
  framerate(30);
  smooth();
  f = loadFont("ArialMT-16.vlw");
}

void draw() {
  background(100);
  
  // A "vector" (really a point) to store the mouse location and screen center location
  Vector3D mouseLoc = new Vector3D(mouseX,mouseY);
  Vector3D centerLoc = new Vector3D(width/2,height/2);  
  
  // Aha, a vector to store the displacement between the mouse and center
  Vector3D v = Vector3D.sub(mouseLoc,centerLoc);
  
  Vector3D blah = new Vector3D(75,0);
  // Render the vector
  drawVector(v,centerLoc,1.0f);
  drawVector(blah,centerLoc,1.0f);
  
  
  float theta = Vector3D.angleBetween(v,blah);
  
  textFont(f);
  fill(255);
  text(int(degrees(theta)),10,40);

}

// Renders a vector object 'v' as an arrow and a location 'loc'
void drawVector(Vector3D v, Vector3D loc, float scayl) {
  pushMatrix();
  float arrowsize = 4;
  // Translate to location to render vector
  translate(loc.x,loc.y);
  stroke(255);
  // Call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading2D());
  // Calculate length of vector & scale it to be bigger or smaller if necessary
  float len = v.magnitude()*scayl;
  // Draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  popMatrix();
}



