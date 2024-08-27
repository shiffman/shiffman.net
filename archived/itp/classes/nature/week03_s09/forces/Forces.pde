// Forces
// Daniel Shiffman <http://www.shiffman.net>

// Demonstrates the accumulation of forces
// on a moving object (wind, drag, gravity)
// Force = Mass * Acceleration
// Acceleration = Force / Mass
// Viscous Force = -c * (Velocity) 

// Mouse press turns on and off displaying vectors

Thing t;
boolean showVectors = true;

void setup() {
  size(200,200);
  smooth();
  PVector a = new PVector(0.0,0.0);
  PVector v = new PVector(0.0,0.0);
  PVector l = new PVector(10,0);
  t = new Thing(a,v,l,1.5);
}

void draw() {
  background(255);

  // Add gravity to thing
  // This isn't "real" gravity, just a made up force vector
  PVector grav = new PVector(0,0.05);
  t.applyForce(grav);

  // Add wind to thing
  // Again, just making up a force vector
  PVector wind = new PVector(0.01,0.0);
  t.applyForce(wind);

  // Add drag force
  float liq_start    = 120; // Liquid location
  float liq_height   = 40; // Liquid height
  // Test if thing intersects liquid
  if ((t.getLoc().y > liq_start) && (t.getLoc().y < liq_start + liq_height)) {
    // Drag is calculated as force vector in the negative direction of velocity
    float c = -0.03;                            // Drag coefficient
    PVector thingVel = t.getVel();              // Velocity of our thing
    PVector force = PVector.mult(thingVel,c);   // Following the formula above
    t.applyForce(force);                        // Adding the force to our object, which will ultimately affect its acceleration
  }

  t.go();

  // Draw the "liquid"
  // Note it would probably be a good idea to make a liquid class
  noStroke();
  fill(100,127);
  rectMode(CORNER);
  rect(0,liq_start,width,liq_height);
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


void mousePressed() {
  showVectors = !showVectors;
}





