// Attraction
// Daniel Shiffman <http://www.shiffman.net>

// Demonstrates attractive force one body exerts on another
// G ---> universal gravitational constant
// m1 --> mass of object #1
// m2 --> mass of object #2
// d ---> distance between objects
// F = (G*m1*m2)/(d*d)

// Click and drag attractive body to move throughout space
// Keypress turns on and off vector display

// Created 2 May 2005

Thing t;
Attractor a;
boolean showVectors = true;

void setup() {
  size(200,200);
  framerate(30);
  background(0);
  colorMode(RGB,255,255,255,100);
  Vector3D ac = new Vector3D(0.0,0.0);
  Vector3D ve = new Vector3D(0.0,1.0);
  Vector3D lo = new Vector3D(50,50);
  // Create new thing with some initial settings
  t = new Thing(ac,ve,lo,10);
  // Create an attractive body
  a = new Attractor(new Vector3D(width/2,height/2),20,0.4);
}

void draw() {
  background(0);
  smooth();
  a.rollover(mouseX,mouseY);

  // Calculate a force exerted by "attractor" on "thing"
  Vector3D f = a.calcGravForce(t);
  // Apply that force to the thing
  t.add_force(f);
  // Update and render the positions of both objects
  t.go();
  a.go();

}

void mousePressed() {
  a.clicked(mouseX,mouseY);
}

void mouseReleased() {
  a.stopDragging();
}

void keyPressed() {
  showVectors = !showVectors;
}

// Renders a vector object 'v' as an arrow and a location 'loc'
void drawVector(Vector3D v, Vector3D loc, float scayl) {
  if (v.magnitude() > 0.0) {
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
}

