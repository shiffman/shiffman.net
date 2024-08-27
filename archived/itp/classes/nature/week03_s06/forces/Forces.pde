// Forces
// Daniel Shiffman <http://www.shiffman.net>

// Demonstrates the accumulation of forces
// on a moving object (wind, drag, gravity)
// Force = Mass * Acceleration
// Acceleration = Force / Mass
// Viscous Force = -c * (Velocity) 

// Mouse press turns on and off displaying vectors

// Created 2 May 2005

Thing t;
boolean showVectors = true;

void setup() {
  size(200,200);
  framerate(30);  
  smooth();
  background(0);
  colorMode(RGB,255,255,255,100);
  Vector3D a = new Vector3D(0.0,0.0);
  Vector3D v = new Vector3D(0.0,0.0);
  Vector3D l = new Vector3D(10,0);
  t = new Thing(a,v,l,1.5);
}

void draw() {
  background(0);

  // Add gravity to thing
  // This isn't "real" gravity, just a made up force vector
  Vector3D grav = new Vector3D(0,0.05);
  t.add_force(grav);

  // Add wind to thing
  // Again, just making up a force vector
  Vector3D wind = new Vector3D(0.03,0.0);
  t.add_force(wind);

  // Add drag force
  float liq_start    = 75; // Liquid location
  float liq_height   = 40; // Liquid height
  // Test if thing intersects liquid
    if ((t.getLoc().y > liq_start) && (t.getLoc().y < liq_start + liq_height)) {
    // Drag is calculated as force vector in the negative direction of velocity
    float c = -0.02f;                           // Drag coefficient
    Vector3D thingVel = t.getVel();             // Velocity of our thing
    Vector3D force = Vector3D.mult(thingVel,c); // Following the formula above
    t.add_force(force);                         // Adding the force to our object, which will ultimately affect its acceleration
  }

  t.go();

  // Draw the "liquid"
  // Note it would probably be a good idea to make a liquid class
  noStroke();
  fill(200,50);
  rectMode(CORNER);
  rect(0,liq_start,width,liq_height);
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


void mousePressed() {
  showVectors = !showVectors;
}




